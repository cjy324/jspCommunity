package com.sbs.example.jspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

public class MemberDao {

	public List<Member> getMemberListForPrint() {
	
		List<Member> members = new ArrayList<>();
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT * FROM ");
		sql.append("member ");
		sql.append("ORDER BY id DESC");
		
		List<Map<String, Object>> membersMapList = MysqlUtil.selectRows(sql);

		for(Map<String, Object> membersMap : membersMapList) {
			Member member = new Member(membersMap);
			
			members.add(member);
			
		}

		return members;
	}

	public int join(Map<String, Object> joinArg) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO member");
		sql.append("SET loginId = ?,", joinArg.get("loginId"));
		sql.append("loginPw = ?,", joinArg.get("loginPw"));
		sql.append("regDate = NOW(),");
		sql.append("updateDate = NOW(),");
		sql.append("name = ?,", joinArg.get("name"));
		sql.append("nickname = ?,", joinArg.get("nickname"));
		sql.append("email = ?,", joinArg.get("email"));
		sql.append("cellPhoneNo = ?", joinArg.get("cellPhoneNo"));

		return MysqlUtil.insert(sql);
	}

	public Member getMemberById(int id) {
		SecSql sql = new SecSql();

		sql.append("SELECT * FROM member");
		sql.append("WHERE id = ?", id);
		
		Map<String, Object> memberMap = MysqlUtil.selectRow(sql);
		
		if(memberMap.isEmpty()) {
			return null;
		}
		
		return new Member(memberMap);
	}

	public Member getMemberByLoginId(String loginId) {
		SecSql sql = new SecSql();

		sql.append("SELECT * FROM member");
		sql.append("WHERE loginId = ?", loginId);
		
		Map<String, Object> memberMap = MysqlUtil.selectRow(sql);
		
		if(memberMap.isEmpty()) {
			return null;
		}
		
		return new Member(memberMap);
	}


}
