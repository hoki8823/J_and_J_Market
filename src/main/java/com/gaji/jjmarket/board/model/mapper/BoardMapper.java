package com.gaji.jjmarket.board.model.mapper;

import java.util.List;

import com.gaji.jjmarket.board.model.domain.BoardVO;

public interface BoardMapper {

	/** 자유게시판 전페 게시글 수 조회 mapper
	 * @return listCount;
	 */
	public int getListCount();
	
	/** 게시글 목록 조회 mapper
	 * @param bpInfo
	 * @return bList
	 */
	public List<BoardVO> getList();
	
}
