<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
var alertMsg = '<%=request.getAttribute("alertMsg") %>'.trim();

if(alertMsg != null && alertMsg){
	alert(alertMsg);
}

var historyBack = '<%=request.getAttribute("historyBack") %>' == 'true';

if(historyBack){
	// history.go(-3);  => 뒤로가기 3회라는 의미
	// history.go(-1); == history.back();
	history.back();
}

var replaceUrl = '<%=request.getAttribute("replaceUrl") %>'.trim();

if(replaceUrl != '' && replaceUrl != 'null'){
	// historyBack으로 뒤로 돌아갈 경로를 설정하는 것
	// location.replace()를 하지 않으면 그냥 뒤로가기가 되어 흔적이 남게 됨
	// location.replace()는 흔적이 남지 않고 설정된 replaceUrl로 historyBack 시행
	location.replace(replaceUrl);  
}

</script>
