<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var ="pageTitle" value="게시물 수정"/>
<%@ include file="../../part/head.jspf" %>

<h1>${article.id}번 ${pageTitle}</h1>

<script>
function check(){
	if(form.title.value.trim().length == 0){
		alert("제목을 입력하세요.")
		form.title.focus();

		return false;
	}
	if(form.body.value.trim().length == 0){
		alert("내용을 입력하세요.")
		form.body.focus();

		return false;
	}

	else return true;

}
</script>


<form action="doModify" method="POST" onsubmit="return check()">
  <input type="hidden" name="id" value="${article.id}">
  <span>TITLE</span>
  <br>
  <input type="text" name="title" maxlength="50" placeholder="수정할 제목 입력" value="${article.title}">
  <hr>
  <span>BODY</span>
  <br>
  <textarea type="text" name="body"  maxlength="1000" placeholder="수정할 내용 입력">${article.body}</textarea>
  <hr>
  <input type="submit" onclick="if(confirm('해당 내용으로 수정하시겠습니까?') == false) {return false;}" value="수정완료"><button type="button" onclick="history.back();">뒤로가기</button>
</form>
	
<%@ include file="../../part/foot.jspf" %>