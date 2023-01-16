package com.myshop.controller;

import javax.validation.Valid;

import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myshop.dto.MemberFormDto;
import com.myshop.entity.Member;
import com.myshop.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/members")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
	//회원가입 화면
	//get방식 request
	@GetMapping(value = "/new")
	public String memberForm(Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		return "member/memberForm";
	}
	
	//회원가입 버튼을 눌렀을 때 실행되는 메소드
	//post방식 request
	//@Valid : 유효성을 검증하려는 객체 앞에 붙인다.
	//BindingResult : 유효성 검증 후에 결과를 BindingResult에 넣어준다
	@PostMapping(value = "/new")
	public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "member/memberForm";
		}
		
		try {
			Member member = Member.createMember(memberFormDto, passwordEncoder);
			memberService.saveMember(member);
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/memberForm";
		}
		
		return "redirect: /";
		//redirect를 쓰는 이유는
		//1.URL의 변화여부가 필요할 때.
		//2. 다음 새로운 요청 url을 보낼 때 최초요청 url은 유효하지 않고 이 두 요청은 전혀 다른 요청을 수행한다.
		//3. 시스템에 변화가 생기는 요청(로그인, 회원가입, 글쓰기)의 경우
	}
	
}
