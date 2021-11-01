package com.gaji.jjmarket.board.model.service;

import java.util.List;

import com.gaji.jjmarket.board.model.domain.BoardVO;

public interface BoardService {
	
	/** 페이징 처리 객체 생성 Service
	 * @param cp
	 * @return bpInfo
	 */
	//public abstract BoardPageInfo getPageInfo(int cp);
	
	/** 게시글 목록 조회
	 * @param bpInfo
	 * @return bList
	 */
	public abstract List<BoardVO> getList();

}
