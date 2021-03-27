package com.cjy.jspCommunity.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cjy.jspCommunity.container.Container;
import com.cjy.jspCommunity.service.ArticleService;

public class UsrHomeController {

	ArticleService articleService;

	public UsrHomeController() {
		articleService = Container.articleService;
	}

	// 홈 메인화면
	public String main(HttpServletRequest request, HttpServletResponse response) {
		return "usr/home/main";
	}

}
