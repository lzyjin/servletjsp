<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- <%@ page errorPage="error.jsp" %> --%> <!-- 에러가 발생하면 자동으로 이 페이지로 이동함 -->

<%
	String test = null;
	int a = 10;
	int b = 0;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- <%-- <%= test.charAt(0) %> --%>  
	<!-- java.lang.NullPointerException 발생 -->
	
	<%=a/b %>
	<!-- java.lang.ArithmeticException: / by zero -->
	
</body>
</html>