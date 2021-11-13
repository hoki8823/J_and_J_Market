package com.gaji.jjmarket;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gaji.jjmarket.market.model.domain.MarketAttachmentVO;
import com.gaji.jjmarket.market.model.domain.MarketPageInfo;
import com.gaji.jjmarket.market.model.domain.MarketVO;
import com.gaji.jjmarket.market.model.service.MarketService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	

	private final MarketService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		
		MarketPageInfo mpInfo;
		int total = service.countMarket();
		mpInfo = new MarketPageInfo(total, Integer.parseInt("1"),Integer.parseInt("5"));
		List<MarketVO> mList = service.selectList(mpInfo);
		
		if(mList != null && !mList.isEmpty()) {
			List<MarketAttachmentVO> thumbnailList2 = service.selectThumbnailList(mList);
			if(thumbnailList2 != null) {
				model.addAttribute("thumbnailList2", thumbnailList2);
			}
		}	
		model.addAttribute("mpInfo", mpInfo);
		model.addAttribute("mList", mList);	
		
		return "common/main";
	}
	
}
