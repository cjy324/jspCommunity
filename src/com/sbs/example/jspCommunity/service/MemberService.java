package com.sbs.example.jspCommunity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.App;
import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dao.MemberDao;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.util.Util;

public class MemberService {

	private MemberDao memberDao;
	private EmailService emailService; 

	public MemberService() {
		memberDao = Container.memberDao;
		emailService = Container.emailService;
	}

	public List<Member> getMemberListForPrint() {
		return memberDao.getMemberListForPrint();

	}

	public int join(Map<String, Object> joinArg) {
		return memberDao.join(joinArg);
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public void doModifyMemberName(int id, String loginId, String name, String nickname, String email,
			String cellphoneNo) {
		memberDao.doModifyMemberName(id, loginId, name, nickname, email, cellphoneNo);

	}

	public Member getMemberByNickname(String nickname) {
		return memberDao.getMemberByNickname(nickname);
	}

	public Member getMemberByNameAndEmail(String name, String email) {
		return memberDao.getMemberByNameAndEmail(name, email);
	}

	public void sendTempLoginPwToEmail(Member actor) {
		// 메일의 제목과 내용 생성
		String siteName = App.getSite();
		String siteLoginUrl = App.getLoginUrl();
		String title = "[" + siteName + "]" + actor.getName() + " 회원님, 임시 패스워드 발급";
		String tempPassword = Util.getTempPassword(6);
		System.out.println(tempPassword);
		String body = "<h1>임시 패스워드 : " + tempPassword + "</h1>";
		body += "<a href=\"" + siteLoginUrl + "\" target=\"_blank\">로그인하러 가기</a>";

		// 메일 발송
		emailService.send(actor.getEmail(), title, body);
		
		// 회원의 패스워드를 방금 생성한 임시 패스워드로 변경
		setTempPassword(actor, tempPassword);
	}

	private void setTempPassword(Member actor, String tempPassword) {
		Map<String, Object> modifyArg = new HashMap<>();
		modifyArg.put("id", actor.getId());
		modifyArg.put("loginPw", tempPassword);
		
		modify(modifyArg);
	}

	private void modify(Map<String, Object> modifyArg) {
		memberDao.modify(modifyArg);
		
	}

}
