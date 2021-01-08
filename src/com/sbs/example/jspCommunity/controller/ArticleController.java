package com.sbs.example.jspCommunity.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.service.ArticleService;
import com.sbs.example.mysqlutil.MysqlUtil;

public class ArticleController {
	
	ArticleService articleService;
	
	public ArticleController() {
		articleService = Container.articleService;
	}

	public String showList(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 * if (request.getParameter("boardId") == null) {
		 * response.getWriter().append("게시판 boardId를 입력해주세요."); continue; }
		 */
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		
		List<Article> articles = articleService.getArticlesForPrintByBoardId(boardId);
		
		/*
		 * if(articles.size() <= 0) {
		 * response.getWriter().append("해당 게시판이 없습니다. 게시판 번호를 확인하세요."); continue; }
		 */
		request.setAttribute("articles", articles);

		return "usr/article/list";
	}

	public String showDetail(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * if (request.getParameter("id") == null) {
		 * response.getWriter().append("게시물 id를 입력해주세요."); return; }
		 */
		
		int id = Integer.parseInt(request.getParameter("id"));

		Article article = articleService.getArticleById(id);
		
		/*
		 * if(article == null) {
		 * response.getWriter().append("해당 게시물이 없습니다. 게시물 번호를 확인하세요."); return; }
		 */
		
		request.setAttribute("article", article);
		
		return "usr/article/detail";
	}

	public String doWrite(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * if (request.getParameter("boardId") == null) {
		 * response.getWriter().append("작성할 boardId를 입력해주세요."); return; }
		 */

		int boardId = Integer.parseInt(request.getParameter("boardId"));

		/*
		 * if (request.getParameter("title") == null) {
		 * response.getWriter().append("title을 입력해주세요."); return; }
		 */

		String title = request.getParameter("title");

		/*
		 * if (request.getParameter("body") == null) {
		 * response.getWriter().append("body를 입력해주세요."); return; }
		 */

		String body = request.getParameter("body");

		
		/*
		 * // 해당 게시판이 존재하는지 확인 List<Article> articles =
		 * articleService.getArticlesForPrintByBoardId(boardId);
		 * 
		 * if (articles.size() <= 0) {
		 * response.getWriter().append("해당 게시판이 없습니다. 게시판 번호를 확인하세요."); return; }
		 */
		
		
		// 게시물 생성
		int id = articleService.add(boardId, title, body);
		
		Article article = articleService.getArticleById(id);


		request.setAttribute("article", article);
		
		return "usr/article/doWrite";
	}

	public String doModify(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * if (request.getParameter("id") == null) {
		 * response.getWriter().append("수정할 게시물 id를 입력해주세요."); return; }
		 */

		int id = Integer.parseInt(request.getParameter("id"));

		/*
		 * if (request.getParameter("title") == null) {
		 * response.getWriter().append("수정할 title을 입력해주세요."); return; }
		 */
		String title = request.getParameter("title");

		/*
		 * if (request.getParameter("body") == null) {
		 * response.getWriter().append("수정할 body를 입력해주세요."); return; }
		 */

		String body = request.getParameter("body");


		/*
		 * //해당 게시물 존재하는지 확인 Article article = articleService.getArticleById(id);
		 * 
		 * if(article == null) {
		 * response.getWriter().append("해당 게시물이 없습니다. 게시물 번호를 확인하세요."); return; }
		 */
		
		// 게시물 수정
		articleService.articleModify(id, title, body);
		
		// 수정된 해당 게시물 정보 다시 불러오기
		Article article = articleService.getArticleById(id);

		request.setAttribute("article", article);
		
		return "usr/article/doModify";
	}

	public String doDelete(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * if (request.getParameter("id") == null) {
		 * response.getWriter().append("삭제할 게시물 id를 입력해주세요."); return; }
		 */

		int id = Integer.parseInt(request.getParameter("id"));
		
		/*
		 * //해당 게시물 존재하는지 확인 Article article = articleService.getArticleById(id);
		 * 
		 * if(article == null) {
		 * response.getWriter().append("해당 게시물이 없습니다. 게시물 번호를 확인하세요."); return; }
		 */
		
		// 게시물 삭제
		articleService.articleDelete(id);
		
		request.setAttribute("id", id);
		
		return "usr/article/doDelete";
	}

}
