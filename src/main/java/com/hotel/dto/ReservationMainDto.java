package com.hotel.dto;

import com.hotel.constant.RoomType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationMainDto {

    private Long id;

    private String roomNm;

    private RoomType roomType;

    private Integer pricePerNight;

    private Integer maxPeople;

    private String imgUrl;

    @QueryProjection
    public ReservationMainDto(Long id, String roomNm, RoomType roomType, Integer pricePerNight, Integer maxPeople, String imgUrl){
        this.id = id;
        this.roomNm = roomNm;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.maxPeople = maxPeople;
        this.imgUrl = imgUrl;

    }


}
