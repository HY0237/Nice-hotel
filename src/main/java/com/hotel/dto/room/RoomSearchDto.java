package com.hotel.dto.room;


import com.hotel.constant.RoomType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class RoomSearchDto {

    private String searchDateType;

    private RoomType searchRoomType;

    private String searchQuery = ""; // 룸 이름

    private Integer searchAdults;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate searchCheckIn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate searchCheckOut;

}
