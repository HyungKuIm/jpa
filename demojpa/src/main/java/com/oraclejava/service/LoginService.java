package com.oraclejava.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oraclejava.dao.KbUserRepository;
import com.oraclejava.model.KbUser;

@Service
public class LoginService implements UserDetailsService {
	private final KbUserRepository kbUserRepository;

	public LoginService(KbUserRepository kbUserRepository) {
		this.kbUserRepository = kbUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		//유저이름으로 검색
		KbUser user = kbUserRepository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("그런 유저 없습니다"));
		
		return new User(user.getUsername(), user.getPassword(),
				AuthorityUtils.createAuthorityList(user.getAuthority().name()));
	}

	
	
	
	
	
}
