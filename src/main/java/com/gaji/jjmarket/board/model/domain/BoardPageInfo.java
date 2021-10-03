package com.gaji.jjmarket.board.model.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardPageInfo {
	
	private int currentPage; 	// 현재 페이지 번호를 표시할 변수
	private int listCount; 		// 전체 게시글 수
	
	private int amount = 10; 	// 한 페이지에 보여질 게시글 수
	private int pageSize = 10; 	// 보여질 페이징바의 페이지 개수
	
	private int maxPage;	 	// 전체 페이지에서 가장 마지막 페이지
	private int startPage;	 	// 페이징바 시작 페이지 번호
	private int endPage; 	 	// 페이징바 끝 페이지 번호
	
	private int boardType; 		// 게시글 타입
	
	// 페이징 처리에 필요한 값을 계산하는 메소드.
	private void makePageInfo() {
		
		// * maxPage - 총 페이지수 
		maxPage = (int)Math.ceil(( (double)listCount / amount));
		
		// * startPage - 페이징바 시작 페이지 번호
		startPage = (currentPage-1)/pageSize * pageSize + 1;
		
		// * endPage - 페이징바 끝 페이지 번호
		endPage = startPage + pageSize - 1;
		
		// 총 페이지의 수가endPage 보다 클 경우
		if(maxPage > endPage) {
			endPage = startPage + pageSize - 1;
		}
		else {
			// 총 페이지 수가 endPage보다 작을 경우
			// ex) maxPage가 13페이지고 endPage가 20페이지일 경우 13이 끝이여야 함.
			endPage = maxPage;
		}
	}
		
}
