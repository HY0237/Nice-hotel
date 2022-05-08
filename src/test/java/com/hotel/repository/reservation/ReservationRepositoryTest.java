package com.hotel.repository.reservation;

import com.hotel.constant.RoomType;
import com.hotel.dto.reservation.ReservationDto;
import com.hotel.dto.reservation.ReservationSearchDto;
import com.hotel.dto.room.RoomFormDto;
import com.hotel.entity.Member;
import com.hotel.entity.Reservation;
import com.hotel.repository.member.MemberRepository;
import com.hotel.service.ReservationService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ReservationRepositoryTest {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ReservationService reservationService;

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
    @DisplayName("회원 메일로 예약 조회 테스트")
    void getReservationByEmail_test() throws Exception {
        this.createReservationList();

        Pageable pageable = PageRequest.of( 0, 4);
        List<Reservation> results = reservationRepository.findReservations("test@test.com", pageable);


        for(Reservation reservation: results){
            System.out.println(reservation.getId());
            System.out.println(reservation.getPrice());
            System.out.println(reservation.getGuest());
            System.out.println(reservation.getCheckIn());
            System.out.println(reservation.getCheckOut());

        }
    }

    @Test
    @DisplayName("회원 메일로 총 예약수 테스트")
    void countReservationByEmail_test() throws Exception {
        this.createReservationList();

        Long total = reservationRepository.countReservation("test@test.com");

        assertEquals(10L, total);
    }

    @Test
    @DisplayName("객실 아이디로 예약 조회 테스트")
    void findReservationByRoomId_test() throws Exception {
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
        List<Reservation> results = reservationRepository.findByRoomIdOrderByIdAsc(roomId);


        for(Reservation reservation: results){
            System.out.println(reservation.getId());
            System.out.println(reservation.getPrice());
            System.out.println(reservation.getGuest());
            System.out.println(reservation.getCheckIn());
            System.out.println(reservation.getCheckOut());

        }
    }

    @Test
    @DisplayName("회원 아이디로 예약 조회 테스트")
    void findReservationByMemberId_test() throws Exception {
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
    @DisplayName("관리자용 예약 조회 테스트")
    void getAdminReservations_test() throws Exception {
        this.createReservationList();

        Pageable pageable = PageRequest.of( 0, 4);
        ReservationSearchDto reservationSearchDto = new ReservationSearchDto();
        Page<Reservation> results = reservationRepository.getAdminReserPage(reservationSearchDto, pageable);


        System.out.println("Page Size: " + results.getSize());
        System.out.println("Total Page: " + results.getTotalPages());
        System.out.println("Total Count: " + results.getTotalElements());
        System.out.println("Next : " + results.nextPageable());

        List<Reservation> reservationList = results.getContent();

        for(Reservation reservation: reservationList){
            System.out.println(reservation.getId());
            System.out.println(reservation.getPrice());
            System.out.println(reservation.getGuest());
            System.out.println(reservation.getCheckIn());
            System.out.println(reservation.getCheckOut());

        }
    }


}