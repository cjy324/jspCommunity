package com.sbs.example.jspCommunity.container;

import com.sbs.example.jspCommunity.controller.AdmMemberController;
import com.sbs.example.jspCommunity.controller.UsrArticleController;
import com.sbs.example.jspCommunity.controller.UsrHomeController;
import com.sbs.example.jspCommunity.controller.UsrMemberController;
import com.sbs.example.jspCommunity.dao.ArticleDao;
import com.sbs.example.jspCommunity.dao.AttrDao;
import com.sbs.example.jspCommunity.dao.MemberDao;
import com.sbs.example.jspCommunity.service.ArticleService;
import com.sbs.example.jspCommunity.service.AttrService;
import com.sbs.example.jspCommunity.service.EmailService;
import com.sbs.example.jspCommunity.service.MemberService;

public class Container {

	public static ArticleService articleService;
	public static ArticleDao articleDao;
	public static UsrMemberController userMembercontroller;
	public static AdmMemberController admMembercontroller;
	public static UsrArticleController articleController;
	public static MemberService memberService;
	public static MemberDao memberDao;
	public static UsrHomeController homeController;
	public static EmailService emailService;
	public static AttrDao attrDao;
	public static AttrService attrService;

	static {
		
		attrDao = new AttrDao();
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		
		attrService = new AttrService();
		emailService = new EmailService();
		memberService = new MemberService();
		articleService = new ArticleService();
		
		homeController = new UsrHomeController();
		admMembercontroller = new AdmMemberController();
		userMembercontroller = new UsrMemberController();
		articleController = new UsrArticleController();
	}
}
