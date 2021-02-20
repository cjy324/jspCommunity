package com.sbs.example.jspCommunity.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.dto.Reply;
import com.sbs.example.jspCommunity.service.ArticleService;
import com.sbs.example.util.Util;

public class UsrArticleController extends Controller {

	ArticleService articleService;

	public UsrArticleController() {
		articleService = Container.articleService;
	}

	// 리스트 가져오기
	public String showList(HttpServletRequest request, HttpServletResponse response) {

		// 게시판 번호 확인
		int boardId = Util.getAsInt(request.getParameter("boardId"), 0);
		if (boardId == 0) {
			return msgAndBack(request, "게시판 번호를 입력해 주세요.");
		}

		String searchKeywordType = request.getParameter("searchKeywordType");
		String searchKeyword = request.getParameter("searchKeyword");

		// 총 게시물 수 카운트
		int totalCount = articleService.getArticlesCountByBoardId(boardId, searchKeywordType, searchKeyword);

		// 페이징
		int articlesInAPage = 20; // 한 페이지에 들어갈 article 수 설정
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
			return msgAndBack(request, "해당 키워드가 포함된 게시물이 존재하지 않습니다.");
		}

		request.setAttribute("articles", articles);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("page", page);
		request.setAttribute("totalPages", totalPages);

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

		Article oldArticle = articleService.getArticleById(id);

		if (oldArticle == null) {
			return msgAndBack(request, id + "번 게시물은 존재하지 않습니다. 게시물 번호를 확인하세요.");
		}

		// 업데이트된 게시물 정보 가져오기

		Member loginedMember = (Member) request.getAttribute("loginedMember");
		Article article = articleService.getForPrintArticleById(id, loginedMember);

		String articleBody = article.getBody();
		articleBody = articleBody.replaceAll("script", "t-script");

		/* 상세페이지 하단 메뉴 시작 */
		/// 현재 게시물의 인덱스값 가져오기
		int currentArticleIndex = 0;
		int boardId = article.getBoardId();

		List<Article> articles = articleService.getArticlesForPrintByBoardId(boardId);
		Collections.reverse(articles);

		for (int i = 0; i < articles.size(); i++) {
			if (article.getId() == articles.get(i).getId()) {
				currentArticleIndex = i;
				break;
			}
		}
		;

		int x = currentArticleIndex;

		boolean beforeArticleBtn = false;
		boolean afterArticleBtn = false;

		if (x - 1 >= 0) {
			beforeArticleBtn = true;
			request.setAttribute("beforeArticleIndex", articles.get(x - 1).getId());
			request.setAttribute("beforeArticleBtn", beforeArticleBtn);
		}

		if (x < articles.size() - 1) {
			afterArticleBtn = true;
			request.setAttribute("afterArticleIndex", articles.get(x + 1).getId());
			request.setAttribute("afterArticleBtn", afterArticleBtn);
		}

		/* 상세페이지 하단 메뉴 끝 */

		/* 상세페이지 댓글리스트 가져오기 시작 */
		// List<Reply> replies = articleService.getArticleReplies(id);

		String relTypeCode = "article";

		// 총 댓글 수 카운트
		int totalCount = articleService.getRepliesCountByArticleId(id, relTypeCode);

		// 페이징
		int repliesInAPage = 5;
		// 한 페이지에 들어갈 article 수 설정
		int page = Util.getAsInt(request.getParameter("page"), 1);
		// pageNum이 null이면 1로 변환,정수형(int)이 아니면 정수형으로 변환
		int pageLimitStartIndex = (page - 1) * repliesInAPage;

		List<Reply> replies = articleService.getRepliesForPrintByArticleId(id, relTypeCode, pageLimitStartIndex,
				repliesInAPage, loginedMember);

		// 대댓글 리스팅
		relTypeCode = "reply";
		List<Reply> reReplies = articleService.getRepliesForPrintByRelTyopeCode(relTypeCode, loginedMember);

