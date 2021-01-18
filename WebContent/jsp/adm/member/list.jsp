<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="회원 리스트"/>
<%@ include file="../../part/head.jspf" %>
	<h1>${pageTitle}</h1>
	<c:forEach var="member" items="${members}">
	<div>
		번호 :
		${member.id}
		<br />
		이름 :
		${member.name}
		<br />
		닉네임 :
		${member.nickname}
		<br />
		e-mail :
		${member.email}
		<br />
		Phone :
		${member.cellphoneNo}
		<br />
		회원등급 :
		${member.authLevel}
		<hr />
	</div>
	</c:forEach>
<%@ include file="../../part/foot.jspf" %>