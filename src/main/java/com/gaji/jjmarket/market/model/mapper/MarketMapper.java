package com.gaji.jjmarket.market.model.mapper;

import java.util.List;

import com.gaji.jjmarket.market.model.domain.MarketAttachmentVO;
import com.gaji.jjmarket.market.model.domain.MarketPageInfo;
import com.gaji.jjmarket.market.model.domain.MarketVO;

public interface MarketMapper {

	/** 거래게시판 전체 게시글 수 조회 mapper
	 * @return listCount;
	 */
	public int countMarket();
	
	/** 게시글 목록 조회 mapper
	 * @param mpInfo
	 * @return mList
	 */
	public List<MarketVO> selectList(MarketPageInfo mpInfo);
	
}
