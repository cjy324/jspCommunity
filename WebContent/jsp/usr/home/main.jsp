<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var ="pageTitle" value="메인화면"/>
<%@ include file="../../part/head.jspf" %>
	
<!--
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/usrHomeMain.css" />
<script src="${pageContext.request.contextPath}/static/usrHomeMain.js" defer></script>
-->	
	


  <!-- 탑바 시작 -->
  <div class="top-bar-padding visible-md-up">
  </div>
  <header class="top-bar visible-md-up ">
    <div class="con height-100p flex flex-jc-sb flex-ai-c">
      <div class="title">
        <a class="" href="#">
          <span>Community Site</span>
        </a>
      </div>
      <!-- 메뉴바 시작-->
      <section class="menu-bar height-100p">
        <nav class="menu-bar_menu height-100p">
          <ul class="flex flex-ai-fe height-100p ">
            <li title="ABOUT">
              <a href="about.html" class="block">
                <span>ABOUT</span>
              </a>
            </li>
            <li>
              <a class="block">
                <span>ARTICLES</span>
              </a>
              <ul>
                <li title="NOTICE">
                  <a href="notice-list-1.html" class="block">
                    <span>NOTICE</span>
                  </a>
                </li>
                <li title="FREE">
                  <a href="free-list-1.html" class="block">
                    <span>FREE</span>
                  </a>
                </li>
              </ul>
            </li>
            <li>
              <a class="block">
                <span>LINK</span>
              </a>
              <ul>
                <li title="T-STORY">
                  <a href="https://cjy324.tistory.com/" target="_blank" class="block">
                    <span>T-STORY</span>
                  </a>
                </li>
                <li title="GITHUB">
                  <a href="https://github.com/cjy324/TIL_Jooyoung#til_jooyoung" target="_blank" class="block">
                    <span>GITHUB</span>
                  </a>
                </li>
              </ul>
            </li>
            <li title="SEARCH">
              <a href="search.html" class="block">
                <span>SEARCH</span>
              </a>
            </li>
            <li title="STATISTICS">
              <a href="statistics.html" class="block">
                <span>STATISTICS</span>
              </a>
            </li>
          </ul>
        </nav>
      </section>
      <!-- 메뉴바 끝 -->
    </div>
  </header>
  <!-- 탑바 끝 -->

  <!-- 모바일 탑바 시작 -->
  <section class="top-bar-padding flex flex-ai-c visible-sm-down">
    <div class="mobile-top-bar_btn-toggle-side-bar flex-as-c visible-sm-down">
      <div></div>
      <div></div>
      <div></div>
    </div>
  </section>
  <header class="mobile-top-bar flex visible-sm-down">
    <div class="flex-1-0-0 flex flex-jc-c flex-ai-c">
      <div class="title">
        <a class="" href="#">
          <span>Community Site</span>
        </a>
      </div>
    </div>
  </header>
  <!-- 모바일 탑바 끝 -->
  <!-- 모바일 사이드메뉴바 시작-->
  <aside class="mobile-side-bar visible-sm-down">
    <nav class="mobile-side-bar_menu">
      <ul class="">
        <li title="ABOUT">
          <a href="about.html" class="block">
            <span>ABOUT</span>
          </a>
        </li>
        <li>
          <a class="block">
            <span>ARTICLES</span>
          </a>
          <ul>
            <li title="NOTICE">
              <a href="notice-list-1.html" class="block">
                <span>NOTICE</span>
              </a>
            </li>
            <li title="FREE">
              <a href="free-list-1.html" class="block">
                <span>FREE</span>
              </a>
            </li>
          </ul>
        </li>
        <li>
          <a class="block">
            <span>LINK</span>
          </a>
          <ul>
            <li title="T-STORY">
              <a href="https://cjy324.tistory.com/" target="_blank" class="block">
                <span>T-STORY</span>
              </a>
            </li>
            <li title="GITHUB">
              <a href="https://github.com/cjy324/TIL_Jooyoung#til_jooyoung" target="_blank" class="block">
                <span>GITHUB</span>
              </a>
            </li>
          </ul>
        </li>
        <li title="SEARCH">
          <a href="search.html" class="block">
            <span>SEARCH</span>
          </a>
        </li>
        <li title="STATISTICS">
          <a href="statistics.html" class="block">
            <span>STATISTICS</span>
          </a>
        </li>
      </ul>
    </nav>
  </aside>
  <!-- 모바일 사이드메뉴바 끝 -->

  <!-- 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1">
    <section class="main-box-section con">
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
    </section>
<%@ include file="../../part/foot.jspf" %>