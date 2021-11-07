package com.gaji.jjmarket.market.model.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gaji.jjmarket.market.model.domain.MarketAttachmentVO;
import com.gaji.jjmarket.market.model.domain.MarketPageInfo;
import com.gaji.jjmarket.market.model.domain.MarketVO;
import com.gaji.jjmarket.market.model.exception.MarketInsertAttachmentFailException;
import com.gaji.jjmarket.market.model.mapper.MarketMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarketServiceImpl implements MarketService{

	private final MarketMapper mapper;
	
	// 전체 페이지 수 조회
	@Override
	public int countMarket() {
		return mapper.countMarket();
	}
	
	// 게시글 목록 조회
	@Override
	public List<MarketVO> selectList(MarketPageInfo mpInfo) {
		return mapper.selectList(mpInfo);
	}
	
	// 썸네일 목록 조회 Service 구현
	@Override
	public List<MarketAttachmentVO> selectThumbnailList(List<MarketVO> mList) {
		return mapper.selectThumbnailList(mList);
	}
	
	// 게시글 상세 조회 이미지 목록 조회 Service 구현
	@Override
	public List<MarketAttachmentVO> selectAttachmentList(int marketNo) {
		return mapper.selectAttachmentList(marketNo);
	}

	// 게시글 상세 조회
	@Override
	public MarketVO selectMarket(int marketNo) {

		MarketVO temp = new MarketVO();
		temp.setMarketNo(marketNo);
		
		MarketVO market = mapper.selectMarket(temp);
		
		if(market != null) {
			int result = mapper.increaseReadCount(marketNo);
			
			if(result > 0) {
				market.setReadCount(market.getReadCount()+1);
			}
			
		}
		return market;
	}
	
	
	// 크로스 사이트 스크립팅 방지 처리 메소드
	private String replaceParameter(String param) {
		String result = param;
		if (param != null) {
			result = result.replaceAll("&", "&amp;");
			result = result.replaceAll("<", "&lt;");
			result = result.replaceAll(">", "&gt;");
			result = result.replaceAll("\"", "&quot;");
		}
		return result;
	}
	
	public String rename(String originFileName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String date = sdf.format(new java.util.Date(System.currentTimeMillis()));

		int ranNum = (int) (Math.random() * 100000);
		String str = "_" + String.format("%05d", ranNum);
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		return date + str + ext;
	}
	
	// 게시글 등록 Service 구현
	@Override
	public int insertMarket(MarketVO market, List<MultipartFile> images, String savePath) {
		int result = 0;

		int marketNo = mapper.selectNextNo();

		if (marketNo > 0) {
			market.setMarketNo(marketNo);

			String marketTitle = market.getMarketTitle();
			String marketContent = market.getMarketContent();

			marketTitle = replaceParameter(marketTitle);
			marketContent = replaceParameter(marketContent);

			market.setMarketTitle(marketTitle);
			market.setMarketContent(marketContent);
		}

		result = mapper.insertMarket(market);

		if (result > 0) {
			List<MarketAttachmentVO> uploadImages = new ArrayList<MarketAttachmentVO>();

			String filePath = "/resources/marketImages";

			for (int i = 0; i < images.size(); i++) {
				if (!images.get(i).getOriginalFilename().equals("")) {
					String fileName = rename(images.get(i).getOriginalFilename());

					MarketAttachmentVO at = new MarketAttachmentVO(filePath, fileName, i, marketNo);

					uploadImages.add(at);
					System.out.println(uploadImages);
				}
			}

			if (!uploadImages.isEmpty()) {
				result = mapper.insertAttachmentList(uploadImages);

				if (result == uploadImages.size()) {
					result = marketNo;

					
					 int size = uploadImages.size();
					  
					 
					 
//						 if(!images.get(0).getOriginalFilename().equals("")) { size = images.size(); }
					 
					for (int i = 0; i < size; i++) {
						try {
							images.get(uploadImages.get(i).getFileLevel())
									.transferTo(new File(savePath + "/" + uploadImages.get(i).getFileName()));
							System.out.println("파일 저장 성공!!!!");
						} catch (Exception e) {
							e.printStackTrace();

							throw new MarketInsertAttachmentFailException("파일 서버 저장 실패");
						}
					}
				}
			} else {
				throw new MarketInsertAttachmentFailException("파일 정보 DB 삽입 실패");
			}
		} else {
			result = marketNo;
		}
		return result;
	}

	
}
