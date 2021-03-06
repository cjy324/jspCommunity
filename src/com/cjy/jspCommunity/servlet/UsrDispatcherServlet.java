package com.cjy.jspCommunity.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cjy.jspCommunity.container.Container;
import com.cjy.jspCommunity.controller.UsrArticleController;
import com.cjy.jspCommunity.controller.UsrHomeController;
import com.cjy.jspCommunity.controller.UsrLikeController;
import com.cjy.jspCommunity.controller.UsrMemberController;

@WebServlet("/usr/*")
public class UsrDispatcherServlet extends DispatcherServlet {

	// (2) doBeforeActionRs의 결과로 도출된 controllerName, actionMethodName 가져와 usr, adm 서블릿으로 전송
	// usr, adm 서블릿에서 각 컨트롤들이 요청 수행후 jspPath 리턴
	protected String doAction(HttpServletRequest request, HttpServletResponse response, String controllerName,
			String actionMethodName) {

		String jspPath = null;

		if (controllerName.equals("home")) {
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
			if (actionMethodName.equals("doKakaoLogin")) {
				jspPath = membercontroller.doKakaoLogin(request, response);
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
			if (actionMethodName.equals("addHitCounts")) {
				jspPath = articleController.addHitCounts(request, response);
			}
			if (actionMethodName.equals("reply")) {
				jspPath = articleController.reply(request, response);
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