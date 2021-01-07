<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%
List<Map<String, Object>> articleMapList = (List<Map<String, Object>>) request.getAttribute("articleMapList");
int boardId = (int) request.getAttribute("boardId");
String boardName = (String) request.getAttribute("boardName");
String boardCode = (String) request.getAttribute("boardCode");
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>게시물 리스트</title>
</head>
<body>
	<h1><%= boardName %> 게시판 게시물 리스트</h1>
	<%
	for (Map<String, Object> articleMap : articleMapList) {
	%>
	<div style="font-weight:bold;">
		번호 :
		<%=articleMap.get("id")%>
		<br />
		제목 :
		<a style="text-decoration:none; color:inherit;" href="http://localhost:8083/jspCommunity/jsp/usr/article/detail?id=<%=articleMap.get("id")%>"><%=articleMap.get("title")%></a>
		<br />
		내용 :
		<%=articleMap.get("body")%>
		<br>
		<a href="http://localhost:8083/jspCommunity/jsp/usr/article/doModify?id=<%=articleMap.get("id")%>">수정</a>
		<a href="http://localhost:8083/jspCommunity/jsp/usr/article/doDelete?id=<%=articleMap.get("id")%>">삭제</a>
		<hr />
	</div>
	<%
	}
	%>
</body>
</html>