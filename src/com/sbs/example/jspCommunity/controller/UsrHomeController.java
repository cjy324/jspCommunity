package com.sbs.example.jspCommunity.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.service.ArticleService;

public class UsrHomeController {

	ArticleService articleService;

	public UsrHomeController() {
		articleService = Container.articleService;
	}

	// 홈 메인화면
	public String main(HttpServletRequest request, HttpServletResponse response) {

		// 공지사항 게시물 리스트 가져오기
		int boardId1 = 1;
		List<Article> articles1 = articleService.getArticlesForPrintByBoardId(boardId1);
		request.setAttribute("articles1", articles1);

		// 게시판2 게시물 리스트 가져오기
		int boardId2 = 2;
		List<Article> articles2 = articleService.getArticlesForPrintByBoardId(boardId2);
		request.setAttribute("articles2", articles2);

		// 게시판3 게시물 리스트 가져오기
		int boardId3 = 3;
		List<Article> articles3 = articleService.getArticlesForPrintByBoardId(boardId3);
		request.setAttribute("articles3", articles3);

		return "usr/home/main";
	}

}
