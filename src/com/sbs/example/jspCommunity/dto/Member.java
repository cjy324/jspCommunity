package com.sbs.example.jspCommunity.dto;

import java.util.Map;

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

	public int id;
	public String loginId;
	public String loginPw;
	public String name;
	public String nickname;
	public String email;
	public String cellphoneNo;
	public int authLevel;

}