<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.model.vo.Member" %>
    
<%

	String msg = (String)request.getAttribute("msg");
	String loc = (String) request.getAttribute("loc");
	String script = (String) request.getAttribute("script");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>

	<script>
	
		alert("<%=msg%>");	
		
		// 비밀번호수정페이지에서 성공하면 창이 자동으로 닫기도록 
		<%= script != null ? script : "" %>
		
		location.assign("<%=request.getContextPath()%><%=loc%>");
	
		
	</script>


</body>
</html>