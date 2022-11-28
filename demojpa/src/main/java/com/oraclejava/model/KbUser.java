package com.oraclejava.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.oraclejava.util.Authority;
import com.oraclejava.validator.UniqueLogin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class KbUser {
	@Id
	@GeneratedValue(generator = "kbuser_generator", 
		strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "kbuser_generator", 
		allocationSize = 1, sequenceName = "kbuser_seq")
	private Long id;   // 사용자id
	
	@Size(min = 2, max = 20, message = "사용자아이디는 2자 이상 20자 이내로 입력해 주세요")
	@UniqueLogin
	private String username;  //사용자이름
	
	// todo: 4~255자 이내로 입력 가능하도록 설정
	@Size(min = 4, max = 255, message = "비밀번호는 4자 이상 255자 이내로 입력 바랍니다.")
	private String password;  //패스워드
	
	@NotBlank(message = "이메일은 필수입력입니다.")
	@Email(message = "이메일 형식이 잘못 되었습니다.")
	private String email; // 사용자 이메일
	
	private int gender;  //성별(기본값 0)
	private boolean admin; // 관리자인지 아닌지(기본값 false, 관리자 true)
	private Authority authority;  // 회원 등급
}






