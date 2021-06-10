<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파라미터 데이터 받기</title>
</head>

<body>
	
	<h1>파라미터로 전송된 데이터 받기</h1>
	<p>
		파라미터로 전송된 데이터는 param객체를 이용해서 출력한다
	</p>
	
	<!-- 이전까지썼던 방법 -->
	<%=request.getParameter("name") %>
	<%=request.getParameterValues("hobby")[0] %>
	
	<!-- EL방식으로 하면 -->
	<h4>${ param.name } ${ param.age } ${ paramValues.hobby[0] }  ${ paramValues.hobby[1] }  ${ paramValues.hobby[2] }</h4>
	
	
	
	
	
	<h3>내장객체(cookie, header)에 있는 데이터 가져오기</h3>
	
	<a href="${ pageContext.request.contextPath }/extraData">데이터 가져오기</a>
	
	
</body>
</html>