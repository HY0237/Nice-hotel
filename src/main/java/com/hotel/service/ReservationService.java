package com.hotel.service;


import com.hotel.dto.reservation.ReservationDto;
import com.hotel.dto.reservation.ReservationMainDto;
import com.hotel.dto.reservation.ReservationSearchDto;
import com.hotel.dto.room.RoomSearchDto;
import com.hotel.entity.Member;
import com.hotel.entity.Reservation;
import com.hotel.entity.Room;
import com.hotel.repository.member.MemberRepository;
import com.hotel.repository.reservation.ReservationRepository;
import com.hotel.repository.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;


    //예약하기
    public Long reservation(ReservationDto reservationDto){
        Room room = roomRepository.findById(reservationDto.getRoomId())
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(reservationDto.getEmail());

        Reservation reservation = Reservation.createReservation(
                reservationDto.getCheckIn(),
                reservationDto.getCheckOut(),
                reservationDto.getPrice(),
                reservationDto.getGuest(),
                member,
                room
        );
        reservationRepository.save(reservation);

        return reservation.getId();

    }

    @Transactional(readOnly = true)
    public ReservationDto getReservationDtl(Long reservationId){
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(EntityNotFoundException::new);
        ReservationDto reservationDto = ReservationDto.createReservationDto(reservation);

        return reservationDto;
    }

    @Transactional(readOnly=true)
    public Page<ReservationMainDto> getReserveRoomPage(RoomSearchDto roomSearchDto, Pageable pageable){
        return roomRepository.getReserveRoomPage(roomSearchDto, pageable);
    }

    @Transactional(readOnly=true)
    public Page<Reservation> getAdminReserPage(ReservationSearchDto reservationSearchDto, Pageable pageable){
        return reservationRepository.getAdminReserPage(reservationSearchDto, pageable);
    }
}
