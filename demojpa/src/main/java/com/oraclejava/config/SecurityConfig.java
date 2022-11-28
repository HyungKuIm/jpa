package com.oraclejava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.oraclejava.util.Authority;

@Configuration
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		//패스워드 암호화 사용(BCrypt) --> Google, MS, Apple, Meta에서 사용중
		return new BCryptPasswordEncoder();
	}
	
	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)
		throws Exception {
		// 인증할 요청을 설정
		http.authorizeHttpRequests(auth -> 
			
			auth
			// css, images 폴더처럼 정적 리소스 엑세스 가능하도록 함
			.mvcMatchers("/css/**", "/js/**", "/images/**", "/webjars/**",
							"/favicon.*", "/icon-*").permitAll()
			// 여기에 풀어주고 싶은 url을 지정하면 됨
			.mvcMatchers("/register", "/login").permitAll()
			// 어드민 컨트롤러 권한 설정
			.antMatchers("/admin/**").hasAuthority(Authority.ADMIN.name())
			// 화이트리스트 방식(모든 요청을 막음)
			.anyRequest().authenticated())
			.formLogin(login -> login
					.loginPage("/login")
					.defaultSuccessUrl("/")
					.permitAll())
			//로그아웃 설정 추가
			.logout(logout -> logout
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.permitAll());
		return http.build();
		
	}
	
	
}







