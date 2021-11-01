package com.gaji.jjmarket.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gaji.jjmarket.board.model.domain.BoardPageInfo;
import com.gaji.jjmarket.board.model.domain.BoardVO;
import com.gaji.jjmarket.board.model.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardMapper mapper;

	@Override
	public List<BoardVO> getList() {
		
		return mapper.getList();
	}



}
