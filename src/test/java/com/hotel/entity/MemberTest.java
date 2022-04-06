package com.hotel.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import com.hotel.repository.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(
        locations = {"classpath:application-test.properties"}
)
public class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    public MemberTest() {
    }

    @Test
    @DisplayName("Auditing 테스트")
    @WithMockUser(
            username = "gildong",
            roles = {"USER"}
    )
    public void auditingTest() {
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
}