package com.gaji.jjmarket.market.model.domain;

public class MarketAttachmentVO {
	
	private int fileNo; 		// 이미지번호
	private String filePath;	// 파일경로
	private String fileName;	// 파일이름
	private int fileLevel;      // 파일레벨
	private int parentMarketNo;		// 게시글번호
	
	
	
	public MarketAttachmentVO() {
		
	}

	public MarketAttachmentVO(String filePath, String fileName, int fileLevel, int parentMarketNo) {
		super();
		this.filePath = filePath;
		this.fileName = fileName;
		this.fileLevel = fileLevel;
		this.parentMarketNo = parentMarketNo;
	}
	
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getFileLevel() {
		return fileLevel;
	}
	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}
	public int getParentMarketNo() {
		return parentMarketNo;
	}
	public void setParentMarketNo(int parentMarketNo) {
		this.parentMarketNo = parentMarketNo;
	}

	@Override
	public String toString() {
		return "MarketAttachmentVO [fileNo=" + fileNo + ", filePath=" + filePath + ", fileName=" + fileName
				+ ", fileLevel=" + fileLevel + ", parentMarketNo=" + parentMarketNo + "]";
	}
	
	
	
}
