package com.gaji.jjmarket.board.model.service;

import org.springframework.stereotype.Service;

import com.gaji.jjmarket.board.model.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardMapper mapper;
	
	

}
