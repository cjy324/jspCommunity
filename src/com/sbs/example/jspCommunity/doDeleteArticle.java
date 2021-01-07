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

@WebServlet("/jsp/usr/article/doDelete")
public class doDeleteArticle extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int id = 0;
		
		if (request.getParameter("id") == null) {
			response.getWriter().append("삭제하실 게시물 id를 입력해주세요.");
			return;
		}
		if (request.getParameter("id") != null) { 
			id = Integer.parseInt(request.getParameter("id")); 
		}
		
		
		//DB 서버 연결
		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");
		
		SecSql sql = new SecSql();
		sql.append("DELETE from article ");
		sql.append("WHERE id = ? ", id);

		MysqlUtil.delete(sql);
		
		
		//DB 서버 연결 종료
		MysqlUtil.closeConnection();
		
		request.setAttribute("id", id);
		
		request.getRequestDispatcher("/jsp/usr/article/doDelete.jsp").forward(request, response);

	}
}