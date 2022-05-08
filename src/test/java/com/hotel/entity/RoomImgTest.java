package com.hotel.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(locations = {"classpath:application-test.properties"})
class RoomImgTest {


    @Test
    @DisplayName("객실 이미지 수정 테스트")
    void updateRoomImg_test() {

        RoomImg roomImg = new RoomImg();
        roomImg.setRepimgYn("Y");

        String imgName ="F:/niceHotel/room";
        String oriImgName ="345";
        String imgUrl="465";

        roomImg.updateRoomImg(oriImgName, imgName, imgUrl);


        Assertions.assertEquals(imgName, roomImg.getImgName());
        Assertions.assertEquals(oriImgName, roomImg.getOriImgName());
        Assertions.assertEquals(imgUrl, roomImg.getImgUrl());


    }

}