package com.cjy.jspCommunity.container;

import com.cjy.jspCommunity.controller.AdmMemberController;
import com.cjy.jspCommunity.controller.UsrArticleController;
import com.cjy.jspCommunity.controller.UsrHomeController;
import com.cjy.jspCommunity.controller.UsrLikeController;
import com.cjy.jspCommunity.controller.UsrMemberController;
import com.cjy.jspCommunity.dao.ArticleDao;
import com.cjy.jspCommunity.dao.AttrDao;
import com.cjy.jspCommunity.dao.LikeDao;
import com.cjy.jspCommunity.dao.MemberDao;
import com.cjy.jspCommunity.service.ArticleService;
import com.cjy.jspCommunity.service.AttrService;
import com.cjy.jspCommunity.service.EmailService;
import com.cjy.jspCommunity.service.KakaoService;
import com.cjy.jspCommunity.service.LikeService;
import com.cjy.jspCommunity.service.MemberService;

public class Container {

	public static ArticleService articleService;
	public static ArticleDao articleDao;
	public static UsrMemberController userMembercontroller;
	public static AdmMemberController admMembercontroller;
	public static UsrArticleController articleController;
	public static MemberService memberService;
	public static KakaoService kakaoService;
	public static MemberDao memberDao;
	public static UsrHomeController homeController;
	public static EmailService emailService;
	public static AttrDao attrDao;
	public static AttrService attrService;
	public static LikeDao likeDao;
	public static LikeService likeService;
	public static UsrLikeController usrLikeController;
	
	

	static {
		
		attrDao = new AttrDao();
		likeDao = new LikeDao();
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		
		attrService = new AttrService();
		likeService = new LikeService();
		emailService = new EmailService();
		memberService = new MemberService();
		kakaoService = new KakaoService();
		articleService = new ArticleService();
		
		usrLikeController = new UsrLikeController();
		admMembercontroller = new AdmMemberController();
		userMembercontroller = new UsrMemberController();
		articleController = new UsrArticleController();
		homeController = new UsrHomeController();
	}
}
