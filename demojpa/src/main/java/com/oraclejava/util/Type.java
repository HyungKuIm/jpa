package com.oraclejava.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class Type {
	public static final Map<Integer, String> GENDERS;
	
	static {
		Map<Integer, String> genders = new LinkedHashMap<>();
		genders.put(0, "선택하지 않음");
		genders.put(1, "남성");
		genders.put(2, "여성");
		GENDERS = genders;
	}
}
