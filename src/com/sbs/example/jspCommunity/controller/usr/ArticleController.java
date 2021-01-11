package com.sbs.example.jspCommunity.controller.usr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.service.ArticleService;

public class ArticleController {

	ArticleService articleService;

	public ArticleController() {
		articleService = Container.articleService;
	}

	//리스트 가져오기
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

	// 게시물 등록 폼
	public String doWriteForm(HttpServletRequest request, HttpServletResponse response) {

		int boardId = Integer.parseInt(request.getParameter("boardId"));

		int memberId = Integer.parseInt(request.getParameter("memberId"));

		request.setAttribute("boardId", boardId);
		request.setAttribute("memberId", memberId);

		return "usr/article/doWriteForm";
	}

	// 게시물 등록
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

	// 게시물 수정 폼
	public String doModifyForm(HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("id"));

		int boardId = Integer.parseInt(request.getParameter("boardId"));

		int memberId = Integer.parseInt(request.getParameter("memberId"));
		
		
		request.setAttribute("id", id);
		request.setAttribute("boardId", boardId);
		request.setAttribute("memberId", memberId);
		

		return "usr/article/doModifyForm";
	}

	// 게시물 수정
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
