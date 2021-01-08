package com.sbs.example.jspCommunity.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.controller.ArticleController;
import com.sbs.example.jspCommunity.controller.MemberController;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.service.ArticleService;
import com.sbs.example.mysqlutil.MysqlUtil;

@WebServlet("/usr/*")
public class DispatcherServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 요청된 uri의 정보를 가져오기
		String requestURI = request.getRequestURI();
		// 가져온 uri의 정보를 /기준으로 쪼개기
		String[] requestUriBits = requestURI.split("/");
		
		//만약, requestURIBits.length가 5보다 작으면
		//즉, /jspCommunity/jsp/usr/article/list 와 같은 형식이 아니면 중지
		if(requestUriBits.length < 5) {
			response.getWriter().append("잘못된 요청입니다.");
			return;
		}
		
		
		String controllerName = requestUriBits[3];
		String actionMethodName = requestUriBits[4];
		String jspPath = null;
		
		
		// DB 서버 연결
		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");
		
		if(controllerName.equals("member")) {
			MemberController membercontroller = Container.membercontroller;
			
			if(actionMethodName.equals("list")) {
				jspPath = membercontroller.showList(request, response);
			}
		}
		
		if(controllerName.equals("article")) {
			ArticleController articleController = Container.articleController;
			
			if(actionMethodName.equals("list")) {
				jspPath = articleController.showList(request, response);
			}
			if(actionMethodName.equals("detail")) {
				jspPath = articleController.showDetail(request, response);
			}
			if(actionMethodName.equals("doWrite")) {
				jspPath = articleController.doWrite(request, response);
			}
			if(actionMethodName.equals("doModify")) {
				jspPath = articleController.doModify(request, response);
			}
			if(actionMethodName.equals("doDelete")) {
				jspPath = articleController.doDelete(request, response);
			}
		}
	
	
		// DB 서버 연결 종료
		MysqlUtil.closeConnection();

		request.getRequestDispatcher("/jsp/" + jspPath + ".jsp").forward(request, response);

	}
}