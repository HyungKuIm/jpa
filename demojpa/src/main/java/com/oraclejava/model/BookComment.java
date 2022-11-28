package com.oraclejava.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BookComment implements Serializable {
	@Id
	@GeneratedValue(generator = "book_comment_generator",
			strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "book_comment_generator",
			allocationSize = 1, sequenceName="book_comment_seq")
	private Long id;   // 코멘트 id
	
	@NotNull(message = "이름은 필수입력입니다.")
	private String name;	// 이름
	
	@NotNull(message = "코멘트는 필수입력입니다.")
	@Size(min = 1, message = "코멘트는 필수입력입니다.")
	private String content; // 코멘트 내용
	//private Long bookId; // 북id
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
}





