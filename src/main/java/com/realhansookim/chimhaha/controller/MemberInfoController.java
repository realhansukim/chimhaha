package com.realhansookim.chimhaha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.realhansookim.chimhaha.service.MemberService;
import com.realhansookim.chimhaha.vo.LoginAdd;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberInfoController {
        private final MemberService memberService;
   @GetMapping("/add")
    public String getLogin(){
        return "/member/add";
    }

    @PostMapping("/add")
    public String addMembers(LoginAdd add, Model model){
        model.addAttribute("inputdata", memberService.addLogin(add));
        
        return "/main";
    } 
}
