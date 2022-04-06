package com.hotel.service;

import com.hotel.dto.client.ClientDto;
import com.hotel.dto.client.ClientSearchDto;
import com.hotel.dto.reservation.ReservationMainDto;
import com.hotel.dto.room.RoomSearchDto;
import com.hotel.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService {

    private final MemberRepository memberRepository;


    @Transactional(readOnly=true)
    public Page<ClientDto> getReserveRoomPage(ClientSearchDto clientSearchDto, Pageable pageable){
        return memberRepository.getClientPage(clientSearchDto, pageable);
    }

}
