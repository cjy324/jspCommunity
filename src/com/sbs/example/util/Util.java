package com.sbs.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

	//json파일 생성 유틸
	public static String getJsonText(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String rs = "";
		try {
			rs = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	
}
