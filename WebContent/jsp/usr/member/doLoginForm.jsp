<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="로그인" />
<%@ include file="../../part/head.jspf"%>

<!-- sha256 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>

<script>
	function check(form) {
	
		let DoLoginIdForm_submited = false;
	
		if ( DoLoginIdForm_submited ) {
			alert('처리중 입니다.');
			return;
		}

		if (form.loginId.value.trim().length == 0) {

			alert("ID를 입력해 주세요.");

			form.loginId.focus();

			return false;

		}

		if (form.loginPw.value.trim().length == 0) {

			alert("PASSWORD를 입력해 주세요.");

			form.loginPw.focus();

			return false;

		}
		
		form.loginPwReal.value = sha256(form.loginPw.value);
		form.loginPw.value = "";
		
		form.submit();
		DoLoginIdForm_submited = true;

	};
</script>

  <!-- 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1">
    <section class="main-box-section con">
      <!-- 메인-로그인 페이지 시작 -->
      <div class="section-login min-height-50vh flex flex-jc-c flex-ai-c">

        <form name="form" onsubmit="return check()" action="doLogin" method="POST">
          <div class="login_cell__title">
            <span>아이디</span>
          </div>
          <div class="login_cell__body">
            <input type="text" name="loginId" maxlength="50" placeholder="ID">
          </div>
          <div class="login_cell__title">
            <span>비밀번호</span>
          </div>
          <div class="login_cell__body">
            <input type="password" name="loginPw" maxlength="50" placeholder="Password">
          </div>
          <div class="loginInput_cell">
            <div>
            <button class="btn" type="submit">Login</button>
            <button class="btn btn-back" type="button" onclick="history.back();">Back</button>
            </div>
            <div>
            <a href="../member/doFindLoginIdForm">ID 찾기</a>
           / <a href="../member/doFindLoginPwForm">PW 찾기</a>
              </div>
            </div>
        </form>
      </div>
      <!-- 메인-로그인 페이지 끝 -->
    </section>
  </main>
  <!-- 메인 컨텐츠 박스 끝 -->



<%@ include file="../../part/foot.jspf"%>