package com.sbs.example.jspCommunity.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/99dan/1")
public class guguDanA extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().append("1 * 1 = 1").append("<br>").append("1 * 2 = 2").append("<br>").append("1 * 3 = 3").append("<br>").append("1 * 4 = 4");
	}

}
