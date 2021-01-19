<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var ="pageTitle" value="메인화면"/>
<%@ include file="../../part/head.jspf" %>
	
	<h1>${pageTitle}</h1>
	<div style="font-size:1.2rem;">
		
		<br>
		<hr>   
		<button type="button"><a style="text-decoration:none;" href="list?boardId=${article.boardId}">리스트로 돌아가기</a></button>
	</div>
	
<%@ include file="../../part/foot.jspf" %>