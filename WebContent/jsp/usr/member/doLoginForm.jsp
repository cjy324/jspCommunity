<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="GetIT | Login" />
<c:set var="REST_ID" value="c5cafbd7423d14e396657832f41dd5e7" />
<c:set var="Redirect_URI" value="https://getit.devj.me/usr/member/doKakaoLogin" />
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
  <main class="main-box flex-grow-1 visible-md-up">
    <section class="main-box-section con">
      <!-- 메인-로그인 페이지 시작 -->
      <div class="section-login min-height-50vh flex flex-jc-c flex-ai-c">

        <form name="form" onsubmit="check(this); return false;" action="doLogin" method="POST">
          <input type="hidden" name="nextUrlAfterLogin" value="${param.nextUrlAfterLogin}">
          <input type="hidden" name="loginPwReal">
          <input type="hidden" name="beforeUrl" value="${param.beforeUrl}">
          <div class="login-title">
          	<img style="height: 38px" src="${pageContext.request.contextPath}/images/GetIT_logo/logo.png">
          </div>
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
             <button class="btn btn-go" type="submit"><i class="fas fa-sign-in-alt"></i> Login</button>
             <button class="btn btn-back" type="button" onclick="history.back();"><i class="fas fa-undo"></i> Back</button>
            </div>
            <div>
             <a href="../member/doFindLoginIdForm">ID 찾기</a>
           / <a href="../member/doFindLoginPwForm">PW 찾기</a>
           	 <!-- 카카오 로그인 -->
			 <a class="block" style="margin-top: 12px" href="https://kauth.kakao.com/oauth/authorize
				?client_id=${REST_ID}
				&redirect_uri=${Redirect_URI}
				&response_type=code">
				<img style="" src="https://developers.kakao.com/tool/resource/static/img/button/login/full/ko/kakao_login_medium_narrow.png" alt="" />
			 </a>
            </div>
            
          </div>
        </form>   
      </div>
      <!-- 메인-로그인 페이지 끝 -->
    </section> 
  </main>
  <!-- 메인 컨텐츠 박스 끝 -->

  
  <!-- 모바일-메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1 visible-sm-down">
    <section class="main-box-section con">
      <!-- 모바일-로그인 페이지 시작 -->
      <div class="mobile-section-login min-height-50vh flex flex-jc-c flex-ai-c">

        <form name="form" onsubmit="check(this); return false;" action="doLogin" method="POST">
          <input type="hidden" name="nextUrlAfterLogin" value="${param.nextUrlAfterLogin}">
          <input type="hidden" name="loginPwReal">
          <input type="hidden" name="beforeUrl" value="${param.beforeUrl}">
          <div class="mobile-login-title">
           <img style="height: 32px" src="${pageContext.request.contextPath}/images/GetIT_logo/logo.png">
          </div>
          <div class="mobile-login_cell__title">
            <span>아이디</span>
          </div>
          <div class="mobile-login_cell__body">
            <input type="text" name="loginId" maxlength="50" placeholder="ID">
          </div>
          <div class="mobile-login_cell__title">
            <span>비밀번호</span>
          </div>
          <div class="mobile-login_cell__body">
            <input type="password" name="loginPw" maxlength="50" placeholder="Password">
          </div>
          <div class="mobile-loginInput_cell">
            <div>
            <button class="btn btn-go" type="submit"><i class="fas fa-sign-in-alt"></i> Login</button>
            <button class="btn btn-back" type="button" onclick="history.back();"><i class="fas fa-undo"></i> Back</button>
            </div>
            <div>
            <a href="../member/doFindLoginIdForm">ID 찾기</a>
           / <a href="../member/doFindLoginPwForm">PW 찾기</a>
           <!-- 카카오 로그인 -->
			  <a class="block" style="margin-top: 12px; width:100%; text-align:center;" href="https://kauth.kakao.com/oauth/authorize
				?client_id=${REST_ID}
				&redirect_uri=${Redirect_URI}
				&response_type=code">
				<img style="" src="https://developers.kakao.com/tool/resource/static/img/button/login/full/ko/kakao_login_medium_wide.png" alt="" />
			  </a>
              </div>
            </div>
        </form>
      </div>
      <!-- 모바일-로그인 페이지 끝 -->
    </section>
  </main>
  <!-- 모바일-메인 컨텐츠 박스 끝 -->



<%@ include file="../../part/foot.jspf"%>