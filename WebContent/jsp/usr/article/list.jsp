<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var ="pageTitle" value="게시물 리스트"/>
<%@ include file="../../part/head.jspf" %>


<script>
let DoSearchForm_submited = false;

function DoSearchForm_submit(form){
	if(DoSearchForm_submited){
			alert("처리중 입니다.");
			return;
	}

	if(form.searchKeyword.value.trim().length == 0){
			alert("검색어를 입력해주세요.");
			form.searchKeyword.focus();
			return;
	}

	form.submit();
	DoSearchForm_submited = true;
}
</script>

	
  <!-- 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1">
    <section class="main-box-section con">
      <!-- 메인-리스트페이지 시작 -->
      <div class="section-article-list">
        <div class="article-list">
          <div class="article-list-name flex">
            <span>${articles.get(0).extra_boardName}</span>
            <span>(Total : ${totalCount})</span>
          </div>
          <header>
            <div class="article-list__cell-head">
              <div class="article-list__cell-id">번호</div>
              <div class="article-list__cell-reg-date">작성일</div>
              <div class="article-list__cell-writer">작성자</div>
              <div class="article-list__cell-title">제목</div>
              <div class="article-list__cell-hitsCount">조회수</div>
              <div class="article-list__cell-likesCount">추천수</div>
            </div>
          </header>
          <div class="article-list__cell-body">
      
          	<c:forEach var="article" items="${articles}" varStatus="status">
            <div>
              <div class="article-list__cell-id">${article.id}</div>
              <div class="article-list__cell-reg-date">${article.regDate}</div>
              <div class="article-list__cell-writer">${article.extra_memberNickname}</div>
              <div class="article-list__cell-title">
                <a href="../article/detail?id=${article.id}" class="hover-underline">${article.title}</a>
                <span>[${article.repliesCount}]</span>
              </div>
              <div class="article-list__cell-hitsCount">${article.hitsCount}</div>
              <div class="article-list__cell-likesCount">${article.likesCount}</div>
            </div>
			</c:forEach>
			
			
          </div>
          <div class="article-list__cell-search flex flex-jc-c">
         	<form onsubmit="DoSearchForm_submit(this); return false;">
         		<input type="hidden" name="boardId" value="${param.boardId}">
         		<select name="searchKeywordType">
         			<option value="titleAndBody">제목+내용</option>
         			<option value="title">제목</option>
         			<option value="body">내용</option>
         		</select>
         		<script>
					const param_searchKeywordType = '${param.searchKeywordType}';

					if(param_searchKeywordType){
						$('select[name="searchKeywordType"]').val(param_searchKeywordType);
					}
         		</script>
         		<input type="text" name="searchKeyword" value="${param.searchKeyword }" placeholder="검색어 입력">
         		<input type="submit" value="검색">
         	</form>
            <c:if test="${sessionScope.loginedMemberId > 0}">           
              <button type="button"><a style="text-decoration:none;" href="doWriteForm?boardId=${param.boardId}">글쓰기</a>
              </button>
         	</c:if>
            </div>
        </div>
      </div>
      <!-- 메인-리스트페이지 끝 -->
      <!-- 메인-리스트 하단 메뉴 시작 -->
      <div class="article-page-menu-section">
        <div class="article-page-menu">
          <ul class="flex flex-jc-c">
 
			<c:set var="aUrl" value="?page=1&boardId=${param.boardId}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
			<li class="before-btn"><a href="${aUrl}" class="flex flex-ai-c">&lt;&lt; </a></li>
          	
          	<c:if test="${boxStartNumBeforePageBtnNeedToShow}">
          		<c:set var="aUrl" value="?boardId=${param.boardId}&page=${boxStartNumBeforePage}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
          		<li class="before-btn"><a href="${aUrl}" class="flex flex-ai-c"> &lt; 이전</a></li>
          	</c:if>
            <c:forEach var="i" begin="${boxStartNum}" end="${boxEndNum}" step="1">
				<c:set var="aClass" value="${page == i ? 'article-page-menu__link--selected' : '???' }" />           
				<c:set var="aUrl" value="?boardId=${param.boardId}&page=${i}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
				<li><a href="${aUrl}" class="page-btn flex flex-ai-c ${aClass}">${i}</a></li>
            </c:forEach>
            <c:if test="${boxEndNumAfterPageBtnNeedToShow}">
          		<c:set var="aUrl" value="?boardId=${param.boardId}&page=${boxEndNumAfterPage}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
          		<li class="after-btn"><a href="${aUrl}" class="flex flex-ai-c">다음 &gt;</a></li>
          	</c:if>
          	
          	<c:set var="aUrl" value="?page=${totalPages}&boardId=${param.boardId}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
			<li class="after-btn"><a href="${aUrl}" class="flex flex-ai-c"> &gt;&gt;</a></li>
          	
          </ul>
        </div>
      </div>
      <!-- 메인-리스트 하단 메뉴 끝 -->
    </section>
  </main>
  <!-- 메인 컨텐츠 박스 끝 -->
	
	
	
<%@ include file="../../part/foot.jspf" %>