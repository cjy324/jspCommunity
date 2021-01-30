package com.sbs.example.jspCommunity.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.service.ArticleService;
import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

@WebServlet("/jsp/usr/article/doModify")
public class doModifyArticle extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		if (request.getParameter("id") == null) {
			response.getWriter().append("수정할 게시물 id를 입력해주세요.");
			return;
		}

		int id = Integer.parseInt(request.getParameter("id"));

		if (request.getParameter("title") == null) {
			response.getWriter().append("수정할 title을 입력해주세요.");
			return;
		}

		String title = request.getParameter("title");

		if (request.getParameter("body") == null) {
			response.getWriter().append("수정할 body를 입력해주세요.");
			return;
		}

		String body = request.getParameter("body");

		// DB 서버 연결
		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");
		
		ArticleService articleService = Container.articleService;

		//해당 게시물 존재하는지 확인
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			response.getWriter().append("해당 게시물이 없습니다. 게시물 번호를 확인하세요.");
			return;
		}
		
		// 게시물 수정
		//articleService.articleModify(id, title, body);
		
		// 수정된 해당 게시물 정보 다시 불러오기
		article = articleService.getArticleById(id);

		
		// DB 서버 연결 종료
		MysqlUtil.closeConnection();

		request.setAttribute("article", article);

		request.getRequestDispatcher("/jsp/usr/article/doModify.jsp").forward(request, response);

	}
}