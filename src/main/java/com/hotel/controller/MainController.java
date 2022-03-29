package com.hotel.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class MainController {


    @GetMapping(value = "/")
    public String home(Model model){
        return "index";
    }

    @GetMapping(value = "/admin/clients")
    public String client(Model model){
        return "/client/clients";
    }

    @GetMapping(value = "/admin/reservations")
    public String reservation(Model model){
        return "/reservation/reservations";
    }


}
