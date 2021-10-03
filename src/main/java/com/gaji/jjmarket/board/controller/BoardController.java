package com.gaji.jjmarket.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gaji.jjmarket.board.model.domain.BoardVO;
import com.gaji.jjmarket.board.model.service.BoardService;
import com.gaji.jjmarket.member.model.domain.MemberVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"loginMember"})
@RequestMapping("/board/*")
public class BoardController {
	
	private final BoardService service;
	
	@GetMapping("list")
	public String boardList(Model model, @ModelAttribute("loginMember") MemberVO loginMember) {
		
		List<BoardVO> bList = service.getList();
		model.addAttribute("bList",bList);
		
		return "board/boardList";
	}

}
