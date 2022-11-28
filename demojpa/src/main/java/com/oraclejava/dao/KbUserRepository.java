package com.oraclejava.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.oraclejava.model.KbUser;

public interface KbUserRepository 
	extends PagingAndSortingRepository<KbUser, Long>{
	
	Optional<KbUser> findByUsername(String username);
}
