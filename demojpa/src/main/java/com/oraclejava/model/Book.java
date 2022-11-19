package com.oraclejava.model;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book implements Serializable{
	@Id
	@GeneratedValue(generator = "book_id_generator", 
		strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "book_id_generator", 
		allocationSize = 1, sequenceName = "book_seq")
	private Long id; // 책 id
	
	@Column(nullable = false)
	private String title;  // 책 제목
	
	@Column(name="contents", nullable = false, length = 4000)
	private String detail;  // 책 상세
}







