package com.sbs.example.jspCommunity.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.jspCommunity.App;
import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.util.Util;

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

	public void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// (1) request와 response들어오면 1차적으로 실행
		Map<String, Object> doBeforeActionRs = doBeforeAction(request, response);

		if (doBeforeActionRs == null) {
			return;
		}

		// (2) doBeforeActionRs의 결과로 도출된 controllerName, actionMethodName 가져와 usr, adm
		// 서블릿으로 전송
		// usr, adm 서블릿에서 각 컨트롤들이 요청 수행후 jspPath 리턴
		String jspPath = doAction(request, response, (String) doBeforeActionRs.get("controllerName"),
				(String) doBeforeActionRs.get("actionMethodName"));

		if (jspPath == null) {
			response.getWriter().append("jsp 정보가 없습니다.");
			return;
		}

		// (3) (1),(2)의 결과로 도출된 jspPath를 받고 forward 수행 후 최종적으로 DB연결 종료
		doAfterAction(request, response, jspPath);
	}

	// 인터셉터
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
		int minBitsCount = 5;

		if (App.isProductMode()) {
			minBitsCount = 4;
		}

		if (requestUriBits.length < minBitsCount) {
			response.getWriter().append("잘못된 요청입니다.");
			return null;
		}

		
		// 환경별 DB접속정보 분기로직 적용
		if (App.isProductMode()) {
		  MysqlUtil.setDBInfo("127.0.0.1", "sbsstLocal", "sbs123414", "jspCommunity");
		}
		else {
		  MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");
		  MysqlUtil.setDevMode(true);
		}
		
		
		int controllerTypeNameIndex = 2;
		int controllerNameIndex = 3;
		int actionMethodNameIndex = 4;

		if (App.isProductMode()) {
			controllerTypeNameIndex = 1;
			controllerNameIndex = 2;
			actionMethodNameIndex = 3;
		}

		String controllerTypeName = requestUriBits[controllerTypeNameIndex];
		String controllerName = requestUriBits[controllerNameIndex];
		String actionMethodName = requestUriBits[actionMethodNameIndex];
		
		//String controllerTypeName = requestUriBits[2]; // usr or adm
		//String controllerName = requestUriBits[3]; // article or member
		//String actionMethodName = requestUriBits[4]; // doJoinForm or .....

		String actionUrl = controllerTypeName + "/" + controllerName + "/" + actionMethodName;

		/* 세션에 로그인 정보 담기 시작 */
		int loginedMemberId = 0;
		boolean isLogined = false;
		Member loginedMember = null;
		boolean isUsingTempPassword = false;

		HttpSession session = request.getSession();


		if (session.getAttribute("loginedMemberId") != null && (int) session.getAttribute("loginedMemberId") > 0) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			isLogined = true;
			loginedMember = Container.memberService.getMemberById(loginedMemberId);
			
			
			/// 임시패스워드 사용 여부 확인
			isUsingTempPassword = Container.memberService.getIsUsingTempPassword(loginedMemberId);
			/// 세션에 임시패스워드 사용중인 회원 정보 담기
			if(isUsingTempPassword) {
				isUsingTempPassword = true;
			}

		}

		request.setAttribute("loginedMemberId", loginedMemberId);
		request.setAttribute("isLogined", isLogined);
		request.setAttribute("loginedMember", loginedMember);
		request.setAttribute("isUsingTempPassword", isUsingTempPassword);
		
		
		/// 현재 이동하려 시도했던 경로 저장(로그인 안되어있어서 취소된 경로)
		String currentUrl = request.getRequestURI();

		if (request.getQueryString() != null) {
			currentUrl += "?" + request.getQueryString();
		}

		String encodedCurrentUrl = Util.getUrlEncoded(currentUrl);

		request.setAttribute("currentUrl", currentUrl);
		request.setAttribute("encodedCurrentUrl", encodedCurrentUrl);
				
		/* 세션에 로그인 정보 담기 끝 */
		
		
		

		/* 로그인 필요한 action list 필터링 시작 */
		List<String> needToLoginActionList = new ArrayList<>();
		needToLoginActionList.add("usr/member/doLogout");
		needToLoginActionList.add("usr/article/doWriteForm");
		needToLoginActionList.add("usr/article/doWrite");
		needToLoginActionList.add("usr/article/doModifyForm");
		needToLoginActionList.add("usr/article/doModify");
		needToLoginActionList.add("usr/article/doDelete");
		needToLoginActionList.add("usr/article/updateLikesCount");
		needToLoginActionList.add("usr/article/reply");
		needToLoginActionList.add("usr/article/doModifyReply");
		needToLoginActionList.add("usr/article/doDeleteReply");
		needToLoginActionList.add("usr/member/showMyPage");
		needToLoginActionList.add("usr/member/doModifyForm");

		/// 로그인 여부 확인
		if (needToLoginActionList.contains(actionUrl)) {
			if ((boolean) request.getAttribute("isLogined") == false) {
				request.setAttribute("alertMsg", "로그인 후 이용해 주세요.");
			  //request.setAttribute("replaceUrl", "../member/doLoginForm");
				request.setAttribute("replaceUrl", "../member/doLoginForm?nextUrlAfterLogin=" + encodedCurrentUrl);

				RequestDispatcher rd = request.getRequestDispatcher("/jsp/common/redirect.jsp");

				rd.forward(request, response);

			}
		}
		/* 로그인 필요한 action list 필터링 끝 */

		/* 로그인 상태면 안되는 action list 필터링 시작 */
		List<String> needToNonLoginActionList = new ArrayList<>();
		needToNonLoginActionList.add("usr/member/doLoginForm");
		needToNonLoginActionList.add("usr/member/doLogin");
		needToNonLoginActionList.add("usr/member/doJoinForm");
		needToNonLoginActionList.add("usr/member/doJoin");
		
		/// 로그인 여부 확인
		if (needToNonLoginActionList.contains(actionUrl)) {
			if ((boolean) request.getAttribute("isLogined")) {
				request.setAttribute("alertMsg", "로그아웃 후 이용해 주세요.");
				request.setAttribute("replaceUrl", "../home/main");

				RequestDispatcher rd = request.getRequestDispatcher("/jsp/common/redirect.jsp");

				rd.forward(request, response);

			}
		}
		/* 로그인 상태면 안되는 action list 필터링 끝 */
		

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
