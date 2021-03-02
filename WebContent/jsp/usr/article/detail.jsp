<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.sbs.example.util.Util"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="GetIT | ${article.title}" />
<%@ include file="../../part/head.jspf"%>


<script>
 	/* 새 댓글 하이라이팅 시작 */
	$(function() {
		if ( param.focusReplyId ) {
			const $target = $('.reply-list-box div[data-id="' + param.focusReplyId + '"]');
			const $target2 = $('.mobile-reply-list-box div[data-id="' + param.focusReplyId + '"]');
			$target.addClass('focus');
			$target2.addClass('focus');
		
			setTimeout(function() {
				const targetOffset = $target.offset();
				const target2Offset = $target2.offset();
				
				$(window).scrollTop(targetOffset.top - 300);
				//$(window).scrollTop(target2Offset.top - 100);
				
				setTimeout(function() {
					$target.removeClass('focus');
					$target2.removeClass('focus');
				}, 1000);
			}, 1000);
		}
	});
	/* 새 댓글 하이라이팅 끝 */
	
	/* 조회수 증가(feat.로컬스토리지) 시작 */
 		window.onload = function loadHitsCount(){
		
		const hitId = localStorage.getItem("hitId");
		const id = ${article.id};
		const hitCount = localStorage.getItem("hitCount");
		//새로고침 여부 확인
		if(hitId == id && hitCount == 1){
			return;
		}
		//10000초 후 addHitCounts() 실행(조회수 증가)
		setTimeout(addHitCounts, 10000);
		
		//localStorage에 현재 url 저장
		localStorage.setItem("hitId", id);
		localStorage.setItem("hitCount", 1);
		//localStorage에 오늘 날짜 저장
		const today = new Date().getDate();
		localStorage.setItem("lastVisitDay", today); 
	
 	 	} 
	function addHitCounts(){
		const articleId = ${article.id};
		$.post(
				'addHitCounts',  //요청할 주소
				{		
					articleId     //데이터 보내기
				},
				function(data){},			
				'json'
			);
		}
	function removeLocalstorage(){
		localStorage.removeItem("hitId");
		alert('기록삭제');
	}
	/* 조회수 증가(feat.로컬스토리지) 끝 */
	
	
</script>

<script>
/* 좋아요, 싫어요 ajax 시작 */
function addLike_submit(form) {
	$.post(
		'../like/doLike',
		 {
		relTypeCode : form.relTypeCode.value,
		relId : form.relId.value
	}, 
	function(data) {
		alert(data.msg);
	}, 
	'json'
	);
};
function cancelLike_submit(form) {
	$.post(
		'../like/doCancelLike',
		 {
		relTypeCode : form.relTypeCode.value,
		relId : form.relId.value
	}, 
	function(data) {
		alert(data.msg);
	}, 
	'json'
	);
};
function addUnLike_submit(form) {
	$.post(
		'../like/doDislike',
		 {
		relTypeCode : form.relTypeCode.value,
		relId : form.relId.value
	}, 
	function(data) {
		alert(data.msg);
	}, 
	'json'
	);
};
function cancelUnLike_submit(form) {
	$.post(
		'../like/doCancelDislike',
		 {
		relTypeCode : form.relTypeCode.value,
		relId : form.relId.value
	}, 
	function(data) {
		alert(data.msg);
	}, 
	'json'
	);
};
/* 좋아요, 싫어요 ajax 끝 */
</script>	


<script>
let replyForm_submited = false;
function checkWrite(form){
	if(replyForm_submited){
		alert('처리중입니다.');
			return;
	}
	if(form.replyBody.value.trim().length == 0){
		alert("내용을 입력하세요.")
		form.replyBody.focus();
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
		alert("내용을 입력하세요.")
		replyModifyForm.body.focus();
		return false;
	}
	replyModifyForm.submit();
	replyModifyForm_submited = true;
}
</script>



