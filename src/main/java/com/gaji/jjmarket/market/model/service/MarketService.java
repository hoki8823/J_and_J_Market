package com.gaji.jjmarket.market.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gaji.jjmarket.market.model.domain.MarketAttachmentVO;
import com.gaji.jjmarket.market.model.domain.MarketPageInfo;
import com.gaji.jjmarket.market.model.domain.MarketVO;

public interface MarketService {
	
	/** 게시글 총 갯수 조회 Service
	 * @return listCount;
	 */
	public int countMarket();
	
	/** 게시글 목록 조회 Service
	 * @param mpInfo 
	 * @return mList
	 */
	public List<MarketVO> selectList(MarketPageInfo mpInfo);
	
	/** 썸네일 목록 조회 Service
	 * @param mList
	 * @return thList
	 */
	List<MarketAttachmentVO> selectThumbnailList(List<MarketVO> mList);
	
	/** 상세조회 Service
	 * @param marketNo
	 * @return market
	 */
	MarketVO selectMarket(int marketNo);
	
	/** 게시글 상세조회 이미지 Service
	 * @param marketNo
	 * @return attachmentList
	 */
	List<MarketAttachmentVO> selectAttachmentList(int marketNo);
	
	/** 게시글 등록 Service
	 * @param market
	 * @param images
	 * @param savePath
	 * @return result
	 */
	int insertMarket(MarketVO market, List<MultipartFile> images, String savePath);
	
	/** 사고 팔고 수정 Service
	 * @param market
	 * @param images
	 * @param savePath
	 * @param beforImages 
	 * @return result
	 */
	int updateMarket(MarketVO market, List<MultipartFile> images, String savePath, int[] beforImages);

	/** 게시글 삭제
	 * @param market
	 * @return result
	 */
	int deleteMarket(MarketVO market);
}
