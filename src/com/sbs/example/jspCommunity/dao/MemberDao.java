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

}
