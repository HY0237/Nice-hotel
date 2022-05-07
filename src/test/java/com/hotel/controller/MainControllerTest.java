package com.hotel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.constant.RoomType;
import com.hotel.dto.reservation.ReservationDto;
import com.hotel.dto.room.RoomFormDto;
import com.hotel.entity.Member;
import com.hotel.repository.member.MemberRepository;
import com.hotel.repository.reservation.ReservationRepository;
import com.hotel.service.ReservationService;
import com.hotel.service.RoomService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
class MainControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RoomService roomService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    MemberRepository memberRepository;

    public Member saveMember(){
        Member member = new Member();
        member.setEmail("test@test.com");
        member.setName("test");
        member.setPhoneNum("1234-1234");
        return memberRepository.save(member);
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

    public Long createRoom() throws Exception {

        RoomFormDto roomFormDto = new RoomFormDto();
        roomFormDto.setRoomNm("테스트 객실");
        roomFormDto.setRoomType(RoomType.SINGLE);
        roomFormDto.setRoomDetail("테스트 객실 입니다");
        roomFormDto.setPricePerNight(1000);
        roomFormDto.setMaxPeople(3);
        List<MultipartFile> multipartFileList = this.createMultipartFiles();
        return roomService.saveRoom(roomFormDto, multipartFileList);


    }

    public void createRoomList() throws Exception {

        for(int i = 1; i <= 10; i++) {
            RoomFormDto roomFormDto = new RoomFormDto();
            roomFormDto.setRoomNm("테스트 상품"+i);
            roomFormDto.setRoomType(RoomType.SINGLE);
            roomFormDto.setRoomDetail("테스트 상품 입니다");
            roomFormDto.setPricePerNight(1000*i);
            roomFormDto.setMaxPeople(i);
            List<MultipartFile> multipartFileList = this.createMultipartFiles();
            roomService.saveRoom(roomFormDto, multipartFileList);
        }

    }
    public void createReservationList(){
        Long roomId = null;
        try {
            roomId = createRoom();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Member member = saveMember();


        for(int i=1; i <=10; i++) {
            ReservationDto reservationDto = new ReservationDto();
            reservationDto.setCheckIn(LocalDate.now());
            reservationDto.setCheckOut(LocalDate.now());
            reservationDto.setPrice(1000*i);
            reservationDto.setGuest(i);
            reservationDto.setRoomId(roomId);
            reservationDto.setEmail(member.getEmail());
            reservationService.reservation(reservationDto);
        }

    }

    @Test
    @DisplayName("예약 가능한 객실 조회 테스트")
    @WithMockUser(username = "test@test.com", roles = "USER")
    public void getReservationAvaliable_test() throws Exception {

        this.createRoomList();

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("객실 예약 페이지 테스트")
    @WithMockUser(username = "test@test.com", roles = "USER")
    public void reservationForm_test() throws Exception{
        Long roomId = createRoom();

        mockMvc.perform(MockMvcRequestBuilders.get("/reservation/new/{roomId}", roomId))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("객실 예약 테스트")
    @WithMockUser(username = "test@test.com", roles = "USER")
    public void reservationNew_test() throws Exception{
        Member member = this.saveMember();
        Long roomId = this.createRoom();

        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setEmail(member.getEmail());
        reservationDto.setName(member.getName());
        reservationDto.setCheckIn(LocalDate.now());
        reservationDto.setCheckOut(LocalDate.now());
        reservationDto.setGuest(3);
        reservationDto.setPrice(10000);
        reservationDto.setRoomId(roomId);


        mockMvc.perform(MockMvcRequestBuilders.post("/reservation/new/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservationDto))
                        .with(csrf()))

                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("예약 내역 상세보기 테스트")
    @WithMockUser(username = "test@test.com", roles = "USER")
    public void reservationHist_test() throws Exception{
        this.createReservationList();

        mockMvc.perform(MockMvcRequestBuilders.get("/reservation/detail"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("예약 삭제 테스트")
    @WithMockUser(username = "test@test.com", roles = "USER")
    public void deleteReservation_test() throws Exception {

        this.createReservationList();

        mockMvc.perform(MockMvcRequestBuilders.delete("/reservation/delete/{reservationId}","17")
                        .with(csrf()))
                .andDo(print());

        Assertions.assertThat(reservationRepository.findById(17L).isEmpty());
    }


}