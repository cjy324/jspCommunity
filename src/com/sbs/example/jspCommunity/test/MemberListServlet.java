package com.sbs.example.jspCommunity.test;

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

@WebServlet("/jsp/usr/member/list")
public class MemberListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//DB 서버 연결
		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");

		
		List<Map<String, Object>> memberMapList = MysqlUtil.selectRows(new SecSql().append("SELECT * FROM member ORDER BY id DESC"));
		
		
		//DB 서버 연결 종료
		MysqlUtil.closeConnection();
		
		request.setAttribute("memberMapList", memberMapList);
		
		request.getRequestDispatcher("/jsp/usr/member/list.jsp").forward(request, response);

	}
}