package com.oraclejava.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.oraclejava.dao.BookCommentRepository;
import com.oraclejava.dao.BookRepository;
import com.oraclejava.model.Book;
import com.oraclejava.model.BookComment;

@Controller
public class BookController {
	
	private BookRepository bookRepository;
	
	private BookCommentRepository bookCommentRepository;

	public BookController(BookRepository bookRepository,
			BookCommentRepository bookCommentRepositry
			) {

		this.bookRepository = bookRepository;
		this.bookCommentRepository = bookCommentRepositry;
	}
	
	// 책추가
	@GetMapping("/add")
	public String addBook(@ModelAttribute Book book,
			Authentication loginUser, Model model) {
		model.addAttribute("username", loginUser.getName());
		model.addAttribute("authority", loginUser.getAuthorities());
		return "addBookForm";
	}
	
	@PostMapping("/add_ok")
	public String addOk(@Validated Book book, 
			BindingResult result,
			Authentication loginUser, 
			Model model) {
		if (result.hasErrors()) {
			return addBook(book, loginUser, model);
		}
		bookRepository.save(book);
		return "redirect:/";
	}
	
	//수정
	@GetMapping("/edit/{id}")
	public String editBook(@PathVariable Long id, Model model) {
		model.addAttribute("book", bookRepository.findById(id));
		return "editBookForm";
	}
	
	@PostMapping("/edit_ok")
	public String editOk(@Validated Book book, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return editBook(book.getId(), model);
		}
		bookRepository.save(book);
		return "redirect:/";
	}
	
	//삭제
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable Long id) {
		bookRepository.deleteById(id);
		return "redirect:/";
	}

	// 2. 페이징을 위한 컨트롤러 수정
	// 3. 블록 추가, html 수정
	// 4. 디버깅, 기능 개선
	@GetMapping("/")
	public String getAllBooks(
			@PageableDefault(size=10, sort="id", 
				direction = Sort.Direction.ASC) Pageable pageable,
			Authentication loginUser,
			Model model, @ModelAttribute BookComment bookComment) {
		
		model.addAttribute("username", loginUser.getName());
		model.addAttribute("authority", loginUser.getAuthorities());
		
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
	
	//댓글 쓰기
	@PostMapping("/postcomment")
	public String postcomment(@Validated BookComment bookComment, 
			BindingResult result, @PageableDefault(size=10, sort="id", 
					direction = Sort.Direction.ASC) Pageable pageable,
			Authentication loginUser,
			Model model) {
		if (result.hasErrors()) {
			return getAllBooks(pageable, loginUser, model, bookComment);
		}
		
		bookCommentRepository.save(bookComment);
		
		return "redirect:/";
	}
}












