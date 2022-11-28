package com.oraclejava.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.oraclejava.model.BookComment;

public interface BookCommentRepository 
	extends PagingAndSortingRepository<BookComment, Long>{

}
