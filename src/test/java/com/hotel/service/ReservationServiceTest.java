package com.hotel.service;

import com.hotel.constant.RoomType;
import com.hotel.dto.reservation.ReservationDto;
import com.hotel.entity.Member;
import com.hotel.entity.Reservation;
import com.hotel.entity.Room;
import com.hotel.repository.MemberRepository;
import com.hotel.repository.reservation.ReservationRepository;
import com.hotel.repository.room.RoomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    MemberRepository memberRepository;


    public Room saveRoom(){
        Room room = new Room();
        room.setRoomNm("테스트 룸");
        room.setRoomType(RoomType.SINGLE);
        room.setRoomDetail("룸 디테일");
        room.setPricePerNight(1000);
        room.setMaxPeople(5);
        return roomRepository.save(room);
    }

    public Member saveMember(){
        Member member = new Member();
        member.setEmail("test@test.com");
        return memberRepository.save(member);
    }

    @Test
    @DisplayName("예약 테스트")
    public void reservation(){
        Room room = saveRoom();
        Member member = saveMember();

        ReservationDto reservationDto= new ReservationDto();
        reservationDto.setCheckIn(LocalDate.now());
        reservationDto.setCheckOut(LocalDate.now());
        reservationDto.setPrice(1000);
        reservationDto.setGuest(5);
        reservationDto.setRoomId(room.getId());
        reservationDto.setEmail("test@test.com");

        Long reservationId = reservationService.reservation(reservationDto);

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(member, reservation.getMember());
        assertEquals(room, reservation.getRoom());
        assertEquals(LocalDate.now(), reservation.getCheckIn());
        assertEquals(LocalDate.now(), reservation.getCheckOut());
        assertEquals(1000, reservation.getPrice());
        assertEquals(5, reservation.getGuest());
    }



}