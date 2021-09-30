package com.gaji.jjmarket.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gaji.jjmarket.member.model.domain.MemberVO;
import com.gaji.jjmarket.member.model.mapper.MemberMapper;

import jdk.internal.org.jline.utils.Log;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/spring/root-context.xml")
@Log4j
public class MemberMapperTests {
	
	@Setter(onMethod = @__({@Autowired }))
	private MemberMapper mapper;
	
	// 로그인 테스트
	@Test
	public void testLogin() {
		MemberVO mem = new MemberVO();
		mem.setMemberId("testid");
		mem.setMemberPwd("testpw");
		//로그인에 실패할 경우 모든 정보 null값으로 가져와서 오류발생
		log.info(mapper.loginAction(mem).getMemberId());
	}
	
	// 회원가입 테스트
	/*
	@Test
	public void testSignUp() {
		MemberVO mem = new MemberVO();
		mem.setMemberId("aaa");
		mem.setMemberPwd("aaa");
		mem.setMemberName("aaa");
		mem.setMemberNickname("aaa");
		mem.setMemberEmail("aaa@naver.com");
		mem.setMemberPhone("01012345678");
		mem.setMemberGender("M");
		mem.setMemberScsnFl("Y");
		
		mapper.signUp(mem);
		mapper.getList().forEach(member -> log.info(member));
		
	}
	*/
	
	// 아이디 중복 체크 테스트
	/*
	@Test
	public void testIdDupCheck() {
		log.info(mapper.idDupCheck("testid"));
	}
	*/
	
	// 닉네임 중복 체크 테스트
	/*
	@Test
	public void testNnDupCheck() {
		log.info(mapper.nnDupCheck("testnickname"));
	}
	*/
}
