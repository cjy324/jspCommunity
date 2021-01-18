package com.sbs.example.jspCommunity.service;

import java.util.List;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dao.MemberDao;
import com.sbs.example.jspCommunity.dto.Member;

public class MemberService {

	private MemberDao memberDao;

	public MemberService() {
		memberDao = Container.memberDao;
	}

	public List<Member> getMemberListForPrint() {
		return memberDao.getMemberListForPrint();

	}

	public int join(String loginId, String loginPw, String name, String nickname, String email, String cellPhoneNo) {
		return memberDao.join(loginId, loginPw, name, nickname, email, cellPhoneNo);
	}

}
