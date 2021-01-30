<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MyPage"/>
<%@ include file="../../part/head.jspf" %>
	
<!-- sha256 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>

	<script>

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

		let DoModifyForm_submited = false;
		
		if ( DoModifyForm_submited ) {
			alert('처리중 입니다.');
			return;
		}

		// 비밀번호 변경시에만 비밀번호 폼 공백 체크
		if ( form.loginPw.value.length > 0 ) {
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

		}

		if (form.name.value.trim().length == 0) {
			alert("이름을 입력해주세요.");
			form.name.focus();
			
			return;
		}

		if (form.nickname.value.trim().length == 0) {
			alert("닉네임을 입력해주세요.");
			form.nickname.focus();
			
			return false;
		}
		if (form.nickname.value != checkedDupNick) {
			alert("닉네임 중복체크를 해주세요.");
			form.dupNickCheck.focus();
			
			return false;
		}

		if (form.email.value.trim().length == 0) {
			alert("e-mail을 입력해주세요.");
			form.email.focus();
			
			return;
		}

		if (form.cellphoneNo.value.trim().length == 0) {
			alert("연락처를 입력해주세요.");
			form.cellphoneNo.focus();
			
			return;
		}
		
		// 비밀번호 변경시에만 비밀번호 암호화
		if ( form.loginPw.value.length > 0 ) {
			form.loginPwReal.value = sha256(form.loginPw.value);
			form.loginPw.value = "";
			form.loginPwConfirm.value = "";

		}


		form.submit();
		DoModifyForm_submited = true;
	};
</script>
	
  <!-- 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1">
    <section class="main-box-section con">
      <!-- 메인-회원정보 페이지 시작 -->
      <div class="section-MyPage min-height-50vh flex flex-jc-c flex-ai-c">
      	<form name="form" onsubmit="check(this); return false;" action="doModifyInfo" method="POST">
      		<input type="hidden" name="id" value="${loginedMember.id}">
      		<input type="hidden" name="loginPwReal">
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
          <div class=MyPage_cell__title>
            <span>Password</span>
          </div>
          <div class=MyPage_cell__body>
            <input type="password" name="loginPw" maxlength="50" placeholder="PW 입력">
          </div>
          <div class=MyPage_cell__title>
            <span>Password Check</span>
          </div>
          <div class=MyPage_cell__body>
            <input type="password" name="loginPwConfirm" maxlength="50" placeholder="PW 확인">
          </div>
          <div class="MyPage_cell__title">
            <span>회원이름</span>
          </div>
          <div class=MyPage_cell__body>
           <input type="text" name="name" value="${loginedMember.name}">
          </div>
          <div class=MyPage_cell__title>
            <span>닉네임</span>
          </div>
          <div class=MyPage_cell__body>
         	<input type="text" name="nickname" placeholder="${loginedMember.nickname}">
			<button class="btn btn-check" name="dupNickCheck" onclick="checkDupNick(this);" type = "button">중복체크</button>
		  </div>
          <div class=MyPage_cell__title>
            <span>e-Mail</span>        
          </div>
          <div class=MyPage_cell__body>          
           <input type="email" name="email" value="${loginedMember.email}">        
          </div>
          <div class=MyPage_cell__title>
            <span>연락처</span>
          </div>
          <div class=MyPage_cell__body>
           <input type="number" name="cellphoneNo" value="${loginedMember.cellphoneNo}">
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
          <button class ="submitModifyBtn btn btn-go" type="submit" onclick="if(confirm('정말 변경하시겠습니까?') == false) {return false;}">변경</button>
          <button class ="cleModifyBtn btn btn-back" type="button" onclick="history.back();">취소</button>
        </div>
      	</form>
      </div>
      <!-- 메인-회원정보 페이지 끝 -->
    </section>
  </main>
  <!-- 메인 컨텐츠 박스 끝 -->
<%@ include file="../../part/foot.jspf" %>