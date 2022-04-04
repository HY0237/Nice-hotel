package com.hotel.dto.reservation;


import com.hotel.constant.RoomType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationSearchDto {

    private String searchDateType;

    private RoomType searchRoomType;

    private String searchBy;

    private String searchQuery= "";
}
