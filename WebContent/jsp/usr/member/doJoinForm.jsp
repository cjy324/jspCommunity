<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="GetIT | Sign-Up" />
<c:set var="REST_ID" value="c5cafbd7423d14e396657832f41dd5e7" />
<c:set var="Redirect_URI" value="https://getit.devj.me/usr/member/doKakaoLogin" />
<%@ include file="../../part/head.jspf"%>

<!-- sha256 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>

<script>
	//변수명과 함수명이 같으면 에러발생!!
	let checkedDupId = "";
	// 아이디 중복체크
	function checkDupId(el){
		//가장 근접한 'form'을 가져오기
		//$(el).parent.parent.parent 방식으로 부모를 찾아도 됨
		const form = $(el).closest('form').get(0);	
		const loginId = form.loginId.value;
		
		//ajax 통신으로 보내고 데이터 받기
		$.get(
			'getLoginIdDup',  //요청할 주소
			{		
				loginId
			},
			function(data){
				if(data.msg){
					alert(data.msg + " (" + data.resultCode + ")");
				}
				if(data.success){
					checkedDupId = data.body.loginId;				
				}
			},			
			'json'
		);
	};
	
	// 닉네임 중복체크
	let checkedDupNick = "";

	function checkDupNick(el){
		const form = $(el).closest('form').get(0);
		const nickname = form.nickname.value;

		$.get(
			'getNicknameDup',  //요청할 주소
			{		
				nickname
			},
			function(data){
				if(data.msg){
					alert(data.msg + " (" + data.resultCode + ")");
				}
				if(data.success){
					checkedDupNick = data.body.nickname;				
				}
			},			
			'json'
		);	
	};

	
	// 폼 공백 체크
	function check(form) {
	
		if (form.loginId.value.trim().length == 0) {
			alert("ID를 입력해 주세요.");
			form.loginId.focus();
			return;
		}
		if (form.loginId.value != checkedDupId) {
			alert("먼저 ID 중복체크를 해주세요.");
			form.dupIdCheck.focus();
			return false;
		}
		if (form.loginPw.value.trim().length == 0) {
			alert("PW를 입력해 주세요.");
			form.loginPw.focus();
			return;
		}
		if (form.loginPw.value != form.loginPwConfirm.value) {
			alert("PW가 일치하지 않습니다. PW를 확인해 주세요.");
			form.loginPwConfirm.focus();
			return;
		}
		if (form.name.value.trim().length == 0) {
			alert("이름을 입력해 주세요.");
			form.name.focus();
			return;
		}
		if (form.nickname.value.trim().length == 0) {
			alert("닉네임을 입력해 주세요.");
			form.nickname.focus();
			return;
		}
		if (form.nickname.value != checkedDupNick) {
			alert("먼저 닉네임 중복체크를 해주세요.");
			form.dupNickCheck.focus();
			return false;
		}
		if (form.email.value.trim().length == 0) {
			alert("e-mail을 입력해 주세요.");
			form.email.focus();	
			return;
		}
		if (form.cellPhoneNo.value.trim().length == 0) {
			alert("연락처를 입력해 주세요.");
			form.cellPhoneNo.focus();	
			return;
		}
		
		// loginPw를 sha256으로 암호화하고
		// loginPw와 loginPwConfirm는 공백값으로 전송
		form.loginPwReal.value = sha256(form.loginPw.value);
		form.loginPw.value = "";
		form.loginPwConfirm.value = "";
		
		form.submit();
	};
