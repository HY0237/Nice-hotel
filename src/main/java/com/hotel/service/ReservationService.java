package com.hotel.service;

import com.hotel.dto.reservation.ReservationDetailDto;
import com.hotel.dto.reservation.ReservationDto;
import com.hotel.dto.reservation.ReservationSearchDto;
import com.hotel.entity.Member;
import com.hotel.entity.Reservation;
import com.hotel.entity.Room;
import com.hotel.entity.RoomImg;
import com.hotel.repository.member.MemberRepository;
import com.hotel.repository.reservation.ReservationRepository;
import com.hotel.repository.room.RoomImgRepository;
import com.hotel.repository.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;
    private final RoomImgRepository roomImgRepository;
    private final ReservationRepository reservationRepository;


    /**
     * 예약자와 회원이 같은지 검증
     */
    @Transactional(readOnly = true)
    public boolean validateReservation(Long reservationId, String email){
        Member member = memberRepository.findByEmail(email);
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(EntityNotFoundException::new);
        Member savedMember = reservation.getMember();

        //이메일로 검사
        if(!StringUtils.equals(savedMember.getEmail(), member.getEmail())){
            return false;
        }

        return true;

    }

    /**
     * 객실 예약 하기
     */
    public Long reservation(ReservationDto reservationDto){
        Room room = roomRepository.findById(reservationDto.getRoomId())
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(reservationDto.getEmail());

        Reservation reservation = Reservation.createReservation( reservationDto, member, room);
        reservationRepository.save(reservation);

        return reservation.getId();

    }

    /**
     * 예약 상세 보기
     */
    @Transactional(readOnly = true)
    public Page<ReservationDetailDto> getReservationHist(String email, Pageable pageable){

        List<Reservation> reservations = reservationRepository.findReservations(email, pageable);
        Long total = reservationRepository.countReservation(email);

        List<ReservationDetailDto> reservationDetailDtos = new ArrayList<>();
        for(Reservation reservation : reservations){
            RoomImg roomImg = roomImgRepository.findByRoomIdAndRepimgYn(reservation.getRoom().getId(), "Y");
            ReservationDetailDto reservationDetailDto = new ReservationDetailDto(reservation, roomImg.getImgUrl());
            reservationDetailDtos.add(reservationDetailDto);
        }

        return new PageImpl<>(reservationDetailDtos, pageable, total);
    }


    /**
     * (관리자 회원) 예약 취소
     * @param reservationId
     */
    public void deleteReservation(Long reservationId){
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(EntityNotFoundException::new);
        reservationRepository.delete(reservation);

    }


    /**
     * (관리자) 예약 조회
     */
    @Transactional(readOnly=true)
    public Page<Reservation> getAdminReserPage(ReservationSearchDto reservationSearchDto, Pageable pageable){
        return reservationRepository.getAdminReserPage(reservationSearchDto, pageable);
    }

    /**
     * (관리자) 예약 내역들 상세보기
     */
    @Transactional(readOnly = true)
    public ReservationDto getAdminReservationDtl(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(EntityNotFoundException::new);

        return ReservationDto.createReservationDto(reservation);
    }






}
