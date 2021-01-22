package com.sbs.example.jspCommunity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.service.MemberService;
import com.sbs.example.util.Util;

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

		Member member = memberService.getMemberByLoginId(loginId);

		// 해당 loginId가 등록된 id인지 확인
		if (member == null) {
			request.setAttribute("alertMsg", "해당 아이디는 없는 아이디입니다. 아이디를 확인하세요.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		// 해당 loginPw가 일치하는지 확인
		if (member.getLoginPw().equals(loginPw) == false) {
			request.setAttribute("alertMsg", "비밀번호가 틀렸습니다.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		// 로그인 여부를 세션에 저장
		HttpSession session = request.getSession();
		session.setAttribute("loginedMemberId", member.getId());

		// 로그인 알림창 보여주고 메인화면으로 이동
		request.setAttribute("alertMsg", member.getNickname() + ", 님 반갑습니다.");
		request.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";
	}

	// 로그아웃
	public String doLogout(HttpServletRequest request, HttpServletResponse response) {

		// 로그인 여부를 세션에서 삭제
		HttpSession session = request.getSession();
		session.removeAttribute("loginedMemberId");

		request.setAttribute("alertMsg", "로그아웃 되었습니다.");
		request.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";

	}

	// 회원가입 폼 작성 시 ajax로 중복체크
	public String getLoginIdDup(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> rs = new HashMap<>();
		
		String loginId = request.getParameter("loginId");
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		String code = null;
		String msg = null;
		
		
		if(member == null) {
			code = "S-1";   //S = success의 약자, 숫자는 유형 개념
							//1이면 일반적인 성공, 2이면 약간 문제는 있지만 성공? 이런 방식
			msg = "해당 ID는 사용이 가능합니다.";
		}
		if(member != null){
			code = "F-1";
			msg = "해당 ID는 이미 사용중입니다.";
		}
		if(loginId.trim().length() == 0){
			code = "F-2";
			msg = "해당 ID는 사용이 불가능합니다.";
		}
		
		rs.put("loginId", loginId);
		rs.put("code", code);
		rs.put("msg", msg);
		
		//rs 맵리스트를 json방식으로 생성해서 data로 보내기
		request.setAttribute("data", Util.getJsonText(rs));
		
		return "common/pure";
	}

}
