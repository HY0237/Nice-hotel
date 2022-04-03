package com.hotel.service;


import com.hotel.dto.ReservationDto;
import com.hotel.entity.Member;
import com.hotel.entity.Reservation;
import com.hotel.entity.Room;
import com.hotel.repository.MemberRepository;
import com.hotel.repository.ReservationRepository;
import com.hotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
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
}
