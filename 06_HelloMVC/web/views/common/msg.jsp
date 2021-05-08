<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");

	/* 선생님풀이 */
	String script = (String)request.getAttribute("script");
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시스템 메세지</title>
</head>

<body>

	<script>
	
		alert("<%=msg%>"); // 문자열 변수를 출력하려면 ""반드시 필요
		
		<%-- <% if(msg.equals("비밀번호 변경 성공")) {  %>
		
			window.close();
		
		<% } %> --%>
		
		
		/* 선생님풀이 */ <%= script != null ? script : ""%> 
		
		
		
		<% System.out.println("here?");%>
		location.replace("<%=request.getContextPath()%><%=request.getAttribute("loc")%>");
		<% System.out.println("here?");%>
	</script>	
	
	
	
	
</body>
</html>