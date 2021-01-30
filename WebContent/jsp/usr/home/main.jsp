<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var ="pageTitle" value="메인화면"/>
<%@ include file="../../part/head.jspf" %>
	
  <!-- 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1 ">
    <section class="main-box-section con ">
      <!-- 메인 홈 시작 -->
      <div class="section-home flex flex-column">
        <div id="section-home-slide">
          <div id="wrapper">
            <div id="slider-wrap">
              <ul id="slider">
                <li>
                  <div>
                    <h3>공지사항</h3>
                    <span>NOTICE</span>
                  </div>
                  <a href="../article/list?boardId=1">
                    <img src="https://images.unsplash.com/photo-1505744386214-51dba16a26fc?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1467&q=80"></a>
                </li>

                <li>
                  <div>
                    <h3>자유게시판</h3>
                    <span>FREE BOARD</span>
                  </div>
                  <a href="../article/list?boardId=2">
                    <img src="https://images.unsplash.com/photo-1516414447565-b14be0adf13e?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1266&q=80"></a>
                </li>

                <li>
                  <div>
                    <h3>News</h3>
                    <span>ARTICLE BOARD</span>
                  </div>
                  <a href="../article/list?boardId=3">
                    <img src="https://images.unsplash.com/photo-1586339949916-3e9457bef6d3?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80"></a>
                </li>

                <li>
                  <div>
                    <h3>준비중입니다.</h3>
                    <span>준비중입니다.</span>
                  </div>
                  <a href="">
                    <img src="https://images.unsplash.com/photo-1611558245524-aff4541a18d2?ixid=MXwxMjA3fDB8MHx0b3BpYy1mZWVkfDE0fEo5eXJQYUhYUlFZfHxlbnwwfHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"></a>
                </li>

              </ul>

              <!--controls-->
              <div class="btns" id="next"><i class="fa fa-arrow-right"></i></div>
              <div class="btns" id="previous"><i class="fa fa-arrow-left"></i></div>
              <div id="counter"></div>

              <div id="pagination-wrap">
                <ul>
                </ul>
              </div>
              <!--controls-->

            </div>
          </div>
        </div>
        <div class="section-home-list section-home-list1 flex">
          <div class="home-list home-list1">
            <div><span>공지사항</span></div>
            <header></header>
            <div class="home-list__cell-body">
            
            <c:forEach var="article" items="${articles1}" end="4">
            <div>
              <div class="home-list__cell-id">${article.id}</div>
              <div class="home-list__cell-writer">${article.extra_memberNickname}</div>
              <div class="home-list__cell-title">
                <a href="../article/detail?id=${article.id}" class="hover-underline">${article.title}</a>
                <span>[5]</span>
              </div>
              <div class="home-list__cell-hitsCount">${article.hitsCount}</div>
              <div class="home-list__cell-likesCount">3</div>
            </div>
			</c:forEach>
            
            </div>
          </div>
          <div class="home-list_img">
            <nav class="flex flex-jc-c">
              <a href="../article/list?boardId=1">
                <div>
                  <div class="img-box">
                    <img src="https://images.unsplash.com/photo-1511067087965-c1b6ce6454d3?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1220&q=80" alt="">
                    <div class="img-txt">
                      Notice
                    </div>
                  </div>
                </div>
              </a>
            </nav>
          </div>
        </div>
        <div class="section-home-list section-home-list2 flex">
          <div class="home-list_img">
            <nav class="flex flex-jc-c">
              <a href="../article/list?boardId=2">
                <div>
                  <div class="img-box">
                    <img src="https://images.unsplash.com/photo-1462642109801-4ac2971a3a51?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1266&q=80" alt="">
                    <div class="img-txt">
                      Free
                    </div>
                  </div>
                </div>
              </a>
            </nav>
          </div>
          <div class="home-list home-list2 flex flex-column">
            <div><span>자유게시판</span></div>
            <header></header>
            <div class="home-list__cell-body">
            
            <c:forEach var="article" items="${articles2}" end="4">
            <div>
              <div class="home-list__cell-id">${article.id}</div>
              <div class="home-list__cell-writer">${article.extra_memberNickname}</div>
              <div class="home-list__cell-title">
                <a href="../article/detail?id=${article.id}" class="hover-underline">${article.title}</a>
                <span>[5]</span>
              </div>
              <div class="home-list__cell-hitsCount">${article.hitsCount}</div>
              <div class="home-list__cell-likesCount">3</div>
            </div>
			</c:forEach>
            
            </div>
          </div>
        </div>
        <div class="section-home-list section-home-list3 flex">
          <div class="home-list home-list3">
            <div><span>News 게시판</span></div>
            <header></header>
            <div class="home-list__cell-body">
            
            <c:forEach var="article" items="${articles3}" end="4">
            <div>
              <div class="home-list__cell-id">${article.id}</div>
              <div class="home-list__cell-writer">${article.extra_memberNickname}</div>
              <div class="home-list__cell-title">
                <a href="../article/detail?id=${article.id}" class="hover-underline">${article.title}</a>
                <span>[5]</span>
              </div>
              <div class="home-list__cell-hitsCount">${article.hitsCount}</div>
              <div class="home-list__cell-likesCount">3</div>
            </div>
			</c:forEach>
            
            </div>
          </div>
          <div class="home-list_img">
            <nav class="flex flex-jc-c">
              <a href="../article/list?boardId=3">
                <div>
                  <div class="img-box">
                    <img src="https://images.unsplash.com/photo-1585719022717-87adb5bc279d?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80" alt="">
                    <div class="img-txt">
                      News
                    </div>
                  </div>
                </div>
              </a>
            </nav>
          </div>
        </div>

      </div>
      <!-- 메인 홈 끝 -->
    </section>
  </main>
  <!-- 메인 컨텐츠 박스 끝 -->
  
  
<%@ include file="../../part/foot.jspf" %>