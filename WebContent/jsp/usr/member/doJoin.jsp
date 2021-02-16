<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var ="pageTitle" value="GetIT | Join"/>
<%@ include file="../../part/head.jspf" %>
	
	
	
<!-- 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1">
    <section class="main-box-section con">
      <!-- 메인-회원정보 페이지 시작 -->
      <div class="section-MyPage min-height-50vh flex flex-jc-c flex-ai-c">
      	<form>
        <nav class="section-MyPage-body">
        <nav style="font-size:1rem; text-align:center;">
        	<nav style="font-size:1.5rem; text-align:center; padding-bottom:10px; border-bottom:4px solid gray;">
        		&lt ${member.nickname}님, 반갑습니다!! &gt
        	</nav>
        <br />
        <br />
		회원번호 :
		${member.id}
		<br />
		아이디 :
		${member.loginId}
		<br />
		닉네임 :
		${member.nickname}
		<br />
		<hr />
		<br />
		<a style="text-decoration:none;" href="../home/main"><i class="fas fa-home"></i> Home으로 이동</a>
		<br />
		<a style="text-decoration:none; margin-top:10px;" href="../member/doLoginForm"><i class="fas fa-sign-in-alt"></i> Login하러 가기</a>
		<br />
		<br />
		</nav>
        </nav>
      	</form>
      </div>
      <!-- 메인-회원정보 페이지 끝 -->
    </section>
  </main>
  <!-- 메인 컨텐츠 박스 끝 -->
	
	
	
	
	
	
	
	
	
<%@ include file="../../part/foot.jspf" %>