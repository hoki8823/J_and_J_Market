package com.gaji.jjmarket.market.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gaji.jjmarket.market.model.domain.MarketAttachmentVO;
import com.gaji.jjmarket.market.model.domain.MarketPageInfo;
import com.gaji.jjmarket.market.model.domain.MarketVO;
import com.gaji.jjmarket.market.model.service.MarketService;
import com.gaji.jjmarket.member.model.domain.MemberVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"loginMember"})
@RequestMapping("/market/*")
public class MarketController {

	private final MarketService service;
	
	@GetMapping("list")
	public String marketList(@RequestParam(value = "cntPerPage", required = false)String cntPerPage,@RequestParam(value = "nowPage", required = false)String nowPage,
			MarketPageInfo vo, Model model, @ModelAttribute("loginMember") MemberVO loginMember,
			RedirectAttributes ra) {
		
		int total = service.countMarket();
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		}else if(nowPage == null) {
			nowPage = "1";
		}else if(cntPerPage == null) {
			cntPerPage = "5";
		}
		
		vo = new MarketPageInfo(total, Integer.parseInt(nowPage),Integer.parseInt(cntPerPage));
		model.addAttribute("paging", vo);
		model.addAttribute("mList",service.selectList(vo));
		
		return "market/marketList";
	}
}
