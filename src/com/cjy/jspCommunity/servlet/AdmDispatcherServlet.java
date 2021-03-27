package com.cjy.jspCommunity.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cjy.jspCommunity.container.Container;
import com.cjy.jspCommunity.controller.AdmMemberController;

@WebServlet("/adm/*")
public class AdmDispatcherServlet extends DispatcherServlet {

	// (2) doBeforeActionRs의 결과로 도출된 controllerName, actionMethodName 가져와 usr, adm
	// 서블릿으로 전송
	// usr, adm 서블릿에서 각 컨트롤들이 요청 수행후 jspPath 리턴
	protected String doAction(HttpServletRequest request, HttpServletResponse response, String controllerName,
			String actionMethodName) {

		String jspPath = null;

		if (controllerName.equals("member")) {
			AdmMemberController membercontroller = Container.admMembercontroller;

			if (actionMethodName.equals("list")) {
				jspPath = membercontroller.showList(request, response);
			}

		}

		return jspPath;

	}

}