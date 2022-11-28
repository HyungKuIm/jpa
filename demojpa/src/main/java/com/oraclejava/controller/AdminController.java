package com.oraclejava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oraclejava.dao.KbUserRepository;


@Controller

public class AdminController {
	
	private KbUserRepository kbUserRepository;

	public AdminController(KbUserRepository kbUserRepository) {
		this.kbUserRepository = kbUserRepository;
	}
	

	@RequestMapping("/admin/list")
	public String listAdmin(Model model) {
		
		model.addAttribute("adminLists", kbUserRepository.findAll());
		
		return "admin/list";
		
	}
	
	
	
}
