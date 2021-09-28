package com.gaji.jjmarket.member.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaji.jjmarket.member.model.domain.MemberVO;
import com.gaji.jjmarket.member.model.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private final MemberMapper mapper;
	
	// 로그인 Service
	@Override
	public MemberVO loginAction(MemberVO inputMember) {
		MemberVO loginMember = mapper.loginAction(inputMember);
		
		// 추후에 bcrypt 암호화 사용예정
		
		if(loginMember != null) {
			if(inputMember.getMemberPwd().equals(loginMember.getMemberPwd())) {
				// DB에서 조회된 회원정보를 반환하면 되지만
				// 비밀번호는 null 값으로 변경해서 내보냄.
				loginMember.setMemberPwd(null);
				
			} else { // 비밀번호가 다를 때
				
				// 로그인이 실패한 모양을 만들어줌
				loginMember = null;
			}
		}
		
		return loginMember;
	}

	// 회원가입 Service 구현
	@Transactional(rollbackFor = Exception.class)
	// SQLException : DB관련 오류가 났을 때 rollback을 하겠다.
	// Exception : 아무 예외 발생시 rollback을 하겠다.
	@Override
	public int signUp(MemberVO signUpMember) {
		// 암호화 추가 예정 ** spring-security-core 모듈
		
		String Pwd = signUpMember.getMemberPwd();
		System.out.println(signUpMember.getMemberPwd());
		signUpMember.setMemberPwd(Pwd);
		
		return mapper.signUp(signUpMember);
	}

}
