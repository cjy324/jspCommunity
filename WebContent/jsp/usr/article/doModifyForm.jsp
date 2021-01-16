<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Article"%>
<%
Article article = (Article) request.getAttribute("article");
String pageTitle = "게시물 수정";
%>
<%@ include file="../../part/head.jspf" %>
	<h1><%=article.getId() + "번"%> <%=pageTitle%></h1>
	<form action="doModify" method="POST">
  <input type="hidden" name="id" value="<%= article.getId() %>">
  <input type="hidden" name="memberId" value="<%= article.getMemberId() %>">
  <span>TITLE</span>
  <br>
  <input type="text" name="title" maxlength="50" placeholder="수정할 제목 입력" value="<%=article.getTitle()%>">
  <hr>
  <span>BODY</span>
  <br>
  <textarea type="text" name="body"  maxlength="1000" placeholder="수정할 내용 입력"><%=article.getBody()%></textarea>
  <hr>
  <input type="submit" onclick="if(confirm('해당 내용으로 수정하시겠습니까?') == false) {return false;}" value="수정완료"><button type="button" onclick="history.back();">뒤로가기</button>
</form>
	
<%@ include file="../../part/foot.jspf" %>