package com.sbs.example.jspCommunity.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.controller.UsrArticleController;
import com.sbs.example.jspCommunity.controller.UsrHomeController;
import com.sbs.example.jspCommunity.controller.UsrLikeController;
import com.sbs.example.jspCommunity.controller.UsrMemberController;
import com.sbs.example.jspCommunity.dto.Member;

@WebServlet("/usr/*")
public class UsrDispatcherServlet extends DispatcherServlet {

	// (2) doBeforeActionRs의 결과로 도출된 controllerName, actionMethodName 가져와 usr, adm 서블릿으로 전송
	// usr, adm 서블릿에서 각 컨트롤들이 요청 수행후 jspPath 리턴
	protected String doAction(HttpServletRequest request, HttpServletResponse response, String controllerName,
			String actionMethodName) {

		String jspPath = null;

		/*
		 * // 로그인 여부를 request에 저장 시작 HttpSession session = request.getSession();
		 * 
		 * int loginedMemberId = -1; boolean isLogined = false; Member loginedMember =
		 * null;
		 * 
		 * if (session.getAttribute("loginedMemberId") != null) { loginedMemberId =
		 * (int) session.getAttribute("loginedMemberId"); isLogined = true;
		 * loginedMember = Container.memberService.getMemberById(loginedMemberId); }
		 * 
		 * request.setAttribute("loginedMemberId", loginedMemberId);
		 * request.setAttribute("isLogined", isLogined);
		 * request.setAttribute("loginedMember", loginedMember); // 로그인 여부를 request에 저장
		 * 끝
		 */

		if(controllerName.equals("home")) {
			UsrHomeController homeController = Container.homeController;

			if (actionMethodName.equals("main")) {
				jspPath = homeController.main(request, response);
			}
		}
		
		
		if (controllerName.equals("member")) {
			UsrMemberController membercontroller = Container.userMembercontroller;

			if (actionMethodName.equals("doJoinForm")) {
				jspPath = membercontroller.doJoinForm(request, response);
			}
			if (actionMethodName.equals("getLoginIdDup")) {
				jspPath = membercontroller.getLoginIdDup(request, response);
			}
			if (actionMethodName.equals("getNicknameDup")) {
				jspPath = membercontroller.getNicknameDup(request, response);
			}
			if (actionMethodName.equals("doJoin")) {
				jspPath = membercontroller.doJoin(request, response);
			}
			if (actionMethodName.equals("doLoginForm")) {
				jspPath = membercontroller.doLoginForm(request, response);
			}
			if (actionMethodName.equals("doLogin")) {
				jspPath = membercontroller.doLogin(request, response);
			}
			if (actionMethodName.equals("doLogout")) {
				jspPath = membercontroller.doLogout(request, response);
			}
			if (actionMethodName.equals("showMyPage")) {
				jspPath = membercontroller.showMyPage(request, response);
			}
			if (actionMethodName.equals("doModifyForm")) {
				jspPath = membercontroller.doModifyForm(request, response);
			}
			if (actionMethodName.equals("doModifyInfo")) {
				jspPath = membercontroller.doModifyInfo(request, response);
			}
			if (actionMethodName.equals("doFindLoginIdForm")) {
				jspPath = membercontroller.doFindLoginIdForm(request, response);
			}
			if (actionMethodName.equals("doFindLoginId")) {
				jspPath = membercontroller.doFindLoginId(request, response);
			}
			if (actionMethodName.equals("doFindLoginPwForm")) {
				jspPath = membercontroller.doFindLoginPwForm(request, response);
			}
			if (actionMethodName.equals("doFindLoginPw")) {
				jspPath = membercontroller.doFindLoginPw(request, response);
			}
		}

		if (controllerName.equals("article")) {
			UsrArticleController articleController = Container.articleController;

			if (actionMethodName.equals("list")) {
				jspPath = articleController.showList(request, response);
			}
			if (actionMethodName.equals("detail")) {
				jspPath = articleController.showDetail(request, response);
			}
			if (actionMethodName.equals("doWriteForm")) {
				jspPath = articleController.doWriteForm(request, response);
			}
			if (actionMethodName.equals("doWrite")) {
				jspPath = articleController.doWrite(request, response);
			}
			if (actionMethodName.equals("doModifyForm")) {
				jspPath = articleController.doModifyForm(request, response);
			}
			if (actionMethodName.equals("doModify")) {
				jspPath = articleController.doModify(request, response);
			}
			if (actionMethodName.equals("doDelete")) {
				jspPath = articleController.doDelete(request, response);
			}
		/*	if (actionMethodName.equals("updateLikesCount")) {
				jspPath = articleController.updateLikesCount(request, response);
			}*/
			if (actionMethodName.equals("addHitCounts")) {
				jspPath = articleController.addHitCounts(request, response);
			}
			if (actionMethodName.equals("reply")) {
				jspPath = articleController.reply(request, response);
			}
			if (actionMethodName.equals("replyAjax")) {
				jspPath = articleController.replyAjax(request, response);
			}
			if (actionMethodName.equals("getReplies")) {
				jspPath = articleController.getReplies(request, response);
			}

			if (actionMethodName.equals("doModifyReply")) {
				jspPath = articleController.doModifyReply(request, response);
			}
			if (actionMethodName.equals("doDeleteReply")) {
				jspPath = articleController.doDeleteReply(request, response);
			}
			if (actionMethodName.equals("search")) {
				jspPath = articleController.search(request, response);
			}
			
		}
		
		else if (controllerName.equals("like")) {
			UsrLikeController likeController = Container.usrLikeController;

			if (actionMethodName.equals("doLike")) {
				jspPath = likeController.doLike(request, response);
			} else if (actionMethodName.equals("doCancelLike")) {
				jspPath = likeController.doCancelLike(request, response);
			} else if (actionMethodName.equals("doDislike")) {
				jspPath = likeController.doDislike(request, response);
			} else if (actionMethodName.equals("doCancelDislike")) {
				jspPath = likeController.doCancelDislike(request, response);
			}
			
		}

		return jspPath;

	}

}