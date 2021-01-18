package com.sbs.example.jspCommunity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.service.MemberService;

public class UsrMemberController {

	private MemberService memberService;

	public UsrMemberController() {
		memberService = Container.memberService;
	}

	// 회원가입 폼
	public String doJoinForm(HttpServletRequest request, HttpServletResponse response) {
		return "usr/member/doJoinForm";
	}

	// 회원가입
	public String doJoin(HttpServletRequest request, HttpServletResponse response) {

		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String cellPhoneNo = request.getParameter("cellPhoneNo");

		// 정보가 하나라도 입력안되면 리턴
		if (loginId == null || loginPw == null || name == null || nickname == null || email == null
				|| cellPhoneNo == null) {
			request.setAttribute("alertMsg", "모든 정보를 입력하세요.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		List<Member> members = memberService.getMemberListForPrint();

		// 해당 loginId가 사용가능한지 중복확인
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getLoginId().equals(loginId) == true) {
				request.setAttribute("alertMsg", "해당 아이디는 이미 사용중인 아이디입니다.");
				request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
				return "common/redirect";
			}
		}

		// 해당 nickname이 사용가능한지 중복확인
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getNickname().equals(nickname) == true) {
				request.setAttribute("alertMsg", "해당 닉네임는 이미 사용중인 닉네임입니다.");
				request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
				return "common/redirect";
			}
		}

		Map<String, Object> joinArg = new HashMap<>();
		joinArg.put("loginId", loginId);
		joinArg.put("loginPw", loginPw);
		joinArg.put("name", name);
		joinArg.put("nickname", nickname);
		joinArg.put("email", email);
		joinArg.put("cellPhoneNo", cellPhoneNo);
		
		// 신규 회원가입
		int id = memberService.join(joinArg);

		// 생성 알림창 보여주고 회원정보로 이동하기
		request.setAttribute("alertMsg", id + "번 회원님 반갑습니다.");
		
		Member member = memberService.getMemberById(id);
		
		request.setAttribute("member", member);

		return "usr/member/doJoin";

	}

	// 로그인 폼
	public String doLoginForm(HttpServletRequest request, HttpServletResponse response) {
		return "usr/member/doLoginForm";
	}

	// 로그인
	public String doLogin(HttpServletRequest request, HttpServletResponse response) {
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");

		// 정보가 하나라도 입력안되면 리턴
		if (loginId == null || loginPw == null) {
			request.setAttribute("alertMsg", "모든 정보를 입력하세요.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		List<Member> members = memberService.getMemberListForPrint();

		// 해당 loginId가 등록된 id인지 확인
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getLoginId().equals(loginId) == false) {
				request.setAttribute("alertMsg", "해당 아이디는 없는 아이디입니다. 아이디를 확인하세요.");
				request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
				return "common/redirect";
			}
		}

		// 해당 loginPw가 일치하는지 확인
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getLoginPw().equals(loginPw) == false) {
				request.setAttribute("alertMsg", "비밀번호가 틀렸습니다.");
				request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
				return "common/redirect";
			}
		}

		// 로그인 알림창 보여주고 리스트로 이동
		request.setAttribute("alertMsg", loginId + " 회원님 반갑습니다.");

		request.setAttribute("replaceUrl", String.format("list"));
		return "common/redirect";

	}

}
