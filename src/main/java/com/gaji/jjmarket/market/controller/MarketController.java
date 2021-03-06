package com.gaji.jjmarket.market.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
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
	
	
	// ???????????? ????????? ?????? view 
	@RequestMapping("insert")
	public String marketInsert() {
		return "market/marketInsert"; 
	}
	
	// ???????????? ?????? ??????
	@RequestMapping("{marketNo}") 
	public String marketView(@PathVariable int marketNo,
							Model model, @RequestHeader(value = "referer", required = false) String referer,
							RedirectAttributes ra, @ModelAttribute("loginMember") MemberVO loginMember) {
		
		MarketVO market = service.selectMarket(marketNo);
		String url = null;
		
		if (market != null) {
			
			List<MarketAttachmentVO> at = service.selectAttachmentList(marketNo);
			
			if(at != null & !at.isEmpty()) {
				model.addAttribute("at", at);
			}
		
			//model.addAttribute("loginMember", loginMember);
			model.addAttribute("market", market);
			url = "market/marketView";
		} else {
			
			if (referer == null) {
				url = "redirect:../list/";
			} else {
				url = "redirect:" + referer;
			}
		}
		return url;
	}
		
	
	// ???????????? ????????? ?????? Controller
	@RequestMapping("insertAction")
	public String marketInsertAction(@ModelAttribute MarketVO market, RedirectAttributes ra,
						@ModelAttribute("loginMember") MemberVO loginMember, 
						@RequestParam(value="images", required=true) List<MultipartFile> images,
						HttpServletRequest request) {
		
		System.out.println(market);
		
		market.setMemNo(loginMember.getMemberNo());
		
		for(int i=0; i<images.size(); i++) {
			System.out.println("images[" + i + "] : " + images.get(i).getOriginalFilename());
		}
		String savePath = null;
		
		savePath = request.getSession().getServletContext().getRealPath("resources/marketImages");
		
		int result = service.insertMarket(market, images, savePath);
		
		String url = null;
		
		if(result > 0) {
			url = "redirect:" + result;
			
			request.getSession().setAttribute("returnListURL", "list");
					
		} else {
			url = "redirect:insert";
		}
		
		return url;
	}
	
	// ????????? ?????? ?????? ?????? Controller
	@RequestMapping("update/{marketNo}")
	public String marketUpdate(@PathVariable("marketNo") int marketNo, Model model) {
		
		// ????????? ?????? ?????? 
		MarketVO market = service.selectMarket(marketNo);
		System.out.println(market);
		
		// ?????? ???????????? ????????? ????????? ?????? ??????
		if(market != null) {
			List<MarketAttachmentVO> attachmentList = service.selectAttachmentList(marketNo);
			model.addAttribute("at", attachmentList);
		}
		
		model.addAttribute("market", market);
		return "market/marketUpdate";
	}
	
	// ????????? ?????? ?????? ?????? Controller
	@RequestMapping("updateAction/{marketNo}")
	public String marketUpdateAction(@PathVariable("marketNo") int marketNo, Model model, RedirectAttributes ra,
									@ModelAttribute MarketVO market, HttpServletRequest request,
									@RequestParam(value="beforeImages", defaultValue="") int[] beforeImages,
									@RequestParam(value="images", required=true) List<MultipartFile> images
									) {
		
		// marketNo??? market??? ??????
		market.setMarketNo(marketNo);
		
		// ?????? ?????? ?????? ??????
		String savePath = request.getSession().getServletContext().getRealPath("resources/marketImages");
		
		int result = service.updateMarket(market, images, savePath, beforeImages);
		
		
		String url = null;
		
		if(result > 0) {

			url = "redirect:../" + marketNo;
			
			request.getSession().setAttribute("returnListURL", "../list");
		}else {
			
			url = "redirect:" + request.getHeader("referer");
		}
		
		return url;
	}
	
	// ????????? ??????
	@RequestMapping("delete/{marketNo}")
	public String deleteMarket(@PathVariable("marketNo") int marketNo,
							 @ModelAttribute MarketVO market,HttpServletRequest request, RedirectAttributes ra ) {
		market.setMarketNo(marketNo);
		
		int result = service.deleteMarket(market);
		
		String url = null;
		
		if(result > 0) {
			url = "redirect:../list";
		} else {
			url = "redirect:" + request.getHeader("referer");
		}
		
		return url;
	}
}
