<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%
List<Map<String, Object>> articleMapList = (List<Map<String, Object>>) request.getAttribute("articleMapList");
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>게시물 수정</title>
</head>
<body>
	<%
	for (Map<String, Object> articleMap : articleMapList) {
	%>
	<h1><%=articleMap.get("id") + "번"%> 게시물 수정</h1>
	<div>
		번호 :
		<%=articleMap.get("id")%>
		<br />
		수정된 제목 :
		<%=articleMap.get("title")%>
		<br />
		수정된 내용 :
		<%=articleMap.get("body")%>
		<br />
		게시판 :
		<%=articleMap.get("boardId")%>
	</div>
	<%
	}
	%>
	<h1>게시물 수정 완료</h1>
</body>
</html>