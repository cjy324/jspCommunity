package com.sbs.example.jspCommunity2;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

@WebServlet("/jsp/usr/article2/detail2")
public class ArticleDetail2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		int id = 1;

		if (request.getParameter("id") == null) {
			response.getWriter().append("게시물 id를 입력해주세요.");
			return;
		}
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}

		// DB 서버 연결
		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");
		
		
		

		List<Map<String, Object>> articleMapList = MysqlUtil
				.selectRows(new SecSql().append("SELECT * FROM article WHERE id = ?", id));

		// DB 서버 연결 종료
		MysqlUtil.closeConnection();

		request.setAttribute("articleMapList", articleMapList);

		request.getRequestDispatcher("/jsp/usr/article2/detail2.jsp").forward(request, response);

	}
}