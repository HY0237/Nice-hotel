package com.hotel.controller;

import com.hotel.dto.client.ClientDto;
import com.hotel.dto.client.ClientSearchDto;
import com.hotel.dto.room.RoomSearchDto;
import com.hotel.entity.Room;
import com.hotel.service.ClientService;
import com.hotel.service.MemberService;
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
public class ClientController {

    private final ClientService clientService;

    // 객실 조히
    @GetMapping(value = {"/admin/clients", "/admin/clients/{page}"})
    public String clientManage(ClientSearchDto clientSearchDto, @PathVariable("page") Optional<Integer> page, Model model){
        Pageable pageable = PageRequest.of(page.isPresent()? page.get() : 0, 4);
        Page<ClientDto> clients = clientService.getReserveRoomPage(clientSearchDto, pageable);
        model.addAttribute("clients", clients);
        model.addAttribute("clientSearchDto", clientSearchDto);
        model.addAttribute("maxPage", 5);
        return "client/clients";
    }

}
