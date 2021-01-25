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

	// 회원 정보 수정
	public void doModifyMemberName(int id, String loginId, String name, String nickname, String email, String cellphoneNo) {
		SecSql sql = new SecSql();

		sql.append("UPDATE member");
		sql.append("SET updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", name = ?", name);
		sql.append(", nickname = ?", nickname);
		sql.append(", email = ?", email);
		sql.append(", cellphoneNo = ?", cellphoneNo);
		sql.append("WHERE id = ?", id);
		
		MysqlUtil.update(sql);
	}

	public Member getMemberByNickname(String nickname) {
		SecSql sql = new SecSql();

		sql.append("SELECT * FROM member");
		sql.append("WHERE nickname = ?", nickname);
		
		Map<String, Object> memberMap = MysqlUtil.selectRow(sql);
		
		if(memberMap.isEmpty()) {
			return null;
		}
		
		return new Member(memberMap);
	}

	public Member getMemberByNameAndEmail(String name, String email) {
		SecSql sql = new SecSql();

		sql.append("SELECT * FROM member");
		sql.append("WHERE name = ?", name);
		sql.append("AND email = ?", email);
		sql.append("ORDER BY id DESC");
		sql.append("LIMIT 1");
		
		Map<String, Object> memberMap = MysqlUtil.selectRow(sql);
		
		if(memberMap.isEmpty()) {
			return null;
		}
		
		return new Member(memberMap);
	}

	// 회원 정보 수정
	public void modify(Map<String, Object> args) {
		SecSql sql = new SecSql();

		sql.append("UPDATE member");
		sql.append("SET updateDate = NOW()");
		
		boolean needToUpdate = false;
		
		if(args.get("loginId") != null) {
			needToUpdate = true;
			sql.append(", loginId = ?", args.get("loginId"));
		}
		
		if(args.get("loginPw") != null) {
			needToUpdate = true;
			sql.append(", loginPw = ?", args.get("loginPw"));
		}
		
		if(args.get("name") != null) {
			needToUpdate = true;
			sql.append(", name = ?", args.get("name"));
		}
		
		if(args.get("nickname") != null) {
			needToUpdate = true;
			sql.append(", nickname = ?", args.get("nickname"));
		}
		
		if(args.get("email") != null) {
			needToUpdate = true;
			sql.append(", email = ?", args.get("email"));
		}
		
		if(args.get("cellphoneNo") != null) {
			needToUpdate = true;
			sql.append(", cellphoneNo = ?", args.get("cellphoneNo"));
		}
		
		if(args.get("authLevel") != null) {
			needToUpdate = true;
			sql.append(", authLevel = ?", args.get("authLevel"));
		}
		
		if(needToUpdate == false) {
			return;
		}

		sql.append("WHERE id = ?", args.get("id"));
		
		MysqlUtil.update(sql);
		
	}


}
