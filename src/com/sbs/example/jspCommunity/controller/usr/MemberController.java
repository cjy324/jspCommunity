package com.sbs.example.jspCommunity.controller.usr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.service.MemberService;

public class MemberController {

	private MemberService memberService;

	public MemberController() {
		memberService = Container.memberService;
	}
	
	// 회원 리스트(임시)
	public String showList(HttpServletRequest request, HttpServletResponse response) {
		List<Member> members = memberService.getMemberListForPrint();

		request.setAttribute("members", members);

		return "adm/member/list";
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
		if(loginId == null || loginPw == null || name == null || nickname == null || email == null || cellPhoneNo == null) {
			request.setAttribute("alertMsg", "모든 정보를 입력하세요.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		// 해당 loginId가 사용가능한지 중복확인
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

		// 신규 회원가입
		int id = memberService.join(loginId, loginPw, name, nickname, email, cellPhoneNo);

		// 생성 알림창 보여주고 회원정보로 이동하기
		request.setAttribute("alertMsg", id + "번 회원님 반갑습니다.");
		request.setAttribute("replaceUrl", String.format("list"));
		return "common/redirect";

	}
	
	//로그인 폼
	public String doLoginForm(HttpServletRequest request, HttpServletResponse response) {
		return "usr/member/doLoginForm";
	}
	
	//로그인
	public String doLogin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
