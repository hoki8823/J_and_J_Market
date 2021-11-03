package com.gaji.jjmarket.market.model.domain;

import lombok.Data;

@Data
public class MarketAttachmentVO {
	
	private int fileNo; 		// 이미지번호
	private String filePath;	// 파일경로
	private String fileName;	// 파일이름
	private int fileLevel;      // 파일레벨
	private int parentMarketNo;		// 게시글번호
	
}
