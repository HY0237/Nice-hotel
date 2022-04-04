package com.hotel.dto.reservation;


import com.hotel.entity.Reservation;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
public class ReservationDto {


    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkIn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOut;

    private int price;

    private int guest;

    private Long roomId;

    private String name;

    @NotEmpty(message = "이메일 핗수 입력 값입니다")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;


    public static ReservationDto createReservationDto(Reservation reservation){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setCheckIn(reservation.getCheckIn());
        reservationDto.setCheckOut(reservation.getCheckOut());
        reservationDto.setPrice(reservation.getPrice());
        reservationDto.setGuest(reservation.getGuest());
        reservationDto.setRoomId(reservation.getRoom().getId());
        reservationDto.setName(reservation.getMember().getName());
        reservationDto.setEmail(reservation.getMember().getEmail());

        return reservationDto;
    }





}
