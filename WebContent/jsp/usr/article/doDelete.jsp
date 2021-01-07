<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%
int id = (int) request.getAttribute("id");
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>게시물 삭제</title>
</head>
<body>
	<h1><%=id + "번"%> 게시물 삭제 완료</h1>
</body>
</html>