		int pageMenuBoxSize = 3; // 한 메인페이지 화면에 나올 하단 페이지 메뉴 버튼 수 ex) 1 2 3 4 5 6 7 8 9 10
		int totalRepliesCount = totalCount; // 전체 article의 수 카운팅
		int totalPages = (int) Math.ceil((double) totalRepliesCount / repliesInAPage); // 총 필요 페이지수 카운팅

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

		request.setAttribute("replies", replies);
		request.setAttribute("reReplies", reReplies);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("page", page);
		request.setAttribute("totalPages", totalPages);

		request.setAttribute("boxStartNum", boxStartNum);
		request.setAttribute("boxEndNum", boxEndNum);
		request.setAttribute("boxStartNumBeforePage", boxStartNumBeforePage);
		request.setAttribute("boxEndNumAfterPage", boxEndNumAfterPage);
		request.setAttribute("boxStartNumBeforePageBtnNeedToShow", boxStartNumBeforePageBtnNeedToShow);
		request.setAttribute("boxEndNumAfterPageBtnNeedToShow", boxEndNumAfterPageBtnNeedToShow);

		/* 상세페이지 댓글리스트 가져오기 끝 */

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

		int memberId = (int) request.getAttribute("loginedMemberId");

		// 게시판 번호가 입력됐는지 확인
		int boardId = Util.getAsInt(request.getParameter("boardId"), 0);
		if (boardId == 0) {
			return msgAndBack(request, "게시판 번호를 입력하세요.");
		}

		// 해당 게시판이 존재하는지 확인
		List<Article> articles = articleService.getArticlesForPrintByBoardId(boardId);

		if (articles.size() <= 0) {
			return msgAndBack(request, boardId + "번 게시판은 존재하지 않습니다. 게시판 번호를 확인하세요.");
		}

		// 게시물 제목이 입력됐는지 확인
		String title = request.getParameter("title");
		if (Util.isEmpty(title)) {
			return msgAndBack(request, "제목을 입력하세요.");
		}

		// 게시물 내용이 입력됐는지 확인
		String body = request.getParameter("body");
		if (Util.isEmpty(body)) {
			return msgAndBack(request, "내용을 입력하세요.");
		}

		// 게시물 생성
		int id = articleService.add(boardId, title, body, memberId);

