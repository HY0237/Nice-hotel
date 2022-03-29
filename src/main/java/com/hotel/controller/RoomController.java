package com.hotel.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class RoomController {

    @GetMapping(value = "/admin/rooms")
    public String room(Model model){
        return "/room/rooms";
    }

    @GetMapping(value = "/admin/room/new")
    public String roomForm(Model model){
        return "/room/roomForm";
    }
}
