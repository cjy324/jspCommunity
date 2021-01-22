<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var ="pageTitle" value="메인화면"/>
<%@ include file="../../part/head.jspf" %>
	
  <!-- 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1 ">
    <section class="main-box-section con ">
      <!-- 메인 홈 시작 -->
      <div class="section-home">
      <div class="section-home-slide ">
        <ul class="slideWrap">
          <li class="slide slide4"><p>slide4</p></li>
    <li class="slide slide1"><p>slide1</p></li>
    <li class="slide slide2"><p>slide2</p></li>
    <li class="slide slide3"><p>slide3</p></li>
    <li class="slide slide4"><p>slide4</p></li>
    <li class="slide slide1"><p>slide1</p></li>
        </ul>
        <ul class="pageBtWrap">
          <li><a href="#" class="pageBt addPageBt"></a></li>
          <li><a href="#" class="pageBt"></a></li>
          <li><a href="#" class="pageBt"></a></li>
          <li><a href="#" class="pageBt"></a></li>
        </ul>
      </div>
      <div class="section-home-list">
        <div class="home-list">
          <span>&lt 공지사항 &gt</span>
          <header>
            <div class="home-list__cell-head">
              <div class="home-list__cell-id">번호</div>
              <div class="home-list__cell-reg-date">작성일</div>
              <div class="home-list__cell-writer">작성자</div>
              <div class="home-list__cell-title">제목</div>
              <div class="home-list__cell-hitsCount">조회수</div>
              <div class="home-list__cell-likesCount">추천수</div>
            </div>
          </header>
          <div class="home-list__cell-body">
            <div>
              <div class="home-list__cell-id">9</div>
              <div class="home-list__cell-reg-date">2020-12-12 12:12:12</div>
              <div class="home-list__cell-writer">홍길동</div>
              <div class="home-list__cell-title">
                <a href="article_9.html" class="hover-underline">제목9</a>
                <span>[5]</span>
              </div>
              <div class="home-list__cell-hitsCount">10</div>
              <div class="home-list__cell-likesCount">3</div>
            </div>
            <div>
              <div class="home-list__cell-id">9</div>
              <div class="home-list__cell-reg-date">2020-12-12 12:12:12</div>
              <div class="home-list__cell-writer">홍길동</div>
              <div class="home-list__cell-title">
                <a href="article_9.html" class="hover-underline">제목9</a>
                <span>[5]</span>
              </div>
              <div class="home-list__cell-hitsCount">10</div>
              <div class="home-list__cell-likesCount">3</div>
            </div>
            <div>
              <div class="home-list__cell-id">9</div>
              <div class="home-list__cell-reg-date">2020-12-12 12:12:12</div>
              <div class="home-list__cell-writer">홍길동</div>
              <div class="home-list__cell-title">
                <a href="article_9.html" class="hover-underline">제목9</a>
                <span>[5]</span>
              </div>
              <div class="home-list__cell-hitsCount">10</div>
              <div class="home-list__cell-likesCount">3</div>
            </div>
            <div>
              <div class="home-list__cell-id">9</div>
              <div class="home-list__cell-reg-date">2020-12-12 12:12:12</div>
              <div class="home-list__cell-writer">홍길동</div>
              <div class="home-list__cell-title">
                <a href="article_9.html" class="hover-underline">제목9</a>
                <span>[5]</span>
              </div>
              <div class="home-list__cell-hitsCount">10</div>
              <div class="home-list__cell-likesCount">3</div>
            </div>
          </div>
        </div>
      </div>
      <div class="section-home-list">
        <div class="home-list">
          <span>&lt 최신글 &gt</span>
          <header>
            <div class="home-list__cell-head">
              <div class="home-list__cell-id">번호</div>
              <div class="home-list__cell-reg-date">작성일</div>
              <div class="home-list__cell-writer">작성자</div>
              <div class="home-list__cell-title">제목</div>
              <div class="home-list__cell-hitsCount">조회수</div>
              <div class="home-list__cell-likesCount">추천수</div>
            </div>
          </header>
          <div class="home-list__cell-body">
            <div>
              <div class="home-list__cell-id">9</div>
              <div class="home-list__cell-reg-date">2020-12-12 12:12:12</div>
              <div class="home-list__cell-writer">홍길동</div>
              <div class="home-list__cell-title">
                <a href="article_9.html" class="hover-underline">제목9</a>
                <span>[5]</span>
              </div>
              <div class="home-list__cell-hitsCount">10</div>
              <div class="home-list__cell-likesCount">3</div>
            </div>
            <div>
              <div class="home-list__cell-id">9</div>
              <div class="home-list__cell-reg-date">2020-12-12 12:12:12</div>
              <div class="home-list__cell-writer">홍길동</div>
              <div class="home-list__cell-title">
                <a href="article_9.html" class="hover-underline">제목9</a>
                <span>[5]</span>
              </div>
              <div class="home-list__cell-hitsCount">10</div>
              <div class="home-list__cell-likesCount">3</div>
            </div>
            <div>
              <div class="home-list__cell-id">9</div>
              <div class="home-list__cell-reg-date">2020-12-12 12:12:12</div>
              <div class="home-list__cell-writer">홍길동</div>
              <div class="home-list__cell-title">
                <a href="article_9.html" class="hover-underline">제목9</a>
                <span>[5]</span>
              </div>
              <div class="home-list__cell-hitsCount">10</div>
              <div class="home-list__cell-likesCount">3</div>
            </div>
            <div>
              <div class="home-list__cell-id">9</div>
              <div class="home-list__cell-reg-date">2020-12-12 12:12:12</div>
              <div class="home-list__cell-writer">홍길동</div>
              <div class="home-list__cell-title">
                <a href="article_9.html" class="hover-underline">제목9</a>
                <span>[5]</span>
              </div>
              <div class="home-list__cell-hitsCount">10</div>
              <div class="home-list__cell-likesCount">3</div>
            </div>
          </div>
        </div>
      </div>
      <div class="section-home-list">
        <div class="home-list">
          <span>&lt 인기게시글 &gt</span>
          <header>
            <div class="home-list__cell-head">
              <div class="home-list__cell-id">번호</div>
              <div class="home-list__cell-reg-date">작성일</div>
              <div class="home-list__cell-writer">작성자</div>
              <div class="home-list__cell-title">제목</div>
              <div class="home-list__cell-hitsCount">조회수</div>
              <div class="home-list__cell-likesCount">추천수</div>
            </div>
          </header>
          <div class="home-list__cell-body">
            <div>
              <div class="home-list__cell-id">9</div>
              <div class="home-list__cell-reg-date">2020-12-12 12:12:12</div>
              <div class="home-list__cell-writer">홍길동</div>
              <div class="home-list__cell-title">
                <a href="article_9.html" class="hover-underline">제목9</a>
                <span>[5]</span>
              </div>
              <div class="home-list__cell-hitsCount">10</div>
              <div class="home-list__cell-likesCount">3</div>
            </div>
            <div>
              <div class="home-list__cell-id">9</div>
              <div class="home-list__cell-reg-date">2020-12-12 12:12:12</div>
              <div class="home-list__cell-writer">홍길동</div>
              <div class="home-list__cell-title">
                <a href="article_9.html" class="hover-underline">제목9</a>
                <span>[5]</span>
              </div>
              <div class="home-list__cell-hitsCount">10</div>
              <div class="home-list__cell-likesCount">3</div>
            </div>
            <div>
              <div class="home-list__cell-id">9</div>
              <div class="home-list__cell-reg-date">2020-12-12 12:12:12</div>
              <div class="home-list__cell-writer">홍길동</div>
              <div class="home-list__cell-title">
                <a href="article_9.html" class="hover-underline">제목9</a>
                <span>[5]</span>
              </div>
              <div class="home-list__cell-hitsCount">10</div>
              <div class="home-list__cell-likesCount">3</div>
            </div>
            <div>
              <div class="home-list__cell-id">9</div>
              <div class="home-list__cell-reg-date">2020-12-12 12:12:12</div>
              <div class="home-list__cell-writer">홍길동</div>
              <div class="home-list__cell-title">
                <a href="article_9.html" class="hover-underline">제목9</a>
                <span>[5]</span>
              </div>
              <div class="home-list__cell-hitsCount">10</div>
              <div class="home-list__cell-likesCount">3</div>
            </div>
          </div>
        </div>
      </div>
       </div>
      <!-- 메인 홈 끝 -->
    </section>
  </main>
  <!-- 메인 컨텐츠 박스 끝 -->
  
  
<%@ include file="../../part/foot.jspf" %>