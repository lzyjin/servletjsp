<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>500 에러 페이지</title>
</head>

<body>

	<h2>500 에러 페이지</h2>
	<h3>서버에 문제가 있어 정상적으로 처리되지 못했습니다</h3>
	<h4><%=exception.getMessage()%></h4>
	
	
</body>
</html>