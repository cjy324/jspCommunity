package com.sbs.example.jspCommunity;

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

@WebServlet("/jsp/usr/article/doWrite")
public class doWriteArticle extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		if (request.getParameter("boardId") == null) {
			response.getWriter().append("작성할 boardId를 입력해주세요.");
			return;
		}

		int boardId = Integer.parseInt(request.getParameter("boardId"));

		if (request.getParameter("title") == null) {
			response.getWriter().append("title을 입력해주세요.");
			return;
		}

		String title = request.getParameter("title");

		if (request.getParameter("body") == null) {
			response.getWriter().append("body를 입력해주세요.");
			return;
		}

		String body = request.getParameter("body");

		// DB 서버 연결
		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");

		ArticleService articleService = Container.articleService;
		
		
		// 해당 게시판이 존재하는지 확인
		List<Article> articles = articleService.getArticlesForPrintByBoardId(boardId);

		if (articles.size() <= 0) {
			response.getWriter().append("해당 게시판이 없습니다. 게시판 번호를 확인하세요.");
			return;
		}
		
		
		// 게시물 생성
		int id = articleService.add(boardId, title, body);
		
		Article article = articleService.getArticleById(id);

		// DB 서버 연결 종료
		MysqlUtil.closeConnection();

		request.setAttribute("article", article);

		request.getRequestDispatcher("/jsp/usr/article/doWrite.jsp").forward(request, response);

	}
}