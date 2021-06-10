<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>

	<h1>경품 추첨 결과</h1>

	<c:choose>
	
		<c:when test="${ param.num % 5 == 0 }">
			<h4>맥북 프로 케이스</h4>
		</c:when>
		
		<c:when test="${ param.num % 5 == 3 }">
			<h4>맥북 프로</h4>
		</c:when>
		
		<c:when test="${ param.num % 5 == 2 }">
			<h4>아이패드 프로</h4>
		</c:when>
		
		<c:otherwise>
			<h4>꽝! 다음 기회에...</h4>
		</c:otherwise>
		
	</c:choose>
	
</body>
</html>