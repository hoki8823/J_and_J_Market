package com.gaji.jjmarket.member.model.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaji.jjmarket.member.model.domain.MemberVO;
import com.gaji.jjmarket.member.model.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private final MemberMapper mapper;
	
	private final BCryptPasswordEncoder enc;
	
	
	@Override
	public List<MemberVO> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	
	// 로그인 Service 구현
	@Override
	public MemberVO loginAction(MemberVO inputMember) {
		MemberVO loginMember = mapper.loginAction(inputMember);
		
		// 추후에 bcrypt 암호화 사용예정
		
		if(loginMember != null) {
			if(enc.matches(inputMember.getMemberPwd(), // 입력받는 비밀번호
					loginMember.getMemberPwd())) { // DB에 저장된 암호화 비밀번호
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
	
	// 아이디 중복 체크 Service 구현
	@Override
	public int idDupCheck(String memberId) {
		return mapper.idDupCheck(memberId);
	}

	// 닉네임 중복 체크 Service 구현
	@Override
	public int nnDupCheck(String memberNickname) {
		return mapper.nnDupCheck(memberNickname);
	}

	
	// 회원가입 Service 구현
	@Transactional(rollbackFor = Exception.class)
	// SQLException : DB관련 오류가 났을 때 rollback을 하겠다.
	// Exception : 아무 예외 발생시 rollback을 하겠다.
	@Override
	public int signUp(MemberVO signUpMember) {
		// 암호화 추가 예정 ** spring-security-core 모듈
		/*
		 * 비밀번호를 저장하는 방법
		 * 
		 * 1. 평문 형태 그대로 저장 -> 범죄 행위
		 * 
		 * 2. SHA-512 같은 단방향 암호화(단방향 해쉬함수)를 사용 -> 같은 비밀번호를 암호화 하면 똑같은 다이제스트가 반환된다는 문제점이
		 * 있음. (해킹에 취약) (암호화된 비밀번호 == 다이제스트) (일반적인 해킹 장비 성능으로 1초에 56억개의 다이제스트를 비교할 수
		 * 있음(참고))
		 * 
		 * 3. bcrypt 방식의 암호화 -> 비밀번호 암호화에 특화된 암호화 방식 -> 입력된 문자열과 임의의 문자열(salt)을 첨부하여
		 * 암호화를 진행 --> 같은 비밀번호를 입력해도 서로 다르 다이제스트가 반환됨.
		 * 
		 * ** Spring-security-core 모듈 추가해야함.
		 */
		// mapper 전달 전에 비밀번호 암호화
		String encPwd = enc.encode(signUpMember.getMemberPwd());

		System.out.println(signUpMember.getMemberPwd());
		signUpMember.setMemberPwd(encPwd);
		
		return mapper.signUp(signUpMember);
		
	}


	
	
	

}
