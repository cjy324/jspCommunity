package com.sbs.example.jspCommunity.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Reply {

	public Reply(Map<String, Object> repliesMap) {
		this.id = (int) repliesMap.get("id");
		this.regDate = (String) repliesMap.get("regDate");
		this.updateDate = (String) repliesMap.get("updateDate");
		this.memberId = (int) repliesMap.get("memberId");
		this.relTypeCode = (String) repliesMap.get("relTypeCode");
		this.relId = (int) repliesMap.get("relId");
		this.body = (String) repliesMap.get("body");
		
		if (repliesMap.containsKey("extra_memberNickname")) {
			this.extra_memberNickname = (String) repliesMap.get("extra_memberNickname");
		}

	}

	public Reply() {

	}

	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private String relTypeCode;
	private int relId;
	private String body;
	private String extra_memberNickname;
	
}
