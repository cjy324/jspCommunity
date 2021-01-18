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

	public int join(String loginId, String loginPw, String name, String nickname, String email, String cellPhoneNo) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO member");
		sql.append("SET loginId = ?,", loginId);
		sql.append("loginPw = ?,", loginPw);
		sql.append("regDate = NOW(),");
		sql.append("updateDate = NOW(),");
		sql.append("name = ?,", name);
		sql.append("nickname = ?,", nickname);
		sql.append("email = ?,", email);
		sql.append("cellPhoneNo = ?", cellPhoneNo);

		return MysqlUtil.insert(sql);
	}

}
