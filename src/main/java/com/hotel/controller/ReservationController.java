package com.hotel.controller;


import com.hotel.dto.ReservationMainDto;
import com.hotel.dto.RoomSearchDto;
import com.hotel.entity.Room;
import com.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final RoomService roomService;

    @GetMapping(value = "/admin/reservations")
    public String reservation(Model model){
        return "reservation/reservations";
    }


    @GetMapping(value ={ "/reservation", "/reservation/{page}"})
    public String reservationNew(RoomSearchDto roomSearchDto, @PathVariable("page") Optional<Integer> page, Model model){
        Pageable pageable = PageRequest.of(page.isPresent()? page.get() : 0, 3);
        Page<ReservationMainDto> rooms = roomService.getReserveRoomPage(roomSearchDto, pageable);
        model.addAttribute("rooms", rooms);
        model.addAttribute("roomSearchDto", roomSearchDto);
        model.addAttribute("maxPage", 5);
        return "reservation/reservationMain";
    }

}
