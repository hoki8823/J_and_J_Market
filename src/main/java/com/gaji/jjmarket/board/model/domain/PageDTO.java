package com.gaji.jjmarket.board.model.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDTO {

	private int startPage; // 페이징바의 시작 번호
	private int endPage; // 페이징바의 끝 번호

	private int total; // 전체 데이터 수
	private Criteria cri;
	
	public PageDTO(Criteria cri,int total) {
		
		this.cri = cri;
		this.total = total;
		
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10))*10;
		this.startPage = this.endPage - 9;
		
		int realEnd = (int)(Math.ceil((total*1)/cri.getAmount()));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		
	}
}
