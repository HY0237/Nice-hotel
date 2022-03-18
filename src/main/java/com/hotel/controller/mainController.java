package com.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {

    @GetMapping(value = "/index")
    public String home(Model model){
        return "index";
    }

    @GetMapping(value = "/clients")
    public String client(Model model){
        return "clients";
    }

    @GetMapping(value = "/rooms")
    public String room(Model model){
        return "rooms";
    }

    @GetMapping(value = "/reservations")
    public String reservation(Model model){
        return "reservations";
    }
}
