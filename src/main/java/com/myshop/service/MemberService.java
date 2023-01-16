package com.myshop.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.myshop.entity.Member;
import com.myshop.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service	//service 클래스의 역할
@Transactional	//서비스 클래스에서 로직을 처리하다가 에러가 발생하면 로직을 수행하기 이전 상태로 되돌려 준다.
@RequiredArgsConstructor	//final이 붙거나 @NotNull이 붙은 필드의 생성자를 자동으로 생성
public class MemberService {
	private final MemberRepository memberRepository;	//의존성 주입
	
	public Member saveMember(Member member) {
		validateDuplicateMember(member);
		return memberRepository.save(member);	//member 테이블에 insert
	}
	
	//이메일 중복 체크하는 메소드 생성
	private void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail());
		if (findMember != null) {
			throw new IllegalStateException("이미 가입된 회원 입니다.");
		}
	}
}
