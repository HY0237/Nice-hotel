package com.hotel.repository;

import com.hotel.constant.RoomType;
import com.hotel.entity.Room;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class RoomRepositoryTest {

    @Autowired
    RoomRepository roomRepository;

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

    public void createRoomList(){
        for(int i=1; i<=10; i++){
            Room room = new Room();
            room.setRoomNm("테스트 객실" +i);
            room.setPricePerNight(40000 +i);
            room.setRoomDetail("객실 상세 설명" +i);
            room.setRoomType(RoomType.SINGLE);
            room.setRegTime(LocalDateTime.now());
            room.setUpdateTime(LocalDateTime.now());
            Room savedRoom = roomRepository.save(room);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByRoomNmTest(){
        this.createRoomList();
        List<Room> roomList = roomRepository.findByRoomNm("테스트 객실1");
        for(Room room: roomList){
            System.out.println(room.toString());
        }
    }

}