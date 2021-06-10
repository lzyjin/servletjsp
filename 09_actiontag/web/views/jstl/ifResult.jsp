<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>if문 처리</title>
</head>

<body>

	<c:if test="${ param.su + param.su2 > 100 }">
		<h3>100이상의 수다!</h3>
	</c:if>
	
	<c:if test="${ param.su > param.su2 }">
		<h3>${ param.su }가 ${ param.su2 } 보다 크다</h3>
	</c:if>
	<c:if test="${ param.su < param.su2 }">
		<h3>${ param.su }가 ${ param.su2 } 보다 작다</h3>
	</c:if>
	
	
	
	
	<c:if test="${ param.color eq 'red' }">
		<h3 style="color:<c:out value='${ param.color }'/>;">당신이 선택한 색은 레드</h3>
	</c:if>
	<c:if test="${ param.color eq 'blue' }">
		<h3 style="color:<c:out value='${ param.color }'/>;">당신이 선택한 색은 블루</h3>
	</c:if>
	<c:if test="${ param.color eq 'yellow' }">
		<h3 style="color:<c:out value='${ param.color }'/>;">당신이 선택한 색은 옐로우</h3>
	</c:if>
	
	
	
	
	
	<c:choose>
		<c:when test="${ param.color eq 'red' }">
			<h3 style="color:<c:out value='${ param.color }'/>;">당신이 선택한 색은 레드</h3>
		</c:when>
		<c:when test="${ param.color eq 'blue' }">
			<h3 style="color:<c:out value='${ param.color }'/>;">당신이 선택한 색은 블루</h3>
		</c:when>
		<c:when test="${ param.color eq 'yellow' }">
			<h3 style="color:<c:out value='${ param.color }'/>;">당신이 선택한 색은 옐로우</h3>
		</c:when>
	</c:choose>
	
</body>
</html>