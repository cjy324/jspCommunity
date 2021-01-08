package com.sbs.example.jspCommunity.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.service.MemberService;

public class MemberController {
	
	private MemberService memberService;
	
	public MemberController() {
		memberService = Container.memberService;
	}

	public String showList(HttpServletRequest request, HttpServletResponse response) {
		List<Member> members = memberService.getMemberListForPrint();
		
		request.setAttribute("members", members);
		
		return "usr/member/list";
	}

}
