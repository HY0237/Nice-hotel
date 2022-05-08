package com.hotel.repository.member;


import com.hotel.constant.Role;
import com.hotel.constant.RoomType;
import com.hotel.dto.client.ClientDto;
import com.hotel.dto.client.ClientSearchDto;
import com.hotel.entity.Member;
import com.hotel.entity.Room;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

import java.util.List;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    public void createMemberList(){
        for(int i=1; i<=10; i++){
            Member member = new Member();
            member.setName("test" + i);
            member.setEmail("test" + i+ "@gmail.com");
            member.setPhoneNum("1234-1234");
            member.setPassword("1234");
            member.setRole(Role.USER);

            memberRepository.save(member);
        }
    }

    @Test
    @DisplayName("회원 페이지 조회 테스트")
    void findAllClients_Test(){
        this.createMemberList();

        /**
         * page:0 일때 member name: test10~test7
         * page:1 일때 member name: test6~test3
         * page:2 일때 member name: test3~test1
         */
        Pageable pageable = PageRequest.of( 1, 4);
        ClientSearchDto clientSearchDto = new ClientSearchDto();
        Page<ClientDto> result = memberRepository.getClientPage(clientSearchDto, pageable);

        System.out.println("Page Size: " + result.getSize());
        System.out.println("Total Page: " + result.getTotalPages());
        System.out.println("Total Count: " + result.getTotalElements());
        System.out.println("Next : " + result.nextPageable());

        List<ClientDto> clientList = result.getContent();
        for(ClientDto clientDto: clientList){
            System.out.println(clientDto.getEmail());
            System.out.println(clientDto.getName());
            System.out.println(clientDto.getPhoneNum());
            System.out.println(clientDto.getRole());
        }
    }



}