<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="신규 회원 가입" />
<%@ include file="../../part/head.jspf"%>
<h1>${pageTitle}</h1>

<form name="form" onsubmit="return check()" action="doJoin"
	method="POST">
	<span>로그인 ID</span>
	<br />
	<input type="text" name="loginId" maxlength="50" placeholder="ID 입력">
	<br />
	<span>로그인 PW</span>
	<br />
	<input type="password" name="loginPw" maxlength="50"
		placeholder="PW 입력">
	<br />
	<span>이름</span>
	<br />
	<input type="text" name="name" maxlength="50" placeholder="이름 입력">
	<br />
	<span>닉네임</span>
	<br />
	<input type="text" name="nickname" maxlength="50" placeholder="닉네임 입력">
	<br />
	<span>E-Mail</span>
	<br />
	<input type="email" name="email" maxlength="100"
		placeholder="이메일 주소 입력">
	<br />
	<span>연락처</span>
	<br />
	<input type="number" name="cellPhoneNo" maxlength="50"
		placeholder="연락처 입력">
	<br />
	<hr />
	<input type="submit" value="회원가입">
	<button type="button" onclick="history.back();">뒤로가기</button>

</form>

<script>
	function check() {

		if (form.loginId.value == "") {
			alert("ID를 입력해주세요.");
			form.loginId.focus();
			
			return false;
		}
		if (form.loginPw.value == "") {
			alert("PW를 입력해주세요.");
			form.loginPw.focus();
			
			return false;
		}
		if (form.name.value == "") {
			alert("이름을 입력해주세요.");
			form.name.focus();
			
			return false;
		}
		if (form.nickname.value == "") {
			alert("닉네임을 입력해주세요.");
			form.nickname.focus();
			
			return false;
		}
		if (form.email.value == "") {
			alert("e-mail을 입력해주세요.");
			form.email.focus();
			
			return false;
		}
		if (form.cellPhoneNo.value == "") {
			alert("연락처를 입력해주세요.");
			form.cellPhoneNo.focus();
			
			return false;
		}
		
		else return true;
	}
</script>


<%@ include file="../../part/foot.jspf"%>