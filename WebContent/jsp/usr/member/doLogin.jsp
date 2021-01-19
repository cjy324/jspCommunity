<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var ="pageTitle" value="로그인"/>
<%@ include file="../../part/head.jspf" %>
	
	<div>
	<span>
	${loginId}님, 로그인 완료!!
	</span>
	</div>
	
<%@ include file="../../part/foot.jspf" %>