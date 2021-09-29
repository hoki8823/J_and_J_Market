package com.gaji.jjmarket.member.model.service;

import com.gaji.jjmarket.member.model.domain.MemberVO;

public interface MemberService {
	
	/** 로그인 Service 
	 * @param inputMember
	 * @return loginMember
	 */
	public abstract MemberVO loginAction(MemberVO inputMember);
	
	/** 아이디 중복 검사 Service
	 * @param memberId
	 * @return result
	 */
	public abstract int idDupCheck(String memberId);
	
	/** 닉네임 중복 검사 Service
	 * @param memberNickname
	 * @return result
	 */
	public abstract int nnDupCheck(String memberNickname);
	
	/** 회원가입 Service
	 * @param signUpMember
	 * @return result
	 */
	public abstract int signUp(MemberVO signUpMember);
	
}
