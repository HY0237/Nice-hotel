package com.hotel.repository;

import com.hotel.constant.RoomType;
import com.hotel.dto.QReservationMainDto;
import com.hotel.dto.ReservationMainDto;
import com.hotel.dto.RoomSearchDto;
import com.hotel.entity.QRoom;
import com.hotel.entity.QRoomImg;
import com.hotel.entity.Room;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class RoomRepositoryCustomImpl implements RoomRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public RoomRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    private BooleanExpression searchRoomByType(RoomType searchRoomType){
        return searchRoomType == null ? null : QRoom.room.roomType.eq(searchRoomType);
    }

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

    private BooleanExpression searchRoomByName(String searchQuery){

        return StringUtils.isEmpty(searchQuery) ? null : QRoom.room.roomNm.like("%" + searchQuery + "%");
    }

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


    @Override
    public Page<ReservationMainDto> getReserveRoomPage(RoomSearchDto roomSearchDto, Pageable pageable) {
        QRoom room = QRoom.room;
        QRoomImg roomImg = QRoomImg.roomImg;

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
                .where(roomImg.repimgYn.eq("Y"))
                .where(searchRoomByName(roomSearchDto.getSearchQuery()))
                .orderBy(room.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<ReservationMainDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);

    }
}
