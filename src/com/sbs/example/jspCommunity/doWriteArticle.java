package com.sbs.example.jspCommunity;

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


@WebServlet("/jsp/usr/article/doWrite")
public class doWriteArticle extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String title = "제목";

		if (request.getParameter("title") == null) {
			response.getWriter().append("title을 입력해주세요.");
			return;
		}
		
		if (request.getParameter("title") != null) { 
			title = request.getParameter("title"); 
		}
		 
		
		String body = "내용";
		
		if (request.getParameter("body") == null) {
			response.getWriter().append("body를 입력해주세요.");
			return;
		}
		
		if (request.getParameter("body") != null) { 
			body = request.getParameter("body"); 
		}

		
		
		//DB 서버 연결
		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");
		
		SecSql sql = new SecSql();
		sql.append("INSERT INTO article ");
		sql.append("SET regDate = NOW(), ");
		sql.append("updateDate = NOW(), ");
		sql.append("memberId = 3, ");
		sql.append("boardId = 3, ");
		sql.append("title = ? ,", title);
		sql.append("`body` = ?", body);

		MysqlUtil.insert(sql);
		
		List<Map<String, Object>> articleMapList = MysqlUtil.selectRows(new SecSql().append("SELECT * FROM article WHERE title = ?", title));
		
		//DB 서버 연결 종료
		MysqlUtil.closeConnection();
		
		request.setAttribute("articleMapList", articleMapList);
		
		request.getRequestDispatcher("/jsp/usr/article/doWrite.jsp").forward(request, response);

	}
}