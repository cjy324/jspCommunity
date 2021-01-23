<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var ="pageTitle" value="게시물 리스트"/>
<%@ include file="../../part/head.jspf" %>

	
  <!-- 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1">
    <section class="main-box-section con">
      <!-- 메인-리스트페이지 시작 -->
      <div class="section-article-list">
        <div class="article-list">
          <div class="article-list-name">
            <span>${articles.get(0).extra_boardName} ${pageTitle}</span>
            <button type="button"><a style="text-decoration:none;" href="doWriteForm?boardId=${param.boardId}">새 게시물 생성</a></button>
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