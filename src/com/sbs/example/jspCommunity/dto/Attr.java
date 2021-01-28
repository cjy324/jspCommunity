package com.sbs.example.jspCommunity.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Attr {
	private int id;
	private String regDate;
	private String updateDate;
	private String expireDate;
	private String relTypeCode;
	private int relId;
	private String typeCode;
	private String type2Code;
	private String value;

	public Attr(Map<String, Object> map) {
		this.id = (int) map.get("id");
		this.regDate = (String) map.get("regDate");
		this.updateDate = (String) map.get("updateDate");
		this.expireDate = (String) map.get("expireDate");
		this.relTypeCode = (String) map.get("relTypeCode");
		this.relId = (int) map.get("relId");
		this.typeCode = (String) map.get("typeCode");
		this.type2Code = (String) map.get("type2Code");
		this.value = (String) map.get("value");
	}
}