package com.gaji.jjmarket.board.model.service;

import java.util.List;

import com.gaji.jjmarket.board.model.domain.BoardVO;
import com.gaji.jjmarket.board.model.domain.Criteria;

public interface BoardService {
	
	/** 게시글 목록 조회
	 * @param cri
	 * @return bList
	 */
	public abstract List<BoardVO> getList(Criteria cri);

}
