package com.hotel.service;

import com.hotel.constant.RoomType;
import com.hotel.dto.reservation.ReservationDetailDto;
import com.hotel.dto.reservation.ReservationDto;
import com.hotel.dto.room.RoomFormDto;
import com.hotel.entity.Member;
import com.hotel.entity.Reservation;
import com.hotel.entity.Room;
import com.hotel.repository.member.MemberRepository;
import com.hotel.repository.reservation.ReservationRepository;
import com.hotel.repository.room.RoomRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class ReservationServiceTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RoomService roomService;

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
    public Long createReservation() throws Exception {
        Long roomId = this.createRoom();
        Member member = saveMember();

        ReservationDto reservationDto= new ReservationDto();
        reservationDto.setCheckIn(LocalDate.now());
        reservationDto.setCheckOut(LocalDate.now());
        reservationDto.setPrice(1000);
        reservationDto.setGuest(5);
        reservationDto.setRoomId(roomId);
        reservationDto.setEmail(member.getEmail());

        return reservationService.reservation(reservationDto);
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
    @DisplayName("예약자 회원 테스트")
    void validateReservation_test() throws Exception {
        Long reservationId = this.createReservation();
        String email = "test@test.com";
        assertEquals(true, reservationService.validateReservation(reservationId, email));

    }


    @Test
    @DisplayName("객실 예약 테스트")
    void reservation_test() throws Exception {
        Long reservationId = this.createReservation();
        assertFalse(reservationRepository.findById(reservationId).isEmpty());

    }

    @Test
    @DisplayName("예약 상세 보기 테스트")
    void getReservationHist_test() throws Exception {
        this.createReservationList();

        Pageable pageable = PageRequest.of( 0, 4);
        Page<ReservationDetailDto> result = reservationService.getReservationHist("test@test.com", pageable);


        System.out.println("Page Size: " + result.getSize());
        System.out.println("Total Page: " + result.getTotalPages());
        System.out.println("Total Count: " + result.getTotalElements());
        System.out.println("Next : " + result.nextPageable());

        List<ReservationDetailDto> reservationDetailDtos= result.getContent();
        for(ReservationDetailDto reservation: reservationDetailDtos){
            System.out.println(reservation.getEmail());
            System.out.println(reservation.getPrice());
            System.out.println(reservation.getRoomName());
            System.out.println(reservation.getGuest());
            System.out.println(reservation.getCheckIn());
            System.out.println(reservation.getCheckOut());

        }
    }

    @Test
    @DisplayName("예약 취소 테스트")
    void deleteReservation_test() throws Exception {

        Long reservationId = this.createReservation();
        reservationService.deleteReservation(reservationId);
        assertThat(reservationRepository.findById(reservationId).isEmpty());
    }

    @Test
    @DisplayName("관리자용 예약 상세보기 테스트")
    void getAdminReservationDtl_test() throws Exception {
        Long reservationId = this.createReservation();

        ReservationDto reservationDetail = reservationService.getAdminReservationDtl(reservationId);
        Reservation reservation = reservationRepository.getById(reservationId);

        assertEquals(reservation.getMember().getEmail(), reservationDetail.getEmail());
        assertEquals(reservation.getRoom().getId(), reservationDetail.getRoomId());
        assertEquals(reservation.getCheckIn(), reservationDetail.getCheckIn());
        assertEquals(reservation.getCheckOut(), reservationDetail.getCheckOut());
        assertEquals(reservation.getPrice(), reservationDetail.getPrice());
        assertEquals(reservation.getGuest(), reservationDetail.getGuest());
    }



}