package com.hotel.service;

import com.hotel.constant.RoomType;
import com.hotel.dto.room.RoomFormDto;
import com.hotel.entity.Room;
import com.hotel.entity.RoomImg;
import com.hotel.repository.room.RoomImgRepository;
import com.hotel.repository.room.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@TestPropertySource(locations = {"classpath:application-test.properties"})
class RoomServiceTest {

    @Autowired
    RoomService roomService;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomImgRepository roomImgRepository;

    RoomServiceTest() {
    }

    List<MultipartFile> createMultipartFiles() throws Exception {
        List<MultipartFile> multipartFileList = new ArrayList();

        for(int i = 0; i < 5; i++) {
            String path = "F:/niceHotel/room";
            String imageName = "image" + i + ".jpg";
            MockMultipartFile multipartFile = new MockMultipartFile(path, imageName, "image/jpg", new byte[]{1, 2, 3, 4});
            multipartFileList.add(multipartFile);
        }

        return multipartFileList;
    }

    @Test
    @DisplayName("상품 등록 테스트")
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN"}
    )
    void saveItem() throws Exception {
        RoomFormDto roomFormDto = new RoomFormDto();
        roomFormDto.setRoomNm("테스트 상품");
        roomFormDto.setRoomType(RoomType.SINGLE);
        roomFormDto.setRoomDetail("테스트 상품 입니다");
        roomFormDto.setPricePerNight(1000);
        roomFormDto.setMaxPeople(100);
        List<MultipartFile> multipartFileList = this.createMultipartFiles();
        Long roomId = this.roomService.saveRoom(roomFormDto, multipartFileList);
        List<RoomImg> roomImgList = this.roomImgRepository.findByRoomIdOrderByIdAsc(roomId);
        Room room = (Room)this.roomRepository.findById(roomId).orElseThrow(EntityNotFoundException::new);
        Assertions.assertEquals(roomFormDto.getRoomNm(), room.getRoomNm());
        Assertions.assertEquals(roomFormDto.getRoomType(), room.getRoomType());
        Assertions.assertEquals(roomFormDto.getRoomDetail(), room.getRoomDetail());
        Assertions.assertEquals(roomFormDto.getPricePerNight(), room.getPricePerNight());
        Assertions.assertEquals(roomFormDto.getMaxPeople(), room.getMaxPeople());
        Assertions.assertEquals(((MultipartFile)multipartFileList.get(0)).getOriginalFilename(), ((RoomImg)roomImgList.get(0)).getOriImgName());
    }

}