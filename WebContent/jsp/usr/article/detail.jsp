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
<title>게시물 상세보기</title>
</head>
<body>
	
	<h1><%=article.getId() + "번"%> 게시물 상세보기</h1>
	<div style="font-size:1.2rem;">
		번호 :
		<%=article.getId()%>
		<br />
		작성일 :
		<%=article.getRegDate()%>
		<br />
		수정일 :
		<%=article.getUpdateDate()%>
		<br />
		작성자 :
		<%=article.getExtra_memberName()%>
		<br />
		제목 :
		<%=article.getTitle()%>
		<br />
		내용 :
		<%=article.getBody()%>
		<br />
		게시판 :
		<%=article.getExtra_boardName()%>
		<br>
		<br>
		<button type="button"><a style="text-decoration:none;" href="doModifyForm?id=<%=article.getId()%>&boardId=<%=article.getBoardId()%>&memberId=<%=article.getMemberId()%>&title=<%=article.getTitle()%>&body=<%=article.getBody()%>">수정</a></button>
		<button onclick="if(confirm('정말 삭제하시겠습니까?') == false) {return false;}" type="button"><a style="text-decoration:none;" href="doDelete?id=<%=article.getId()%>">삭제</a></button>
		<hr>   
		<button type="button"><a style="text-decoration:none;" href="list?boardId=<%=article.getBoardId()%>">리스트로 돌아가기</a></button>
	</div>
	
</body>
</html>