<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="신규 회원 가입" />
<%@ include file="../../part/head.jspf"%>

<script>
	
	//변수명과 함수명이 같으면 에러발생!!
	let checkedDupId = "";
	
	
	// 중복체크
	function checkDupId(el){
		//가장 근접한 'form'을 가져오기
		//$(el).parent.parent.parent 방식으로 부모를 찾아도 됨
		const form = $(el).closest('form').get(0);
		
		const loginId = form.loginId.value;
		
		
		//ajax 통신으로 보내고 데이터 받기
		$.get(
			'getLoginIdDup',  //요청할 주소
			{		
				//	loginId:loginId도 맞다
				// 최신 JS에서는 이 방식이 가능함
				loginId:loginId
			},
			function(data){
				if( data.code.substring(0,2) == "S-"){
					alert(data.msg + " (" + data.code + ")");
					checkedDupId = data.loginId;
				
				}
				else{
					alert(data.msg + " (" + data.code + ")");
				//	alert();
				}
			},			
			'json'
		
		);
		
	};


	
	// 폼 공백 체크
	function check(form) {
	
		if (form.loginId.value.trim().length == 0) {
			alert("ID를 입력해주세요.");
			form.loginId.focus();
			
			return;
		}
		
		if (form.loginId.value != checkedDupId) {
			alert("먼저 ID 중복체크를 해주세요.");
			form.dupIdCheck.focus();
			
			return false;
		}
		
		if (form.loginPw.value.trim().length == 0) {
			alert("PW를 입력해주세요.");
			form.loginPw.focus();
			
			return;
		}
		if (form.loginPw.value != form.loginPwConfirm.value) {
			alert("PW가 일치하지 않습니다. PW를 확인해 주세요.");
			form.loginPwConfirm.focus();
			
			return;
		}
		
		if (form.name.value.trim().length == 0) {
			alert("이름을 입력해주세요.");
			form.name.focus();
			
			return;
		}
		if (form.nickname.value.trim().length == 0) {
			alert("닉네임을 입력해주세요.");
			form.nickname.focus();
			
			return;
		}
		if (form.email.value.trim().length == 0) {
			alert("e-mail을 입력해주세요.");
			form.email.focus();
			
			return;
		}
		if (form.cellPhoneNo.value.trim().length == 0) {
			alert("연락처를 입력해주세요.");
			form.cellPhoneNo.focus();
			
			return;
		}
		
		form.submit();
	};
</script>


  <!-- 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1">
    <section class="main-box-section con">
      <!-- 메인-회원가입 페이지 시작 -->
      <div class="section-join min-height-50vh flex flex-jc-c flex-ai-c">

        <form name="form" onsubmit="check(this); return false;" action="doJoin" method="POST">
          <div class=join_cell__title>
            <span>신규 ID</span>
          </div>
          <div class=join_cell__body>
            <input type="text" name="loginId" maxlength="50" placeholder="ID 입력">
            <button name="dupIdCheck" onclick="checkDupId(this);" type="button">중복체크</button>
          </div>
          <div class=join_cell__title>
            <span>Password</span>
          </div>
          <div class=join_cell__body>
            <input type="password" name="loginPw" maxlength="50" placeholder="PW 입력">
          </div>
          <div class=join_cell__title>
            <span>Password Check</span>
          </div>
          <div class=join_cell__body>
            <input type="password" name="loginPwConfirm" maxlength="50" placeholder="PW 입력">
          </div>
          <div class=join_cell__title>
            <span>이름</span>
          </div>
          <div class=join_cell__body>
            <input type="text" name="name" maxlength="50" placeholder="이름 입력">
          </div>
          <div class=join_cell__title>
            <span>닉네임</span>
          </div>
          <div class=join_cell__body>
            <input type="text" name="nickname" maxlength="50" placeholder="닉네임 입력">
          </div>
          <div class=join_cell__title>
            <span>E-Mail</span>
          </div>
          <div class=join_cell__body>
            <input type="email" name="email" maxlength="100" placeholder="이메일 주소 입력">
          </div>
          <div class=join_cell__title>
            <span>연락처</span>
          </div>
          <div class=join_cell__body>
            <input type="number" name="cellPhoneNo" maxlength="50" placeholder="연락처 입력">
          </div>
          <div class=joinInput-cell>
            <input type="submit" value="회원가입">
            <button type="button" onclick="history.back();">뒤로가기</button>
          </div>
        </form>
      </div>
      <!-- 메인-회원가입 페이지 끝 -->
    </section>
  </main>
  <!-- 메인 컨텐츠 박스 끝 -->




<%@ include file="../../part/foot.jspf"%>