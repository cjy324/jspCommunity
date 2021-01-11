<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Article"%>

<%
int boardId= (int) request.getAttribute("boardId");
int memberId= (int) request.getAttribute("memberId");
%>

<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>게시물 생성</title>
</head>
<body>
<h1>신규 게시물 등록</h1>
	
<form action="/jspCommunity/usr/article/doWrite" target="_blank" method="POST">
  <input type="hidden" name="boardId" value="<%= boardId %>">
  <input type="hidden" name="memberId" value="<%= memberId %>">
  <span>TITLE</span>
  <br/>
  <input type="text" name="title" maxlength="10" placeholder="제목 입력">
  <hr/>
  <span>BODY</span>
  <br/>
  <textarea type="text" name="body"  maxlength="1000" placeholder="내용 입력"></textarea>
  <hr/>
  <input type="submit" value="등록">
</form>
	
</body>
</html>