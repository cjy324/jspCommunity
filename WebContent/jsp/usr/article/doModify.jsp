<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Article"%>
<%
Article article = (Article) request.getAttribute("article");
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>게시물 수정</title>
</head>
<body>
	<h1><%=article.id + "번"%> 게시물 수정 완료</h1>
	<div style="font-size:1.2rem;">
		번호 :
		<%=article.id%>
		<br />
		수정된 제목 :
		<%=article.title%>
		<br />
		수정된 내용 :
		<%=article.body%>
		<br />
		게시판 :
		<%=article.extra_boardName%>
	</div>
</body>
</html>