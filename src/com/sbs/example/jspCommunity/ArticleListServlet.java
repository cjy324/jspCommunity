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


@WebServlet("/jsp/usr/article/list")
public class ArticleListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String code = "notice";
		
		if (request.getParameter("code") == null) {
			response.getWriter().append("게시판 code를 입력해주세요.");
			return;
		}
		if (request.getParameter("code") != null) { code =
		request.getParameter("code"); }
		 
		
		//DB 서버 연결
		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");
				
		SecSql sql1 = new SecSql();
		sql1.append("SELECT * FROM board ");
		sql1.append("WHERE code = ?", code);
				
		Map<String, Object> boardMap = MysqlUtil.selectRow(sql1);
		if (boardMap.isEmpty()) {
			response.getWriter().append("code를 확인해 주세요.");
			return;
		}
		
		
		int boardId = (int) boardMap.get("id");
		String boardName = (String) boardMap.get("name");
		String boardCode = (String) boardMap.get("code");
		
		SecSql sql2 = new SecSql();
		sql2.append("SELECT * FROM article ");
		sql2.append("WHERE boardId = ?", boardId);
		
		List<Map<String, Object>> articleMapList = MysqlUtil.selectRows(sql2);
		
		
		
		//DB 서버 연결 종료
		MysqlUtil.closeConnection();
		
		request.setAttribute("articleMapList", articleMapList);
		request.setAttribute("boardId", boardId);
		request.setAttribute("boardName", boardName);
		request.setAttribute("boardCode", boardCode);
		
		request.getRequestDispatcher("/jsp/usr/article/list.jsp").forward(request, response);

	}
}