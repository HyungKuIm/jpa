package com.oraclejava.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD})   // <-- 1
@Retention(RetentionPolicy.RUNTIME) // <-- 2
@Constraint(validatedBy = UniqueLoginValidator.class)  // <-- 3
public @interface UniqueLogin { // <-- 4
	String message() default "이미 사용중인 아이디입니다.";  // <-- 5
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};
	// 1.  이 어노테이션이 사용되는 장소(메소드와 필드에 사용 가능)
	// 2.  이 어노테이션의 유통기한 지정(실행시에만 '사용'가능)
	// 3.  이 어노테이션의 제약 대상 지정(validatedBy에 체크하는 로직을 구현한 클래스 지정)
	// 4.  @interface를 붙여주면 어노테이션이라는 것을 알려줌
	// 5.  에러 메시지(기본값은 default 뒤에 ""로 지정가능)
}







