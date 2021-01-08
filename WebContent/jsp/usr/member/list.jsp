<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Member" %>
<%
List<Member> members = (List<Member>) request.getAttribute("members");
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>회원 리스트</title>
</head>
<body>
	<h1>회원 리스트</h1>
	<%
	for (Member member : members) {
	%>
	<div>
		번호 :
		<%=member.id%>
		<br />
		이름 :
		<%=member.name%>
		<br />
		닉네임 :
		<%=member.nickname%>
		<br />
		e-mail :
		<%=member.email%>
		<br />
		Phone :
		<%=member.cellphoneNo%>
		<br />
		회원등급 :
		<%=member.authLevel%>
		<hr />
	</div>
	<%
	}
	%>
</body>
</html>