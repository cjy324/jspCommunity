<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var ="pageTitle" value="메인화면"/>
<%@ include file="../../part/head.jspf" %>
	
  <!-- 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1 visible-md-up">
    <section class="main-box-section con ">
      <!-- 메인 홈 시작 -->
      <div class="section-home flex flex-column">
        <div id="section-home-slide">
          <div id="wrapper">
            <div id="slider-wrap">
              <ul id="slider">
                <li>
                  <div>
                    <h3>APPLE</h3>
                    <span>"애플 아이폰 12 미니, DxOMark Mobile 122점 획득"</span>
                  </div>
                  <a href="../article/list?boardId=3">
                    <img src="https://underkg.co.kr:44391/files/attach/images/145/158/798/002/92a951e821610b40e416acf7a532b7e6.jpg"></a>
                </li>

                <li>
                  <div>
                    <h3>SANSUNG</h3>
                    <span>"삼성 갤럭시 A52 5G 및 A72 5G 추가 유출"</span>
                  </div>
                  <a href="../article/list?boardId=3">
                    <img src="https://underkg.co.kr:44391/files/attach/images/145/200/798/002/b60a1d7858a0eff3a2fac3326da25e40.jpg"></a>
                </li>

                <li>
                  <div>
                    <h3>APPLE</h3>
                    <span>"2021년 애플 모바일 기기 전망, 새 아이패드 프로 나오고 iOS는 '관리 모드'"</span>
                  </div>
                  <a href="../article/list?boardId=3">
                    <img src="https://www.itworld.co.kr/files/itworld/2021/01/magic-keyboard-smart-keyboard-folio-100841331-large.jpg"></a>
                </li>

                <li>
                  <div>
                    <h3>LG</h3>
                    <span>"여의봉처럼 스마트폰-태블릿 변화무쌍…‘LG 롤러블’ 깜짝 공개"</span>
                  </div>
                  <a href="../article/list?boardId=3">
                    <img src="http://flexible.img.hani.co.kr/flexible/normal/970/541/imgdb/original/2021/0112/20210112501569.jpg"></a>
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
            <div><span>NOTICE</span></div>
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
                    <img src="https://images.unsplash.com/photo-1509395062183-67c5ad6faff9?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80" alt="">
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
                    <img src="https://insight-prd-data.s3.ap-northeast-2.amazonaws.com/wp-content/uploads/2017/07/%EC%95%84%EC%9D%B4%ED%8C%A8%EB%93%9C%ED%94%84%EB%A1%9C_%EB%A6%AC%EB%B7%B0_01.png" alt="">
                    <div class="img-txt">
                      Free
                    </div>
                  </div>
                </div>
              </a>
            </nav>
          </div>
          <div class="home-list home-list2 flex flex-column">
            <div><span>FREE BOARD</span></div>
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
            <div><span>NEWS</span></div>
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
  
  
  <!-- 모바일 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1 visible-sm-down" style="min-height:500px;">
    <section class="main-box-section con ">
      <!-- 모바일 메인 홈 시작 -->
      <div class="mobile-section-home">
        <div class="mobile-section-home-list1 ">
          <div class="mobile-home-list_img">
            <nav class="flex flex-jc-c">
              <a href="../article/list?boardId=1">
                <div>
                  <div class="mobile-img-box">
                    <img src="https://images.unsplash.com/photo-1509395062183-67c5ad6faff9?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80" alt="">
                    <div class="mobile-img-txt">
                      Notice
                    </div>
                  </div>
                </div>
              </a>
            </nav>
          </div>
        </div>
        <div class="mobile-section-home-list2">
          <div class="mobile-home-list_img">
            <nav class="flex flex-jc-c">
              <a href="../article/list?boardId=2">
                <div>
                  <div class="mobile-img-box">
                    <img src="https://insight-prd-data.s3.ap-northeast-2.amazonaws.com/wp-content/uploads/2017/07/%EC%95%84%EC%9D%B4%ED%8C%A8%EB%93%9C%ED%94%84%EB%A1%9C_%EB%A6%AC%EB%B7%B0_01.png" alt="">
                    <div class="mobile-img-txt">
                      Free
                    </div>
                  </div>
                </div>
              </a>
            </nav>
          </div>
        </div>
        <div class="mobile-section-home-list3">
          <div class="mobile-home-list_img">
            <nav class="flex flex-jc-c">
              <a href="../article/list?boardId=3">
                <div>
                  <div class="mobile-img-box">
                    <img src="https://images.unsplash.com/photo-1585719022717-87adb5bc279d?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80" alt="">
                    <div class="mobile-img-txt">
                      News
                    </div>
                  </div>
                </div>
              </a>
            </nav>
          </div>
        </div>
      </div>
      <!-- 모바일 메인 홈 시작 -->
    </section>
  </main>
  <!-- 모바일 메인 컨텐츠 박스 끝 -->
  
  
<%@ include file="../../part/foot.jspf" %>