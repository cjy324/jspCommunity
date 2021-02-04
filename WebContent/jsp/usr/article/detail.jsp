<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물 상세보기" />
<%@ include file="../../part/head.jspf"%>

<script>

let replyForm_submited = false;

function checkWrite(form){

	if(replyForm_submited){

		alert('처리중입니다.');
			return;
	}

	if(form.reply.value.trim().length == 0){
		alert("댓글을 입력하세요.")
		form.reply.focus();

		return false;
	}


	form.submit();
	replyForm_submited = true;
}


let replyModifyForm_submited = false;

function checkModify(replyModifyForm){

	if(replyModifyForm_submited){

		alert('처리중입니다.');
			return;
	}

	if(replyModifyForm.body.value.trim().length == 0){
		alert("댓글을 입력하세요.")
		replyModifyForm.body.focus();

		return false;
	}


	replyModifyForm.submit();
	replyModifyForm_submited = true;
}

</script>



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
								<div class="article-detail-cell-likesCount flex">
                    <form class="updateLikesCount" action="updateLikesCount" method="POST">
                      <input type="hidden" name="articleId" value="${article.id}">
                      <input type="hidden" name="memberId" value="${loginedMemberId}">
                      <input type="hidden" name="addLike" value="1">
                      <button class="btn addLike" type="submit">
                        <i class="far fa-thumbs-up"></i>
                        &nbsp;<span class="likesCount">${article.likesCount}</span>
                      </button>
                    </form>
                    <form class="updateLikesCount" action="updateLikesCount" method="POST">
                      <input type="hidden" name="articleId" value="${article.id}">
                      <input type="hidden" name="memberId" value="${loginedMemberId}">
                      <input type="hidden" name="addUnLike" value="-1">
                      <button class="btn btn-warning addUnLike" name="addUnLike" type="submit">
                        <i class="far fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${article.unLikesCount}</span>
                      </button>
                    </form>
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
						<c:if test="${loginedMemberId == article.extra_memberId}">	
							<button class="btn" type="button">
								<a href="doModifyForm?id=${article.id}&boardId=${article.boardId}&title=${article.title}&body=${article.body}">수정</a>
							</button>
							<button class="btn btn-warning" onclick="if(confirm('정말 삭제하시겠습니까?') == false) {return false;}" type="button">
								<a href="doDelete?id=${article.id}">삭제</a>
							</button>
						</c:if>
					</div>
				</div>
			</div>
		</section>
		<!-- 메인-상세페이지 끝 -->
		
		<!-- 댓글창 시작 -->    
		<c:if test="${isLogined}">
          <div class="reply-write-box">
            <form class="reply-write-box-form flex flex-jc-c flex-ai-c" name="form" onsubmit="checkWrite(this); return false;" action="reply" method="POST">
              <input type="hidden" name="articleId" value="${article.id}">
              <input type="hidden" name="memberId" value="${loginedMemberId}">
              <span>댓글</span>
              <input type="text" name="replyBody" maxlength="50" placeholder="댓글 입력">
              <div class="reply-write-box-form__option ">          
                <button class="btn" type="submit">등록</button>
              </div>
            </form>
          </div>
          </c:if>
          <c:forEach var="reply" items="${replies}">
            <div class="reply-list-box">
              <div class="reply-list-box-writer">${reply.extra_memberNickname}</div>
              <div class="reply-list-box__cell flex flex-jc-sb">
               <div class="reply-list-box__cell-contents flex flex-ai-c">
                 <div class="reply-list-box__cell-updateDate">${reply.updateDate}</div>
                 <div class="reply-list-box__cell-body">${reply.body}</div>                  
               </div> 
               <div class="reply-list-box-cell__option">
               <c:if test="${loginedMemberId == reply.memberId}">
                <form class="replyModifyForm flex flex-jc-a" name="replyModifyForm" action="doModifyReply" method="POST" onsubmit="checkModify(this); return false;">
                  <input type="hidden" name="id" value="${reply.id}">
                  <input type="hidden" name="relId" value="${reply.relId}">
                  <input type="hidden" name="memberId" value="${loginedMemberId}">
                  <input class="replyBodyInput" type="text" name="body" maxlength="50" placeholder="${reply.body}" value="${reply.body}">
                  <div class="replyModifyForm__option flex flex-jc-fe">
                    <button class="btn" type="submit" onclick="if(confirm('해당 내용으로 수정하시겠습니까?') == false) {return false;}">수정</button>
                    <button class="btn btn-back" type="button" onclick="location.reload()">취소</button>
                  </div>
                </form>
                <div class="reply-list-box-cell__option-btns flex flex-ai-c">
                <button class="btn doModifyReplyForm" type="button">수정</button>
                <button class="btn btn-warning" onclick="if(confirm('정말 삭제하시겠습니까?') == false) {return false;}" type="button">
                  <a href="doDeleteReply?id=${reply.id}&relId=${reply.relId}">삭제</a>
                </button>
                </div>
              </c:if>
            </div>
            </div>
            </div>
          </c:forEach>

        </div>

        <div class="reply-page-menu-section">
          <div class="reply-page-menu">
            <ul class="flex flex-jc-c">


              <c:if test="${boxStartNumBeforePageBtnNeedToShow}">
                <c:set var="aUrl" value="?id=${param.id}&page=${boxStartNumBeforePage}" />
                <li class="before-btn"><a href="${aUrl}" class="flex flex-ai-c"> &lt; 이전</a></li>
              </c:if>
              <c:forEach var="i" begin="${boxStartNum}" end="${boxEndNum}" step="1">
                <c:set var="aClass" value="${page == i ? 'article-page-menu__link--selected' : '???' }" />
                <c:set var="aUrl" value="?id=${param.id}&page=${i}" />
                <li><a href="${aUrl}" class="page-btn flex flex-ai-c ${aClass}">${i}</a></li>
              </c:forEach>
              <c:if test="${boxEndNumAfterPageBtnNeedToShow}">
                <c:set var="aUrl" value="?id=${param.id}&page=${boxEndNumAfterPage}" />
                <li class="after-btn"><a href="${aUrl}" class="flex flex-ai-c">다음 &gt;</a></li>
              </c:if>


            </ul>
          </div>
 

      <!-- 댓글창 끝 -->
		
		<!-- 메인-상세 하단 메뉴 시작 -->
		<section class="section-3 con-min-width">
			<div class="con">
				<div class="article-detail-bottom-cell flex flex-jc-c">
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