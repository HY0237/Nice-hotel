package com.hotel.service;

import com.hotel.constant.Role;
import com.hotel.dto.MemberFormDto;
import com.hotel.dto.client.ClientDto;
import com.hotel.entity.Member;
import com.hotel.repository.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class ClientServiceTest {

    @Autowired
    ClientService clientService;

    @Autowired
    MemberService memberService;


    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test@gmail.com");
        memberFormDto.setName("test");
        memberFormDto.setPassword("1234");
        memberFormDto.setPhoneNum("123-24-5235");
        memberFormDto.setRole(Role.USER);
        return Member.createMember(memberFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("회원 정보 내역 테스트")
    public void clientDetailTest(){
        Member member = createMember();
        Member savedMember = memberService.saveMember(member);
        ClientDto clientDto = clientService.getClientDtl(savedMember.getId());

        assertEquals(clientDto.getEmail(), savedMember.getEmail());
        assertEquals(clientDto.getName(), savedMember.getName());
        assertEquals(clientDto.getPhoneNum(), savedMember.getPhoneNum());
        assertEquals(member.getRole(), savedMember.getRole());
    }

    @Test
    @DisplayName("회원 정보 수정 테스트")
    public void clientUpdateTest() throws Exception {
        Member member = createMember();
        Member savedMember = memberService.saveMember(member);
        ClientDto clientDto = clientService.getClientDtl(savedMember.getId());
        clientDto.setName("t");
        Long member_id= clientService.updateClient(clientDto);
        ClientDto new_clientDto = clientService.getClientDtl(member_id);


        assertEquals(clientDto.getEmail(), new_clientDto.getEmail());
        assertEquals(clientDto.getName(), new_clientDto.getName());
        assertEquals(clientDto.getPhoneNum(), new_clientDto.getPhoneNum());
        assertEquals(clientDto.getRole(), new_clientDto.getRole());
    }

}