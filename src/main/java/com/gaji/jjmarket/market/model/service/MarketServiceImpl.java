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

	
	// 사고 팔고 수정 Service 구현
	@Override
	public int updateMarket(MarketVO market, List<MultipartFile> images, String savePath, int[] beforImages) {
		
		// 크로스사이트스크립트  방지 처리
		String marketTitle = market.getMarketTitle();
		String marketContent = market.getMarketContent();

		marketTitle = replaceParameter(marketTitle);
		marketContent = replaceParameter(marketContent);

		market.setMarketTitle(marketTitle);
		market.setMarketContent(marketContent);
		
		// 사고 팔고 글 수정 DAO 호출
		int result = mapper.updateMarket(market);
		
		if(result > 0) {
			// 수정 전 업로드된 파일 정보를 얻어옴.
			// -> 새롭게 삽입 또는 수정되는 파일과 비교하기 위함.
			List<MarketAttachmentVO> oldFiles = mapper.selectAttachmentList(market.getMarketNo());
			
			// 새로 업로드된 파일 정보를 담을 리스트
			List<MarketAttachmentVO> uploadImages = new ArrayList<MarketAttachmentVO>();
			
			// 삭제 되어야할 파일 정보를 담을 리스트
			List<MarketAttachmentVO> removeFileList = new ArrayList<MarketAttachmentVO>();
			
			int lv = 0; // 파일 레벨을 지정하기 위한 변수
			for(MarketAttachmentVO old : oldFiles) {
				
				for(int i=0; i<beforImages.length ; i++) {
					
					if(old.getFileNo() == beforImages[i] && old.getFileLevel() != i) {
						//if(i == 0) lv = 0;
						//else	   
							lv = i;
						System.out.println(beforImages[i] + " / " + old);
						System.out.println("lv : " + lv);
						MarketAttachmentVO newAt = new MarketAttachmentVO(old.getFilePath(), old.getFileName(), lv, market.getMarketNo());
						newAt.setFileNo(old.getFileNo());
						
						result = mapper.updateOldFile(newAt);
						
						if(result == 0) {
							throw new RuntimeException("파일 정보 수정 중 오류 발생");
						}
					}
				}
				
				
				boolean flag = true;
				for(int i=0; i<beforImages.length ; i++) {
					if(old.getFileNo() == beforImages[i]) {
						flag = false;
						break;
					}
				}
				
				if(flag) {
					removeFileList.add(old);
				}
			}
			
			if(lv == 0) {
				lv = beforImages.length;
			}else {
				lv++;
			}
			
			
			
			// DB에 저장할 웹상 이미지 접근 경로
			String filePath = "/resources/marketImages";
			
			// 새롭게 업로드된 파일 정보를 가지고 있는 images에 반복 접근
			for (int i = 0; i < images.size(); i++) {
				if (!images.get(i).getOriginalFilename().equals("")) {
					
					// 파일명 변경
					String fileName = rename(images.get(i).getOriginalFilename());

					// MarketAttachment 객체 생성
					MarketAttachmentVO at = new MarketAttachmentVO(filePath, fileName, lv++, market.getMarketNo());

					uploadImages.add(at);
				}
			}
			
			
			// 새롭게 삽입된 이미지 모두 삽입.
			if(!uploadImages.isEmpty()) {
				result = mapper.insertAttachmentList(uploadImages);
			}
			
		
			
			if(result > 0 && !removeFileList.isEmpty()) {
				result = mapper.deleteAttachmentList(removeFileList);
				if(result <= 0) {
					throw new RuntimeException("파일 정보 삭제 중 오류 발생");
				}
			}
			
			
			if(result > 0) {
				
				for(int i=0 ; i<uploadImages.size(); i++) {
					
					try {
						images.get(uploadImages.get(i).getFileLevel())
							.transferTo(new File(savePath + "/" + uploadImages.get(i).getFileName()) );                                             
					}catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("파일 정보 수정 실패");
					}
				}
			}
			
			
			// 이전 파일 서버에서 삭제하는 코드 
			for(MarketAttachmentVO removeFile : removeFileList) {
				File tmp = new File(savePath + "/" + removeFile.getFileName());
				tmp.delete();
			}
		}
		
		
		return result;
	}
	
	// 게시글 삭제 Service 구현
	@Override
	public int deleteMarket(MarketVO market) {
		return mapper.deleteMarket(market);
	}
	
}
