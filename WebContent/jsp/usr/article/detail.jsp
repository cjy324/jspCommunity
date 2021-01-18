<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Article"%>
<%
//Article article = (Article) request.getAttribute("article");
String pageTitle = "게시물 상세보기";
%>

<%@ include file="../../part/head.jspf" %>
	
	<h1>${article.id}번 <%=pageTitle%></h1>
	<div style="font-size:1.2rem;">
		번호 :
		${article.id}
		<br />
		작성일 :
		${article.regDate}
		<br />
		수정일 :
		${article.updateDate}
		<br />
		작성자 :
		${article.extra_memberName}
		<br />
		제목 :
		${article.title}
		<br />
		내용 :
		${article.body}
		<br />
		게시판 :
		${article.extra_boardName}
		<br>
		<br>
		<button type="button"><a style="text-decoration:none;" href="doModifyForm?id=${article.id}&boardId=${article.boardId}&memberId=${article.memberId}&title=${article.title}&body=${article.body}">수정</a></button>
		<button onclick="if(confirm('정말 삭제하시겠습니까?') == false) {return false;}" type="button"><a style="text-decoration:none;" href="doDelete?id=${article.id}">삭제</a></button>
		<hr>   
		<button type="button"><a style="text-decoration:none;" href="list?boardId=${article.boardId}">리스트로 돌아가기</a></button>
	</div>
	
<%@ include file="../../part/foot.jspf" %>