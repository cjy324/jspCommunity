<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="신규 게시물 등록" />
<%@ include file="../../part/head.jspf"%>
<h1>${pageTitle}</h1>

<script>

let DoWriteForm_submited = false;

function check(form){

	if(DoWriteForm_submited){

		alert('처리중입니다.');
			return;
	}

	if(form.title.value.trim().length == 0){
		alert("제목을 입력하세요.")
		form.title.focus();

		return false;
	}
	
	const editor = $(form).find('.toast-ui-editor').data('data-toast-editor');
	const body = editor.getMarkdown().trim();
	
	if(body.length == 0){
		alert("내용을 입력하세요.")
		form.body.focus();

		return false;
	}
	
	form.body.value = body;

	form.submit();
	DoWriteForm_submited = true;
}

</script>

<form name="form" onsubmit="check(this); return false;" action="doWrite" method="POST">
	<input type="hidden" name="boardId" value="${param.boardId}"> 
	<input type="hidden" name="body">
	<span>TITLE</span>
	<br />
	<input type="text" name="title" maxlength="50" placeholder="제목 입력">
	<hr />
	<span>BODY</span>
	<br />
	
	<script type="text/x-template"></script>
 	<div class="toast-ui-editor"></div>
	<hr />
	<input type="submit" value="등록">
	<button type="button" onclick="history.back();">뒤로가기</button>

</form>




<%@ include file="../../part/foot.jspf"%>