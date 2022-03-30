package com.hotel.dto;


import com.hotel.constant.RoomType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomSearchDto {

    private String searchDateType;

    private RoomType searchRoomType;

//    private String searchByPrice; //가격이 높은 순 가격이 낮은순

    private String searchQuery = ""; // 룸 이름
}
