package com.oraclejava.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.oraclejava.dao.KbUserRepository;
import com.oraclejava.model.KbUser;
import com.oraclejava.util.Authority;

@Controller
public class LoginController {

	private final PasswordEncoder passwordEncoder;
	private final KbUserRepository kbUserRepository;
	
	//의존성 주입(DI)??
	public LoginController(
			KbUserRepository kbUserRepository,
			PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		this.kbUserRepository = kbUserRepository;
	}
	
	// 로그인
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	

	// 회원가입
	@GetMapping("/register")
	public String register(@ModelAttribute("user") KbUser kbUser) {  // 이때 오브젝트의 이름은 "bkUser"
		return "register";
	}
	
	@PostMapping("/register")
	public String regi_ok(@Validated @ModelAttribute("user") KbUser kbUser,
				BindingResult result) {
		if (result.hasErrors()) {
			return register(kbUser);
		}
		
		// 패스워드 암호화!
		kbUser.setPassword(passwordEncoder.encode(kbUser.getPassword()));
		
		// 권한 부여
		kbUser.setAuthority(Authority.USER);  // 일반 사용자
		
		// DB저장
		kbUserRepository.save(kbUser);
		
		return "redirect:/login?regi_ok";
	}
}













