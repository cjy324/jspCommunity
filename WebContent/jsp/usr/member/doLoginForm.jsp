<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="로그인" />
<%@ include file="../../part/head.jspf"%>
<h1>${pageTitle}</h1>

<script>
	function check() {

		if (form.loginId.value.trim().length == 0) {

			alert("ID를 입력해주세요.");

			form.loginId.focus();

			return false;

		}

		else if (form.loginPw.trim().length == 0) {

			alert("PASSWORD를 입력해 주세요.");

			form.loginPw.focus();

			return false;

		}

		else
			return true;

	}
</script>

  <!-- 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1">
    <section class="main-box-section con">
      <!-- 메인-로그인 페이지 시작 -->
      <div class="section-login min-height-50vh flex flex-jc-c flex-ai-c">

        <form name="form" onsubmit="return check()" action="doLogin" method="POST">
          <div class="login_cell__title">
            <span>로그인 ID</span>
          </div>
          <div class="login_cell__body">
            <input type="text" name="loginId" maxlength="50" placeholder="ID 입력">
          </div>
          <div class="login_cell__title">
            <span>로그인 PW</span>
          </div>
          <div class="login_cell__body">
            <input type="password" name="loginPw" maxlength="50" placeholder="PW 입력">
          </div>
          <div class="loginInput_cell">
            <input type="submit" value="로그인">
            <button type="button" onclick="history.back();">뒤로가기</button>
            <div>
        </form>
      </div>
      <!-- 메인-로그인 페이지 끝 -->
    </section>
  </main>
  <!-- 메인 컨텐츠 박스 끝 -->



<%@ include file="../../part/foot.jspf"%>