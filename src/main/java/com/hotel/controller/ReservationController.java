package com.hotel.controller;


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
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final RoomService roomService;

    private final ReservationService reservationService;

    // admin 예약 보기
    @GetMapping(value = {"/admin/reservations", "/admin/reservations/{page}"})
    public String reservation(ReservationSearchDto reservationSearchDto, @PathVariable("page") Optional<Integer> page, Model model){
        Pageable pageable = PageRequest.of(page.isPresent()? page.get() : 0, 4);
        Page<Reservation> reservations = reservationService.getAdminReserPage(reservationSearchDto, pageable);
        model.addAttribute("reservations", reservations);
        model.addAttribute("reservationSearchDto", reservationSearchDto);
        model.addAttribute("maxPage", 5);
        return "reservation/reservations";
    }

    //admin 예약 디테일 보기
    @GetMapping(value = "/reservation/{reservationId}")
    public String reservationDtl(@PathVariable("reservationId") Long reservationId, Model model){
        try {
            ReservationDto reservationDto = reservationService.getReservationDtl(reservationId);
            RoomFormDto roomFormDto = roomService.getRoomDtl(reservationDto.getRoomId());
            model.addAttribute("roomFormDto", roomFormDto);
            model.addAttribute("reservationDto", reservationDto);
        }catch (EntityNotFoundException e){
            model.addAttribute("errorMessage", "존재하지 않는 예약입니다");
            return "reservation/reservations";
        }

        return "reservation/reservationForm";
    }


    //admin 예약 가능한 룸 검색
    @GetMapping(value ={ "/admin/reservation", "/admin/reservation/{page}"})
    public String reservation(RoomSearchDto roomSearchDto, @PathVariable("page") Optional<Integer> page, Model model){
        Pageable pageable = PageRequest.of(page.isPresent()? page.get() : 0, 6);
        Page<ReservationMainDto> rooms = reservationService.getReserveRoomPage(roomSearchDto, pageable);
        model.addAttribute("rooms", rooms);
        model.addAttribute("roomSearchDto", roomSearchDto);
        model.addAttribute("maxPage", 5);
        return "reservation/reservationSearch";
    }


    //예약 작성
    @GetMapping(value = "/reservation/new/{roomId}")
    public String reservationRoom(ReservationDto reservationDto, Model model, @PathVariable("roomId") Long roomId){

        RoomFormDto roomFormDto = roomService.getRoomDtl(roomId);
        model.addAttribute("roomFormDto", roomFormDto);
        model.addAttribute("reservationDto", reservationDto);
        return "reservation/reservationForm";
    }

    //예약 저장
    @PostMapping(value= "/reservation/new")
    public @ResponseBody
    ResponseEntity reservationNew(@RequestBody @Valid ReservationDto reservationDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for(FieldError fieldError: fieldErrors){
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        Long reservationId;

        try {
            reservationId = reservationService.reservation(reservationDto);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Long>(reservationId, HttpStatus.OK);
    }



}
