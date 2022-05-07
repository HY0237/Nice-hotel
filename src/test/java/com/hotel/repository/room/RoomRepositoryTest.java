package com.hotel.repository.room;

import com.hotel.constant.RoomType;
import com.hotel.dto.client.ClientDto;
import com.hotel.dto.client.ClientSearchDto;
import com.hotel.dto.reservation.ReservationMainDto;
import com.hotel.dto.room.RoomFormDto;
import com.hotel.dto.room.RoomSearchDto;
import com.hotel.entity.Room;
import com.hotel.repository.room.RoomRepository;
import com.hotel.service.RoomService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class RoomRepositoryTest {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomService roomService;

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

    public void createRoomList() throws Exception {

        for(int i = 1; i <= 10; i++) {
            RoomFormDto roomFormDto = new RoomFormDto();
            roomFormDto.setRoomNm("테스트 객실"+i);
            roomFormDto.setRoomType(RoomType.SINGLE);
            roomFormDto.setRoomDetail("테스트 객실 입니다");
            roomFormDto.setPricePerNight(1000*i);
            roomFormDto.setMaxPeople(i);
            List<MultipartFile> multipartFileList = this.createMultipartFiles();
            roomService.saveRoom(roomFormDto, multipartFileList);
        }

    }

    @Test
    @DisplayName("객실 저장 테스트")
    public void createRoomTest(){
        Room room = new Room();
        room.setRoomNm("테스트 객실");
        room.setPricePerNight(40000);
        room.setRoomDetail("객실 상세 설명");
        room.setRoomType(RoomType.SINGLE);
        room.setRegTime(LocalDateTime.now());
        room.setUpdateTime(LocalDateTime.now());
        Room savedRoom = roomRepository.save(room);
        System.out.println(savedRoom.toString());
    }


    @Test
    @DisplayName("객실명 조회 테스트")
    public void findByRoomNmTest() throws Exception {
        this.createRoomList();
        List<Room> roomList = roomRepository.findByRoomNm("테스트 객실1");
        for(Room room: roomList){
            System.out.println(room.toString());
        }
    }

    @Test
    @DisplayName("관리자 페이지 전체 객실 조회 테스트")
    public void findAllRooms_Test() throws Exception {
        this.createRoomList();

        /**
         * page:0 일때 member name: 테스트 객실10~테스트 객실7
         * page:1 일때 member name: 테스트 객실6~테스트 객실3
         * page:2 일때 member name: 테스트 객실3~테스트 객실1
         */
        Pageable pageable = PageRequest.of( 1, 4);
        RoomSearchDto roomSearchDto = new RoomSearchDto();
        Page<Room> result = roomRepository.getAdminRoomPage(roomSearchDto, pageable);


        System.out.println("Page Size: " + result.getSize());
        System.out.println("Total Page: " + result.getTotalPages());
        System.out.println("Total Count: " + result.getTotalElements());
        System.out.println("Next : " + result.nextPageable());

        List<Room> roomList = result.getContent();
        for(Room room: roomList){
            System.out.println(room.getRoomNm());
            System.out.println(room.getRoomType());
            System.out.println(room.getRoomDetail());
            System.out.println(room.getMaxPeople());
            System.out.println(room.getPricePerNight());
        }
    }

    @Test
    @DisplayName("예약 가능한 객실 페이지 전체 조회 테스트")
    public void findAllAvailRooms_Test() throws Exception {

        this.createRoomList();

        /**
         * page:0 일때 member name: 테스트 객실10~테스트 객실8
         * page:1 일때 member name: 테스트 객실7~테스트 객실5
         * page:2 일때 member name: 테스트 객실4~테스트 객실2
         * page:3 일때 member name: 테스트 객실1
         */
        Pageable pageable = PageRequest.of( 1, 3);
        RoomSearchDto roomSearchDto = new RoomSearchDto();
        Page<ReservationMainDto> result = roomRepository.getReserveRoomPage(roomSearchDto, pageable);


        System.out.println("Page Size: " + result.getSize());
        System.out.println("Total Page: " + result.getTotalPages());
        System.out.println("Total Count: " + result.getTotalElements());
        System.out.println("Next : " + result.nextPageable());

        List<ReservationMainDto> roomList = result.getContent();
        for(ReservationMainDto room: roomList){
            System.out.println(room.getRoomNm());
            System.out.println(room.getRoomType());
            System.out.println(room.getMaxPeople());
            System.out.println(room.getPricePerNight());
        }
    }

}