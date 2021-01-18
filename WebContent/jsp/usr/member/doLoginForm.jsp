<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="신규 회원 가입" />
<%@ include file="../../part/head.jspf"%>
<h1>${pageTitle}</h1>

<form action="doJoin" method="POST">
	<span>로그인 ID</span>
	<br />
	<input type="text" name="loginId" maxlength="50" placeholder="ID 입력">
	<br />
	<span>로그인 PW</span>
	<br />
	<input type="text" name="loginPw" maxlength="50" placeholder="PW 입력">
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
	<input type="text" name="email" maxlength="50" placeholder="이메일 주소 입력">
	<br />
	<span>연락처</span>
	<br />
	<input type="text" name="cellPhoneNo" maxlength="50" placeholder="연락처 입력">
	<br />
	<hr />
	<input type="submit" value="회원가입">
	<button type="button" onclick="history.back();">뒤로가기</button>

</form>

<%@ include file="../../part/foot.jspf"%>