</script>


  <!-- 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1 visible-md-up">
    <section class="main-box-section con">
      <!-- 회원가입 페이지 시작 -->
      <div class="section-join min-height-50vh flex flex-jc-c flex-ai-c">

        <form class="flex flex-jc-c flex-ai-c" name="form" onsubmit="check(this); return false;" action="doJoin" method="POST">
          <input type="hidden" name="loginPwReal">
          
          <div>Sign-Up</div>
          
          <div class="section-join__cell">
            <div class="join_cell__title">
              <span>신규 ID</span>
            </div>
            <div class="join_cell__body">
              <input class="idInput" type="text" name="loginId" maxlength="50" placeholder="ID 입력">
              <button class="btn btn-check" name="dupIdCheck" onclick="checkDupId(this);" type="button"><i class="far fa-check-circle"></i> 중복체크</button>
            </div>
            <div class="join_cell__title">
              <span>Password</span>
            </div>
            <div class="join_cell__body">
              <input type="password" name="loginPw" maxlength="50" placeholder="PW 입력">
            </div>
            <div class="join_cell__title">
              <span>Password Check</span>
            </div>
            <div class="join_cell__body">
              <input type="password" name="loginPwConfirm" maxlength="50" placeholder="PW 입력">
            </div>
            <div class="join_cell__title">
              <span>이름</span>
            </div>
            <div class="join_cell__body">
              <input type="text" name="name" maxlength="50" placeholder="이름 입력">
            </div>
          </div>

          <div class="section-join__cell">
            <div class="join_cell__title">
              <span>닉네임</span>
            </div>
            <div class="join_cell__body">
              <input type="text" name="nickname" maxlength="50" placeholder="닉네임 입력">
              <button class="btn btn-check" name="dupNickCheck" onclick="checkDupNick(this);" type="button"><i class="far fa-check-circle"></i> 중복체크</button>
            </div>
            <div class="join_cell__title">
              <span>e-mail</span>
            </div>
            <div class="join_cell__body">
              <input type="email" name="email" maxlength="100" placeholder="이메일 주소 입력">
            </div>
            <div class="join_cell__title">
              <span>연락처</span>
            </div>
            <div class="join_cell__body">
              <input type="tel" name="cellPhoneNo" maxlength="11" placeholder="'-' 생략하고 입력">
            </div>
            <div class="joinInput-cell">
              <button class="btn btn-go" type="submit"><i class="fas fa-sign-in-alt"></i> 회원가입</button>
              <button class="btn btn-back" type="button" onclick="history.back();"><i class="fas fa-undo"></i> 뒤로가기</button>
              <!-- 카카오 로그인 -->
			  <a class="block" style="margin-top: 12px; margin-right: 18px;" href="https://kauth.kakao.com/oauth/authorize
				?client_id=${REST_ID}
				&redirect_uri=${Redirect_URI}
				&response_type=code">
				<img style="" src="https://developers.kakao.com/tool/resource/static/img/button/login/full/ko/kakao_login_medium_narrow.png" alt="" />
			  </a>
            </div> 
          </div>
        </form>
      </div>
      <!-- 회원가입 페이지 끝 -->
    </section>
  </main>
  <!-- 메인 컨텐츠 박스 끝 -->

  <!-- 모바일-메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1 visible-sm-down">
    <section class="main-box-section con">
      <!-- 모바일-회원가입 페이지 시작 -->
      <div class="mobile-section-join min-height-50vh flex flex-jc-c flex-ai-c">

        <form class="" name="form" onsubmit="check(this); return false;" action="doJoin" method="POST">
          <input type="hidden" name="loginPwReal">

          <div>Sign-Up</div>
          
          <div class="mobile-join_cell__title">
            <span>신규 ID</span>
          </div>
          <div class="mobile-join_cell__body">
            <input class="idInput" type="text" name="loginId" maxlength="50" placeholder="ID 입력">
            <button class="btn btn-check" name="dupIdCheck" onclick="checkDupId(this);" type="button"><i class="far fa-check-circle"></i> 중복체크</button>
          </div>
          <div class="mobile-join_cell__title">
            <span>Password</span>
          </div>
          <div class="mobile-join_cell__body">
            <input type="password" name="loginPw" maxlength="50" placeholder="PW 입력">
          </div>
          <div class="mobile-join_cell__title">
            <span>Password Check</span>
          </div>
          <div class="mobile-join_cell__body">
            <input type="password" name="loginPwConfirm" maxlength="50" placeholder="PW 입력">
          </div>
          <div class="mobile-join_cell__title">
            <span>이름</span>
          </div>
          <div class="mobile-join_cell__body">
            <input type="text" name="name" maxlength="50" placeholder="이름 입력">
          </div>
          <div class="mobile-join_cell__title">
            <span>닉네임</span>
          </div>
          <div class="mobile-join_cell__body">
            <input type="text" name="nickname" maxlength="50" placeholder="닉네임 입력">
            <button class="btn btn-check" name="dupNickCheck" onclick="checkDupNick(this);" type="button"><i class="far fa-check-circle"></i> 중복체크</button>
          </div>
          <div class="mobile-join_cell__title">
            <span>e-mail</span>
          </div>
          <div class="mobile-join_cell__body">
            <input type="email" name="email" maxlength="100" placeholder="이메일 주소 입력">
          </div>
          <div class="mobile-join_cell__title">
            <span>연락처</span>
          </div>
          <div class="mobile-join_cell__body">
            <input type="tel" name="cellPhoneNo" maxlength="50" placeholder="'-' 생략하고 입력">
          </div>

          <div class="mobile-joinInput-cell flex flex-column ">
            <button class="btn btn-go" type="submit"><i class="fas fa-sign-in-alt"></i> 회원가입</button>
            <button class="btn btn-back" type="button" onclick="history.back();"><i class="fas fa-undo"></i> 뒤로가기</button>
          	<!-- 카카오 로그인 -->
			  <a class="block" style="margin-top: 12px; width:100%; text-align:center;" href="https://kauth.kakao.com/oauth/authorize
				?client_id=${REST_ID}
				&redirect_uri=${Redirect_URI}
				&response_type=code">
				<img style="" src="https://developers.kakao.com/tool/resource/static/img/button/login/full/ko/kakao_login_medium_wide.png" alt="" />
			  </a>
          </div>
        </form>
      </div>
      <!-- 모바일-회원가입 페이지 끝 -->
    </section>
  </main>
  <!-- 모바일-메인 컨텐츠 박스 끝 -->




<%@ include file="../../part/foot.jspf"%>