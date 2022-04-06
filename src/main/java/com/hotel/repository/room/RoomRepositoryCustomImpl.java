package com.hotel.repository.room;

import com.hotel.constant.RoomType;
import com.hotel.dto.QReservationMainDto;
import com.hotel.dto.reservation.ReservationMainDto;
import com.hotel.dto.room.RoomSearchDto;
import com.hotel.entity.QReservation;
import com.hotel.entity.QRoom;
import com.hotel.entity.QRoomImg;
import com.hotel.entity.Room;
import com.querydsl.core.QueryResults;
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


    //객실 타입 옵션
    private BooleanExpression searchRoomByType(RoomType searchRoomType){
        return searchRoomType == null ? null : QRoom.room.roomType.eq(searchRoomType);
    }

    //시간별 옵션
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

    //룸 이름
    private BooleanExpression searchRoomByName(String searchQuery){

        return StringUtils.isEmpty(searchQuery) ? null : QRoom.room.roomNm.like("%" + searchQuery + "%");
    }

    //게스트 인원수 옵션
    private BooleanExpression searchMaxGuest(Integer guest){

        if(guest == null){
            guest = 1;
        }

        return QRoom.room.maxPeople.goe(guest);
    }


    // 체크인 체크아웃 옵션
    private BooleanExpression searchDate(LocalDate checkIn, LocalDate checkOut){


        LocalDate checkIn_default= LocalDate.now();
        LocalDate checkOut_default = LocalDate.now();
        checkOut_default=checkOut_default.plusDays(1);

        if(checkIn == null && checkOut == null){
            checkIn = checkIn_default;
            checkOut = checkOut_default;
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

    // 날짜 룸 타입 이름을 통헤 객실 조회
    @Override
    public Page<Room> getAdminRoomPage(RoomSearchDto roomSearchDto, Pageable pageable) {

        QueryResults<Room> results = queryFactory
                .selectFrom(QRoom.room)
                .where(regDtsAfter(roomSearchDto.getSearchDateType()),
                        searchRoomByType(roomSearchDto.getSearchRoomType()),
                        searchRoomByName(roomSearchDto.getSearchQuery()))
                .orderBy(QRoom.room.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Room> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }


    //날짜 사람 방이름을 통해 예약 가능한 객실 조회
    @Override
    public Page<ReservationMainDto> getReserveRoomPage(RoomSearchDto roomSearchDto, Pageable pageable) {
        QRoom room = QRoom.room;
        QRoomImg roomImg = QRoomImg.roomImg;
        QReservation reservation = QReservation.reservation;

        QueryResults<ReservationMainDto> results = queryFactory
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
                                .where(searchDate(roomSearchDto.getSearchCheckIn(), roomSearchDto.getSearchCheckOut()))
                ), roomImg.repimgYn.eq("Y"),
                        searchRoomByName(roomSearchDto.getSearchQuery()),
                        searchMaxGuest(roomSearchDto.getSearchAdults()))
                .orderBy(room.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<ReservationMainDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);

    }
}
