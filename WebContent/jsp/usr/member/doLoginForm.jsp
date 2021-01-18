<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="로그인" />
<%@ include file="../../part/head.jspf"%>
<h1>${pageTitle}</h1>

<form action="doLogin" method="POST">
	<span>로그인 ID</span>
	<br />
	<input type="text" name="loginId" maxlength="50" placeholder="ID 입력">
	<br />
	<span>로그인 PW</span>
	<hr />
	<input type="submit" value="로그인">
	<button type="button" onclick="history.back();">뒤로가기</button>

</form>

<%@ include file="../../part/foot.jspf"%>