<!-- 메인 컨텐츠 박스 시작 -->
<main class="main-box flex-grow-1 visible-md-up">
  <section class="main-box-section con">
    <!-- 메인-상세페이지 시작 -->
    <section class="section-2 min-height-50vh">
      <div class="height-100p">
        <div class="article-detail-cell height-100p">
          <div class="article-detail-cell__board-name">
            <div>
              <span>${article.extra_boardName}</span>
            </div>
          </div>
          <div class="article-detail-cell__head-contents-box">
            <div class="article-detail-cell__head-contents-1 flex">
              <div class="article-detail-cell__id">
                <div>
                  <span>No. </span>
                  <span>${article.id}</span>
                </div>
              </div>
              <div class="article-detail-cell__memberNickname">
                <div>
                  <span><i class="far fa-user-circle"></i> </span>
                  <span>${article.extra_memberNickname}</span>
                </div>
              </div>
              <div class="article-detail-cell__reg-date">
                <div>
                  <span><i class="far fa-clock"></i> </span>
                  <span>${article.regDate}</span>
                </div>
              </div>
              <div class="article-detail-cell__update-date">
                <div>
                  <span><i class="fas fa-clock"></i> </span>
                  <span>${article.updateDate}</span>
                </div>
              </div>

            </div>

            <div class="article-detail-cell__head-contents-2 flex flex-jc-sb flex-ai-c">
              <div class="article-detail-cell__title">
                <div>
                  <span>${article.title}</span>
                </div>
              </div>
              <div class="article-detail-cell__title-contents flex flex-jc-fe flex-ai-c">
                <div class="article-detail-cell-hitsCount">
                  <i class="far fa-eye"></i>&nbsp;
                  <span>${article.hitsCount}</span>
                </div>
                <div class="article-detail-cell-likesCount flex">
                
                	<c:if test="${isLogined == false}">
                	<form>
                		<button class="addLike" type="button" onclick="alert('로그인 후 이용해 주세요.')">
                        	<i class="far fa-thumbs-up"></i>
                        	&nbsp;<span class="likesCount">${article.extra_likeOnlyPoint}</span>
                    	</button>
                    </form>	
                    <form>
                    	<button class="addUnLike" type="button" onclick="alert('로그인 후 이용해 주세요.')">
                        	<i class="far fa-thumbs-down"></i>
                        	&nbsp;<span class="unLikesCount">${article.extra_dislikeOnlyPoint}</span>
                    	</button>
                    </form>
					</c:if>	
					
                  <c:if test="${article.extra.actorCanLike}">
					
					<form class="addLike" name="form" onsubmit="addLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="article">
          				<input type="hidden" name="relId" value="${article.id}">
                    	<button class="addLike" type="submit" onclick="if ( !confirm('`좋아요` 처리 하시겠습니까?') ) return false;">
                       		<i class="far fa-thumbs-up"></i>
                       		&nbsp;<span class="likesCount">${article.extra_likeOnlyPoint}</span>
                   		</button>
					</form>
                  </c:if>

                  <c:if test="${article.extra.actorCanCancelLike}">
                  <form class="addLike" name="form" onsubmit="cancelLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="article">
          				<input type="hidden" name="relId" value="${article.id}">
                    <button class="addLike" type="submit" onclick="if ( !confirm('`좋아요`를 취소 처리 하시겠습니까?') ) return false;">
                        <i class="fas fa-thumbs-up"></i>
                        &nbsp;<span class="likesCount">${article.extra_likeOnlyPoint}</span>
                    </button>
                    </form>
                  </c:if>

                  <c:if test="${article.extra.actorCanDislike}">
                  <form class="addUnLike" name="form" onsubmit="addUnLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="article">
          				<input type="hidden" name="relId" value="${article.id}">
                    <button class="addUnLike" type="submit" onclick="if ( !confirm('`싫어요` 처리 하시겠습니까?') ) return false;">
                        <i class="far fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${article.extra_dislikeOnlyPoint}</span>
                    </button>
                    </form>
                  </c:if>

                  <c:if test="${article.extra.actorCanCancelDislike}">
                  <form class="addUnLike" name="form" onsubmit="cancelUnLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="article">
          				<input type="hidden" name="relId" value="${article.id}">
                    <button class="addUnLike" type="submit" onclick="if ( !confirm('`싫어요`를 취소 처리 하시겠습니까?') ) return false;">
                        <i class="fas fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${article.extra_dislikeOnlyPoint}</span>
                    </button>
                    </form>
                  </c:if>

                </div>
              </div>
            </div>
          </div>

          <script type="text/x-template">
            ${articleBody}
			</script>
          <div class="article-detail-cell__body height-70p toast-ui-viewer">
          </div>
          <div class="article-detail-cell__option flex flex-jc-fe">
            <c:if test="${loginedMemberId == article.extra_memberId}">
              <button class="btn btn-modify" type="button">
                <a href="doModifyForm?id=${article.id}"><i class="far fa-edit"></i> 수정</a>
              </button>
              <button class="btn btn-back" onclick="if(confirm('정말 삭제하시겠습니까?') == false) {return false;}" type="button">
                <a href="doDelete?id=${article.id}"><i class="far fa-trash-alt"></i> 삭제</a>
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
          <input type="hidden" name="redirectUrl"
				value="${Util.getNewUrl(currentUrl, 'focusReplyId', '[NEW_REPLY_ID]')}" />
          <span><i class="far fa-comment-dots"></i></span>
          <input type="text" name="replyBody" placeholder="댓글 입력">
          <div class="reply-write-box-form__option ">
            <button class="btn btn-go" type="submit"><i class="fas fa-pen"></i> 등록</button>
          </div>
        </form>
      </div>
    </c:if>
    <div class="reply-count-box"><i class="far fa-comments"></i> Total ${article.repliesCount}</div>
    <c:forEach var="reply" items="${replies}">
      <div class="reply-list-box">
        <div class="reply-list-box-writer"><i class="far fa-user-circle"></i> ${reply.extra_memberNickname}</div>
        <div class="reply-list-box__cell flex flex-jc-sb">
          <div class="reply-list-box__cell-contents flex flex-ai-fs">
            <div class="reply-list-box__cell-updateDate">${reply.updateDate}</div>
            <div data-id="${reply.id}" class="reply-list-box__cell-body">${reply.body}</div>
            <nav class="reply-list-box__cell-reReply"><i class="fas fa-reply"></i> 답글쓰기</nav>
          </div>
          <div class="reply-list-box-cell__option">
          <div class="reply-detail-cell-likesCount flex flex-jc-fe">
				
				<c:if test="${isLogined == false}">
                	<form>
                		<button class="addLike" type="button" onclick="alert('로그인 후 이용해 주세요.')">
                        	<i class="far fa-thumbs-up"></i>
                        	&nbsp;<span class="likesCount">${reply.extra_likeOnlyPoint}</span>
                    	</button>
                    	<button class="addUnLike" type="button" onclick="alert('로그인 후 이용해 주세요.')">
                        	<i class="far fa-thumbs-down"></i>
                        	&nbsp;<span class="unLikesCount">${reply.extra_dislikeOnlyPoint}</span>
                    	</button>
                    </form>
				</c:if>	
				
                  <c:if test="${reply.extra.actorCanLike}">
					
					<form class="addLike" name="form" onsubmit="addLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="reply">
          				<input type="hidden" name="relId" value="${reply.id}">
                    	<button class="addLike" type="submit" onclick="if ( !confirm('`좋아요` 처리 하시겠습니까?') ) return false;">
                       		<i class="far fa-thumbs-up"></i>
                       		&nbsp;<span class="likesCount">${reply.extra_likeOnlyPoint}</span>
                   		</button>
					</form>
                  </c:if>

                  <c:if test="${reply.extra.actorCanCancelLike}">
                  <form class="addLike" name="form" onsubmit="cancelLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="reply">
          				<input type="hidden" name="relId" value="${reply.id}">
                    <button class="addLike" type="submit" onclick="if ( !confirm('`좋아요`를 취소 처리 하시겠습니까?') ) return false;">
                        <i class="fas fa-thumbs-up"></i>
                        &nbsp;<span class="likesCount">${reply.extra_likeOnlyPoint}</span>
                    </button>
                    </form>
                  </c:if>

                  <c:if test="${reply.extra.actorCanDislike}">
                  <form class="addUnLike" name="form" onsubmit="addUnLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="reply">
          				<input type="hidden" name="relId" value="${reply.id}">
                    <button class="addUnLike" type="submit" onclick="if ( !confirm('`싫어요` 처리 하시겠습니까?') ) return false;">
                        <i class="far fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${reply.extra_dislikeOnlyPoint}</span>
                    </button>
                    </form>
                  </c:if>

                  <c:if test="${reply.extra.actorCanCancelDislike}">
                  <form class="addUnLike" name="form" onsubmit="cancelUnLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="reply">
          				<input type="hidden" name="relId" value="${reply.id}">
                    <button class="addUnLike" type="submit" onclick="if ( !confirm('`싫어요`를 취소 처리 하시겠습니까?') ) return false;">
                        <i class="fas fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${reply.extra_dislikeOnlyPoint}</span>
                    </button>
                    </form>
                  </c:if>

                </div>
            <c:if test="${loginedMemberId == reply.memberId}">
              <form class="replyModifyForm flex flex-jc-a" name="replyModifyForm" action="doModifyReply" method="POST" onsubmit="checkModify(this); return false;">
                <input type="hidden" name="id" value="${reply.id}">
                <input type="hidden" name="relId" value="${reply.relId}">
                <input type="hidden" name="memberId" value="${loginedMemberId}">
                <input type="hidden" name="redirectUrl"
				value="${Util.getNewUrl(currentUrl, 'focusReplyId', '[NEW_REPLY_ID]')}" />
                <input class="replyBodyInput" type="text" name="body" placeholder="${reply.body}" value="${reply.body}">
                <div class="replyModifyForm__option flex flex-jc-fe">
                  <button class="btn btn-modify" type="submit" onclick="if(confirm('해당 내용으로 수정하시겠습니까?') == false) {return false;}"><i class="far fa-edit"></i> 수정</button>
                  <button class="btn btn-back" type="button" onclick="location.reload()"><i class="fas fa-ban"></i> 취소</button>
                </div>
              </form>
             
              <div class="reply-list-box-cell__option-btns flex flex-ai-c">
                <button class="btn btn-modify doModifyReplyForm" type="button"><i class="far fa-edit"></i> 수정</button>
                <button class="btn btn-back" onclick="if(confirm('정말 삭제하시겠습니까?') == false) {return false;}" type="button">
                  <a href="doDeleteReply?id=${reply.id}&articleId=${article.id}&relId=${reply.relId}"><i class="far fa-trash-alt"></i> 삭제</a>
                </button>
              </div>
            </c:if>
          </div>
        </div>
        
        <!-- 대댓글창 시작 -->
          <div class="reReply-write-box">
            <form class="reReply-write-box-form flex flex-jc-fe flex-ai-c" name="form" onsubmit="checkWrite(this); return false;" action="reply" method="POST">
              <input type="hidden" name="replyId" value="${reply.id}">
              <input type="hidden" name="memberId" value="${loginedMemberId}">
              <input type="hidden" name="redirectUrl"
				value="${Util.getNewUrl(currentUrl, 'focusReplyId', '[NEW_REPLY_ID]')}" />
              <span><i class="fas fa-reply"></i></span>
              <input type="text" name="replyBody" maxlength="50" placeholder="댓글 입력">
              <div class="reReply-write-box-form__option ">
                <button class="btn btn-go" type="submit"><i class="fas fa-pen"></i> 등록</button>
              </div>
            </form>
          </div>
        </div> 
            <c:forEach var="reReply" items="${reReplies}">
            <c:if test="${reReply.relId == reply.id}">
          <div class="reReply-list-box">
            <div class="reReply-list-box-writer"><i class="fas fa-reply"></i><i class="far fa-user-circle"></i> ${reReply.extra_memberNickname}</div>
            <div class="reReply-list-box__cell flex flex-jc-sb">
              <div class="reReply-list-box__cell-contents flex flex-ai-fs">
                <div class="reReply-list-box__cell-updateDate">${reReply.updateDate}</div>
                <div class="reReply-list-box__cell-body">${reReply.body}</div>
              </div>
              
              <div class="reReply-list-box-cell__option">
                <div class="reReply-detail-cell-likesCount flex flex-jc-fe">
				
				<c:if test="${isLogined == false}">
                	<form>
                		<button class="addLike" type="button" onclick="alert('로그인 후 이용해 주세요.')">
                        	<i class="far fa-thumbs-up"></i>
                        	&nbsp;<span class="likesCount">${reReply.extra_likeOnlyPoint}</span>
                    	</button>
                    	<button class="addUnLike" type="button" onclick="alert('로그인 후 이용해 주세요.')">
                        	<i class="far fa-thumbs-down"></i>
                        	&nbsp;<span class="unLikesCount">${reReply.extra_dislikeOnlyPoint}</span>
                    	</button>
                    </form>
				</c:if>
					
                  <c:if test="${reReply.extra.actorCanLike}">
					
					<form class="addLike" name="form" onsubmit="addLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="reply">
          				<input type="hidden" name="relId" value="${reReply.id}">
                    	<button class="addLike" type="submit" onclick="if ( !confirm('`좋아요` 처리 하시겠습니까?') ) return false;">
                       		<i class="far fa-thumbs-up"></i>
                       		&nbsp;<span class="likesCount">${reReply.extra_likeOnlyPoint}</span>
                   		</button>
					</form>
                  </c:if>

                  <c:if test="${reReply.extra.actorCanCancelLike}">
                  <form class="addLike" name="form" onsubmit="cancelLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="reply">
          				<input type="hidden" name="relId" value="${reReply.id}">
                    <button class="addLike" type="submit" onclick="if ( !confirm('`좋아요`를 취소 처리 하시겠습니까?') ) return false;">
                        <i class="fas fa-thumbs-up"></i>
                        &nbsp;<span class="likesCount">${reReply.extra_likeOnlyPoint}</span>
                    </button>
                    </form>
                  </c:if>

                  <c:if test="${reReply.extra.actorCanDislike}">
                  <form class="addUnLike" name="form" onsubmit="addUnLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="reply">
          				<input type="hidden" name="relId" value="${reReply.id}">
                    <button class="addUnLike" type="submit" onclick="if ( !confirm('`싫어요` 처리 하시겠습니까?') ) return false;">
                        <i class="far fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${reReply.extra_dislikeOnlyPoint}</span>
                    </button>
                    </form>
                  </c:if>

                  <c:if test="${reReply.extra.actorCanCancelDislike}">
                  <form class="addUnLike" name="form" onsubmit="cancelUnLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="reply">
          				<input type="hidden" name="relId" value="${reReply.id}">
                    <button class="addUnLike" type="submit" onclick="if ( !confirm('`싫어요`를 취소 처리 하시겠습니까?') ) return false;">
                        <i class="fas fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${reReply.extra_dislikeOnlyPoint}</span>
                    </button>
                    </form>
                  </c:if>

                </div>
                <c:if test="${loginedMemberId == reReply.memberId}">
                  <form class="reReplyModifyForm flex flex-jc-a" name="replyModifyForm" action="doModifyReply" method="POST" onsubmit="checkModify(this); return false;">
                    <input type="hidden" name="id" value="${reReply.id}">
                    <input type="hidden" name="relId" value="${reReply.relId}">
                    <input type="hidden" name="memberId" value="${loginedMemberId}">
                    <input type="hidden" name="redirectUrl"
				value="${Util.getNewUrl(currentUrl, 'focusReplyId', '[NEW_REPLY_ID]')}" />
                    <input class="reReplyBodyInput" type="text" name="body" maxlength="50" placeholder="${reReply.body}" value="${reReply.body}">
                    <div class="reReplyModifyForm__option flex flex-jc-fe">
                      <button class="btn btn-modify" type="submit" onclick="if(confirm('해당 내용으로 수정하시겠습니까?') == false) {return false;}"><i class="far fa-edit"></i> 수정</button>
                      <button class="btn btn-back" type="button" onclick="location.reload()"><i class="fas fa-ban"></i> 취소</button>
                    </div>
                  </form>              
                  <div class="reReply-list-box-cell__option-btns flex flex-ai-c">
                    <button class="btn btn-modify doModifyreReplyForm" type="button"><i class="far fa-edit"></i> 수정</button>
                    <button class="btn btn-back" onclick="if(confirm('정말 삭제하시겠습니까?') == false) {return false;}" type="button">
                      <a href="doDeleteReply?id=${reReply.id}&articleId=${article.id}&relId=${reReply.relId}"><i class="far fa-trash-alt"></i> 삭제</a>
                    </button>
                  </div>
                </c:if>
              </div>
            </div>
          </div>
          </c:if>
        </c:forEach>
          <!-- 대댓글창 끝 -->
      </div>
    </c:forEach>
    </div>

    <div class="reply-page-menu-section">
      <div class="reply-page-menu">
        <ul class="flex flex-jc-c">
	   	  <c:if test="${totalPages > 1}"> 
		  <c:set var="aUrl" value="?page=1&id=${param.id}" />
              <li class="before-btn"><a href="${aUrl}" class="flex flex-ai-c">&lt;&lt; </a></li>
          </c:if>
          <c:if test="${boxStartNumBeforePageBtnNeedToShow}">
            <c:set var="aUrl" value="?id=${param.id}&page=${boxStartNumBeforePage}" />
            <li class="before-btn"><a href="${aUrl}" class="flex flex-ai-c"> &lt; </a></li>
          </c:if>
          <c:forEach var="i" begin="${boxStartNum}" end="${boxEndNum}" step="1">
            <c:set var="aClass" value="${page == i ? 'reply-page-menu__link--selected' : '???' }" />
            <c:set var="aUrl" value="?id=${param.id}&page=${i}" />
            <li><a href="${aUrl}" class="page-btn flex flex-ai-c ${aClass}">${i}</a></li>
          </c:forEach>
          <c:if test="${boxEndNumAfterPageBtnNeedToShow}">
            <c:set var="aUrl" value="?id=${param.id}&page=${boxEndNumAfterPage}" />
            <li class="after-btn"><a href="${aUrl}" class="flex flex-ai-c"> &gt;</a></li>
          </c:if>
          <c:if test="${totalPages > 1}"> 
          <c:set var="aUrl" value="?page=${totalPages}&id=${param.id}" />
              <li class="after-btn"><a href="${aUrl}" class="flex flex-ai-c"> &gt;&gt;</a></li>
		  </c:if>
	
        </ul>
      </div>

      <!-- 댓글창 끝 -->

      <!-- 메인-상세 하단 메뉴 시작 -->
      <section class="section-3 con-min-width">
        <div class="con">
          <div class="article-detail-bottom-cell flex flex-jc-c">
            <c:if test ="${beforeArticleBtn}">	
          <div class="./"><a href="../article/detail?id=${beforeArticleIndex}&boardId=${article.boardId}"><i class="fas fa-caret-left"></i> prev</a></div>
        </c:if>
          <div class="./">
            <c:if test="${not empty param.listUrl}">
             <a href="${param.listUrl}">
            </c:if>
            <c:if test="${empty param.listUrl}">
            <a href="../article/list?boardId=${article.boardId}">
            </c:if>
             <i class="fas fa-list"></i> list
            </a>
          </div>
         <c:if test ="${afterArticleBtn}">	
          <div class="./"><a href="../article/detail?id=${afterArticleIndex}&boardId=${article.boardId}">next <i class="fas fa-caret-right"></i> </a></div>
        </c:if>
          </div>
        </div>
      </section>
      <!-- 메인-상세 하단 메뉴 끝 -->
  </section>
