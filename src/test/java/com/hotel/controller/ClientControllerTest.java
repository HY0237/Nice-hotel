package com.hotel.controller;

import com.hotel.constant.Role;
import com.hotel.dto.MemberFormDto;
import com.hotel.entity.Member;
import com.hotel.service.ClientService;
import com.hotel.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class ClientControllerTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    ClientService clientService;


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



}