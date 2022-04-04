package com.hotel.repository.reservation;


import com.hotel.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ReservationRepository  extends JpaRepository<Reservation, Long>, QuerydslPredicateExecutor<Reservation>, ReservationRepositoryCustom {
}
