package com.hotel.dto.room;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RoomDto {

    private Long id;

    private String roomNm;

    private Integer pricePerNight;

    private String roomDetail;

    private String roomStatus;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

}
