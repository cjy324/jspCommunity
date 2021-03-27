package com.cjy.jspCommunity.dto;

import java.util.LinkedHashMap;
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
		this.extra = new LinkedHashMap<>(); //부가정보를 가져와 Map형태로 담기

		if (repliesMap.containsKey("extra_memberNickname")) {
			this.extra_memberNickname = (String) repliesMap.get("extra_memberNickname");
		}
		if (repliesMap.containsKey("extra_likePoint")) {
			this.extra_likePoint = (int) repliesMap.get("extra_likePoint");
		}
		if (repliesMap.containsKey("extra_likeOnlyPoint")) {
			this.extra_likeOnlyPoint = (int) repliesMap.get("extra_likeOnlyPoint");
		}
		if (repliesMap.containsKey("extra_dislikeOnlyPoint")) {
			this.extra_dislikeOnlyPoint = (int) repliesMap.get("extra_dislikeOnlyPoint");
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
	private Map<String, Object> extra;  //부가정보를 가져와 Map형태로 담기
	private int extra_likePoint;
	private int extra_likeOnlyPoint;
	private int extra_dislikeOnlyPoint;

}
