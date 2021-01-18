<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var ="pageTitle" value="회원 가입"/>
<%@ include file="../../part/head.jspf" %>
	<h1>${member.id}번 회원 가입 완료</h1>
	<div style="font-size:1.2rem;">
		회원번호 :
		${member.id}
		<br />
		아이디 :
		${member.loginId}
		<br />
		닉네임 :
		${member.nickname}
		<hr />
		<a style="text-decoration:none;" href="list">리스트로 돌아가기</a>
	</div>
<%@ include file="../../part/foot.jspf" %>