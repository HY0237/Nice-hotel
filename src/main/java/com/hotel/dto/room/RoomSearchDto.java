package com.hotel.dto.room;


import com.hotel.constant.RoomType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class RoomSearchDto {

    private String searchDateType;

    private RoomType searchRoomType;

//    private String searchByPrice; //가격이 높은 순 가격이 낮은순

    private String searchQuery = ""; // 룸 이름

    private Integer searchAdults;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate searchCheckIn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate searchCheckOut;

}
