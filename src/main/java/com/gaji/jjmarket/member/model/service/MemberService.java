package com.gaji.jjmarket.member.model.service;

import com.gaji.jjmarket.member.model.domain.MemberVO;

public interface MemberService {
	
	/** 로그인 Service 
	 * @param inputMember
	 * @return loginMember
	 */
	public abstract MemberVO loginAction(MemberVO inputMember);
	
}
