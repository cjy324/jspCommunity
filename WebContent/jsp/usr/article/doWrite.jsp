<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var ="pageTitle" value="게시물 생성"/>
<%@ include file="../../part/head.jspf" %>
	<h1>${article.id}번 게시물 생성 완료</h1>
	<div style="font-size:1.2rem;">
		번호 :
		${article.id}
		<br />
		제목 :
		${article.title}
		<br />
		내용 :
		${article.body}
		<br />
		게시판 :
		${article.extra_boardName}
		<hr />
		<a style="text-decoration:none;" href="list?boardId=${article.boardId}">리스트로 돌아가기</a>
	</div>
<%@ include file="../../part/foot.jspf" %>