package com.hotel.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    private Long roomId;

    @NotEmpty(message = "이메일 핗수 입력 값입니다")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;





}
