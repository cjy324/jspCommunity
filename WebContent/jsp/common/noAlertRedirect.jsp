<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
var replaceUrl = '<%=request.getAttribute("replaceUrl") %>'.trim();

if(replaceUrl){
	location.replace(replaceUrl);  
}
</script>
