<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var ="pageTitle" value="GetIT | HOME"/>
<%@ include file="../../part/head.jspf" %>

	
  <!-- 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1 visible-md-up">
    <!-- 메인 홈 시작 -->
    <div class="section-home flex flex-column">
      <div class="section-home-box section-home-box1 flex flex-jc-sb">
        <div class="home-box_letter home-box_letter1 flex flex-column flex-jc-c">
          <header>Get NOTICE</header>
          <div class="">
            Get IT 사이트 관련 주요 알림들을 확인하세요~!
          </div>
          <div class="">
            <i class="fas fa-bullhorn"></i> 공지사항들을 확인하고 싶다면?
            <br>
            <i class="fas fa-exclamation-circle"></i> Get IT 사이트에 대한 정보를 알고싶다면?
          </div>
          <div class="flex flex-jc-c">
            <a href="../article/list?boardId=1">
              <nav title="게시판으로 이동">
                More <i class="fas fa-arrow-right"></i>
              </nav>
            </a>  
          </div>
        </div>
        <div class="home-box_img" title="게시판으로 이동">
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
      <div class="section-home-box section-home-box2 flex flex-jc-sb">
        <div class="home-box_img" title="게시판으로 이동">
          <nav class="flex flex-jc-c">
            <a href="../article/list?boardId=2">
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
        <div class="home-box_letter home-box_letter2 flex flex-column flex-jc-c">
          <header>Get NEWS</header>
          <div class="">
            Apple, Samsung, 그 외 Smart 기기들과 관련된
            <br>
            최신정보들을 빠르게 가져가세요~!
          </div>
          <div class="">
            <i class="fas fa-mobile-alt"></i>
            iPhone / galaxy 
            <br>
            <i class="fas fa-tablet-alt"></i>
            iPad / galaxy tab
            <br>
            
            etc.
          </div>
          <div class="flex flex-jc-c">
            <a href="../article/list?boardId=2">
              <nav title="게시판으로 이동">   
                <i class="fas fa-arrow-left"></i> More
              </nav>
            </a>
          </div>
        </div>

      </div>
      <div class="section-home-box section-home-box3 flex flex-jc-sb">
        <div class="home-box_letter home-box_letter3 flex flex-column flex-jc-c">
          <header>Get TIP's</header>
          <div class="">
            내가 쓰고 있는 Smart 기기들에 대한
            <br>
            소소한 Tip들을 공유해요~!
          </div>
          <div class="">
            <i class="fas fa-users"></i>
            Tip's
          </div>
          <div class="flex flex-jc-c">
            <a href="../article/list?boardId=3">
              <nav title="게시판으로 이동">  
                More <i class="fas fa-arrow-right"></i>
              </nav>
            </a>
          </div>
        </div>
        <div class="home-box_img" title="게시판으로 이동">
          <nav class="flex flex-jc-c">
            <a href="../article/list?boardId=3">
              <div>
                <div class="img-box">
                  <img src="https://insight-prd-data.s3.ap-northeast-2.amazonaws.com/wp-content/uploads/2017/07/%EC%95%84%EC%9D%B4%ED%8C%A8%EB%93%9C%ED%94%84%EB%A1%9C_%EB%A6%AC%EB%B7%B0_01.png" alt="">
                  <div class="img-txt">
                    Tip's
                  </div>
                </div>
              </div>
            </a>
          </nav>
        </div>
      </div>
    </div>
    <!-- 메인 홈 끝 -->
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
                      NOTICE <i class="fas fa-arrow-right"></i>
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
                    <img src="https://images.unsplash.com/photo-1585719022717-87adb5bc279d?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80" alt="">
                    <div class="mobile-img-txt">
                      NEWS <i class="fas fa-arrow-right"></i>
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
                    <img src="https://insight-prd-data.s3.ap-northeast-2.amazonaws.com/wp-content/uploads/2017/07/%EC%95%84%EC%9D%B4%ED%8C%A8%EB%93%9C%ED%94%84%EB%A1%9C_%EB%A6%AC%EB%B7%B0_01.png" alt="">
                    <div class="mobile-img-txt">
                      TIP's <i class="fas fa-arrow-right"></i>
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