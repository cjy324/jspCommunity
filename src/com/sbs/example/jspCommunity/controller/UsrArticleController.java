package com.sbs.example.jspCommunity.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.service.ArticleService;
import com.sbs.example.util.Util;

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

		// 페이징
		int articlesInAPage = 10; // 한 페이지에 들어갈 article 수 설정
		int page = Util.getAsInt(request.getParameter("page"), 1); // pageNum이 null이면 1로 변환, 정수형(int)이 아니면 정수형으로
																	// 변환
		int pageLimitStartIndex = (page - 1) * articlesInAPage;

		List<Article> articles = articleService.getArticlesForPrintByBoardId(boardId, pageLimitStartIndex,
				articlesInAPage, searchKeywordType, searchKeyword);

		int pageMenuBoxSize = 5; // 한 메인페이지 화면에 나올 하단 페이지 메뉴 버튼 수 ex) 1 2 3 4 5 6 7 8 9 10
		int totalArticlesCount = totalCount; // 전체 article의 수 카운팅
		int totalPages = (int) Math.ceil((double) totalArticlesCount / articlesInAPage); // 총 필요 페이지 수 카운팅

		// 총 필요 페이지 수까지 버튼 만들기
		// 하단 페이지 이동 버튼 메뉴 만들기
		// 1. pageMenuBox내 시작 번호, 끝 번호 설정

		int previousPageNumCount = (page - 1) / pageMenuBoxSize; // 현재 페이지가 2이면 previousPageNumCount = 1/5
		int boxStartNum = pageMenuBoxSize * previousPageNumCount + 1; // 총 페이지 수 30이면 1~5 6~10 11~15
		int boxEndNum = pageMenuBoxSize + boxStartNum - 1;

		if (boxEndNum > totalPages) {
			boxEndNum = totalPages;
		}

		// 2. '이전','다음' 버튼 페이지 계산
		int boxStartNumBeforePage = boxStartNum - 1;
		if (boxStartNumBeforePage < 1) {
			boxStartNumBeforePage = 1;
		}
		int boxEndNumAfterPage = boxEndNum + 1;
		if (boxEndNumAfterPage > totalPages) {
			boxEndNumAfterPage = totalPages;
		}

		// 3. '이전','다음' 버튼 필요 유무 판별
		boolean boxStartNumBeforePageBtnNeedToShow = boxStartNumBeforePage != boxStartNum;
		boolean boxEndNumAfterPageBtnNeedToShow = boxEndNumAfterPage != boxEndNum;

		// 만약, 해당 게시판 번호의 게시판이 없으면 알림 메시지와 뒤로 돌아가기 실시

		if (articles.size() <= 0) {
			request.setAttribute("alertMsg", "해당 키워드가 포함된 게시물이 존재하지 않습니다.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";

		}

		request.setAttribute("totalCount", totalCount);
		request.setAttribute("articles", articles);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("page", page);

		request.setAttribute("boxStartNum", boxStartNum);
		request.setAttribute("boxEndNum", boxEndNum);
		request.setAttribute("boxStartNumBeforePage", boxStartNumBeforePage);
		request.setAttribute("boxEndNumAfterPage", boxEndNumAfterPage);
		request.setAttribute("boxStartNumBeforePageBtnNeedToShow", boxStartNumBeforePageBtnNeedToShow);
		request.setAttribute("boxEndNumAfterPageBtnNeedToShow", boxEndNumAfterPageBtnNeedToShow);
		
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

		String articleBody = article.getBody();
		articleBody = articleBody.replaceAll("script", "t-script");

		// 상세페이지 하단 메뉴

		int boardId = article.getBoardId();
		Article beforeArticle = articleService.getArticleByIdAndBoardId(id - 1, boardId);
		Article afterArticle = articleService.getArticleByIdAndBoardId(id + 1, boardId);

		String beforeArticleBtn = "";
		String afterArticleBtn = "";

		if (beforeArticle != null) {
			beforeArticleBtn = "<div class=\"./\"><a href=\"../article/detail?id=" + beforeArticle.getId()
					+ "\" class=\"hover-underline\">&lt 이전글</a></div>";
			request.setAttribute("beforeArticleBtn", beforeArticleBtn);
		}

		if (afterArticle != null) {
			afterArticleBtn = "<div class=\"./\"><a href=\"../article/detail?id=" + afterArticle.getId()
					+ "\"class=\"hover-underline\">다음글 &gt</a></div>";
			request.setAttribute("afterArticleBtn", afterArticleBtn);
		}

		request.setAttribute("article", article);
		request.setAttribute("articleBody", articleBody);

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

		String articleBody = article.getBody();
		articleBody = articleBody.replaceAll("script", "t-script");

		request.setAttribute("article", article);
		request.setAttribute("articleBody", articleBody);

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
