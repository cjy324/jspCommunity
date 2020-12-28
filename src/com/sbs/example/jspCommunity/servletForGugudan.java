package com.sbs.example.jspCommunity;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servletForGugudan")
public class servletForGugudan extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int dan = 1;

		if (request.getParameter("dan") != null) {
			dan = Integer.parseInt(request.getParameter("dan"));
		}

		int limit = 9;

		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		
		String result = "";
		
		for(int i = 1; i <= limit; i++) {
			result += "<div>" + dan + " * " + i + " = " + (dan*i) + "</div>";
		}
		request.setAttribute("dan", dan); //dan 변수 저장소에 dan 요청값을 세팅한다.
		request.setAttribute("result", result); //result 변수 저장소에 result 요청값을 세팅한다. 
		RequestDispatcher rd = request.getRequestDispatcher("jsp/usr/home/JSPForGugudan.jsp");
		//들어온 요청 정보를 RequestDispatcher가 저장하고 servletAndJSP로 위임한다.
		
        rd.forward(request, response); //servletAndJSP에게 요청값에 대한 응답값을 보내준다. 

	}

}
