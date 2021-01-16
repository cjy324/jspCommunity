<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Article"%>

<%
int boardId= (int) Integer.parseInt(request.getParameter("boardId"));
int memberId= (int) Integer.parseInt(request.getParameter("memberId"));
String pageTitle = "신규 게시물 등록";
%>

<%@ include file="../../part/head.jspf" %>
<h1><%=pageTitle%></h1>
	
<form action="doWrite" method="POST">
  <input type="hidden" name="boardId" value="<%= boardId %>">
  <input type="hidden" name="memberId" value="<%= memberId %>">
  <span>TITLE</span>
  <br/>
  <input type="text" name="title" maxlength="50" placeholder="제목 입력">
  <hr/>
  <span>BODY</span>
  <br/>
  <textarea type="text" name="body"  maxlength="1000" placeholder="내용 입력"></textarea>
  <hr/>
  <input type="submit" value="등록"><button type="button" onclick="history.back();">뒤로가기</button>
  
</form>
	
<%@ include file="../../part/foot.jspf" %>