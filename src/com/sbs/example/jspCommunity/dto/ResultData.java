package com.sbs.example.jspCommunity.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

@Data
// 결과보고서 객체를 HashMap이 아닌 전용객체(ResultData)로 변경
public class ResultData {   //result 관련 값들을 모아 결과값을 계산, 정리해주는 객체
	private String resultCode;
	private String msg;
	private Object body;

	// 조합1: 코드, 메시지
	public ResultData(String resultCode, String msg) {
		this(resultCode, msg, null);
	}

	// 조합2: 코드, 메시지, 내용
	public ResultData(String resultCode, String msg, Object body) {
		this.resultCode = resultCode;
		this.msg = msg;
		this.body = body;
	}

	// 조합3: 코드, 메시지, 내용1키, 내용1값
	public ResultData(String resultCode, String msg, String bodyParam1Key, Object bodyParam1Value) {
		this(resultCode, msg, bodyParam1Key, bodyParam1Value, null, null);
	}

	// 조합4: 코드, 메시지, 내용1키, 내용1값, 내용2키, 내용2값
	public ResultData(String resultCode, String msg, String bodyParam1Key, Object bodyParam1Value, String bodyParam2Key, Object bodyParam2Value) {
		this(resultCode, msg, bodyParam1Key, bodyParam1Value, bodyParam2Key, bodyParam2Value, null, null);
	}

	// 조합5: 코드, 메시지, 내용1키, 내용1값, 내용2키, 내용2값, 내용3키, 내용3값
	public ResultData(String resultCode, String msg, String bodyParam1Key, Object bodyParam1Value, String bodyParam2Key, Object bodyParam2Value, String bodyParam3Key, Object bodyParam3Value) {
		this(resultCode, msg, bodyParam1Key, bodyParam1Value, bodyParam2Key, bodyParam2Value, bodyParam3Key, bodyParam3Value, null, null);
	}

	// 조합6: 코드, 메시지, 내용1키, 내용1값, 내용2키, 내용2값, 내용3키, 내용3값, 내용4키, 내용4값
	public ResultData(String resultCode, String msg, String bodyParam1Key, Object bodyParam1Value, String bodyParam2Key, Object bodyParam2Value, String bodyParam3Key, Object bodyParam3Value, String bodyParam4Key, Object bodyParam4Value) {
		this(resultCode, msg);

		Map<String, Object> body = new LinkedHashMap<>();

		if (bodyParam1Key != null) {
			body.put(bodyParam1Key, bodyParam1Value);
		}

		if (bodyParam2Key != null) {
			body.put(bodyParam2Key, bodyParam2Value);
		}

		if (bodyParam3Key != null) {
			body.put(bodyParam3Key, bodyParam3Value);
		}

		if (bodyParam4Key != null) {
			body.put(bodyParam4Key, bodyParam4Value);
		}

		if (body.isEmpty() == false) {
			this.body = body;
		}
	}

	public boolean isFail() {
		return isSuccess() == false;
	}

	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}
}
