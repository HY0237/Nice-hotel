package com.hotel.entity;

import com.hotel.dto.reservation.ReservationDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="reservation")
@Getter
@Setter
@ToString
public class Reservation extends BaseEntity{

    @Id
    @Column(name= "reservation_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDate checkIn;

    @Column(nullable = false)
    private LocalDate checkOut;

    @Column(nullable = false)
    private int price;

    @Column
    private int guest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "member_id" )
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public static Reservation createReservation(ReservationDto reservationDto, Member member, Room room){
        Reservation reservation = new Reservation();
        reservation.setCheckIn(reservationDto.getCheckIn());
        reservation.setCheckOut(reservationDto.getCheckOut());
        reservation.setPrice(reservationDto.getPrice());
        reservation.setGuest(reservationDto.getGuest());
        reservation.setMember(member);
        reservation.setRoom(room);
        return reservation;
    }



}
