package com.cjy.jspCommunity.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Member {

	public Member(Map<String, Object> memberMap) {
		this.id = (int) memberMap.get("id");
		this.regDate = (String) memberMap.get("regDate");
		this.updateDate = (String) memberMap.get("updateDate");
		this.loginId = (String) memberMap.get("loginId");
		this.loginPw = (String) memberMap.get("loginPw");
		this.name = (String) memberMap.get("name");
		this.nickname = (String) memberMap.get("nickname");
		this.email = (String) memberMap.get("email");
		this.cellphoneNo = (String) memberMap.get("cellphoneNo");
		this.authLevel = (int) memberMap.get("authLevel");
		
		this.loginProviderTypeCode = (String) memberMap.get("loginProviderTypeCode");
		this.onLoginProviderMemberId = (String) memberMap.get("onLoginProviderMemberId");

	}

	private int id;
	private String regDate;
	private String updateDate;
	private String loginId;
	private String loginPw;
	private String name;
	private String nickname;
	private String email;
	private String cellphoneNo;
	private int authLevel;
	private String loginProviderTypeCode;  //일반 로그인 common, 카카오 로그인 kakaoRest
	private String onLoginProviderMemberId;

}