package com.hotel.service;

import com.hotel.constant.Role;
import com.hotel.dto.client.ClientDto;
import com.hotel.dto.client.ClientSearchDto;
import com.hotel.entity.Member;
import com.hotel.entity.Reservation;
import com.hotel.repository.member.MemberRepository;
import com.hotel.repository.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;;
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
     */
    @Transactional(readOnly=true)
    public Page<ClientDto> getClientPage(ClientSearchDto clientSearchDto, Pageable pageable){
        return memberRepository.getClientPage(clientSearchDto, pageable);
    }

    /**
     * 회원 정보 상세보기
     */
    @Transactional(readOnly = true)
    public ClientDto getClientDtl(Long clientId){

        //USER 회원 조회
        Member member = memberRepository.findByIdAndRole(clientId, Role.USER);
        if(member == null){
            throw new IllegalStateException("회원정보가 올바르지 않습니다");
        }
        //User ClientDTO로 변환
        ClientDto clientDto = new ClientDto(member);
        return clientDto;

    }

    /**
     * 회원 수정
     */
    public Long updateClient(ClientDto clientDto){
        Member member = memberRepository.findById(clientDto.getId()).orElseThrow(EntityExistsException::new);
        member.updateClient(clientDto);
        return member.getId();
    }

    /**
     *회원 삭제 및 회원 관련 예약 삭제
     */
    public void deleteClient(Long clientId){
        //회원 관련한 예약 삭제
        List<Reservation> reservationList = reservationRepository.findByMemberIdOrderByIdAsc(clientId);
        for(Reservation reservation: reservationList){
            reservationRepository.delete(reservation);
        }
        //회원 삭제
        Member member = memberRepository.findById(clientId).orElseThrow(EntityNotFoundException::new);
        memberRepository.delete(member);
    }

}
