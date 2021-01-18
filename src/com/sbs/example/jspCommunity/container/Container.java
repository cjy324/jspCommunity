package com.sbs.example.jspCommunity.container;

import com.sbs.example.jspCommunity.controller.usr.ArticleController;
import com.sbs.example.jspCommunity.controller.usr.MemberController;
import com.sbs.example.jspCommunity.dao.ArticleDao;
import com.sbs.example.jspCommunity.dao.MemberDao;
import com.sbs.example.jspCommunity.service.ArticleService;
import com.sbs.example.jspCommunity.service.MemberService;

public class Container {

	public static ArticleService articleService;
	public static ArticleDao articleDao;
	public static MemberController membercontroller;
	public static com.sbs.example.jspCommunity.controller.adm.MemberController admMembercontroller;
	public static ArticleController articleController;
	public static MemberService memberService;
	public static MemberDao memberDao;

	static {
		
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		
		memberService = new MemberService();
		articleService = new ArticleService();
		
		admMembercontroller = new com.sbs.example.jspCommunity.controller.adm.MemberController();
		membercontroller = new MemberController();
		articleController = new ArticleController();
	}
}
