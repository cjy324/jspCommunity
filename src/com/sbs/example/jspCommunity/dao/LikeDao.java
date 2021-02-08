package com.sbs.example.jspCommunity.dao;

import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

public class LikeDao {

	public int getPoint(String relTypeCode, int relId, int memberId) {
		
			SecSql sql = new SecSql();
			sql.append("SELECT IFNULL(SUM(L.point), 0) AS `point`");
			sql.append("FROM `like` AS L");
			sql.append("WHERE 1");
			sql.append("AND L.relTypeCode = ?", relTypeCode);
			sql.append("AND L.relId = ?", relId);
			sql.append("AND L.memberId = ?", memberId);

			return MysqlUtil.selectRowIntValue(sql);
		}

	public int removePoint(String relTypeCode, int relId, int memberId) {
		SecSql sql = new SecSql();
		sql.append("DELETE FROM `like`");
		sql.append("WHERE 1");
		sql.append("AND relTypeCode = ?", relTypeCode);
		sql.append("AND relId = ?", relId);
		sql.append("AND memberId = ?", memberId);

		return MysqlUtil.delete(sql);
	}

	public int setPoint(String relTypeCode, int relId, int memberId, int point) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO `like`");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", relTypeCode = ?", relTypeCode);
		sql.append(", relId = ?", relId);
		sql.append(", memberId = ?", memberId);
		sql.append(", `point` = ?", point);

		return MysqlUtil.insert(sql);
	}
	}

