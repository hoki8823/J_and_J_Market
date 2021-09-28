package com.gaji.jjmarket.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gaji.jjmarket.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService service;
	
	//로그인 화면 전환용 Controller
	@GetMapping("login")
	public String loginView() {
		return "member/login";
	}
	
}
