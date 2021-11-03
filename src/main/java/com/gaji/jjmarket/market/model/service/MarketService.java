package com.gaji.jjmarket.market.model.service;

import java.util.List;

import com.gaji.jjmarket.market.model.domain.MarketPageInfo;
import com.gaji.jjmarket.market.model.domain.MarketVO;

public interface MarketService {
	
	/** 게시글 총 갯수 조회 Service
	 * @return listCount;
	 */
	public int countMarket();
	
	/** 게시글 목록 조회 Service
	 * @param mpInfo 
	 * @return mList
	 */
	public List<MarketVO> selectList(MarketPageInfo mpInfo);
}
