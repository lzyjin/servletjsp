<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %> <!-- 이 태그가 있으면 exception 객체 사용 가능 --> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>

	<h1 style="color:red">에러에러 에러 발생 !! :O)</h1>
	
	<!-- 어떤 에러가 발생했는지 궁금하면 exception 객체 사용 -->
	
	<h3><%=exception.getMessage()%></h3>  <%-- <%@ page isErrorPage="true" %> 이 코드 쓰니까 에러가 사라진다 --%>
	
	<h3><a href="<%=request.getContextPath()%>">메인페이지로</a></h3>
	
	
</body>
</html>