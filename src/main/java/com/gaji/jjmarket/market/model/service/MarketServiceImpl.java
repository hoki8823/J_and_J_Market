package com.gaji.jjmarket.market.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gaji.jjmarket.market.model.domain.MarketPageInfo;
import com.gaji.jjmarket.market.model.domain.MarketVO;
import com.gaji.jjmarket.market.model.mapper.MarketMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarketServiceImpl implements MarketService{

	private final MarketMapper mapper;
	
	@Override
	public int countMarket() {
		return mapper.countMarket();
	}
	
	@Override
	public List<MarketVO> selectList(MarketPageInfo mpInfo) {
		return mapper.selectList(mpInfo);
	}
	
}
