package com.oraclejava.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.oraclejava.dao.BookRepository;
import com.oraclejava.model.Book;

@Controller
public class BookController {
	
	private BookRepository bookRepository;

	public BookController(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	// 2. 페이징을 위한 컨트롤러 수정
	// 3. 블록 추가, html 수정
	// 4. 디버깅, 기능 개선
	@GetMapping("/")
	public String getAllBooks(
			@PageableDefault(size=10, sort="id", 
				direction = Sort.Direction.DESC) Pageable pageable,
			Model model) {
		Page<Book> books =
				bookRepository.findAll(pageable);
		int current = books.getNumber() + 1;   // 현재 페이지
//		int begin = 1;   // 시작 페이지
//		int end = books.getTotalPages();  // 마지막 페이지
		
		//블록의 크기
		int block = 10;
		//총 블록
		int blockNum = (int)Math.ceil((double)books.getTotalPages() 
				/ block);
		//현재 블록
		int nowBlock = (int)Math.ceil((double)current / block);
		
		//시작 페이지
		int begin = (nowBlock * block) - (block - 1);
		
		// 보험용! ///////////////////////////////////
		if (begin <= 1) {
			begin = 1;  // < 를 연타!했을 경우 넘어가는 걸 방지!!
		}
		/////////////////////////////////////////////
		
		int end = nowBlock * block;
		
		// 보험용! ///////////////////////////////////
		if (books.getTotalPages() <= end) {
			end = books.getTotalPages();   // > 를 연타!했을 경우 넘어가는 걸 방지!!
		}
		/////////////////////////////////////////////
		
		
		model.addAttribute("books", books);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("blockNum", blockNum);
		model.addAttribute("nowBlock", nowBlock);
		model.addAttribute("pageNum", books.getTotalPages());
		//model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}
}




