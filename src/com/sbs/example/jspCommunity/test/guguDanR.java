package com.sbs.example.jspCommunity.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/99dan")
public class guguDanR extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 입력된 데이터의 문자셋은 UTF-8 이다.
		request.setCharacterEncoding("UTF-8");
		// 출력될 문서는 html(UTF-8) 이다.
		response.setContentType("text/html; charset=UTF-8");
		
		int dan = request.getParameter("dan") != null ? Integer.parseInt(request.getParameter("dan")) : 1;
		int limit = request.getParameter("limit") != null ? Integer.parseInt(request.getParameter("limit")) : 1;
	
		
		for(int i = 1; i <= limit; i++) {
			response.getWriter().append(dan + " * " + i + "<br>");
		}
		
		
	}


}
