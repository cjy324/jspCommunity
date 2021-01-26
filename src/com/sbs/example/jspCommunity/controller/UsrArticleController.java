package com.sbs.example.jspCommunity.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Board;
import com.sbs.example.jspCommunity.service.ArticleService;

public class UsrArticleController {

	ArticleService articleService;

	public UsrArticleController() {
		articleService = Container.articleService;
	}

	// 리스트 가져오기
	public String showList(HttpServletRequest request, HttpServletResponse response) {

		int boardId = Integer.parseInt(request.getParameter("boardId"));

		String searchKeywordType = request.getParameter("searchKeywordType");
		String searchKeyword = request.getParameter("searchKeyword");

		// 총 게시물 수 카운트
		int totalCount = articleService.getArticlesCountByBoardId(boardId, searchKeywordType, searchKeyword);

		List<Article> articles = articleService.getArticlesForPrintByBoardId(boardId, searchKeywordType, searchKeyword);

		// 만약, 해당 게시판 번호의 게시판이 없으면 알림 메시지와 뒤로 돌아가기 실시

		if (articles.size() <= 0) {
			request.setAttribute("alertMsg", "해당 키워드가 포함된 게시물이 존재하지 않습니다.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";

		}

		request.setAttribute("totalCount", totalCount);
		request.setAttribute("articles", articles);

		return "usr/article/list";
	}

	// 게시물 상세보기
	public String showDetail(HttpServletRequest request, HttpServletResponse response) {

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

		return "usr/article/doWriteForm";
	}

	// 게시물 등록
	public String doWrite(HttpServletRequest request, HttpServletResponse response) {

		int boardId = Integer.parseInt(request.getParameter("boardId"));
		int memberId = (int) request.getAttribute("loginedMemberId");

		String title = request.getParameter("title");

		String body = request.getParameter("body");

		// 해당 게시판이 존재하는지 확인
		List<Article> articles = articleService.getArticlesForPrintByBoardId(boardId, null, null);

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

	}

	// 게시물 수정 폼
	public String doModifyForm(HttpServletRequest request, HttpServletResponse response) {

		int memberId = (int) request.getAttribute("loginedMemberId");

		// 해당 게시판이 존재하는지 확인
		int id = Integer.parseInt(request.getParameter("id"));

		Article article = articleService.getArticleById(id);

		if (article == null) {
			request.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다. 게시물 번호를 확인하세요.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		// 작성자 본인 여부 체크
		if (article.getMemberId() != memberId) {
			request.setAttribute("alertMsg", "작성자만 수정이 가능합니다.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		request.setAttribute("article", article);

		return "usr/article/doModifyForm";
	}

	// 게시물 수정
	public String doModify(HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String body = request.getParameter("body");

		// 게시물 수정
		articleService.articleModify(id, title, body);

		// 수정 알림창 보여주고 detail로 이동하기
		request.setAttribute("alertMsg", id + "번 게시물이 수정되었습니다.");
		request.setAttribute("replaceUrl", String.format("detail?id=%d", id));
		return "common/redirect";

	}

	// 게시물 삭제
	public String doDelete(HttpServletRequest request, HttpServletResponse response) {

		int memberId = (int) request.getAttribute("loginedMemberId");

		// 해당 게시판이 존재하는지 확인
		int id = Integer.parseInt(request.getParameter("id"));

		Article article = articleService.getArticleById(id);

		if (article == null) {
			request.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다. 게시물 번호를 확인하세요.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		// 작성자 본인 여부 체크
		if (article.getMemberId() != memberId) {
			request.setAttribute("alertMsg", "작성자만 삭제가 가능합니다.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		// 게시물 삭제
		articleService.articleDelete(id);

		// 삭제 알림창 보여주고 리스트로 이동하기
		request.setAttribute("alertMsg", id + "번 게시물이 삭제되었습니다.");
		request.setAttribute("replaceUrl", String.format("list?boardId=%d", article.getBoardId()));
		return "common/redirect";

	}

}
