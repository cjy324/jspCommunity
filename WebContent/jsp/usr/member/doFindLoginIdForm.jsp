<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="GetIT | FindID" />
<%@ include file="../../part/head.jspf"%>

<script>
	function check(form) {
		let DoFindLoginIdForm_submited = false;
	
		if ( DoFindLoginIdForm_submited ) {
			alert('처리중 입니다.');
			return;
		}

		if (form.name.value.trim().length == 0) {

			alert("이름을 입력해 주세요.");

			form.name.focus();

			return false;

		}

		if (form.email.value.trim().length == 0) {

			alert("e-mail을 입력해 주세요.");

			form.email.focus();

			return false;

		}
		
		form.submit();
		DoFindLoginIdForm_submited = true;

	};
</script>

  <!-- 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1 visible-md-up">
    <section class="main-box-section con">
      <!-- 메인-로그인아이디 찾기 페이지 시작 -->
      <div class="section-login min-height-50vh flex flex-jc-c flex-ai-c">

        <form name="form" onsubmit="check(this); return false;" action="doFindLoginId" method="POST">
          <div class="mobile-login-title">
           ID 찾기
          </div>
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
            <button class="btn btn-go" type="submit"><i class="fas fa-search"></i> ID 찾기</button>
            <button class="btn btn-back" type="button" onclick="history.back();"><i class="fas fa-undo-alt"></i> Back</button>
          </div>
        </form>
      </div>
      <!-- 메인-로그인아이디 찾기 페이지 끝 -->
    </section>
  </main>
  <!-- 메인 컨텐츠 박스 끝 -->
  
  <!-- 모바일-메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1 visible-sm-down">
    <section class="main-box-section con">
      <!-- 모바일-로그인아이디 찾기 페이지 시작 -->
      <div class="mobile-section-login min-height-50vh flex flex-jc-c flex-ai-c">

        <form name="form" onsubmit="check(this); return false;" action="doFindLoginId" method="POST">
          <div class="mobile-login-title">
           ID 찾기
          </div>
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
          <div class="mobile-loginInput_cell">
            <button class="btn btn-go" type="submit"><i class="fas fa-search"></i> ID 찾기</button>
            <button class="btn btn-back" type="button" onclick="history.back();"><i class="fas fa-undo-alt"></i> Back</button>
          </div>
        </form>
      </div>
      <!-- 모바일-로그인아이디 찾기 페이지 끝 -->	
    </section>
  </main>
  <!-- 모바일-메인 컨텐츠 박스 끝 -->



<%@ include file="../../part/foot.jspf"%>