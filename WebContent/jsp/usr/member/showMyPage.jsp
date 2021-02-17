<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="GetIT | MyPage"/>
<%@ include file="../../part/head.jspf" %>
  
  <!-- 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1 visible-md-up">
    <section class="main-box-section con">
      <!-- 회원정보 페이지 시작 -->
      <div class="section-MyPage min-height-50vh flex flex-jc-c flex-ai-c ">
        <form name="form" onsubmit="check(this); return false;" action="doModifyInfo" method="POST">
          <input type="hidden" name="id" value="${loginedMember.id}">
          <input type="hidden" name="loginPwReal">

          <div class="section-MyPage-body flex flex-jc-c flex-ai-c">
            <div>MY PAGE</div>
            <div class="section-MyPage-body__cell">
              <div class="MyPage_cell__title">
                <span>회원번호</span>
                <div class="MyPage_cell__body">
                  <span>${loginedMember.id}</span>
                </div>
              </div>

              <div class="MyPage_cell__title">
                <span>회원ID</span>
                <div class="MyPage_cell__body">
                  <span>${loginedMember.loginId}</span>
                </div>
              </div>

              <div class="MyPage_cell__title">
                <span>회원이름</span>
                <div class=MyPage_cell__body>
                  <span>${loginedMember.name}</span>
                </div>
              </div>
              
              <div class="MyPage_cell__title">
                <span>닉네임</span>
                <div class="MyPage_cell__body">
                  <span>${loginedMember.nickname}</span>
                </div>
              </div>
            </div>

            <div class="section-MyPage-body__cell">
              
              <div class=MyPage_cell__title>
                <span>e-mail</span>
                <div class=MyPage_cell__body>
                  <span>${loginedMember.email}</span>
                </div>
              </div>

              <div class=MyPage_cell__title>
                <span>연락처</span>
                <div class=MyPage_cell__body>
                  <span>${loginedMember.cellphoneNo}</span>
                </div>
              </div>

              <div class=MyPage_cell__title>
                <span>회원등급</span>
                <div class=MyPage_cell__body>
                  <span>${loginedMember.authLevel}</span>
                </div>
              </div>

              <div class=MyPage_cell__title>
                <span>회원가입일</span>
                <div class=MyPage_cell__body>
                  <span>${loginedMember.regDate}</span>
                </div>
              </div>
              <div class="section-MyPage-body__option flex flex-jc-fe flex-ai-fe">
                <button class ="doModifyBtn btn btn-go" type="button"><a href="../member/doModifyForm"><i class="fas fa-user-cog"></i> 수정</a></button>
              </div>
            </div>
          </div>
        </form>
      </div>
      <!-- 회원정보 페이지 끝 -->
    </section>
  </main>
  <!-- 메인 컨텐츠 박스 끝 -->

  <!-- 모바일-메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1 visible-sm-down">
    <section class="main-box-section con">
      <!-- 모바일-회원정보 페이지 시작 -->
      <div class="mobile-section-MyPage min-height-50vh flex flex-jc-c flex-ai-c ">
        <form name="form" onsubmit="check(this); return false;" action="doModifyInfo" method="POST">
          <input type="hidden" name="id" value="${loginedMember.id}">
          <input type="hidden" name="loginPwReal">

          <div class="mobile-section-MyPage-body flex flex-jc-c flex-column">
            <div>
              MY PAGE
            </div>
            <div class="mobile-MyPage_cell__title">
              <span>회원번호</span>
              <div class="mobile-MyPage_cell__body">
                <span>${loginedMember.id}</span>
              </div>
            </div>

            <div class="mobile-MyPage_cell__title">
              <span>회원ID</span>
              <div class="mobile-MyPage_cell__body">
                <span>${loginedMember.loginId}</span>
              </div>
            </div>

            <div class="mobile-MyPage_cell__title">
              <span>회원이름</span>
              <div class="mobile-MyPage_cell__body">
                <span>${loginedMember.name}</span>
              </div>
            </div>

            <div class="mobile-MyPage_cell__title">
              <span>닉네임</span>
              <div class="mobile-MyPage_cell__body">
                <span>${loginedMember.nickname}</span>
              </div>
            </div>
            <div class="mobile-MyPage_cell__title">
              <span>e-mail</span>
              <div class="mobile-MyPage_cell__body">
                <span>${loginedMember.email}</span>
              </div>
            </div>

            <div class="mobile-MyPage_cell__title">
              <span>연락처</span>
              <div class="mobile-MyPage_cell__body">
                <span>${loginedMember.cellphoneNo}</span>
              </div>
            </div>

            <div class="mobile-MyPage_cell__title">
              <span>회원등급</span>
              <div class="mobile-MyPage_cell__body">
                <span>${loginedMember.authLevel}</span>
              </div>
            </div>

            <div class="mobile-MyPage_cell__title">
              <span>회원가입일</span>
              <div class="mobile-MyPage_cell__body">
                <span>${loginedMember.regDate}</span>
              </div>
            </div>
            <div class="mobile-section-MyPage-body__option flex flex-column">
              <button class ="doModifyBtn btn btn-go" type="button"><a href="../member/doModifyForm"><i class="fas fa-user-cog"></i> 수정</a></button>
            </div>

          </div>
        </form>
      </div>
      <!-- 모바일-회원정보 페이지 끝 -->
    </section>
  </main>
  <!-- 모바일-메인 컨텐츠 박스 끝 -->
  
  
<%@ include file="../../part/foot.jspf" %>