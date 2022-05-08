package com.hotel.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import com.hotel.constant.Role;
import com.hotel.dto.MemberFormDto;
import com.hotel.dto.client.ClientDto;
import com.hotel.repository.member.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(locations = {"classpath:application-test.properties"})
class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    @Autowired
    PasswordEncoder passwordEncoder;

    private Member member;


    @Test
    @DisplayName("Auditing 테스트")
    @WithMockUser(username = "gildong", roles = {"USER"})
    void auditingTest() {
        Member newMember = new Member();
        this.memberRepository.save(newMember);
        this.em.flush();
        this.em.clear();
        Member member = (Member)this.memberRepository.findById(newMember.getId()).orElseThrow(EntityNotFoundException::new);
        System.out.println("register time : " + member.getRegTime());
        System.out.println("update time : " + member.getUpdateTime());
        System.out.println("create member : " + member.getCreatedBy());
        System.out.println("modify member : " + member.getModifiedBy());
    }
    @Test
    @DisplayName("DTO에서 Member Entity 생성 테스트")
    void createMember_test() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test@gmail.com");
        memberFormDto.setName("test");
        memberFormDto.setPassword("1234");
        memberFormDto.setPhoneNum("123-24-5235");
        memberFormDto.setRole(Role.USER);
        Member createdMember = member.createMember(memberFormDto, passwordEncoder);

        Assertions.assertEquals(memberFormDto.getEmail(), createdMember.getEmail());
        Assertions.assertEquals(memberFormDto.getName(), createdMember.getName());
        Assertions.assertEquals(memberFormDto.getPhoneNum(), createdMember.getPhoneNum());
        Assertions.assertEquals(memberFormDto.getRole(), createdMember.getRole());

    }

    @Test
    @DisplayName("회원 수정 테스트")
    void updateClient_test() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test@gmail.com");
        memberFormDto.setName("test");
        memberFormDto.setPassword("1234");
        memberFormDto.setPhoneNum("123-24-5235");
        memberFormDto.setRole(Role.USER);
        Member createdMember = member.createMember(memberFormDto, passwordEncoder);

        ClientDto clientDto = new ClientDto();
        clientDto.setEmail("test12@gmail.com");
        clientDto.setName("test12");
        clientDto.setPhoneNum("12324-5235");
        createdMember.updateClient(clientDto);

        Assertions.assertEquals(clientDto.getEmail(), createdMember.getEmail());
        Assertions.assertEquals(clientDto.getName(), createdMember.getName());
        Assertions.assertEquals(clientDto.getPhoneNum(), createdMember.getPhoneNum());


    }
}