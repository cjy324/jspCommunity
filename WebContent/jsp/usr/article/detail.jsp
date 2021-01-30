<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물 상세보기" />
<%@ include file="../../part/head.jspf"%>

<!-- 메인 컨텐츠 박스 시작 -->
<main class="main-box flex-grow-1">
	<section class="main-box-section con">
		<!-- 메인-상세페이지 시작 -->
		<section class="section-2 min-height-50vh">
			<div class="height-100p">
				<div class="article-detail-cell height-100p">
					<div class="article-detail-cell__board-name">
						<div>
							<span>${article.extra_boardName} Board</span>
						</div>
					</div>
					<div class="article-detail-cell__head-contents-box">
						<div class="article-detail-cell__head-contents-1 flex">

							<div class="article-detail-cell__id">
								<div>
									<span>번호 : </span>
									<span>${article.id}</span>
								</div>
							</div>
							<div class="article-detail-cell__memberNickname">
								<div>
									<span>작성자 : </span>
									<span>${article.extra_memberNickname}</span>
								</div>
							</div>
							<div class="article-detail-cell__reg-date">
								<div>
									<span>작성일 : </span>
									<span>${article.regDate}</span>
								</div>
							</div>
							<div class="article-detail-cell__update-date">
								<div>
									<span> 수정일 : </span>
									<span>${article.updateDate}</span>
								</div>
							</div>

						</div>

						<div class="article-detail-cell__head-contents-2 flex flex-jc-sb">
							<div class="article-detail-cell__title">
								<div>
									<span>제목 : </span>
									<span>${article.title}</span>
								</div>
							</div>
							<div class="article-detail-cell__title-contents flex flex-jc-fe">
								<div class="article-detail-cell-hitsCount">
									<i class="far fa-eye"></i>
									<span>${article.hitsCount}</span>
								</div>
								<div class="article-detail-cell-likesCount">
									<i class="far fa-thumbs-up"></i>
									<span>30</span>
								</div>
								<div class="article-detail-cell-commentsCount">
									<i class="far fa-comment-dots"></i>
									<span>50</span>
								</div>
							</div>
						</div>
					</div>
					
					 <script type = "text/x-template">
				${articleBody}
			</script>
		<div class="article-detail-cell__body height-70p toast-ui-viewer">
            </div>
					<div class="article-detail-cell__tag flex">
						<nav>
							#
							<a href="#" target="_blank">tag1</a>
						</nav>
						<nav>
							#
							<a href="#" target="_blank">tag2</a>
						</nav>
						<nav>
							#
							<a href="#" target="_blank">tag3</a>
						</nav>
					</div>
					<div class="article-detail-cell__option flex flex-jc-fe">
						<c:if test="${sessionScope.loginedMemberId > 0}">
							<button class="btn" type="button">
								<a href="doModifyForm?id=${article.id}&boardId=${article.boardId}&title=${article.title}&body=${article.body}">수정</a>
							</button>
							<button class="btn btn-warning"
								onclick="if(confirm('정말 삭제하시겠습니까?') == false) {return false;}"
								type="button">
								<a href="doDelete?id=${article.id}">삭제</a>
							</button>
						</c:if>
					</div>
				</div>
			</div>
		</section>
		<!-- 메인-상세페이지 끝 -->
		<!-- 메인-상세 하단 메뉴 시작 -->
		<section class="section-3 con-min-width">
			<div class="con">
				<div class="article-list-bottom-cell flex flex-jc-c">
					${beforeArticleBtn}
					<div class="./">
						<a href="list?boardId=${article.boardId}">
							<i class="fas fa-th-list"></i>
							목록
						</a>
					</div>
					${afterArticleBtn}
				</div>
			</div>
		</section>
		<!-- 메인-상세 하단 메뉴 끝 -->
	</section>
</main>
<!-- 메인 컨텐츠 박스 끝 -->

<%@ include file="../../part/foot.jspf"%>