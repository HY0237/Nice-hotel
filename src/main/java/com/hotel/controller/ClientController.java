package com.hotel.controller;

import com.hotel.dto.client.ClientDto;
import com.hotel.dto.client.ClientSearchDto;
import com.hotel.dto.room.RoomFormDto;
import com.hotel.dto.room.RoomSearchDto;
import com.hotel.entity.Room;
import com.hotel.service.ClientService;
import com.hotel.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    /**
     * (관리자) 회원 전체 조회
     * @param clientSearchDto
     * @param page
     * @param model
     * @return
     */
    @GetMapping(value = {"/admin/clients", "/admin/clients/{page}"})
    public String clientManage(ClientSearchDto clientSearchDto, @PathVariable("page") Optional<Integer> page, Model model){
        Pageable pageable = PageRequest.of(page.isPresent()? page.get() : 0, 4);
        Page<ClientDto> clients = clientService.getReserveRoomPage(clientSearchDto, pageable);
        model.addAttribute("clients", clients);
        model.addAttribute("clientSearchDto", clientSearchDto);
        model.addAttribute("maxPage", 5);
        return "client/clients";
    }

    /**
     * (관리자) 회원 정보 상세보기
     * @param clientId
     * @param model
     * @return
     */
    @GetMapping(value = "/admin/client/{clientId}")
    public String clientDtl(@PathVariable("clientId") Long clientId, Model model){

        try{
            ClientDto clientDto = clientService.getClientDtl(clientId);
            model.addAttribute("clientDto", clientDto);
        }catch (EntityNotFoundException e){
            model.addAttribute("errorMessage", "존재하지 않는 회원입니다");
            return "client/clientForm";
        }

        return "client/clientForm";
    }

    /**
     * (관리자) 회원 정보 수정
     * @param clientDto
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping(value = "/admin/client/{clientId}")
    public String clientUpdate(@Valid ClientDto clientDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "client/clientForm";
        }

        try{
            clientService.updateClient(clientDto);
        }catch (Exception e) {
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
            return "client/clientForm";
        }

        return "redirect:/admin/clients";
    }

    /** (관리자) 회원 삭제
     *
     * @param clientId
     * @return
     */
    @DeleteMapping(value= "/admin/client/delete/{clientId}")
    public @ResponseBody ResponseEntity clientDelete(@PathVariable("clientId") Long clientId){

        clientService.deleteClient(clientId);

        return new ResponseEntity<Long>(clientId, HttpStatus.OK);

    }


}
