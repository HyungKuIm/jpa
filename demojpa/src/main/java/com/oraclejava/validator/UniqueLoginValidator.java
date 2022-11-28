package com.oraclejava.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.oraclejava.dao.KbUserRepository;


public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String>{

	private final KbUserRepository kbUserRepository;
	
	// 의존성 주입시 생성자가 2개 이상 필요할 경우(바로 이런 경우임. Validator가 동작하려면
	//  반드시 기본 생성자가 필요함
	//  이럴 경우에는 아래처럼 기본 생성자를 만들고 의존성 주입하는 곳에는 @Autowired를 붙여줘서
	//  주입하겠다고 알려주면 됨
	public UniqueLoginValidator() {
		this.kbUserRepository = null;
	}

	//의존성 주입(DI)
	@Autowired
	public UniqueLoginValidator(KbUserRepository kbUserRepository) {
		this.kbUserRepository = kbUserRepository;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return kbUserRepository == null || 
				kbUserRepository.findByUsername(value).isEmpty();
	}

	
	
	
}
