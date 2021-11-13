package com.gaji.jjmarket.market.model.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MarketVO {

	private int marketNo;
	private String marketTitle;
	private String marketContent;
	private Timestamp createDt;
	private int readCount;
	private String marketFl;
	private int price;
	private String status;
	private String deliveryCharge;
	private int memNo;
	private int categoryCd;
	private int transactionCategory;
	private int transactionStatus;
	private int itemCount;
	private String nickname;
	private String certifiedFl;
	private String categoryNm;

	
}