</main>
<!-- 메인 컨텐츠 박스 끝 -->

<!-- 모바일 메인 컨텐츠 박스 시작 -->
<main class="main-box flex-grow-1 visible-sm-down" style="min-height:500px;">
  <section class="main-box-section con">
    <!-- 모바일 메인-상세페이지 시작 -->
    <section class="mobile-section-2 min-height-50vh">
      <div class="height-100p">
        <div class="mobile-article-detail-cell height-100p">
          <div class="mobile-article-detail-cell__board-name">
            <div>
              <span>${article.extra_boardName}</span>
            </div>
          </div>

          <div class="mobile-article-detail-cell__head-contents-box">

            <div class="mobile-article-detail-cell__head-contents-2 flex flex-ai-c">
              <div class="mobile-article-detail-cell__title">
                <div class="mobile-article-detail-cell__id">
                  <span>No. ${article.id}</span>
                </div>
                <div>
                  <span>${article.title}</span>
                </div>
                <div class="mobile-article-detail-cell__head-contents-1 flex">
                  <div class="mobile-article-detail-cell__memberNickname">
                    <div>
                      <span><i class="far fa-user-circle"></i> ${article.extra_memberNickname} </span>
                    </div>
                  </div>
                  <div class="mobile-article-detail-cell__update-date">
                    <div>
                      <span> / <i class="fas fa-clock"></i> ${article.updateDate}</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <script type="text/x-template">
            ${articleBody}
			</script>
          <div class="mobile-article-detail-cell__body height-70p toast-ui-viewer">
          </div>
          <div class="mobile-article-detail-cell__body-contents flex flex-jc-fe flex-ai-c">
            <div class="mobile-article-detail-cell-hitsCount"><i class="far fa-eye"></i>&nbsp;<span>${article.hitsCount}</span></div>
            <div class="mobile-article-detail-cell-likesCount flex">
            	
            	<c:if test="${isLogined == false}">
                	<form>
                		<button class="addLike" type="button" onclick="alert('로그인 후 이용해 주세요.')">
                        	<i class="far fa-thumbs-up"></i>
                        	&nbsp;<span class="likesCount">${article.extra_likeOnlyPoint}</span>
                    	</button>
                    </form>
                    <form>
                    	<button class="addUnLike" type="button" onclick="alert('로그인 후 이용해 주세요.')">
                        	<i class="far fa-thumbs-down"></i>
                        	&nbsp;<span class="unLikesCount">${article.extra_dislikeOnlyPoint}</span>
                    	</button>
                    </form>
				</c:if>
				
                  <c:if test="${article.extra.actorCanLike}">
					
					<form class="addLike" name="form" onsubmit="addLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="article">
          				<input type="hidden" name="relId" value="${article.id}">
                    	<button class="addLike" type="submit" onclick="if ( !confirm('`좋아요` 처리 하시겠습니까?') ) return false;">
                       		<i class="far fa-thumbs-up"></i>
                       		&nbsp;<span class="likesCount">${article.extra_likeOnlyPoint}</span>
                   		</button>
					</form>
                  </c:if>

                  <c:if test="${article.extra.actorCanCancelLike}">
                  <form class="addLike" name="form" onsubmit="cancelLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="article">
          				<input type="hidden" name="relId" value="${article.id}">
                    <button class="addLike" type="submit" onclick="if ( !confirm('`좋아요`를 취소 처리 하시겠습니까?') ) return false;">
                        <i class="fas fa-thumbs-up"></i>
                        &nbsp;<span class="likesCount">${article.extra_likeOnlyPoint}</span>
                    </button>
                    </form>
                  </c:if>

                  <c:if test="${article.extra.actorCanDislike}">
                  <form class="addUnLike" name="form" onsubmit="addUnLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="article">
          				<input type="hidden" name="relId" value="${article.id}">
                    <button class="addUnLike" type="submit" onclick="if ( !confirm('`싫어요` 처리 하시겠습니까?') ) return false;">
                        <i class="far fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${article.extra_dislikeOnlyPoint}</span>
                    </button>
                  </form>
                  </c:if>

                  <c:if test="${article.extra.actorCanCancelDislike}">
                  <form class="addUnLike" name="form" onsubmit="cancelUnLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="article">
          				<input type="hidden" name="relId" value="${article.id}">
                    <button class="addUnLike" type="submit" onclick="if ( !confirm('`싫어요`를 취소 처리 하시겠습니까?') ) return false;">
                        <i class="fas fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${article.extra_dislikeOnlyPoint}</span>
                    </button>
                  </form>
                  </c:if>

            </div>
          </div>
        </div>
        <div class="mobile-article-detail-cell__option flex">
          <c:if test="${loginedMemberId == article.extra_memberId}">
            <button class="btn btn-modify" type="button"><a style="text-decoration:none;" href="doModifyForm?id=${article.id}"><i class="far fa-edit"></i> 수정</a></button>
            <button class="btn btn-back" onclick="if(confirm('정말 삭제하시겠습니까?') == false) {return false;}" type="button"><a style="text-decoration:none;" href="doDelete?id=${article.id}"><i class="far fa-trash-alt"></i> 삭제</a></button>
          </c:if>
        </div>
      </div>
      </div>

      <!-- 모바일 댓글창 시작 -->
      <c:if test="${isLogined}">
        <div class="mobile-reply-write-box">
          <form class="mobile-reply-write-box-form flex flex-jc-c flex-ai-c" name="form" onsubmit="checkWrite(this); return false;" action="reply" method="POST">
            <input type="hidden" name="articleId" value="${article.id}">
            <input type="hidden" name="memberId" value="${loginedMemberId}">
            <input type="hidden" name="redirectUrl"
				value="${Util.getNewUrl(currentUrl, 'focusReplyId', '[NEW_REPLY_ID]')}" />
            <span><i class="far fa-comment-dots"></i></span>
            <input type="text" name="replyBody" placeholder="댓글 입력">
            <div class="mobile-reply-write-box-form__option ">
              <button class="m-btn btn-go" type="submit"><i class="fas fa-pen"></i> 등록</button>
            </div>
          </form>
        </div>
      </c:if>
      <div class="mobile-reply-count-box"><i class="far fa-comments"></i> Total ${article.repliesCount}</div>
      <c:forEach var="reply" items="${replies}">
        <div class="mobile-reply-list-box">
          <div class="mobile-reply-list-box-writer"><i class="far fa-user-circle"></i> ${reply.extra_memberNickname}</div>
          <div class="mobile-reply-list-box__cell flex flex-column">
            <div class="mobile-reply-list-box__cell-contents">
              <div class="mobile-reply-list-box__cell-updateDate">${reply.updateDate}</div>
              <div data-id="${reply.id}" class="mobile-reply-list-box__cell-body">${reply.body}</div>
              <nav class="reply-list-box__cell-reReply"><i class="fas fa-reply"></i> 답글쓰기</nav>
            </div>
            <div class="mobile-reply-detail-cell-likesCount flex">
            
				<c:if test="${isLogined == false}">
                	<form>
                		<button class="addLike" type="button" onclick="alert('로그인 후 이용해 주세요.')">
                        	<i class="far fa-thumbs-up"></i>
                        	&nbsp;<span class="likesCount">${reply.extra_likeOnlyPoint}</span>
                    	</button>
                    </form>
                    <form>
                    	<button class="addUnLike" type="button" onclick="alert('로그인 후 이용해 주세요.')">
                        	<i class="far fa-thumbs-down"></i>
                        	&nbsp;<span class="unLikesCount">${reply.extra_dislikeOnlyPoint}</span>
                    	</button>
                    </form>
				</c:if>
					
                  <c:if test="${reply.extra.actorCanLike}">
					
					<form class="addLike" name="form" onsubmit="addLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="reply">
          				<input type="hidden" name="relId" value="${reply.id}">
                    	<button class="addLike" type="submit" onclick="if ( !confirm('`좋아요` 처리 하시겠습니까?') ) return false;">
                       		<i class="far fa-thumbs-up"></i>
                       		&nbsp;<span class="likesCount">${reply.extra_likeOnlyPoint}</span>
                   		</button>
					</form>
                  </c:if>

                  <c:if test="${reply.extra.actorCanCancelLike}">
                  <form class="addLike" name="form" onsubmit="cancelLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="reply">
          				<input type="hidden" name="relId" value="${reply.id}">
                    <button class="addLike" type="submit" onclick="if ( !confirm('`좋아요`를 취소 처리 하시겠습니까?') ) return false;">
                        <i class="fas fa-thumbs-up"></i>
                        &nbsp;<span class="likesCount">${reply.extra_likeOnlyPoint}</span>
                    </button>
                    </form>
                  </c:if>

                  <c:if test="${reply.extra.actorCanDislike}">
                  <form class="addUnLike" name="form" onsubmit="addUnLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="reply">
          				<input type="hidden" name="relId" value="${reply.id}">
                    <button class="addUnLike" type="submit" onclick="if ( !confirm('`싫어요` 처리 하시겠습니까?') ) return false;">
                        <i class="far fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${reply.extra_dislikeOnlyPoint}</span>
                    </button>
                    </form>
                  </c:if>

                  <c:if test="${reply.extra.actorCanCancelDislike}">
                  <form class="addUnLike" name="form" onsubmit="cancelUnLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="reply">
          				<input type="hidden" name="relId" value="${reply.id}">
                    <button class="addUnLike" type="submit" onclick="if ( !confirm('`싫어요`를 취소 처리 하시겠습니까?') ) return false;">
                        <i class="fas fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${reply.extra_dislikeOnlyPoint}</span>
                    </button>
                    </form>
                  </c:if>

                </div>
            <div class="mobile-reply-list-box-cell__option">
              <c:if test="${loginedMemberId == reply.memberId}">
                <form class="mobile-replyModifyForm flex" name="replyModifyForm" action="doModifyReply" method="POST" onsubmit="checkModify(this); return false;">
                  <input type="hidden" name="id" value="${reply.id}">
                  <input type="hidden" name="relId" value="${reply.relId}">
                  <input type="hidden" name="memberId" value="${loginedMemberId}">
                  <input type="hidden" name="redirectUrl"
				value="${Util.getNewUrl(currentUrl, 'focusReplyId', '[NEW_REPLY_ID]')}" />
                  <input class="mobile-replyBodyInput" type="text" name="body" placeholder="${reply.body}" value="${reply.body}">
                  <div class="mobile-replyModifyForm__option flex flex-jc-fe">
                    <button class="btn btn-modify" type="submit" onclick="if(confirm('해당 내용으로 수정하시겠습니까?') == false) {return false;}"><i class="far fa-edit"></i> 수정</button>
                    <button class="btn btn-back" type="button" onclick="location.reload()"><i class="fas fa-ban"></i> 취소</button>
                  </div>
                </form>
                
                <div class="mobile-reply-list-box-cell__option-btns flex flex-ai-c flex-jc-fe">
                  <button class="btn btn-modify doModifyReplyForm" type="button"><i class="far fa-edit"></i> 수정</button>
                  <button class="btn btn-back" onclick="if(confirm('정말 삭제하시겠습니까?') == false) {return false;}" type="button">
                    <a href="doDeleteReply?id=${reply.id}&articleId=${article.id}&relId=${reply.relId}"><i class="far fa-trash-alt"></i> 삭제</a>
                  </button>
                </div>
              </c:if>
            </div>
          </div>
        
        
        <!-- 모바일 대댓글창 시작 -->

          <div class="mobile-reReply-write-box">
            <form class="mobile-reReply-write-box-form flex flex-jc-c flex-ai-c" name="form" onsubmit="checkWrite(this); return false;" action="reply" method="POST">
              <input type="hidden" name="replyId" value="${reply.id}">
              <input type="hidden" name="memberId" value="${loginedMemberId}">
              <input type="hidden" name="redirectUrl"
				value="${Util.getNewUrl(currentUrl, 'focusReplyId', '[NEW_REPLY_ID]')}" />
              <span><i class="fas fa-reply"></i></span>
              <input type="text" name="replyBody" maxlength="50" placeholder="댓글 입력">
              <div class="mobile-reReply-write-box-form__option ">
                <button class="m-btn btn-go" type="submit"><i class="fas fa-pen"></i> 등록</button>
              </div>
            </form>
          </div>
		</div>
        <c:forEach var="reReply" items="${reReplies}">
         <c:if test="${reReply.relId == reply.id}">
          <div class="mobile-reReply-list-box">
            <div class="mobile-reReply-list-box-writer"><i class="fas fa-reply"></i><i class="far fa-user-circle"></i> ${reReply.extra_memberNickname}</div>
            <div class="mobile-reReply-list-box__cell flex flex-column">
              <div class="mobile-reReply-list-box__cell-contents">
                <div class="mobile-reReply-list-box__cell-updateDate">${reReply.updateDate}</div>
                <div class="mobile-reReply-list-box__cell-body">${reReply.body}</div>
              </div>
              </div>
              <div class="mobile-reReply-detail-cell-likesCount flex">
              
				<c:if test="${isLogined == false}">
                	<form>
                		<button class="addLike" type="button" onclick="alert('로그인 후 이용해 주세요.')">
                        	<i class="far fa-thumbs-up"></i>
                        	&nbsp;<span class="likesCount">${reReply.extra_likeOnlyPoint}</span>
                    	</button>
                    	<button class="addUnLike" type="button" onclick="alert('로그인 후 이용해 주세요.')">
                        	<i class="far fa-thumbs-down"></i>
                        	&nbsp;<span class="unLikesCount">${reReply.extra_dislikeOnlyPoint}</span>
                    	</button>
                    </form>
				</c:if>
					
                  <c:if test="${reReply.extra.actorCanLike}">
					
					<form class="addLike" name="form" onsubmit="addLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="reply">
          				<input type="hidden" name="relId" value="${reReply.id}">
                    	<button class="addLike" type="submit" onclick="if ( !confirm('`좋아요` 처리 하시겠습니까?') ) return false;">
                       		<i class="far fa-thumbs-up"></i>
                       		&nbsp;<span class="likesCount">${reReply.extra_likeOnlyPoint}</span>
                   		</button>
					</form>
                  </c:if>

                  <c:if test="${reReply.extra.actorCanCancelLike}">
                  <form class="addLike" name="form" onsubmit="cancelLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="reply">
          				<input type="hidden" name="relId" value="${reReply.id}">
                    <button class="addLike" type="submit" onclick="if ( !confirm('`좋아요`를 취소 처리 하시겠습니까?') ) return false;">
                        <i class="fas fa-thumbs-up"></i>
                        &nbsp;<span class="likesCount">${reReply.extra_likeOnlyPoint}</span>
                    </button>
                    </form>
                  </c:if>

                  <c:if test="${reReply.extra.actorCanDislike}">
                  <form class="addUnLike" name="form" onsubmit="addUnLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="reply">
          				<input type="hidden" name="relId" value="${reReply.id}">
                    <button class="addUnLike" type="submit" onclick="if ( !confirm('`싫어요` 처리 하시겠습니까?') ) return false;">
                        <i class="far fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${reReply.extra_dislikeOnlyPoint}</span>
                    </button>
                    </form>
                  </c:if>

                  <c:if test="${reReply.extra.actorCanCancelDislike}">
                  <form class="addUnLike" name="form" onsubmit="cancelUnLike_submit(this);" method="POST">
          				<input type="hidden" name="relTypeCode" value="reply">
          				<input type="hidden" name="relId" value="${reReply.id}">
                    <button class="addUnLike" type="submit" onclick="if ( !confirm('`싫어요`를 취소 처리 하시겠습니까?') ) return false;">
                        <i class="fas fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${reReply.extra_dislikeOnlyPoint}</span>
                    </button>
                    </form>
                  </c:if>

                </div>
              <div class="mobile-reReply-list-box-cell__option">
                <c:if test="${loginedMemberId == reReply.memberId}">
                  <form class="mobile-reReplyModifyForm flex" name="replyModifyForm" action="doModifyReply" method="POST" onsubmit="checkModify(this); return false;">
                    <input type="hidden" name="id" value="${reReply.id}">
                    <input type="hidden" name="relId" value="${reReply.relId}">
                    <input type="hidden" name="memberId" value="${loginedMemberId}">
                    <input type="hidden" name="redirectUrl"
				value="${Util.getNewUrl(currentUrl, 'focusReplyId', '[NEW_REPLY_ID]')}" />
                    <input class="mobile-reReplyBodyInput" type="text" name="body" placeholder="${reReply.body}" value="${reReply.body}">
                    <div class="mobile-reReplyModifyForm__option flex flex-jc-fe">
                      <button class="btn btn-modify" type="submit" onclick="if(confirm('해당 내용으로 수정하시겠습니까?') == false) {return false;}"><i class="far fa-edit"></i> 수정</button>
                      <button class="btn btn-back" type="button" onclick="location.reload()"><i class="fas fa-ban"></i> 취소</button>
                    </div>
                  </form>
                  <div class="mobile-reReply-list-box-cell__option-btns flex flex-ai-c flex-jc-fe">
                    <button class="btn btn-modify doModifyreReplyForm" type="button"><i class="far fa-edit"></i> 수정</button>
                    <button class="btn btn-back" onclick="if(confirm('정말 삭제하시겠습니까?') == false) {return false;}" type="button">
                      <a href="doDeleteReply?id=${reReply.id}&articleId=${article.id}&relId=${reReply.relId}"><i class="far fa-trash-alt"></i> 삭제</a>
                    </button>
                  </div>
                </c:if>
              </div>
            </div>
          
          </c:if>
        </c:forEach>
        </div>
          <!-- 모바일 대댓글창 끝 -->
        
        
        </div>
      </c:forEach>
      </div>
      <div class="mobile-reply-page-menu-section">
        <div class="mobile-reply-page-menu">
          <ul class="flex flex-jc-c">
			<c:if test="${totalPages > 1}"> 
			<c:set var="aUrl" value="?page=1&id=${param.id}" />
              <li class="before-btn"><a href="${aUrl}" class="flex flex-ai-c">&lt;&lt; </a></li>
            </c:if>  
            <c:if test="${boxStartNumBeforePageBtnNeedToShow}">
              <c:set var="aUrl" value="?id=${param.id}&page=${boxStartNumBeforePage}" />
              <li class="before-btn"><a href="${aUrl}" class="flex flex-ai-c"> &lt; </a></li>
            </c:if>
            <c:forEach var="i" begin="${boxStartNum}" end="${boxEndNum}" step="1">
              <c:set var="aClass" value="${page == i ? 'mobile-reply-page-menu__link--selected' : '???' }" />
              <c:set var="aUrl" value="?id=${param.id}&page=${i}" />
              <li><a href="${aUrl}" class="page-btn flex flex-ai-c ${aClass}">${i}</a></li>
            </c:forEach>
            <c:if test="${boxEndNumAfterPageBtnNeedToShow}">
              <c:set var="aUrl" value="?id=${param.id}&page=${boxEndNumAfterPage}" />
              <li class="after-btn"><a href="${aUrl}" class="flex flex-ai-c"> &gt;</a></li>
            </c:if>
            <c:if test="${totalPages > 1}"> 
            <c:set var="aUrl" value="?page=${totalPages}&id=${param.id}" />
              <li class="after-btn"><a href="${aUrl}" class="flex flex-ai-c"> &gt;&gt;</a></li>
			</c:if>
          </ul>
        </div>
        <!-- 모바일 댓글창 끝 -->
    </section>
    <!-- 모바일 메인-상세페이지 끝 -->

    <!-- 모바일 메인-상세 하단 메뉴 시작 -->
    <section class="mobile-section-3 con-min-width">
      <div class="con">
        <div class="mobile-article-detail-bottom-cell flex flex-jc-c">
        <c:if test ="${beforeArticleBtn}">	
          <div class="./"><a href="../article/detail?id=${beforeArticleIndex}&boardId=${article.boardId}"><i class="fas fa-caret-left"></i> prev</a></div>
        </c:if>
          <div class="./">
            <c:if test="${not empty param.listUrl}">
             <a href="${param.listUrl}">
            </c:if>
            <c:if test="${empty param.listUrl}">
            <a href="../article/list?boardId=${article.boardId}">
            </c:if>
             <i class="fas fa-list"></i> list
            </a>
          </div>
         <c:if test ="${afterArticleBtn}">	
          <div class="./"><a href="../article/detail?id=${afterArticleIndex}&boardId=${article.boardId}">next <i class="fas fa-caret-right"></i> </a></div>
        </c:if>
          
        </div>
      </div>
    </section>
    <!-- 모바일 메인-상세 하단 메뉴 끝 -->
  </section>
</main>
<!-- 모바일 메인 컨텐츠 박스 끝 -->


<%@ include file="../../part/foot.jspf"%>