<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Article"%>
<%
List<Article> articles = (List<Article>) request.getAttribute("articles");
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>게시물 리스트</title>
</head>
<body>
	<h1><%=articles.get(0).getExtra_boardName()%> 게시물 리스트</h1>
	<button type="button"><a style="text-decoration:none;" href="doWriteForm?boardId=<%=request.getParameter("boardId")%>&memberId=1">새 게시물 생성</a></button>
	<br>
	<br>	
	<%
	for (Article article : articles) {
	%>
	<div style="font-weight:bold;">
		번호 :
		<%=article.getId()%>
		<br />
		작성일 :
		<%=article.getRegDate()%>
		<br />
		갱신일 :
		<%=article.getUpdateDate()%>
		<br />
		작성자 :
		<%=article.getExtra_memberName()%>
		<br />
		제목 :
		<a style="text-decoration:none;" href="detail?id=<%=article.getId()%>"><%=article.getTitle()%></a>
		<hr />
	</div>
	<%
	}
	%>
</body>
</html>