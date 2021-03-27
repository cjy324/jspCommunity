<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
var alertMsg = '<%=request.getAttribute("alertMsg") %>'.trim();

if(alertMsg != null && alertMsg){
	alert(alertMsg);
}

var historyBack = '<%=request.getAttribute("historyBack") %>' == 'true';

if(historyBack){
	history.back();
}

var replaceUrl = '<%=request.getAttribute("replaceUrl") %>'.trim();

if(replaceUrl != '' && replaceUrl != 'null'){
	location.replace(replaceUrl);  
}

</script>