		// 생성 알림창 보여주고 detail로 이동하기
		return msgAndReplaceUrl(request, id + "번 게시물이 생성되었습니다.", String.format("detail?id=%d", id));

	}

	// 게시물 수정 폼
	public String doModifyForm(HttpServletRequest request, HttpServletResponse response) {

		int memberId = (int) request.getAttribute("loginedMemberId");

		// 게시물 번호가 입력됐는지 확인
		int id = Util.getAsInt(request.getParameter("id"), 0);

		if (id == 0) {
			return msgAndBack(request, "게시물 번호를 입력하세요.");
		}

		// 해당 게시물이 존재하는지 확인
		Article article = articleService.getArticleById(id);

		if (article == null) {
			return msgAndBack(request, id + "번 게시물은 존재하지 않습니다. 게시물 번호를 확인하세요.");
		}

		// 작성자 본인 여부 체크
		if (article.getMemberId() != memberId) {
			return msgAndBack(request, id + "작성자만 수정이 가능합니다.");
		}

		String articleBody = article.getBody();
		articleBody = articleBody.replaceAll("script", "t-script");

		request.setAttribute("article", article);
		request.setAttribute("articleBody", articleBody);

		return "usr/article/doModifyForm";
	}

	// 게시물 수정
	public String doModify(HttpServletRequest request, HttpServletResponse response) {

		// 게시물 번호가 입력됐는지 확인
		int id = Util.getAsInt(request.getParameter("id"), 0);
		if (id == 0) {
			return msgAndBack(request, "게시물 번호를 입력하세요.");
		}

		// 게시물 제목이 입력됐는지 확인
		String title = request.getParameter("title");
		if (Util.isEmpty(title)) {
			return msgAndBack(request, "제목을 입력하세요.");
		}

		// 게시물 내용이 입력됐는지 확인
		String body = request.getParameter("body");
		if (Util.isEmpty(body)) {
			return msgAndBack(request, "내용을 입력하세요.");
		}

		// 게시물 수정
		Map<String, Object> args = new HashMap<>();
		args.put("id", id);
		args.put("title", title);
		args.put("body", body);

		articleService.articleModify(args);

		// 수정 알림창 보여주고 detail로 이동하기
		return msgAndReplaceUrl(request, id + "번 게시물이 수정되었습니다.", String.format("detail?id=%d", id));

	}

	// 게시물 삭제
	public String doDelete(HttpServletRequest request, HttpServletResponse response) {

		int memberId = (int) request.getAttribute("loginedMemberId");

		// 게시물 번호가 입력됐는지 확인
		int id = Util.getAsInt(request.getParameter("id"), 0);
		if (id == 0) {
			return msgAndBack(request, "게시물 번호를 입력하세요.");
		}

		// 해당 게시물이 존재하는지 확인
		Article article = articleService.getArticleById(id);

		if (article == null) {
			return msgAndBack(request, id + "번 게시물은 존재하지 않습니다. 게시물 번호를 확인하세요.");
		}

		// 작성자 본인 여부 체크
		if (article.getMemberId() != memberId) {
			return msgAndBack(request, "작성자만 삭제가 가능합니다.");
		}

		// 게시물 삭제
		articleService.articleDelete(id);

		// 삭제 알림창 보여주고 리스트로 이동하기
		return msgAndReplaceUrl(request, id + "번 게시물이 삭제되었습니다.",
				String.format("list?boardId=%d", article.getBoardId()));

	}

	// 좋아요 업데이트(without ajax)
	public String updateLikesCount(HttpServletRequest request, HttpServletResponse response) {

		// 게시물 번호가 입력됐는지 확인
		int articleId = Util.getAsInt(request.getParameter("articleId"), 0);
		if (articleId == 0) {
			return msgAndBack(request, "게시물 번호를 입력하세요.");
		}

		// 회원 아이디 확인
		int memberId = Util.getAsInt(request.getParameter("memberId"), 0);
		if (memberId == 0) {
			return msgAndBack(request, "회원 아이디를 입력하세요.");
		}

		// like
		int addLike = Util.getAsInt(request.getParameter("addLike"), 0);

		// unlike
		int addUnLike = Util.getAsInt(request.getParameter("addUnLike"), 0);

		System.out.println("articleId: " + articleId);
		System.out.println("memberId: " + memberId);
		System.out.println("addLike: " + addLike);
		System.out.println("addUnLike: " + addUnLike);

		// 이미 좋아요, 싫어요한 회원인지 확인
		boolean isAlreadylikeMember = articleService.isAlreadylikeMember(memberId, articleId);

		// 기존 좋아요,싫어요 기록 삭제
		if (isAlreadylikeMember) {
			articleService.removeLikeMember(memberId, articleId);

			// 게시물에 대한 LikesCount 가져오기
			int getArticleLikesCount = articleService.getArticleLikesCount(articleId);

			// 게시물에 대한 unLikesCount 가져오기
			int getArticleUnLikesCount = articleService.getArticleUnLikesCount(articleId);

			// 게시물 정보 수정
			Map<String, Object> args2 = new HashMap<>();
			args2.put("id", articleId);
			args2.put("likesCount", getArticleLikesCount);
			args2.put("unLikesCount", getArticleUnLikesCount);

			articleService.articleModify(args2);

			// 새로고침
			return noMsgAndReplaceUrl(request, "detail?id=" + articleId);
		}

		// 좋아요,싫어요 추가
		Map<String, Object> args = new HashMap<>();
		args.put("memberId", memberId);
		args.put("relTypeCode", "article");
		args.put("relId", articleId);

		if (addLike != 0) {
			args.put("point", addLike);
		}
		if (addUnLike != 0) {
			args.put("point", addUnLike);
		}

		articleService.addLikesCount(args);

		// 게시물에 대한 LikesCount 가져오기
		int getArticleLikesCount = articleService.getArticleLikesCount(articleId);

		// 게시물에 대한 unLikesCount 가져오기
		int getArticleUnLikesCount = articleService.getArticleUnLikesCount(articleId);

		// 게시물 정보 수정
		Map<String, Object> args2 = new HashMap<>();
		args2.put("id", articleId);
		args2.put("likesCount", getArticleLikesCount);
		args2.put("unLikesCount", getArticleUnLikesCount);

		articleService.articleModify(args2);

		// 새로고침
		return noMsgAndReplaceUrl(request, "detail?id=" + articleId);
	}

	// 댓글 등록
	public String reply(HttpServletRequest request, HttpServletResponse response) {

		String relTypeCode = "";
		int relId = 0;

		// 게시물 번호가 입력됐는지 확인
		int articleId = Util.getAsInt(request.getParameter("articleId"), 0);
		if (articleId != 0) {
			relTypeCode = "article";
			relId = articleId;
		}

		// 댓글 번호가 입력됐는지 확인
		int originReplyId = Util.getAsInt(request.getParameter("replyId"), 0);
		if (originReplyId != 0) {
			relTypeCode = "reply";
			relId = originReplyId;
		}

		// 회원 아이디 확인
		int memberId = Util.getAsInt(request.getParameter("memberId"), 0);
		if (memberId == 0) {
			return msgAndBack(request, "회원 아이디를 입력하세요.");
		}

		// 댓글이 입력됐는지 확인
		String replyBody = request.getParameter("replyBody");
		if (Util.isEmpty(replyBody)) {
			return msgAndBack(request, "내용을 입력하세요.");
		}

		// 댓글 등록
		int replyId = articleService.addReply(relId, memberId, relTypeCode, replyBody);

		// 게시물에 대한 댓글수 가져오기
		int getArticleRepliesCount = articleService.getArticleRepliesCount(articleId);

		// 게시물 정보 수정
		Map<String, Object> args2 = new HashMap<>();
		args2.put("id", articleId);
		args2.put("repliesCount", getArticleRepliesCount);

		articleService.articleModify(args2);

		// 새로고침
		// return noMsgAndReplaceUrl(request, "detail?id=" + articleId);
		String redirectUrl = request.getParameter("redirectUrl");
		redirectUrl = redirectUrl.replace("[NEW_REPLY_ID]", replyId + "");
		return msgAndReplaceUrl(request, replyId + "번 댓글이 등록되었습니다.", redirectUrl);

	}

	// 댓글 수정
	public String doModifyReply(HttpServletRequest request, HttpServletResponse response) {

		// 댓글 번호가 입력됐는지 확인
		int id = Util.getAsInt(request.getParameter("id"), 0);
		if (id == 0) {
			return msgAndBack(request, "댓글 번호를 입력하세요.");
		}

		// 대상 번호
		int relId = Util.getAsInt(request.getParameter("relId"), 0);
		if (relId == 0) {
			return msgAndBack(request, "번호를 입력하세요.");
		}

		// 댓글 내용이 입력됐는지 확인
		String body = request.getParameter("body");
		if (Util.isEmpty(body)) {
			return msgAndBack(request, "내용을 입력하세요.");
		}

		// 댓글 수정
		Map<String, Object> args = new HashMap<>();
		args.put("id", id);
		args.put("body", body);

		articleService.replyModify(args);

		// 수정 알림창 보여주고 새로고침
		// return msgAndReplaceUrl(request, id + "번 댓글이 수정되었습니다.",
		// String.format("detail?id=%d", relId));

		String redirectUrl = request.getParameter("redirectUrl");
		redirectUrl = redirectUrl.replace("[NEW_REPLY_ID]", id + "");
		return msgAndReplaceUrl(request, id + "번 댓글이 수정되었습니다.", redirectUrl);
	}

	// 댓글 삭제
	public String doDeleteReply(HttpServletRequest request, HttpServletResponse response) {
		int memberId = (int) request.getAttribute("loginedMemberId");

		// 댓글들이 달린 게시물 번호
		int articleId = Util.getAsInt(request.getParameter("articleId"), 0);
		if (articleId == 0) {
			return msgAndBack(request, "게시물 번호를 입력하세요.");
		}

		// 댓글 번호가 입력됐는지 확인
		int id = Util.getAsInt(request.getParameter("id"), 0);
		if (id == 0) {
			return msgAndBack(request, "댓글 번호를 입력하세요.");
		}

		// 대상 번호
		int relId = Util.getAsInt(request.getParameter("relId"), 0);
		if (relId == 0) {
			return msgAndBack(request, "번호를 입력하세요.");
		}

		// 해당 댓글이 존재하는지 확인
		Reply reply = articleService.getReplyById(id);

		if (reply == null) {
			return msgAndBack(request, id + "번 댓글은 존재하지 않습니다. 댓글 번호를 확인하세요.");
		}

		// 작성자 본인 여부 체크
		if (reply.getMemberId() != memberId) {
			return msgAndBack(request, "작성자만 삭제가 가능합니다.");
		}

		// 댓글 삭제
		articleService.replyDelete(id);

		// 게시물에 대한 댓글수 가져오기
		int getArticleRepliesCount = articleService.getArticleRepliesCount(articleId);

		// 게시물 정보 수정
		Map<String, Object> args2 = new HashMap<>();
		args2.put("id", articleId);
		args2.put("repliesCount", getArticleRepliesCount);

		articleService.articleModify(args2);

		// 삭제 알림창 보여주고 새로고침
		return msgAndReplaceUrl(request, "삭제되었습니다.", "detail?id=" + articleId);

	}

	// 조회수 증가
	public String addHitCounts(HttpServletRequest request, HttpServletResponse response) {
		int articleId = Integer.parseInt(request.getParameter("articleId"));

		// 조회수 증가

		articleService.addView(articleId);
		int hitsCount = articleService.getViewCount(articleId);

		// 게시물 정보에 조회수 업데이트
		Map<String, Object> args = new HashMap<>();
		args.put("id", articleId);
		args.put("hitsCount", hitsCount);

		articleService.addArticleHitsCount(args);

		return jsonWithData(request, null);
	}

	// 검색 페이지
	public String search(HttpServletRequest request, HttpServletResponse response) {

		String searchKeywordType = request.getParameter("searchKeywordType");
		String searchKeyword = request.getParameter("searchKeyword");

		// 총 게시물 수 카운트
		int totalCount = articleService.getArticlesCountBySearchKeyword(searchKeywordType, searchKeyword);

		// 페이징
		int articlesInAPage = 20; // 한 페이지에 들어갈 article 수 설정
		int page = Util.getAsInt(request.getParameter("page"), 1); // pageNum이 null이면 1로 변환, 정수형(int)이 아니면 정수형으로
																	// 변환
		int pageLimitStartIndex = (page - 1) * articlesInAPage;

		List<Article> articles = articleService.getArticlesForPrintBySearchKeyword(pageLimitStartIndex, articlesInAPage,
				searchKeywordType, searchKeyword);

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
			return msgAndBack(request, "해당 키워드가 포함된 게시물이 존재하지 않습니다.");
		}

		request.setAttribute("articles", articles);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("page", page);
		request.setAttribute("totalPages", totalPages);

		request.setAttribute("boxStartNum", boxStartNum);
		request.setAttribute("boxEndNum", boxEndNum);
		request.setAttribute("boxStartNumBeforePage", boxStartNumBeforePage);
		request.setAttribute("boxEndNumAfterPage", boxEndNumAfterPage);
		request.setAttribute("boxStartNumBeforePageBtnNeedToShow", boxStartNumBeforePageBtnNeedToShow);
		request.setAttribute("boxEndNumAfterPageBtnNeedToShow", boxEndNumAfterPageBtnNeedToShow);

		return "usr/article/search";
	}

}