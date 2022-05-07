package com.hotel.service;

import com.hotel.constant.Role;
import com.hotel.dto.client.ClientDto;
import com.hotel.dto.client.ClientSearchDto;
import com.hotel.dto.reservation.ReservationMainDto;
import com.hotel.dto.room.RoomFormDto;
import com.hotel.dto.room.RoomImgDto;
import com.hotel.dto.room.RoomSearchDto;
import com.hotel.entity.Member;
import com.hotel.entity.Reservation;
import com.hotel.entity.Room;
import com.hotel.entity.RoomImg;
import com.hotel.repository.member.MemberRepository;
import com.hotel.repository.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class ClientService {

    private final MemberRepository memberRepository;

    private final ReservationRepository reservationRepository;


    /**
     * 회원 조회
     * @param clientSearchDto
     * @param pageable
     * @return
     */
    @Transactional(readOnly=true)
    public Page<ClientDto> getClientPage(ClientSearchDto clientSearchDto, Pageable pageable){
        return memberRepository.getClientPage(clientSearchDto, pageable);
    }

    /**
     * 회원 정보 내역
     * @param clientId
     * @return
     */
    @Transactional(readOnly = true)
    public ClientDto getClientDtl(Long clientId){

        Member member = memberRepository.findByIdAndRole(clientId, Role.USER);

        if(member == null){
            throw new IllegalStateException("회원정보가 올바르지 않습니다");
        }
        ClientDto clientDto = new ClientDto(member);

        return clientDto;

    }

    /** 회원 수정
     *
     * @param clientDto
     * @return
     * @throws Exception
     */
    public Long updateClient(ClientDto clientDto) throws Exception{

        Member member = memberRepository.findById(clientDto.getId()).orElseThrow(EntityExistsException::new);
        member.updateClient(clientDto);

        return member.getId();

    }

    /**
     *
     * 회원 삭제
     *
     * @param clientId
     */
    public void deleteClient(Long clientId){
        Member member = memberRepository.findById(clientId).orElseThrow(EntityNotFoundException::new);

        List<Reservation> reservationList = reservationRepository.findByMemberIdOrderByIdAsc(clientId);
        for(Reservation reservation: reservationList){
            reservationRepository.delete(reservation);
        }

        memberRepository.delete(member);
    }

}
