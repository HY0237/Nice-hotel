package com.hotel.controller;


import com.hotel.dto.reservation.ReservationDetailDto;
import com.hotel.dto.reservation.ReservationDto;
import com.hotel.dto.reservation.ReservationMainDto;
import com.hotel.dto.reservation.ReservationSearchDto;
import com.hotel.dto.room.RoomFormDto;
import com.hotel.dto.room.RoomSearchDto;
import com.hotel.entity.Reservation;
import com.hotel.service.ReservationService;
import com.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final RoomService roomService;

    private final ReservationService reservationService;

    // (관리자) 예약 전체 조회
    @GetMapping(value = {"/admin/reservations", "/admin/reservations/{page}"})
    public String reservation(ReservationSearchDto reservationSearchDto, @PathVariable("page") Optional<Integer> page, Model model){
        Pageable pageable = PageRequest.of(page.isPresent()? page.get() : 0, 4);
        Page<Reservation> reservations = reservationService.getAdminReserPage(reservationSearchDto, pageable);
        model.addAttribute("reservations", reservations);
        model.addAttribute("reservationSearchDto", reservationSearchDto);
        model.addAttribute("maxPage", 5);
        return "reservation/reservations";
    }

    // (관리자) 예약 내역 상세보기
    @GetMapping(value = "/admin/reservation/detail/{reservationId}")
    public String reservationDtl(@PathVariable("reservationId") Long reservationId, Model model){
        try {
            ReservationDto reservationDto = reservationService.getAdminReservationDtl(reservationId);
            RoomFormDto roomFormDto = roomService.getRoomDtl(reservationDto.getRoomId());
            model.addAttribute("roomFormDto", roomFormDto);
            model.addAttribute("reservationDto", reservationDto);
        }catch (EntityNotFoundException e){
            model.addAttribute("errorMessage", "존재하지 않는 예약입니다");
            return "reservation/reservations";
        }

        return "reservation/reservationForm";
    }

    // (관리자) 예약 삭제
    @DeleteMapping(value= "/admin/reservation/delete/{reservationId}")
    public @ResponseBody ResponseEntity reservationCancel(@PathVariable("reservationId") Long reservationId){

        reservationService.deleteReservation(reservationId);

        return new ResponseEntity<Long>(reservationId, HttpStatus.OK);

    }




}
