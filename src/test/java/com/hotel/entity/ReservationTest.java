package com.hotel.entity;


import com.hotel.constant.RoomType;
import com.hotel.dto.reservation.ReservationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;



@SpringBootTest
@Transactional
@TestPropertySource(locations = {"classpath:application-test.properties"})
class ReservationTest {

  private Reservation reservation;

    @Test
    @DisplayName("Reservation 생성자 테스트")
    void createMember_test() {

        Member member = new Member();
        member.setEmail("test@test.com");
        member.setName("testName");
        member.setPhoneNum("1234-1234");

        Room room = new Room();
        room.setRoomNm("test");
        room.setRoomDetail("test detail");
        room.setRoomType(RoomType.SINGLE);
        room.setMaxPeople(2);
        room.setPricePerNight(2000);

        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setCheckIn(LocalDate.now());
        reservationDto.setCheckOut(LocalDate.now());
        reservationDto.setPrice(2000);
        reservationDto.setGuest(3);

        Reservation createdReserve = reservation.createReservation( reservationDto, member, room);

        Assertions.assertEquals(reservationDto.getCheckIn(), createdReserve.getCheckIn());
        Assertions.assertEquals(reservationDto.getCheckOut(), createdReserve.getCheckOut());
        Assertions.assertEquals(reservationDto.getPrice(), createdReserve.getPrice());
        Assertions.assertEquals(reservationDto.getGuest(), createdReserve.getGuest());
        Assertions.assertEquals(member.getEmail(), createdReserve.getMember().getEmail());
        Assertions.assertEquals(room.getRoomNm(), createdReserve.getRoom().getRoomNm());

    }

}