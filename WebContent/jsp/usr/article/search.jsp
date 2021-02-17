<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var ="pageTitle" value="GetIT | Search"/>
<%@ include file="../../part/head.jspf" %>


<script>
function saveHitsCount(el){

	const visitUrl = $(el).closest('a').get(0);

	// url의 파라미터를 찾는 함수
	$.urlParam = function(name){
	    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(visitUrl);
	    if (results==null){
	       return null;
	    }
	    else{
	       return results[1] || 0;
	    }
	}

	const hitId = localStorage.getItem("hitId");
	const visitArticleId = $.urlParam('id');
	const hitCount = localStorage.getItem("hitCount");

	
	//해당 페이지 재방문 여부 확인
 	if(hitCount == 1){
		return;
	} 
	//저장
	localStorage.setItem("hitId", visitArticleId);
	localStorage.setItem("hitCount", hitCount);
} 


</script>	


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
  <main class="main-box flex-grow-1 visible-md-up">
    <section class="main-box-section con">
      <!-- 메인-검색 리스트페이지 시작 -->
      <div class="section-search-list">
        <div class="search-list">
          <div class="search-list-name">
            <span><i class="fas fa-search"></i> SEARCH</span>
          </div>
          <div class="search-list__cell-search flex flex-jc-c ">
            <form onsubmit="DoSearchForm_submit(this); return false;">
              <select name="searchKeywordType">
                <option value="titleAndBody">제목+내용</option>
                <option value="title">제목</option>
                <option value="body">내용</option>
              </select>
              <script>
                const param_searchKeywordType = '${param.searchKeywordType}';
                if (param_searchKeywordType) {
                  $('select[name="searchKeywordType"]').val(param_searchKeywordType);
                }
              </script>
              <input type="text" name="searchKeyword" value="${param.searchKeyword }" placeholder="keyword 입력">
              <button class="btn" type="submit">검색</button>
            </form>
          </div>
          <div class="search-list__cell-recommendKeyword flex flex-column flex-ai-c">
            <div><span>&lt 추천 키워드 &gt</span></div>
            <div class="recommendKeyword-box">
              <div>아이폰</div>
              <div>갤럭시</div>
              <div>아이패드</div>
              <div>갤럭시탭</div>
              <div>워치</div>
            </div>
          </div>
          <div class="search-totalCount">
            <span><i class="far fa-copy"></i> Total : ${totalCount}</span>
          </div>
          <header>
            <div class="article-list__cell-head">
              <div class="article-list__cell-id">번호</div>
              <div class="article-list__cell-update-date">작성일</div>
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
              <div class="article-list__cell-update-date">${article.updateDate}</div>
              <div class="article-list__cell-writer">${article.extra_memberNickname}</div>
              <div class="article-list__cell-title">
                <a href="../article/detail?id=${article.id}&listUrl=${encodedCurrentUrl}" class="hover-underline" onclick="saveHitsCount(this);">${article.title}</a>
                <span>[${article.repliesCount}]</span>
              </div>
              <div class="article-list__cell-hitsCount">${article.hitsCount}</div>
              <div class="article-list__cell-likesCount">
              	<i class="far fa-thumbs-up"></i> ${article.extra_likeOnlyPoint} /
 				<i class="far fa-thumbs-down"></i> ${article.extra_dislikeOnlyPoint}
              </div>
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
 			<c:if test="${totalPages > 1}"> 
			<c:set var="aUrl" value="?page=1&boardId=${param.boardId}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
			<li class="before-btn"><a href="${aUrl}" class="flex flex-ai-c">&lt;&lt; </a></li>
          	</c:if>
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
          	<c:if test="${totalPages > 1}"> 
          	<c:set var="aUrl" value="?page=${totalPages}&boardId=${param.boardId}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
			<li class="after-btn"><a href="${aUrl}" class="flex flex-ai-c"> &gt;&gt;</a></li>
          	</c:if>
          </ul>
        </div>
      </div>
      <!-- 메인-검색 리스트 하단 메뉴 끝 -->
    </section>
  </main>
  <!-- 메인 컨텐츠 박스 끝 -->
  
  
  <!-- 모바일 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1 visible-sm-down " style="min-height:500px;">
    <section class="main-box-section con ">
      <!-- 모바일 메인-검색 리스트페이지 시작 -->
      <div class="mobile-section-search-list">
        <div class="mobile-search-list">
          <div class="mobile-search-list__cell-search flex flex-jc-c">
            <form class="flex flex-ai-c" onsubmit="DoSearchForm_submit(this); return false;">
              <select name="searchKeywordType">
                <option value="titleAndBody">제목+내용</option>
                <option value="title">제목</option>
                <option value="body">내용</option>
              </select>
              <script>
                const param_searchKeywordType = '${param.searchKeywordType}';
                if (param_searchKeywordType) {
                  $('select[name="searchKeywordType"]').val(param_searchKeywordType);
                }
              </script>
              <input type="text" name="searchKeyword" value="${param.searchKeyword }" placeholder="keyword 입력">
              <div><button class="m-btn" type="submit">검색</button></div>
            </form>
          </div>
          <section class="mobile-top-bar-padding flex flex-ai-c visible-sm-down"></section>
          <div class="mobile-search-list-name flex flex-jc-sb">
            <span><i class="fas fa-search"></i> SEARCH</span>
          </div>
          <div class="mobile-search-list__cell-recommendKeyword flex flex-column flex-ai-c">
            <div><span>&lt 추천 키워드 &gt</span></div>
            <div class="mobile-recommendKeyword-box">
              <div>아이폰</div>
              <div>갤럭시</div>
              <div>아이패드</div>
              <div>갤럭시탭</div>
              <div>워치</div>
            </div>
          </div>
          <div class="mobile-search-totalCount">
            <span><i class="far fa-copy"></i> Total : ${totalCount}</span>
          </div>
          <header>
            <div class="mobile-article-list__cell-head">
              <div class="mobile-article-list__cell-id">No.</div>
              <div class="mobile-article-list__cell-title">제목</div>
              <div class="mobile-article-list__cell-reply"><i class="far fa-comments"></i></div>
            </div>
          </header>
           
          <div class="mobile-search-list__cell-body">
          <c:forEach var="article" items="${articles}">
              <div>
                <div class="mobile-article-list__cell-id">${article.id}</div>          
                <div class="mobile-article-list__cell-title flex flex-column">
                  <a href="../article/detail?id=${article.id}&listUrl=${encodedCurrentUrl}" class="hover-underline" onclick="saveHitsCount(this);">${article.title}</a>
                  <div class="mobile-article-list__cell-title-contents flex">
                    <div class="mobile-article-list__cell-writer">${article.extra_memberNickname}</div>
                    <div class="mobile-article-list__cell-update-date">${article.updateDate}</div>
                    <div class="mobile-article-list__cell-hitsCount"><i class="far fa-eye"></i>${article.hitsCount}</div>
                    <div class="mobile-article-list__cell-likesCount"><i class="far fa-thumbs-up"></i> ${article.extra_likeOnlyPoint} / <i class="far fa-thumbs-down"></i> ${article.extra_dislikeOnlyPoint}</div>
                  </div>
                </div>
                <div class="mobile-article-list__cell-reply">${article.repliesCount}</div>
              </div>
              </c:forEach>
          </div>     
        </div>
      </div>
      <!-- 모바일 메인-검색 리스트페이지 끝 -->
      <!-- 모바일 메인-검색 리스트 하단 메뉴 시작 -->
      <div class="mobile-article-page-menu-section">
        <div class="mobile-article-page-menu">
          <ul class="flex flex-jc-c">
			<c:if test="${totalPages > 1}"> 
            <c:set var="aUrl" value="?page=1&boardId=${param.boardId}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
			<li class="before-btn"><a href="${aUrl}" class="flex flex-ai-c">&lt;&lt; </a></li>
          	</c:if>
          	<c:if test="${boxStartNumBeforePageBtnNeedToShow}">
          		<c:set var="aUrl" value="?boardId=${param.boardId}&page=${boxStartNumBeforePage}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
          		<li class="before-btn"><a href="${aUrl}" class="flex flex-ai-c"> &lt; 이전</a></li>
          	</c:if>
            <c:forEach var="i" begin="${boxStartNum}" end="${boxEndNum}" step="1">
				<c:set var="aClass" value="${page == i ? 'mobile-article-page-menu__link--selected' : '???' }" />           
				<c:set var="aUrl" value="?boardId=${param.boardId}&page=${i}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
				<li><a href="${aUrl}" class="page-btn flex flex-ai-c ${aClass}">${i}</a></li>
            </c:forEach>
            <c:if test="${boxEndNumAfterPageBtnNeedToShow}">
          		<c:set var="aUrl" value="?boardId=${param.boardId}&page=${boxEndNumAfterPage}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
          		<li class="after-btn"><a href="${aUrl}" class="flex flex-ai-c">다음 &gt;</a></li>
          	</c:if>
          	<c:if test="${totalPages > 1}"> 
          	<c:set var="aUrl" value="?page=${totalPages}&boardId=${param.boardId}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
			<li class="after-btn"><a href="${aUrl}" class="flex flex-ai-c"> &gt;&gt;</a></li>
			</c:if>
          </ul>
        </div>
      </div>
      <!-- 모바일 메인-리스트 하단 메뉴 끝 -->
    </section>
  </main>
  <!-- 모바일 메인 컨텐츠 박스 끝 -->
	
	
	
<%@ include file="../../part/foot.jspf" %>