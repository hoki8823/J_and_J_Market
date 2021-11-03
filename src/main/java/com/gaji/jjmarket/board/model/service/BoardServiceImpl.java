package com.gaji.jjmarket.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gaji.jjmarket.board.model.domain.BoardVO;
import com.gaji.jjmarket.board.model.domain.Criteria;
import com.gaji.jjmarket.board.model.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardMapper mapper;
	
	// 게시글 목록 조회
	@Override
	public List<BoardVO> getList(Criteria cri) {
		return mapper.getListWithPaging(cri);
	}



}
