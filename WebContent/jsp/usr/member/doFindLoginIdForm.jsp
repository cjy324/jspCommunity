<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="아이디 찾기" />
<%@ include file="../../part/head.jspf"%>

<!-- sha256 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>

<script>
	function check(form) {

		if (form.loginId.value.trim().length == 0) {

			alert("ID를 입력해주세요.");

			form.loginId.focus();

			return false;

		}

		if (form.loginPw.trim().length == 0) {

			alert("PASSWORD를 입력해 주세요.");

			form.loginPw.focus();

			return false;

		}
		
		form.loginPwReal.value = sha256(form.loginPw.value);
		form.loginPw.value = "";
		
		form.submit();

	};
</script>

  <!-- 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1">
    <section class="main-box-section con">
      <!-- 메인-로그인 페이지 시작 -->
      <div class="section-login min-height-50vh flex flex-jc-c flex-ai-c">

        <form name="form" onsubmit="check(this); return false;" action="doFindLoginId" method="POST">
          
          <div class="login_cell__title">
            <span>이름</span>
          </div>
          <div class="login_cell__body">
            <input type="text" name="name" maxlength="50" placeholder="이름 입력">
          </div>
          <div class="login_cell__title">
            <span>이메일 주소</span>
          </div>
          <div class="login_cell__body">
            <input type="email" name="email" maxlength="50" placeholder="email 입력">
          </div>
          <div class="loginInput_cell">
            <input type="submit" value="아이디 찾기">
            <button type="button" onclick="history.back();">뒤로가기</button>
            <div>
        </form>
      </div>
      <!-- 메인-로그인 페이지 끝 -->
    </section>
  </main>
  <!-- 메인 컨텐츠 박스 끝 -->



<%@ include file="../../part/foot.jspf"%>