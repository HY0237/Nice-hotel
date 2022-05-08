package com.hotel.controller;

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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
class ReservationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RoomService roomService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    MemberRepository memberRepository;

    public Member saveMember(){
        Member member = new Member();
        member.setEmail("test@test.com");
        member.setName("testName");
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
    @DisplayName("예약 전체 조회 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    void getReservationAll_test() throws Exception {

        this.createReservationList();

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/reservations/{page}", "0"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("예약 정보 상세보기 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    void getReservationDtl_test() throws Exception {

        this.createReservationList();

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/reservation/detail/{reservationId}", "17"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("예약 삭제 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    void deleteReservation_test() throws Exception {

        this.createReservationList();

        mockMvc.perform(MockMvcRequestBuilders.delete("/admin/reservation/delete/{reservationId}","17")
                        .with(csrf()))
                .andDo(print());

        Assertions.assertThat(reservationRepository.findById(17L)).isEmpty();
    }

}