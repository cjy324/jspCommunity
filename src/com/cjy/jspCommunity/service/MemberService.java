package com.cjy.jspCommunity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cjy.jspCommunity.App;
import com.cjy.jspCommunity.container.Container;
import com.cjy.jspCommunity.dao.MemberDao;
import com.cjy.jspCommunity.dto.Member;
import com.cjy.jspCommunity.dto.ResultData;
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

	// 임시패스워드 발급
	public ResultData sendTempLoginPwToEmail(Member actor) {
		// 메일의 제목과 내용 생성
		String siteName = App.getSiteName();
		String siteLoginUrl = App.getLoginUrl();
		String title = "[" + siteName + "]" + actor.getName() + " 회원님, 임시 패스워드 발급";
		String tempPassword = Util.getTempPassword(6);
		String body = "<h1>임시 패스워드 : " + tempPassword + "</h1>";
		body += "<a href=\"" + siteLoginUrl + "\" target=\"_blank\">로그인하러 가기</a>";

		// 메일 발송 결과를 int값으로 받음
		int sendRs = emailService.send(actor.getEmail(), title, body);

		if (sendRs != 1) {
			return new ResultData("F-1", "메일 발송에 실패했습니다.");
		}

		// 발급받은 임시패스워드로 회원 정보 업데이트
		setTempPassword(actor, tempPassword);

		String resultMsg = "회원님의 임시 비밀번호가 \"" + actor.getEmail() + "\"로 발송되었습니다.";
		return new ResultData("S-1", resultMsg, "email", actor.getEmail());

	}

	private void setTempPassword(Member actor, String tempPassword) {
		Map<String, Object> modifyArg = new HashMap<>();
		modifyArg.put("id", actor.getId());
		modifyArg.put("loginPw", Util.sha256(tempPassword));

		// 임시패스워드 발급 정보 저장
		setIsUsingTempPassword(actor.getId(), true);

		// 회원정보 수정
		modify(modifyArg);
	}

	public void setIsUsingTempPassword(int actorId, boolean use) {
		attrService.setValue("member__" + actorId + "__extra__isUsingTempPassword", use, null);

	}

	public boolean getIsUsingTempPassword(int actorId) {
		return attrService.getValueAsBoolean("member__" + actorId + "__extra__isUsingTempPassword");

	}

	public void modify(Map<String, Object> modifyArg) {
		memberDao.modify(modifyArg);

	}

	public Member getMemberByOnLoginProviderMemberId(String loginProviderTypeCode, String onLoginProviderMemberId) {
		return memberDao.getMemberByOnLoginProviderMemberId(loginProviderTypeCode, onLoginProviderMemberId);
	}

	public int joinByKakao(Map<String, Object> kakaoUser) {

		String loginProviderTypeCode = (String) kakaoUser.get("loginProviderTypeCode");
		String onLoginProviderMemberId = (String) kakaoUser.get("onLoginProviderMemberId");

		String loginId = loginProviderTypeCode + "___" + onLoginProviderMemberId;

		kakaoUser.put("loginId", loginId);  //ex) kakaoRest___카카오로그인 시 발급된 고유 ID
		kakaoUser.put("loginPw", Util.getUUIDStr()); //암호 랜덤 생성
		//알 수 있는 기타정보는 카카오 유저정보로 입력
		kakaoUser.put("name", kakaoUser.get("nickname"));
		kakaoUser.put("cellPhoneNo", "연락처 미등록");
		if(kakaoUser.get("email") == null) {
			kakaoUser.put("email", "이메일 미등록");
		}
		
		return memberDao.join(kakaoUser);
	}

	public void updateToken(int memberId, String token_name, String token, String token_expires_in) {
		attrService.setValue("member", memberId, "extra", token_name, token, token_expires_in);
	}

}
