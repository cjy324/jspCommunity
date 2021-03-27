package com.cjy.jspCommunity.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cjy.jspCommunity.container.Container;
import com.cjy.jspCommunity.dto.Member;
import com.cjy.jspCommunity.service.MemberService;

public class AdmMemberController {

	private MemberService memberService;

	public AdmMemberController() {
		memberService = Container.memberService;
	}

	// 회원 리스트
	public String showList(HttpServletRequest request, HttpServletResponse response) {
		List<Member> members = memberService.getMemberListForPrint();

		request.setAttribute("members", members);

		return "adm/member/list";
	}

}
