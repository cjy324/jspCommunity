<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var ="pageTitle" value="GetIt | Join"/>
<%@ include file="../../part/head.jspf" %>
	
	
	
<!-- 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1">
    <section class="main-box-section con">
      <!-- 메인-회원정보 페이지 시작 -->
      <div class="section-MyPage min-height-50vh flex flex-jc-c flex-ai-c">
      	<form>
        <div class="section-MyPage-body">
        <div style="font-size:1rem; text-align:center;">
        <h1>&lt&lt ${member.nickname}님, 회원가입 완료!! &gt&gt</h1>
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
		<hr />
		<br />
		<a style="text-decoration:none;" href="../home/main"><i class="fas fa-home"></i> Home으로 이동</a>
		<br />
		<br />
		<a style="text-decoration:none;" href="../member/doLoginForm"><i class="fas fa-sign-in-alt"></i> Login하러 가기</a>
		<br />
		<br />
		</div>
        </div>
      	</form>
      </div>
      <!-- 메인-회원정보 페이지 끝 -->
    </section>
  </main>
  <!-- 메인 컨텐츠 박스 끝 -->
	
	
	
	
	
	
	
	
	
<%@ include file="../../part/foot.jspf" %>