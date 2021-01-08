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

@WebServlet("/jsp/usr/article/list")
public class ArticleListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		if (request.getParameter("boardId") == null) {
			response.getWriter().append("게시판 boardId를 입력해주세요.");
			return;
		}

		int boardId = Integer.parseInt(request.getParameter("boardId"));

		// DB 서버 연결
		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");

		ArticleService articleService = Container.articleService;
		List<Article> articles = articleService.getArticlesForPrintByBoardId(boardId);
		
		if(articles.size() <= 0) {
			response.getWriter().append("해당 게시판이 없습니다. 게시판 번호를 확인하세요.");
			return;
		}

		// DB 서버 연결 종료
		MysqlUtil.closeConnection();

		request.setAttribute("articles", articles);

		request.getRequestDispatcher("/jsp/usr/article/list.jsp").forward(request, response);

	}
}