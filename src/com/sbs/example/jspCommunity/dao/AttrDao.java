package com.sbs.example.jspCommunity.dao;

import java.util.Map;

import com.sbs.example.jspCommunity.dto.Attr;
import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

public class AttrDao {
	public int setValue(String relTypeCode, int relId, String typeCode, String type2Code, String value, String expireDate) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO attr (regDate, updateDate, expireDate, `relTypeCode`, `relId`, `typeCode`, `type2Code`, `value`)");

		if ( expireDate == null ) {
			sql.append("VALUES (NOW(), NOW(), NULL, ?, ?, ?, ?, ?)", relTypeCode, relId, typeCode, type2Code, value);			
		}
		else {
			sql.append("VALUES (NOW(), NOW(), ?, ?, ?, ?, ?, ?)", expireDate, relTypeCode, relId, typeCode, type2Code, value);
		}

		sql.append("ON DUPLICATE KEY UPDATE");
		sql.append("updateDate = NOW()");

		if ( expireDate != null ) {
			sql.append(", expireDate = ?", expireDate);
		}

		sql.append(", `value` = ?", value);

		return MysqlUtil.update(sql);
	}

	public Attr get(String relTypeCode, int relId, String typeCode, String type2Code) {
		SecSql sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM attr");
		sql.append("WHERE 1");
		sql.append("AND `relTypeCode` = ?", relTypeCode);
		sql.append("AND `relId` = ?", relId);
		sql.append("AND `typeCode` = ?", typeCode);
		sql.append("AND `type2Code` = ?", type2Code);
		sql.append("AND  (expireDate >= NOW() OR expireDate IS NULL)");
		
		Map<String, Object> row = MysqlUtil.selectRow(sql);
		
		if(row.isEmpty()) {
			return null;
		}

		return new Attr(row);
	}

	public String getValue(String relTypeCode, int relId, String typeCode, String type2Code) {
		SecSql sql = new SecSql();

		sql.append("SELECT `value`");
		sql.append("FROM attr");
		sql.append("WHERE 1");
		sql.append("AND `relTypeCode` = ?", relTypeCode);
		sql.append("AND `relId` = ?", relId);
		sql.append("AND `typeCode` = ?", typeCode);
		sql.append("AND `type2Code` = ?", type2Code);
		sql.append("AND  (expireDate >= NOW() OR expireDate IS NULL)");

		return MysqlUtil.selectRowStringValue(sql);
	}

	public int remove(String relTypeCode, int relId, String typeCode, String type2Code) {
		SecSql sql = new SecSql();

		sql.append("DELETE FROM attr");
		sql.append("WHERE 1");
		sql.append("AND `relTypeCode` = ?", relTypeCode);
		sql.append("AND `relId` = ?", relId);
		sql.append("AND `typeCode` = ?", typeCode);
		sql.append("AND `type2Code` = ?", type2Code);

		return MysqlUtil.delete(sql);
	}
}