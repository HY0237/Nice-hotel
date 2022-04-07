package com.hotel.repository.reservation;


import com.hotel.entity.Reservation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository  extends JpaRepository<Reservation, Long>, QuerydslPredicateExecutor<Reservation>, ReservationRepositoryCustom {


    @Query("select r from Reservation r "+
            "where r.member.email = :email "+
            "order by r.regTime desc")
    List<Reservation> findReservations(@Param("email") String email, Pageable pageable);

    @Query("select count(r) from Reservation r "+
            "where r.member.email = :email ")
    Long countReservation(@Param("email") String email);

}
