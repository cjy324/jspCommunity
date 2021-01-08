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

@WebServlet("/jsp/usr/article2/doModify2")
public class doModifyArticle2 extends HttpServlet {
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
		
		
		String title = "제목";
		
		if (request.getParameter("title") == null) {
			response.getWriter().append("수정할 title을 입력해주세요.");
			return;
		}
		if (request.getParameter("title") != null) { 
			title = request.getParameter("title"); 
		}
		 
		
		String body = "내용";
		
		if (request.getParameter("body") == null) {
			response.getWriter().append("수정할 body를 입력해주세요.");
			return;
		}
		if (request.getParameter("body") != null) { 
			body = request.getParameter("body"); 
		}

		
		
		//DB 서버 연결
		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");
		
		SecSql sql = new SecSql();
		sql.append("UPDATE article ");
		sql.append("SET ");
		sql.append("updateDate = NOW(), ");
		sql.append("title = ? , ", title);
		sql.append("`body` = ? ", body);
		sql.append("WHERE id = ? ", id);

		MysqlUtil.update(sql);
		
		List<Map<String, Object>> articleMapList = MysqlUtil.selectRows(new SecSql().append("SELECT * FROM article WHERE id = ?", id));
		
		//DB 서버 연결 종료
		MysqlUtil.closeConnection();
		
		request.setAttribute("articleMapList", articleMapList);
		
		request.getRequestDispatcher("/jsp/usr/article2/doModify2.jsp").forward(request, response);

	}
}