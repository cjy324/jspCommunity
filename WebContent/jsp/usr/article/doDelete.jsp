<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%
int id = (int) request.getAttribute("id");
String pageTitle = "게시물 삭제";
%>
<%@ include file="../../part/head.jspf" %>
	<h1><%=id + "번"%> 게시물 삭제 완료</h1>
<%@ include file="../../part/foot.jspf" %>