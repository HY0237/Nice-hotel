package com.hotel.entity;

import com.hotel.constant.RoomType;
import com.hotel.dto.room.RoomFormDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(locations = {"classpath:application-test.properties"})
class RoomTest {

    @Test
    @DisplayName("객실 이미지 수정 테스트")
    void updateRoom_test(){

        Room room = new Room();
        room.setRoomNm("test");
        room.setRoomType(RoomType.SINGLE);
        room.setRoomDetail("testtest");
        room.setMaxPeople(3);
        room.setPricePerNight(2000);

        RoomFormDto roomFormDto = new RoomFormDto();
        roomFormDto.setRoomNm("test10");
        roomFormDto.setRoomType(RoomType.DOUBLE);
        roomFormDto.setRoomDetail("testtestest");
        roomFormDto.setMaxPeople(4);
        roomFormDto.setPricePerNight(3000);

        room.updateRoom(roomFormDto);

        Assertions.assertEquals(roomFormDto.getRoomNm(), room.getRoomNm());
        Assertions.assertEquals(roomFormDto.getRoomType(), room.getRoomType());
        Assertions.assertEquals(roomFormDto.getRoomDetail(), room.getRoomDetail());
        Assertions.assertEquals(roomFormDto.getMaxPeople(), room.getMaxPeople());
        Assertions.assertEquals(roomFormDto.getPricePerNight(), room.getPricePerNight());



    }

}