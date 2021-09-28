package com.gaji.jjmarket.member.model.mapper;

import java.util.List;

import com.gaji.jjmarket.member.model.domain.MemberVO;

public interface MemberMapper {
	
	public List<MemberVO> getList();
	
	/** 로그인 Mapper 
	 * @param inputMember
	 * @return loginMember
	 */
	public MemberVO loginAction(MemberVO inputMember);
	
}
