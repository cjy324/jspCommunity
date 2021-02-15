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
<main class="main-box flex-grow-1 visible-md-up">
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

            <div class="article-detail-cell__head-contents-2 flex flex-jc-sb flex-ai-c">
              <div class="article-detail-cell__title">
                <div>
                  <span>제목 : </span>
                  <span>${article.title}</span>
                </div>
              </div>
              <div class="article-detail-cell__title-contents flex flex-jc-fe flex-ai-c">
                <div class="article-detail-cell-hitsCount">
                  <i class="far fa-eye"></i>
                  <span>${article.hitsCount}</span>
                </div>
                <div class="article-detail-cell-likesCount flex">
                	<c:if test="${isLogined == false}">
                		<button class="addLike" type="button" onclick="alert('로그인 후 이용해 주세요.')">
                        <i class="far fa-thumbs-up"></i>
                        &nbsp;<span class="likesCount">${article.extra_likeOnlyPoint}</span>
                      </a>
                    </button>
                    <button class="addUnLike" type="button" onclick="alert('로그인 후 이용해 주세요.')">
                        <i class="far fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${article.extra_dislikeOnlyPoint}</span>
                      </a>
                    </button>
					</c:if>
                  <c:if test="${article.extra.actorCanLike}">

                    <button class="addLike" type="button">
                      <a href="../like/doLike?relTypeCode=article&relId=${article.id}&redirectUrl=${encodedCurrentUrl}" onclick="if ( !confirm('`좋아요` 처리 하시겠습니까?') ) return false;">
                        <i class="far fa-thumbs-up"></i>
                        &nbsp;<span class="likesCount">${article.extra_likeOnlyPoint}</span>
                      </a>
                    </button>

                  </c:if>

                  <c:if test="${article.extra.actorCanCancelLike}">
                    <button class="addLike" type="button">
                      <a href="../like/doCancelLike?relTypeCode=article&relId=${article.id}&redirectUrl=${encodedCurrentUrl}" onclick="if ( !confirm('`좋아요`를 취소 처리 하시겠습니까?') ) return false;">
                        <i class="fas fa-thumbs-up"></i>
                        &nbsp;<span class="likesCount">${article.extra_likeOnlyPoint}</span>
                      </a>
                    </button>
                  </c:if>

                  <c:if test="${article.extra.actorCanDislike}">
                    <button class="addUnLike" type="button">
                      <a href="../like/doDislike?relTypeCode=article&relId=${article.id}&redirectUrl=${encodedCurrentUrl}" onclick="if ( !confirm('`싫어요` 처리 하시겠습니까?') ) return false;">
                        <i class="far fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${article.extra_dislikeOnlyPoint}</span>
                      </a>
                    </button>
                  </c:if>

                  <c:if test="${article.extra.actorCanCancelDislike}">
                    <button class="addUnLike" type="button">
                      <a href="../like/doCancelDislike?relTypeCode=article&relId=${article.id}&redirectUrl=${encodedCurrentUrl}" onclick="if ( !confirm('`싫어요`를 취소 처리 하시겠습니까?') ) return false;">
                        <i class="fas fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${article.extra_dislikeOnlyPoint}</span>
                      </a>
                    </button>
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
              <button class="btn btn-back" onclick="if(confirm('정말 삭제하시겠습니까?') == false) {return false;}" type="button">
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
          <input type="hidden" name="redirectUrl"
				value="${Util.getNewUrl(currentUrl, 'focusReplyId', '[NEW_REPLY_ID]')}" />
          <span>댓글</span>
          <input type="text" name="replyBody" placeholder="댓글 입력">
          <div class="reply-write-box-form__option ">
            <button class="btn btn-go" type="submit">등록</button>
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
            <div data-id="${reply.id}" class="reply-list-box__cell-body">${reply.body}</div>
          </div>
          <div class="reply-list-box-cell__option">
          <div class="article-detail-cell-likesCount flex">
				
				<c:if test="${isLogined == false}">
                		<button class="addLike" type="button" onclick="alert('로그인 후 이용해 주세요.')">
                        <i class="far fa-thumbs-up"></i>
                        &nbsp;<span class="likesCount">${reply.extra_likeOnlyPoint}</span>
                      </a>
                    </button>
                    <button class="addUnLike" type="button" onclick="alert('로그인 후 이용해 주세요.')">
                        <i class="far fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${reply.extra_dislikeOnlyPoint}</span>
                      </a>
                    </button>
					</c:if>
                  <c:if test="${reply.extra.actorCanLike}">

                    <button class="addLike" type="button">
                      <a href="../like/doLike?relTypeCode=reply&relId=${reply.id}&redirectUrl=${encodedCurrentUrl}" onclick="if ( !confirm('`좋아요` 처리 하시겠습니까?') ) return false;">
                        <i class="far fa-thumbs-up"></i>
                        &nbsp;<span class="likesCount">${reply.extra_likeOnlyPoint}</span>
                      </a>
                    </button>

                  </c:if>

                  <c:if test="${reply.extra.actorCanCancelLike}">
                    <button class="addLike" type="button">
                      <a href="../like/doCancelLike?relTypeCode=reply&relId=${reply.id}&redirectUrl=${encodedCurrentUrl}" onclick="if ( !confirm('`좋아요`를 취소 처리 하시겠습니까?') ) return false;">
                        <i class="fas fa-thumbs-up"></i>
                        &nbsp;<span class="likesCount">${reply.extra_likeOnlyPoint}</span>
                      </a>
                    </button>
                  </c:if>

                  <c:if test="${reply.extra.actorCanDislike}">
                    <button class="addUnLike" type="button">
                      <a href="../like/doDislike?relTypeCode=reply&relId=${reply.id}&redirectUrl=${encodedCurrentUrl}" onclick="if ( !confirm('`싫어요` 처리 하시겠습니까?') ) return false;">
                        <i class="far fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${reply.extra_dislikeOnlyPoint}</span>
                      </a>
                    </button>
                  </c:if>

                  <c:if test="${reply.extra.actorCanCancelDislike}">
                    <button class="addUnLike" type="button">
                      <a href="../like/doCancelDislike?relTypeCode=reply&relId=${reply.id}&redirectUrl=${encodedCurrentUrl}" onclick="if ( !confirm('`싫어요`를 취소 처리 하시겠습니까?') ) return false;">
                        <i class="fas fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${reply.extra_dislikeOnlyPoint}</span>
                      </a>
                    </button>
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
                  <button class="btn" type="submit" onclick="if(confirm('해당 내용으로 수정하시겠습니까?') == false) {return false;}">수정</button>
                  <button class="btn btn-back" type="button" onclick="location.reload()">취소</button>
                </div>
              </form>
             
              <div class="reply-list-box-cell__option-btns flex flex-ai-c">
                <button class="btn doModifyReplyForm" type="button">수정</button>
                <button class="btn btn-back" onclick="if(confirm('정말 삭제하시겠습니까?') == false) {return false;}" type="button">
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
            <c:if test ="${beforeArticleBtn}">	
          <div class="./"><a href="../article/detail?id=${beforeArticleIndex}&boardId=${article.boardId}">&lt 이전글</a></div>
        </c:if>
          <div class="./">
            <c:if test="${not empty param.listUrl}">
             <a href="${param.listUrl}">
            </c:if>
            <c:if test="${empty param.listUrl}">
            <a href="../article/list?boardId=${article.boardId}">
            </c:if>
              목록
            </a>
          </div>
         <c:if test ="${afterArticleBtn}">	
          <div class="./"><a href="../article/detail?id=${afterArticleIndex}&boardId=${article.boardId}">다음글 &gt</a></div>
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
                  <span>${article.id}</span>
                </div>
                <div>
                  <span>${article.title}</span>
                </div>
                <div class="mobile-article-detail-cell__head-contents-1 flex">
                  <div class="mobile-article-detail-cell__memberNickname">
                    <div>
                      <span>${article.extra_memberNickname} </span>
                    </div>
                  </div>
                  <div class="mobile-article-detail-cell__update-date">
                    <div>
                      <span> / ${article.updateDate}</span>
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
            <div class="mobile-article-detail-cell-hitsCount"><i class="far fa-eye"></i><span>${article.hitsCount}</span></div>
            <div class="mobile-article-detail-cell-likesCount flex">
            	<c:if test="${isLogined == false}">
                		<button class="addLike" type="button" onclick="alert('로그인 후 이용해 주세요.')">
                        <i class="far fa-thumbs-up"></i>
                        &nbsp;<span class="likesCount">${article.extra_likeOnlyPoint}</span>
                      </a>
                    </button>
                    <button class="addUnLike" type="button" onclick="alert('로그인 후 이용해 주세요.')">
                        <i class="far fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${article.extra_dislikeOnlyPoint}</span>
                      </a>
                    </button>
					</c:if>
            
            
              <c:if test="${article.extra.actorCanLike}">

                <button class="addLike" type="button">
                  <a href="../like/doLike?relTypeCode=article&relId=${article.id}&redirectUrl=${encodedCurrentUrl}" onclick="if ( !confirm('`좋아요` 처리 하시겠습니까?') ) return false;">
                    <i class="far fa-thumbs-up"></i>
                    &nbsp;<span class="likesCount">${article.extra_likeOnlyPoint}</span>
                  </a>
                </button>

              </c:if>

              <c:if test="${article.extra.actorCanCancelLike}">
                <button class="addLike" type="button">
                  <a href="../like/doCancelLike?relTypeCode=article&relId=${article.id}&redirectUrl=${encodedCurrentUrl}" onclick="if ( !confirm('`좋아요`를 취소 처리 하시겠습니까?') ) return false;">
                    <i class="fas fa-thumbs-up"></i>
                    &nbsp;<span class="likesCount">${article.extra_likeOnlyPoint}</span>
                  </a>
                </button>
              </c:if>

              <c:if test="${article.extra.actorCanDislike}">
                <button class="addUnLike" type="button">
                  <a href="../like/doDislike?relTypeCode=article&relId=${article.id}&redirectUrl=${encodedCurrentUrl}" onclick="if ( !confirm('`싫어요` 처리 하시겠습니까?') ) return false;">
                    <i class="far fa-thumbs-down"></i>
                    &nbsp;<span class="unLikesCount">${article.extra_dislikeOnlyPoint}</span>
                  </a>
                </button>
              </c:if>

              <c:if test="${article.extra.actorCanCancelDislike}">
                <button class="addUnLike" type="button">
                  <a href="../like/doCancelDislike?relTypeCode=article&relId=${article.id}&redirectUrl=${encodedCurrentUrl}" onclick="if ( !confirm('`싫어요`를 취소 처리 하시겠습니까?') ) return false;">
                    <i class="fas fa-thumbs-down"></i>
                    &nbsp;<span class="unLikesCount">${article.extra_dislikeOnlyPoint}</span>
                  </a>
                </button>
              </c:if>

            </div>
          </div>
        </div>
        <div class="mobile-article-detail-cell__tag flex">
          <nav># <a href="#" target="_blank">tag1</a></nav>
          <nav># <a href="#" target="_blank">tag2</a></nav>
          <nav># <a href="#" target="_blank">tag3</a></nav>
        </div>
        <div class="mobile-article-detail-cell__option flex flex-jc-fe">
          <c:if test="${sessionScope.loginedMemberId > 0}">
            <button class="btn" type="button"><a style="text-decoration:none;" href="doModifyForm?id=${article.id}&boardId=${article.boardId}&title=${article.title}&body=${article.body}">수정</a></button>
            <button class="btn btn-back" onclick="if(confirm('정말 삭제하시겠습니까?') == false) {return false;}" type="button"><a style="text-decoration:none;" href="doDelete?id=${article.id}">삭제</a></button>
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
            <span>댓글</span>
            <input type="text" name="replyBody" placeholder="댓글 입력">
            <div class="mobile-reply-write-box-form__option ">
              <button class="m-btn btn-go" type="submit">등록</button>
            </div>
          </form>
        </div>
      </c:if>
      <c:forEach var="reply" items="${replies}">
        <div class="mobile-reply-list-box">
          <div class="mobile-reply-list-box-writer">${reply.extra_memberNickname}</div>
          <div class="mobile-reply-list-box__cell flex flex-column">
            <div class="mobile-reply-list-box__cell-contents">
              <div class="mobile-reply-list-box__cell-updateDate">${reply.updateDate}</div>
              <div data-id="${reply.id}" class="mobile-reply-list-box__cell-body">${reply.body}</div>
            </div>
            <div class="mobile-article-detail-cell-likesCount flex">
				<c:if test="${isLogined == false}">
                		<button class="addLike" type="button" onclick="alert('로그인 후 이용해 주세요.')">
                        <i class="far fa-thumbs-up"></i>
                        &nbsp;<span class="likesCount">${reply.extra_likeOnlyPoint}</span>
                      </a>
                    </button>
                    <button class="addUnLike" type="button" onclick="alert('로그인 후 이용해 주세요.')">
                        <i class="far fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${reply.extra_dislikeOnlyPoint}</span>
                      </a>
                    </button>
					</c:if>	
					
                  <c:if test="${reply.extra.actorCanLike}">

                    <button class="addLike" type="button">
                      <a href="../like/doLike?relTypeCode=reply&relId=${reply.id}&redirectUrl=${encodedCurrentUrl}" onclick="if ( !confirm('`좋아요` 처리 하시겠습니까?') ) return false;">
                        <i class="far fa-thumbs-up"></i>
                        &nbsp;<span class="likesCount">${reply.extra_likeOnlyPoint}</span>
                      </a>
                    </button>

                  </c:if>

                  <c:if test="${reply.extra.actorCanCancelLike}">
                    <button class="addLike" type="button">
                      <a href="../like/doCancelLike?relTypeCode=reply&relId=${reply.id}&redirectUrl=${encodedCurrentUrl}" onclick="if ( !confirm('`좋아요`를 취소 처리 하시겠습니까?') ) return false;">
                        <i class="fas fa-thumbs-up"></i>
                        &nbsp;<span class="likesCount">${reply.extra_likeOnlyPoint}</span>
                      </a>
                    </button>
                  </c:if>

                  <c:if test="${reply.extra.actorCanDislike}">
                    <button class="addUnLike" type="button">
                      <a href="../like/doDislike?relTypeCode=reply&relId=${reply.id}&redirectUrl=${encodedCurrentUrl}" onclick="if ( !confirm('`싫어요` 처리 하시겠습니까?') ) return false;">
                        <i class="far fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${reply.extra_dislikeOnlyPoint}</span>
                      </a>
                    </button>
                  </c:if>

                  <c:if test="${reply.extra.actorCanCancelDislike}">
                    <button class="addUnLike" type="button">
                      <a href="../like/doCancelDislike?relTypeCode=reply&relId=${reply.id}&redirectUrl=${encodedCurrentUrl}" onclick="if ( !confirm('`싫어요`를 취소 처리 하시겠습니까?') ) return false;">
                        <i class="fas fa-thumbs-down"></i>
                        &nbsp;<span class="unLikesCount">${reply.extra_dislikeOnlyPoint}</span>
                      </a>
                    </button>
                  </c:if>

                </div>
            <div class="mobile-reply-list-box-cell__option">
              <c:if test="${loginedMemberId == reply.memberId}">
                <form class="mobile-replyModifyForm flex flex-jc-a" name="replyModifyForm" action="doModifyReply" method="POST" onsubmit="checkModify(this); return false;">
                  <input type="hidden" name="id" value="${reply.id}">
                  <input type="hidden" name="relId" value="${reply.relId}">
                  <input type="hidden" name="memberId" value="${loginedMemberId}">
                  <input type="hidden" name="redirectUrl"
				value="${Util.getNewUrl(currentUrl, 'focusReplyId', '[NEW_REPLY_ID]')}" />
                  <input class="mobile-replyBodyInput" type="text" name="body" placeholder="${reply.body}" value="${reply.body}">
                  <div class="mobile-replyModifyForm__option flex flex-jc-fe">
                    <button class="btn" type="submit" onclick="if(confirm('해당 내용으로 수정하시겠습니까?') == false) {return false;}">수정</button>
                    <button class="btn btn-back" type="button" onclick="location.reload()">취소</button>
                  </div>
                </form>
                
                <div class="mobile-reply-list-box-cell__option-btns flex flex-ai-c">
                  <button class="btn doModifyReplyForm" type="button">수정</button>
                  <button class="btn btn-back" onclick="if(confirm('정말 삭제하시겠습니까?') == false) {return false;}" type="button">
                    <a href="doDeleteReply?id=${reply.id}&relId=${reply.relId}">삭제</a>
                  </button>
                </div>
              </c:if>
            </div>
          </div>
        </div>
      </c:forEach>
      </div>
      <div class="mobile-reply-page-menu-section">
        <div class="mobile-reply-page-menu">
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
        <!-- 모바일 댓글창 끝 -->
    </section>
    <!-- 모바일 메인-상세페이지 끝 -->

    <!-- 모바일 메인-상세 하단 메뉴 시작 -->
    <section class="mobile-section-3 con-min-width">
      <div class="con">
        <div class="mobile-article-detail-bottom-cell flex flex-jc-c">
        <c:if test ="${beforeArticleBtn}">	
          <div class="./"><a href="../article/detail?id=${beforeArticleIndex}&boardId=${article.boardId}">&lt 이전글</a></div>
        </c:if>
          <div class="./">
            <c:if test="${not empty param.listUrl}">
             <a href="${param.listUrl}">
            </c:if>
            <c:if test="${empty param.listUrl}">
            <a href="../article/list?boardId=${article.boardId}">
            </c:if>
              목록
            </a>
          </div>
         <c:if test ="${afterArticleBtn}">	
          <div class="./"><a href="../article/detail?id=${afterArticleIndex}&boardId=${article.boardId}">다음글 &gt</a></div>
        </c:if>
          
        </div>
      </div>
    </section>
    <!-- 모바일 메인-상세 하단 메뉴 끝 -->
  </section>
</main>
<!-- 모바일 메인 컨텐츠 박스 끝 -->


<%@ include file="../../part/foot.jspf"%>