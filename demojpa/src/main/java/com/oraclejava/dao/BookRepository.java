package com.oraclejava.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.oraclejava.model.Book;

// 1. CrudRepository => PagingAndSortingRepository
public interface BookRepository 
	extends PagingAndSortingRepository<Book, Long>{   
	

}
