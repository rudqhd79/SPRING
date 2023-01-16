package com.myshop.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import com.myshop.dto.MemberFormDto;
import com.myshop.entity.Member;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberServiceTest {

	@Autowired
	MemberService memberService;
		
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Member createMember() {
		MemberFormDto member = new MemberFormDto();
		//test이기 때문에 임시 데이터 넣기
		member.setName("홍길동");
		member.setEmail("test@email.com");
		member.setAddress("서울시 마포구 합정동");
		member.setPassword("1234");
		
		return Member.createMember(member, passwordEncoder);
	}
	
	@Test
	@DisplayName("회원가입 테스트")
	public void saveMemberTest() {
		//createMember 메소드 다시 실행
		Member member = createMember();
		Member saveMember = memberService.saveMember(member);	//insert
		
		//test 할 때 값을 비교하는 것
		//저장하려고 했던 값과 실제 저장된 데이터를 비교하는 것이다
		assertEquals(member.getEmail(), saveMember.getEmail());
		assertEquals(member.getAddress(), saveMember.getAddress());
		assertEquals(member.getId(), saveMember.getId());
		assertEquals(member.getName(), saveMember.getName());
		assertEquals(member.getPassword(), saveMember.getPassword());
	}
	
	@Test
	@DisplayName("중복 회원 가입 테스트")
	public void saveDuplicateMemberTest() {
		Member member1 = createMember();
		Member member2 = createMember();
		
		memberService.saveMember(member1);
		
		//예외처리 테스트
		Throwable e = assertThrows(IllegalStateException.class, () -> {	// <-는 예외가 발생 한것 중 어떤것이 발생하게 끔 유도하는 메소드이다
			memberService.saveMember(member2);
		});
		
		//assertEquals는 비교하는 것
		assertEquals("이미 가입된 회원 입니다.",  e.getMessage());
	}
}
