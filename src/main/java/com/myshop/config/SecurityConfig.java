package com.myshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration	//이 클래스는 spring의 설정 파일로 쓰겠다는 annotation 이다.
@EnableWebSecurity	//springSecurityFilterChain이 자동으로 포함된다
//1개 이상의 @Bean을 제공하는 클래스의 경우 반드시 @Configuration을 명시해 주어야 싱글톤이 보장된다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//http 요청에 대해 보안을 설정하는 메소드 (페이지 권한 설정, 로그인 페이지 설정, 로그아웃 메소드에 대한 설정을 한다)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	}
	
	@Bean
	//비밀번호 암호화를 위해서 사용하는 빈(bean)
	public PasswordEncoder passwordEncoder() {
		//BCryptPasswordEncoder()는 
		return new BCryptPasswordEncoder();
	}
	
}
