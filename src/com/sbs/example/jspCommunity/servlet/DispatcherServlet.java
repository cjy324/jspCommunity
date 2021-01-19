package com.sbs.example.jspCommunity.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.mysqlutil.MysqlUtil;

//각 서블릿의 중복되는 코드를 템플릿메서드 패턴으로 중복제거
public abstract class DispatcherServlet extends HttpServlet {

	// doGet 메서드 호출
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	// doPost 메서드 호출
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}
	
	public void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// (1) request와 response들어오면 1차적으로 실행
		Map<String, Object> doBeforeActionRs = doBeforeAction(request, response);

		if (doBeforeActionRs == null) {
			return;
		}

		// (2) doBeforeActionRs의 결과로 도출된 controllerName, actionMethodName 가져와 usr, adm 서블릿으로 전송
		// usr, adm 서블릿에서 각 컨트롤들이 요청 수행후 jspPath 리턴
		String jspPath = doAction(request, response, (String) doBeforeActionRs.get("controllerName"),
				(String) doBeforeActionRs.get("actionMethodName"));
		
		if(jspPath == null) {
			return;
		}

		// (3) (1),(2)의 결과로 도출된 jspPath를 받고 forward 수행 후 최종적으로 DB연결 종료
		doAfterAction(request, response, jspPath);
	}
	
	private Map<String, Object> doBeforeAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// 요청된 uri의 정보를 가져오기
		String requestURI = request.getRequestURI();
		// 가져온 uri의 정보를 /기준으로 쪼개기
		String[] requestUriBits = requestURI.split("/");

		// 만약, requestURIBits.length가 5보다 작으면
		// 즉, /jspCommunity/jsp/usr/article/list 와 같은 형식이 아니면 중지
		if (requestUriBits.length < 5) {
			response.getWriter().append("잘못된 요청입니다.");
			return null;
		}

		// DB 서버 연결
		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");

		String controllerName = requestUriBits[3];
		String actionMethodName = requestUriBits[4];

		Map<String, Object> rs = new HashMap<>();
		rs.put("controllerName", controllerName);
		rs.put("actionMethodName", actionMethodName);

		return rs;
	}

	protected abstract String doAction(HttpServletRequest request, HttpServletResponse response, String controllerName,
			String actionMethodName);

	private void doAfterAction(HttpServletRequest request, HttpServletResponse response, String jspPath)
			throws ServletException, IOException {
		
		// DB 서버 연결 종료
		MysqlUtil.closeConnection();
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/" + jspPath + ".jsp");

		rd.forward(request, response);

	}

}
