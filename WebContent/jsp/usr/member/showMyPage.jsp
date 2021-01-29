<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MyPage"/>
<%@ include file="../../part/head.jspf" %>
	
  <!-- 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1">
    <section class="main-box-section con">
      <!-- 메인-회원정보 페이지 시작 -->
      <div class="section-MyPage min-height-50vh flex flex-jc-c flex-ai-c">
      	<div>
        <div class="section-MyPage-body">
          <div class="MyPage_cell__title">
            <span>회원번호</span>
          </div>
          <div class="MyPage_cell__body">
            <span>${loginedMember.id}</span>
          </div>
          <div class="MyPage_cell__title">
            <span>회원ID</span>
          </div>
          <div class="MyPage_cell__body">
          <span>${loginedMember.loginId}</span>
          </div>
          <div class="MyPage_cell__title">
            <span>회원이름</span>
          </div>
          <div class=MyPage_cell__body>
          	<span>${loginedMember.name}</span>
          </div>
          <div class=MyPage_cell__title>
            <span>닉네임</span>
          </div>
          <div class=MyPage_cell__body>
          	<span>${loginedMember.nickname}</span>
		  </div>
          <div class=MyPage_cell__title>
            <span>e-Mail</span>        
          </div>
          <div class=MyPage_cell__body>
         	<span>${loginedMember.email}</span>       
          </div>
          <div class=MyPage_cell__title>
            <span>연락처</span>
          </div>
          <div class=MyPage_cell__body>
         	<span>${loginedMember.cellphoneNo}</span> 
          </div>
          <div class=MyPage_cell__title>
            <span>회원등급</span>
          </div>
          <div class=MyPage_cell__body>
            <span>${loginedMember.authLevel}</span>
          </div>
          <div class=MyPage_cell__title>
            <span>회원가입일</span>
          </div>
          <div class=MyPage_cell__body>
            <span>${loginedMember.regDate}</span>
          </div>
          <button class ="doModifyBtn btn" type="button"><a href="../member/doModifyForm">수정</a></button>
        </div>
      	</div>
      </div>
      <!-- 메인-회원정보 페이지 끝 -->
    </section>
  </main>
  <!-- 메인 컨텐츠 박스 끝 -->
<%@ include file="../../part/foot.jspf" %>