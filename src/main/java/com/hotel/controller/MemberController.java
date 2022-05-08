package com.hotel.controller;

import com.hotel.dto.MemberFormDto;
import com.hotel.entity.Member;
import com.hotel.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {


    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    /**
     * 로그인
     * @return
     */
    @GetMapping(value = "/login")
    public String login(){
        return "user/login";
    }

    /**
     * 로그인 에러
     * @param model
     * @return
     */
    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호 확인해주세요");
        return "user/login";
    }

    /**
     * 회원 가입 페이지
     * @param model
     * @return
     */
    @GetMapping(value = "/register")
    public String register(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "user/register";
    }

    /**
     * 회원 가입
     * @param memberFormDto
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping(value = "/register")
    public String register(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "user/register";
        }

        try{
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);

        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "user/register";
        }
        return "redirect:/";
    }
}
