package com.gaji.jjmarket.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gaji.jjmarket.member.model.domain.MemberVO;
import com.gaji.jjmarket.member.model.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/spring/root-context.xml")
@Log4j
public class MemberMapperTests {
	
	@Setter(onMethod = @__({@Autowired }))
	private MemberMapper mapper;
	
	
	@Test 
	public void testGetList() {
		mapper.getList().forEach(member -> log.info(member)); 
  	}
	
	@Test
	public void testLogin() {
		MemberVO mem = new MemberVO();
		mem.setMemberId("testid");
		mem.setMemberPwd("testpw1");
		//로그인에 실패할 경우 모든 정보 null값으로 가져와서 오류발생
		log.info(mapper.loginAction(mem).getMemberId());
	}
	
	
}
