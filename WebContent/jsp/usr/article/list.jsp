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
            <span>${articles.get(0).extra_boardName} ${pageTitle}</span>
            <span>(Total : ${totalCount})</span>
            <button type="button"><a style="text-decoration:none;" href="doWriteForm?boardId=${param.boardId}">새 게시물 생성</a></button>
         	<form action="" onsubmit="DoSearchForm_submit(this); return false;">
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
          
          	<c:forEach var="article" items="${articles}">
            <div>
              <div class="article-list__cell-id">${article.id}</div>
              <div class="article-list__cell-reg-date">${article.regDate}</div>
              <div class="article-list__cell-writer">${article.extra_memberNickname}</div>
              <div class="article-list__cell-title">
                <a href="../article/detail?id=${article.id}" class="hover-underline">${article.title}</a>
                <span>[5]</span>
              </div>
              <div class="article-list__cell-hitsCount">10</div>
              <div class="article-list__cell-likesCount">3</div>
            </div>
			</c:forEach>

          </div>
        </div>
      </div>
      <!-- 메인-리스트페이지 끝 -->
      <!-- 메인-리스트 하단 메뉴 시작 -->
      <div class="article-page-menu-section">
        <div class="article-page-menu">
          <ul class="flex flex-jc-c">
            <li class="before-btn"><a href="#" class="flex flex-ai-c"> &lt; 이전</a></li>
            <li><a href="#" class="page-btn flex flex-ai-c article-page-menu__link--selected">1</a></li>
            <li><a href="#" class="page-btn flex flex-ai-c">2</a></li>
            <li><a href="#" class="page-btn flex flex-ai-c">3</a></li>
            <li><a href="#" class="page-btn flex flex-ai-c">4</a></li>
            <li><a href="#" class="page-btn flex flex-ai-c">5</a></li>
            <li><a href="#" class="page-btn flex flex-ai-c">6</a></li>
            <li><a href="#" class="page-btn flex flex-ai-c">7</a></li>
            <li><a href="#" class="page-btn flex flex-ai-c">8</a></li>
            <li><a href="#" class="page-btn flex flex-ai-c">9</a></li>
            <li><a href="#" class="page-btn flex flex-ai-c">10</a></li>
            <li class="after-btn"><a href="#" class="flex flex-ai-c">다음 &gt;</a></li>
          </ul>
        </div>
      </div>
      <!-- 메인-리스트 하단 메뉴 끝 -->
    </section>
  </main>
  <!-- 메인 컨텐츠 박스 끝 -->
	
	
	
<%@ include file="../../part/foot.jspf" %>