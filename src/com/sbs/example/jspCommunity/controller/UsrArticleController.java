package com.sbs.example.jspCommunity.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.service.ArticleService;

public class UsrArticleController {

	ArticleService articleService;

	public UsrArticleController() {
		articleService = Container.articleService;
	}

	// 리스트 가져오기
	public String showList(HttpServletRequest request, HttpServletResponse response) {

		/*
		 * if (request.getParameter("boardId") == null) {
		 * response.getWriter().append("게시판 boardId를 입력해주세요."); continue; }
		 */
		int boardId = Integer.parseInt(request.getParameter("boardId"));

		List<Article> articles = articleService.getArticlesForPrintByBoardId(boardId);

		// 만약, 해당 게시판 번호의 게시판이 없으면 알림 메시지와 뒤로 돌아가기 실시
		if (articles.size() <= 0) {
			request.setAttribute("alertMsg", boardId + "번 게시물은 존재하지 않습니다. 게시판 번호를 확인하세요.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		request.setAttribute("articles", articles);

		return "usr/article/list";
	}

	// 게시물 상세보기
	public String showDetail(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * if (request.getParameter("id") == null) {
		 * response.getWriter().append("게시물 id를 입력해주세요."); return; }
		 */

		int id = Integer.parseInt(request.getParameter("id"));

		Article article = articleService.getArticleById(id);

		if (article == null) {
			request.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다. 게시물 번호를 확인하세요.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		request.setAttribute("article", article);

		return "usr/article/detail";
	}

	// 게시물 등록 폼
	public String doWriteForm(HttpServletRequest request, HttpServletResponse response) {

		// 로그인 여부 체크
		if ((boolean) request.getAttribute("isLogined") == false) {
			request.setAttribute("alertMsg", "로그인 후 이용가능합니다.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		return "usr/article/doWriteForm";
	}

	// 게시물 등록
	public String doWrite(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * if (request.getParameter("boardId") == null) {
		 * response.getWriter().append("작성할 boardId를 입력해주세요."); return; }
		 */

		int boardId = Integer.parseInt(request.getParameter("boardId"));
		int memberId = Integer.parseInt(request.getParameter("memberId"));

		/*
		 * if (request.getParameter("title") == null) {
		 * response.getWriter().append("title을 입력해주세요."); return; }
		 */

		String title = request.getParameter("title");

		/*
		 * if (request.getParameter("body") == null) {
		 * response.getWriter().append("body를 입력해주세요."); return; }
		 */

		String body = request.getParameter("body");

		// 해당 게시판이 존재하는지 확인
		List<Article> articles = articleService.getArticlesForPrintByBoardId(boardId);

		if (articles.size() <= 0) {
			request.setAttribute("alertMsg", boardId + "번 게시판은 존재하지 않습니다. 게시판 번호를 확인하세요.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		// 게시물 생성
		int id = articleService.add(boardId, title, body, memberId);

		// 생성 알림창 보여주고 detail로 이동하기
		request.setAttribute("alertMsg", id + "번 게시물이 생성되었습니다.");
		request.setAttribute("replaceUrl", String.format("detail?id=%d", id));
		return "common/redirect";

		/*
		 * Article article = articleService.getArticleById(id);
		 * 
		 * request.setAttribute("article", article);
		 * 
		 * return "usr/article/doWrite";
		 */
	}

	// 게시물 수정 폼
	public String doModifyForm(HttpServletRequest request, HttpServletResponse response) {

		// 로그인 여부 체크
		if ((boolean) request.getAttribute("isLogined") == false) {
			request.setAttribute("alertMsg", "로그인 후 이용가능합니다.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		int memberId = Integer.parseInt(request.getParameter("memberId"));

		// 본인 여부 체크
		if ((int) request.getAttribute("loginedMemberId") != memberId) {
			request.setAttribute("alertMsg", "작성자만 수정이 가능합니다.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		int id = Integer.parseInt(request.getParameter("id"));

		Article article = articleService.getArticleById(id);

		if (article == null) {
			request.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다. 게시물 번호를 확인하세요.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		if (article.getMemberId() != memberId) {
			request.setAttribute("alertMsg", id + "번 게시물에 대한 권한이 없습니다.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		request.setAttribute("article", article);

		return "usr/article/doModifyForm";
	}

	// 게시물 수정
	public String doModify(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * if (request.getParameter("id") == null) {
		 * response.getWriter().append("수정할 게시물 id를 입력해주세요."); return; }
		 */

		int id = Integer.parseInt(request.getParameter("id"));

		/*
		 * if (request.getParameter("title") == null) {
		 * response.getWriter().append("수정할 title을 입력해주세요."); return; }
		 */
		String title = request.getParameter("title");

		/*
		 * if (request.getParameter("body") == null) {
		 * response.getWriter().append("수정할 body를 입력해주세요."); return; }
		 */

		String body = request.getParameter("body");

		Article article = articleService.getArticleById(id);

		if (article == null) {
			request.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다. 게시물 번호를 확인하세요.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		// 게시물 수정
		articleService.articleModify(id, title, body);

		// 수정 알림창 보여주고 detail로 이동하기
		request.setAttribute("alertMsg", id + "번 게시물이 수정되었습니다.");
		request.setAttribute("replaceUrl", String.format("detail?id=%d", id));
		return "common/redirect";

		/*
		 * // 수정된 해당 게시물 정보 다시 불러오기 article = articleService.getArticleById(id);
		 * 
		 * request.setAttribute("article", article);
		 * 
		 * return "usr/article/doModify";
		 */
	}

	// 게시물 삭제
	public String doDelete(HttpServletRequest request, HttpServletResponse response) {

		// 로그인 여부 체크
		if ((boolean) request.getAttribute("isLogined") == false) {
			request.setAttribute("alertMsg", "로그인 후 이용가능합니다.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		int memberId = Integer.parseInt(request.getParameter("memberId"));

		// 본인 여부 체크
		if ((int) request.getAttribute("loginedMemberId") != memberId) {
			request.setAttribute("alertMsg", "작성자만 삭제가 가능합니다.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		int id = Integer.parseInt(request.getParameter("id"));

		Article article = articleService.getArticleById(id);

		if (article == null) {
			request.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다. 게시물 번호를 확인하세요.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		// 게시물 삭제
		articleService.articleDelete(id);

		// 삭제 알림창 보여주고 리스트로 이동하기
		request.setAttribute("alertMsg", id + "번 게시물이 삭제되었습니다.");
		request.setAttribute("replaceUrl", String.format("list?boardId=%d", article.getBoardId()));
		return "common/redirect";

		/*
		 * request.setAttribute("id", id);
		 * 
		 * return "usr/article/doDelete";
		 */
	}

}
