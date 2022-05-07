package com.hotel.controller;


import com.hotel.constant.Role;
import com.hotel.entity.Member;
import com.hotel.repository.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class ClientControllerTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MockMvc mockMvc;


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

    /**
     * 회원 전체 조회 테스트
     * Page 0 : test10~test7
     * Page 1: test6~test3
     * Page 2: test3~test1
     * @throws Exception
     */
    @Test
    @DisplayName("회원 전체 조회 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void getClientAll_test() throws Exception {

        this.createMemberList();
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/clients/{page}", "0"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * 회원 이름으로 회원 조회 테스트
     * 회원 이름 1을 입력 값으로 넣었을때 test1, test10 출력
     * @throws Exception
     */
    @Test
    @DisplayName("회원 이름으로 조회 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void getClientAllByName_test() throws Exception {

        this.createMemberList();
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/clients/{page}", "0")
                        .param("searchBy", "name")
                        .param("searchQuery", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * 회원 이메일로 회원 조회 테스트
     * 회원 이메일 1을 입력 값으로 넣었을때 test1, test10 출력
     * @throws Exception
     */
    @Test
    @DisplayName("회원 이메일로 조회 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void getClientAllByEmail_test() throws Exception {

        this.createMemberList();
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/clients/{page}", "0")
                        .param("searchBy", "email")
                        .param("searchQuery", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원 정보 상세보기 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void getClientDtl_test() throws Exception {

        this.createMemberList();
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/client/{clientId}", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원 수정 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void postClientUpdate_test() throws Exception {

        this.createMemberList();


        mockMvc.perform(MockMvcRequestBuilders.post("/admin/client/{clientId}", "10")
                .param("id","10")
                        .param("name","test")
                        .param("email","test@gmail.com")
                        .param("phoneNum","1234-1234")
                        .with(csrf()))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/clients"));
    }

    @Test
    @DisplayName("회원 삭제 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void deleteClient_test() throws Exception {

        this.createMemberList();


        mockMvc.perform(MockMvcRequestBuilders.delete("/admin/client/delete/{clientId}","10")
                        .with(csrf()))
                .andDo(print());

        Assertions.assertThat(memberRepository.findById(10L).isEmpty());
    }









}