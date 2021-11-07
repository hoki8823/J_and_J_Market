package com.gaji.jjmarket.market.model.mapper;

import java.util.List;

import com.gaji.jjmarket.market.model.domain.MarketAttachmentVO;
import com.gaji.jjmarket.market.model.domain.MarketPageInfo;
import com.gaji.jjmarket.market.model.domain.MarketVO;

public interface MarketMapper {

	/** 거래게시판 전체 게시글 수 조회 mapper
	 * @return listCount;
	 */
	public int countMarket();
	
	/** 게시글 목록 조회 mapper
	 * @param mpInfo
	 * @return mList
	 */
	public List<MarketVO> selectList(MarketPageInfo mpInfo);
	
	/** 썸네일 목록 조회 mapper
	 * @param mList
	 * @return thList
	 */
	public List<MarketAttachmentVO> selectThumbnailList(List<MarketVO> mList);
	
	/** 상세조회 DAO
	 * @param temp
	 * @return market
	 */
	public MarketVO selectMarket(MarketVO temp);
	
	/** 게시글에 포함된 이미지 목록 조회 DAO
	 * @param marketNo
	 * @return attachmentList
	 */
	public List<MarketAttachmentVO> selectAttachmentList(int marketNo);

	/** 조회수 증가 DAO
	 * @param marketNo
	 * @return
	 */
	public int increaseReadCount(int marketNo);
	
	/** 게시글 등록 DAO
	 * @param market
	 * @return result
	 */
	public int insertMarket(MarketVO market) ;

	/** 다음 게시글 번호 조회 DAO
	 * @return result
	 */
	public int selectNextNo();

	/** 파일 정보 삽입 DAO
	 * @param uploadImages
	 * @return result
	 */
	public int insertAttachmentList(List<MarketAttachmentVO> uploadImages) ;
	
}
