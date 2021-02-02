package com.sbs.example.jspCommunity.controller;

import javax.servlet.http.HttpServletRequest;

import com.sbs.example.jspCommunity.dto.ResultData;

public class Controller {

	// 메시지 후 뒤로가기 함수
	protected String msgAndBack(HttpServletRequest request, String msg) {
		request.setAttribute("alertMsg", msg);
		request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
		return "common/redirect";
	}

	// 메시지 후 해당 경로로 이동하기 함수
	protected String msgAndReplaceUrl(HttpServletRequest request, String msg, String url) {
		request.setAttribute("alertMsg", msg);
		request.setAttribute("replaceUrl", url);
		return "common/redirect";
	}

	// 메시지없이 해당 경로로 이동하기 함수
	protected String noMsgAndReplaceUrl(HttpServletRequest request, String url) {
		request.setAttribute("replaceUrl", url);
		return "common/noAlertRedirect";
	}


	// json 생성 함수
	protected String jsonWithData(HttpServletRequest request, ResultData data) {
		request.setAttribute("data", data);
		return "common/json";
	}

}
