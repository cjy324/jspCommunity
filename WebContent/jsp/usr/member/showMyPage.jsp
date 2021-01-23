<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MyPage"/>
<%@ include file="../../part/head.jspf" %>
	
	<script>

	//아이디 중복체크
	let checkedDupId = "";

	function checkDupId(el){
		const form = $(el).closest('form').get(0);
		
		const loginId = form.loginId.value;

		$.get(
			'getLoginIdDup',  
			{		
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
	

	
	// 닉네임 중복체크
	let checkedDupNick = "";

	function checkDupNick(el){
		const form = $(el).closest('form').get(0);
		const nickname = form.nickname.value;

		$.get(
			'getNicknameDup',  //요청할 주소
			{		
				nickname:nickname
			},
			function(data){
				if( data.code.substring(0,2) == "S-"){
					alert(data.msg + " (" + data.code + ")");
					checkedDupNick = data.nickname;
				}
				else{
					alert(data.msg + " (" + data.code + ")");
				}
			},			
			'json'
		);	
	};


	// 아이디 폼 공백 체크
	function check0(form) {
		
		if (form0.loginId.value.trim().length == 0) {
			alert("ID를 입력해주세요.");
			form0.loginId.focus();
			
			return;
		}
		
		if (form0.loginId.value != checkedDupId) {
			alert("먼저 ID 중복체크를 해주세요.");
			form0.dupIdCheck.focus();
			
			return false;
		}

		form0.submit();
	};

	
	// 이름 폼 공백 체크
	function check1(form) {
		
		if (form1.name.value.trim().length == 0) {
			alert("이름을 입력해주세요.");
			form1.name.focus();
			
			return;
		}

		form1.submit();
	};

	// 닉네임 폼 공백 체크
	function check2(form) {
		if (form2.nickname.value.trim().length == 0) {
			alert("닉네임을 입력해주세요.");
			form2.nickname.focus();
			
			return false;
		}
		if (form2.nickname.value != checkedDupNick) {
			alert("먼저 중복체크를 해주세요.");
			form2.dupNickCheck.focus();
			
			return false;
		}
		
		form2.submit();
	};
	
	
	// 이메일 폼 공백 체크
	function check3(form) {		
		if (form3.email.value.trim().length == 0) {
			alert("e-mail을 입력해주세요.");
			form3.email.focus();
			
			return;
		}

		form3.submit();
	};
	
	// 연락처 폼 공백 체크
	function check4(form) {
		
		if (form4.cellphoneNo.value.trim().length == 0) {
			alert("연락처를 입력해주세요.");
			form4.cellphoneNo.focus();
			
			return;
		}
		
		form4.submit();
	};
</script>
	
  <!-- 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1">
    <section class="main-box-section con">
      <!-- 메인-회원정보 페이지 시작 -->
      <div class="section-MyPage min-height-50vh flex flex-jc-c flex-ai-c">
        <div class="section-MyPage-body">
          <div class="MyPage_cell__title">
            <span>회원번호</span>
          </div>
          <div class="MyPage_cell__body">
            <span>${loginedMember.id}</span>
          </div>
          <div class="MyPage_cell__title">
            <span>회원ID</span>
            <button class ="infoModifyBtn0" type = "button">수정</button>
          </div>
          <div class="MyPage_cell__body">
            <span class="oldLoginIdInfo">${loginedMember.loginId}</span>
		<form class="newLoginIdInfo" name="form0" onsubmit="check0(this); return false;" action="doModifyInfo" method="POST">
		   <input type="hidden" name="id" value="${loginedMember.id}">
           <input type="text" name="loginId" placeholder="${loginedMember.loginId}">
           <button name="dupIdCheck" onclick="checkDupId(this);" type = "button">중복체크</button>
           <input type="submit" onclick="if(confirm('정말 변경하시겠습니까?') == false) {return false;}" value="변경">
           <button class ="cleModifyBtn0" type="button">취소</button>
        </form>
          </div>
          <div class="MyPage_cell__title">
            <span>회원이름</span>
            <button class ="infoModifyBtn1" type = "button">수정</button>
          </div>
          <div class=MyPage_cell__body>
            <span class="oldNameInfo">${loginedMember.name}</span>
            <form class="newNameInfo" name="form1" onsubmit="check1(this); return false;" action="doModifyInfo" method="POST">
		   <input type="hidden" name="id" value="${loginedMember.id}">
           <input type="text" name="name" placeholder="${loginedMember.name}">
           <input type="submit" onclick="if(confirm('정말 변경하시겠습니까?') == false) {return false;}" value="변경">
           <button class ="cleModifyBtn1" type="button">취소</button>
        </form>
          </div>
          <div class=MyPage_cell__title>
            <span>닉네임</span>
            <button class ="infoModifyBtn2" type = "button">수정</button>
          </div>
          <div class=MyPage_cell__body>
            <span class="oldNicknameInfo">${loginedMember.nickname}</span>
            <form class="newNicknameInfo" name="form2" onsubmit="check2(this); return false;" action="doModifyInfo" method="POST">
			<input type="hidden" name="id" value="${loginedMember.id}">
			<input type="text" name="nickname" placeholder="${loginedMember.nickname}">
			<button name="dupNickCheck" onclick="checkDupNick(this);" type = "button">중복체크</button>
			<input type="submit" onclick="if(confirm('정말 변경하시겠습니까?') == false) {return false;}"  value="변경">
              <button class ="cleModifyBtn2" type="button">취소</button>
        </form>
          </div>
          <div class=MyPage_cell__title>
            <span>e-Mail</span>
            <button class ="infoModifyBtn3" type = "button">수정</button>
          </div>
          <div class=MyPage_cell__body>
            <span class="oldEmailInfo">${loginedMember.email}</span>
            <form class="newEmailInfo" name="form3" onsubmit="check3(this); return false;" action="doModifyInfo" method="POST">
		   <input type="hidden" name="id" value="${loginedMember.id}">
           <input type="email" name="email" placeholder="${loginedMember.email}">
           <input type="submit" onclick="if(confirm('정말 변경하시겠습니까?') == false) {return false;}" value="변경">
              <button class ="cleModifyBtn3" type="button">취소</button>
        </form>
          </div>
          <div class=MyPage_cell__title>
            <span>연락처</span>
            <button class ="infoModifyBtn4" type = "button">수정</button>
          </div>
          <div class=MyPage_cell__body>
            <span class="oldPhoneInfo">${loginedMember.cellphoneNo}</span>
            <form class="newPhoneInfo" name="form4" onsubmit="check4(this); return false;" action="doModifyInfo" method="POST">
		   <input type="hidden" name="id" value="${loginedMember.id}">
           <input type="number" name="cellphoneNo" placeholder="${loginedMember.cellphoneNo}">
           <input type="submit" onclick="if(confirm('정말 변경하시겠습니까?') == false) {return false;}" value="변경">
              <button class ="cleModifyBtn4" type="button">취소</button>
        </form>
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
        </div>
      </div>
      <!-- 메인-회원가입 페이지 끝 -->
    </section>
  </main>
  <!-- 메인 컨텐츠 박스 끝 -->
<%@ include file="../../part/foot.jspf" %>