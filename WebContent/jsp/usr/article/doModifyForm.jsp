<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
int id= (int) Integer.parseInt(request.getParameter("id"));
int boardId= (int) Integer.parseInt(request.getParameter("boardId"));
int memberId= (int) Integer.parseInt(request.getParameter("memberId"));
String title= (String) request.getParameter("title");
String body= (String) request.getParameter("body");
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
</head>
<body>
	<h1><%=id + "번"%> 게시물 수정</h1>
	<form action="doModify" method="POST">
  <input type="hidden" name="id" value="<%= id %>">
  <input type="hidden" name="memberId" value="<%= memberId %>">
  <span>TITLE</span>
  <br>
  <input type="text" name="title" maxlength="50" placeholder="수정할 제목 입력" value="<%=title%>">
  <hr>
  <span>BODY</span>
  <br>
  <textarea type="text" name="body"  maxlength="1000" placeholder="수정할 내용 입력"><%=body%></textarea>
  <hr>
  <input type="submit" value="수정 완료"><button type="button" onclick="history.back();">뒤로가기</button>
</form>
	
	
</body>
</html>