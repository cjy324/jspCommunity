<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="GetIT | Write" />
<%@ include file="../../part/head.jspf"%>

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

  <!-- 메인 컨텐츠 박스 시작 -->
  <main class="main-box flex-grow-1">
    <section class="main-box-section con">
      <!-- 메인-글쓰기,수정페이지 시작 -->
      <section class="section-2 min-height-50vh">
        <div class="height-100p">
          <div class="article-writeAndModify-cell height-100p">
            <div class="article-writeAndModify-cell__board-name visible-md-up">
              <div>
                <span>신규 게시물 작성</span>
              </div>
            </div>
            <div class="mobile-article-writeAndModify-cell__board-name visible-sm-down">
              <div>
                <span>신규 게시물 작성</span>
              </div>
            </div>
            <div class="article-writeAndModify-cell__contents-box">
              <form name="form" onsubmit="check(this); return false;" action="doWrite" method="POST">
                <input type="hidden" name="boardId" value="${param.boardId}">
                <input type="hidden" name="body">
                <span>TITLE</span>
                <br />
                <input type="text" name="title" maxlength="50" placeholder="제목 입력">
                <hr />
                <span>BODY</span>
                <script type="text/x-template"></script>
                <div class="article-writeAndModify-cell__body toast-ui-editor height-70p">
                </div>
                
                <div class="ad">
      		<script async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<!-- 수평 반응형 8 -->
<ins class="adsbygoogle"
     style="display:block"
     data-ad-client="ca-pub-7996879977557531"
     data-ad-slot="3815135040"
     data-ad-format="auto"
     data-full-width-responsive="true"></ins>
<script>
     (adsbygoogle = window.adsbygoogle || []).push({});
</script>
      	  </div>
                
                <div class="article-writeAndModify-cell__option flex flex-jc-fe visible-md-up">
                  <button class="btn btn-go" type="submit"><i class="fas fa-pen"></i> 등록</button>
                  <button class="btn btn-back" type="button" onclick="history.back();">
                    <i class="fas fa-ban"></i> 취소
                  </button>
                </div>
                <div class="mobile-article-writeAndModify-cell__option flex flex-jc-fe visible-sm-down">
                  <button class="btn btn-go" type="submit"><i class="fas fa-pen"></i> 등록</button>
                  <button class="btn btn-back" type="button" onclick="history.back();">
                    <i class="fas fa-ban"></i> 취소
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </section>
      <!-- 메인-글쓰기,수정페이지 시작 -->
    </section>
  </main>
  <!-- 메인 컨텐츠 박스 끝 -->
  
  




<%@ include file="../../part/foot.jspf"%>