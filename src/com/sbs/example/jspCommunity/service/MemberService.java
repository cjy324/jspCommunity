package com.sbs.example.jspCommunity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.App;
import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dao.MemberDao;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.dto.ResultData;
import com.sbs.example.util.Util;

public class MemberService {

	private MemberDao memberDao;
	private EmailService emailService;
	private AttrService attrService;

	public MemberService() {
		memberDao = Container.memberDao;
		emailService = Container.emailService;
		attrService = Container.attrService;
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

	public Member getMemberByNickname(String nickname) {
		return memberDao.getMemberByNickname(nickname);
	}

	public Member getMemberByNameAndEmail(String name, String email) {
		return memberDao.getMemberByNameAndEmail(name, email);
	}

	public ResultData sendTempLoginPwToEmail(Member actor) {
		// 메일의 제목과 내용 생성
		String siteName = App.getSite();
		String siteLoginUrl = App.getLoginUrl();
		String title = "[" + siteName + "]" + actor.getName() + " 회원님, 임시 패스워드 발급";
		String tempPassword = Util.getTempPassword(6);
		String body = "<h1>임시 패스워드 : " + tempPassword + "</h1>";
		body += "<a href=\"" + siteLoginUrl + "\" target=\"_blank\">로그인하러 가기</a>";

		// 메일 발송(결과를 알 수 없음)
		// emailService.send(actor.getEmail(), title, body);

		// 메일 발송 결과를 int값으로 받음(개선)
		int sendRs = emailService.send(actor.getEmail(), title, body);

		Map<String, Object> rs = new HashMap<>();

		/*  //ResultData객체 도입으로 삭제
		 * if (sendRs == 1) { // 발송 성공인 경우 rs.put("resultCode", "S-1");
		 * rs.put("resultMsg", "회원님의 임시 비밀번호가 \"" + actor.getEmail() + "\"로 발송되었습니다.");
		 * 
		 * // 회원의 패스워드를 방금 생성한 임시 패스워드로 변경 setTempPassword(actor, tempPassword); } else
		 * { // 발송 실패인 경우 rs.put("resultCode", "F-1"); rs.put("resultMsg",
		 * "메일 발송에 실패했습니다.");
		 * 
		 * }
		 * 
		 * 
		 * return rs;
		 */

		
		if(sendRs != 1) {
			return new ResultData("F-1", "메일 발송에 실패했습니다.");
		}
		
		setTempPassword(actor, tempPassword);
		String resultMsg = "회원님의 임시 비밀번호가 \"" + actor.getEmail() + "\"로 발송되었습니다.";
		return new ResultData("S-1", resultMsg, "email", actor.getEmail());
		
	}

	private void setTempPassword(Member actor, String tempPassword) {
		Map<String, Object> modifyArg = new HashMap<>();
		modifyArg.put("id", actor.getId());
		modifyArg.put("loginPw", Util.sha256(tempPassword));

		attrService.setValue("member__" + actor.getId() + "__extra__isUsingTempPassword", "1", null);
		
		modify(modifyArg);
	}

	public void modify(Map<String, Object> modifyArg) {
		memberDao.modify(modifyArg);

	}

}
