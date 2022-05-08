package com.hotel.repository.room;

import com.hotel.constant.RoomType;
import com.hotel.dto.QReservationMainDto;
import com.hotel.dto.reservation.ReservationMainDto;
import com.hotel.dto.room.RoomSearchDto;
import com.hotel.entity.QReservation;
import com.hotel.entity.QRoom;
import com.hotel.entity.QRoomImg;
import com.hotel.entity.Room;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class RoomRepositoryCustomImpl implements RoomRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public RoomRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    /**
     * 객실 타입 옵션
     * @param searchRoomType
     * @return
     */
    private BooleanExpression searchRoomByType(RoomType searchRoomType){
        return searchRoomType == null ? null : QRoom.room.roomType.eq(searchRoomType);
    }

    /**
     * 수정 기간 옵션
     * @param searchDateType
     * @return
     */
    private BooleanExpression regDtsAfter(String searchDateType){
        LocalDateTime dateTime = LocalDateTime.now();

        if(StringUtils.equals("all", searchDateType) || searchDateType == null){
            return null;
        }else if(StringUtils.equals("1d", searchDateType)){
            dateTime = dateTime.minusDays(1);
        }
        else if(StringUtils.equals("1w", searchDateType)){
            dateTime = dateTime.minusWeeks(1);
        }
        else if(StringUtils.equals("1m", searchDateType)){
            dateTime = dateTime.minusMonths(1);
        }
        else if(StringUtils.equals("6m", searchDateType)){
            dateTime = dateTime.minusMonths(6);
        }

        return QRoom.room.regTime.after(dateTime);
    }

    /**
     * 객실 이름 옵션
     * @param searchQuery
     * @return
     */
    private BooleanExpression searchRoomByName(String searchQuery){

        return StringUtils.isEmpty(searchQuery) ? null : QRoom.room.roomNm.like("%" + searchQuery + "%");
    }


    /**
     * 게스트 인원 수 옵션
     * @param guest
     * @return
     */
    private BooleanExpression searchMaxGuest(Integer guest){

        return guest == null? QRoom.room.maxPeople.goe(1): QRoom.room.maxPeople.goe(guest);
    }


    /**
     * 체크인 체크아웃 날짜 옵션
     * @param checkIn
     * @param checkOut
     * @return
     */
    private BooleanExpression searchDate(LocalDate checkIn, LocalDate checkOut){


        LocalDate checkInDefault = LocalDate.now();
        LocalDate checkOutDefault = LocalDate.now().plusDays(1);


        if(checkIn == null && checkOut == null){
            checkIn = checkInDefault;
            checkOut = checkOutDefault;
        }

        //case 1 : QcheckIn >= checkIn and QcheckOut < checkOut
        BooleanExpression case1 =
                QReservation.reservation.checkIn.goe(checkIn)
                        .and(QReservation.reservation.checkIn.lt(checkOut));

        //case 2 : QcheckOut > checkIn and QcheckOut <= checkOut
        BooleanExpression case2 =
                QReservation.reservation.checkOut.gt(checkIn)
                        .and(QReservation.reservation.checkOut.loe(checkOut));

        //case 3 : QcheckIn < checkIn and QcheckOut > checkOut
        BooleanExpression case3 =
                QReservation.reservation.checkIn.lt(checkIn)
                        .and(QReservation.reservation.checkOut.gt(checkOut));


        return case1.or(case2).or(case3);
    }


    /**
     * 기간, 객실 타입, 객실 이음 옵션으로 객실 조회
     * @param roomSearchDto
     * @param pageable
     * @return
     */
    @Override
    public Page<Room> getAdminRoomPage(RoomSearchDto roomSearchDto, Pageable pageable) {

        List<Room> contents = queryFactory
                .selectFrom(QRoom.room)
                .where(regDtsAfter(roomSearchDto.getSearchDateType()),
                        searchRoomByType(roomSearchDto.getSearchRoomType()),
                        searchRoomByName(roomSearchDto.getSearchQuery()))
                .orderBy(QRoom.room.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        long total = contents.size();

        return new PageImpl<>(contents, pageable, total);
    }



    /**
     * 날짜, 게스트 수, 객실 이름 옵션으로 예약 가능한 객실 조회
     * @param roomSearchDto
     * @param pageable
     * @return
     */
    @Override
    public Page<ReservationMainDto> getReserveRoomPage(RoomSearchDto roomSearchDto, Pageable pageable) {
        QRoom room = QRoom.room;
        QRoomImg roomImg = QRoomImg.roomImg;
        QReservation reservation = QReservation.reservation;

        List<ReservationMainDto> contents = queryFactory
                .select(
                        new QReservationMainDto(
                                room.id,
                                room.roomNm,
                                room.roomType,
                                room.pricePerNight,
                                room.maxPeople,
                                roomImg.imgUrl)
                )
                .from(roomImg)
                .join(roomImg.room(), room)
                .where(room.id.notIn(
                        JPAExpressions
                                .select(reservation.room().id)
                                .from(reservation)
                                .where(searchDate(roomSearchDto.getSearchCheckIn(), roomSearchDto.getSearchCheckOut()))),
                                        roomImg.repimgYn.eq("Y"),
                                        searchRoomByName(roomSearchDto.getSearchQuery()),
                                        searchMaxGuest(roomSearchDto.getSearchAdults()))
                .orderBy(room.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        long total = contents.size();

        return new PageImpl<>(contents, pageable, total);

    }
}
