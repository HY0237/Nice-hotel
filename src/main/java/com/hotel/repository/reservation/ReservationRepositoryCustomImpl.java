package com.hotel.repository.reservation;

import com.hotel.constant.RoomType;
import com.hotel.dto.reservation.ReservationSearchDto;
import com.hotel.entity.QReservation;
import com.hotel.entity.QRoom;
import com.hotel.entity.Reservation;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class ReservationRepositoryCustomImpl implements  ReservationRepositoryCustom{


    private JPAQueryFactory queryFactory;

    public ReservationRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    private BooleanExpression searchRoomByType(RoomType searchRoomType){
        return searchRoomType == null ? null : QReservation.reservation.room().roomType.eq(searchRoomType);
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


    private BooleanExpression searchByLike(String searchBy, String searchQuery){

        if(StringUtils.equals("name", searchBy)){
            return QReservation.reservation.member().name.like("%" + searchQuery + "%");
        }else if(StringUtils.equals("email", searchBy)){
            return QReservation.reservation.member().email.like("%" + searchQuery + "%");
        }else if(StringUtils.equals("roomNm", searchBy)){
            return QReservation.reservation.room().roomNm.like("%" + searchQuery + "%");
        }

        return  null;
    }


    @Override
    public Page<Reservation> getAdminReserPage(ReservationSearchDto reservationSearchDto, Pageable pageable) {

        List<Reservation> contents = queryFactory
                .selectFrom(QReservation.reservation)
                .where(regDtsAfter(reservationSearchDto.getSearchDateType()),
                        searchRoomByType(reservationSearchDto.getSearchRoomType()),
                        searchByLike(reservationSearchDto.getSearchBy(), reservationSearchDto.getSearchQuery()))
                .orderBy(QReservation.reservation.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        long total = contents.size();

        return new PageImpl<>(contents, pageable, total);
    }
}
