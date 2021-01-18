<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="로그인" />
<%@ include file="../../part/head.jspf"%>
<h1>${pageTitle}</h1>

<form name="form" onsubmit="return check()" action="doLogin" method="POST">
	<span>로그인 ID</span>
	<br />
	<input type="text" name="loginId" maxlength="50" placeholder="ID 입력">
	<br />
	<span>로그인 PW</span>
	<br />
	<input type="password" name="loginPw" maxlength="50" placeholder="PW 입력">
	<hr />
	<input type="submit" value="로그인">
	<button type="button" onclick="history.back();">뒤로가기</button>

</form>

<script>

function check() {

  if(form.loginId.value == "") {

    alert("ID를 입력해주세요.");

    form.loginId.focus();

    return false;

  }

  else if(form.loginPw.value == "") {

    alert("PASSWORD를 입력해 주세요.");

    form.loginPw.focus();

    return false;

  }

  else return true;

}

</script>

<%@ include file="../../part/foot.jspf"%>