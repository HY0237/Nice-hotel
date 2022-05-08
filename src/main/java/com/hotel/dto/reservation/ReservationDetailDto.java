package com.hotel.dto.reservation;

import com.hotel.constant.RoomType;
import com.hotel.entity.Reservation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ReservationDetailDto {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkIn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOut;

    private int price;

    private int guest;

    private String roomName;

    private RoomType roomType;

    private String roomImgUri;

    private String name;

    @NotEmpty(message = "이메일 핗수 입력 값입니다")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;

    private String reservationDate;

    public ReservationDetailDto(Reservation reservation, String roomImgUri){
        this.id = reservation.getId();
        this.checkIn= reservation.getCheckIn();
        this.checkOut= reservation.getCheckOut();
        this.price = reservation.getPrice();
        this.guest = reservation.getGuest();
        this.roomName = reservation.getRoom().getRoomNm();
        this.roomImgUri = roomImgUri;
        this.roomType = reservation.getRoom().getRoomType();
        this.email = reservation.getMember().getEmail();
        this.name = reservation.getMember().getName();
        this.reservationDate = reservation.getRegTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    }
}
