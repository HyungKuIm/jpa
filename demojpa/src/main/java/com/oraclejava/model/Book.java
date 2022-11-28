package com.oraclejava.model;



import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@NotNull(message = "제목을 입력해 주세요")
	@Size(min = 1, max=127, message="제목은 1자 이상 127자 이내로 입력바랍니다.")
	private String title;  // 책 제목
	
	@Column(name="contents", nullable = false, length = 4000)
	@NotNull(message = "상세를 입력해 주세요")
	@Size(min = 1, max=2000, message="상세는 1자 이상 2000자 이내로 입력바랍니다.")
	private String detail;  // 책 상세
	
	@OneToMany(mappedBy = "book")
	private List<BookComment> bookComments;
}







