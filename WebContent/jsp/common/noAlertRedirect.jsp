<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
var replaceUrl = '<%=request.getAttribute("replaceUrl") %>'.trim();

if(replaceUrl){
	// historyBack으로 뒤로 돌아갈 경로를 설정하는 것
	// location.replace()를 하지 않으면 그냥 뒤로가기가 되어 흔적이 남게 됨
	// location.replace()는 흔적이 남지 않고 설정된 replaceUrl로 historyBack 시행
	location.replace(replaceUrl);  
}
</script>