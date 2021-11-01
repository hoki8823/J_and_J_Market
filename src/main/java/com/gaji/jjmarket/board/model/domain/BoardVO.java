package com.gaji.jjmarket.board.model.domain;



import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardVO {

	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private Timestamp createDate;
	private String status;
	private int readCount;
	private int boardCode;
	private int categoryCode;
	private int memberNo;
	private String nickname;
	private String memberGrade;
	private String categoryName;
	
}
