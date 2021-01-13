package com.sbs.example.jspCommunity.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Member {

	public Member(Map<String, Object> memberMap) {
		this.id = (int)memberMap.get("id");
		this.loginId = (String)memberMap.get("loginId");
		this.loginPw = (String)memberMap.get("loginPw");
		this.name = (String)memberMap.get("name");
		this.nickname = (String)memberMap.get("nickname");
		this.email = (String)memberMap.get("email");
		this.cellphoneNo = (String)memberMap.get("cellphoneNo");
		this.authLevel = (int)memberMap.get("authLevel");
	
	}

	private int id;
	private String loginId;
	private String loginPw;
	private String name;
	private String nickname;
	private String email;
	private String cellphoneNo;
	private int authLevel;

}