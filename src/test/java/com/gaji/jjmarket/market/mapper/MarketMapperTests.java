package com.gaji.jjmarket.market.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gaji.jjmarket.market.model.domain.MarketPageInfo;
import com.gaji.jjmarket.market.model.mapper.MarketMapper;
import com.gaji.jjmarket.market.model.service.MarketService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/spring/root-context.xml")
@Log4j
public class MarketMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private MarketMapper mapper;
	/*
	@Test
	public void countMarket() {
		
		int total = mapper.countMarket(); 
		
		MarketPageInfo vo = new MarketPageInfo(total,2,10);
		
		log.info(mapper.selectList(vo));
	}
	*/
	@Test
	public void selectAttachmentList() {
		
		log.info(mapper.selectAttachmentList(37));
	}
	
	
}
