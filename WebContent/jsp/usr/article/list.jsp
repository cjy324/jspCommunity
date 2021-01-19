<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var ="pageTitle" value="게시물 리스트"/>
<%@ include file="../../part/head.jspf" %>

	<h1>${articles.get(0).extra_boardName} ${pageTitle}</h1>
	<button type="button"><a style="text-decoration:none;" href="doWriteForm?boardId=${param.boardId}&memberId=1">새 게시물 생성</a></button>
	<br>
	<br>
	<c:forEach var="article" items="${articles}">	
	<div style="font-weight:bold;">
		번호 :
		${article.id}
		<br />
		작성일 :
		${article.regDate}
		<br />
		갱신일 :
		${article.updateDate}
		<br />
		작성자 :
		${article.extra_memberNickname}
		<br />
		제목 :
		<a style="text-decoration:none;" href="detail?id=${article.id}">${article.title}</a>
		<hr />
	</div>
	</c:forEach>
<%@ include file="../../part/foot.jspf" %>