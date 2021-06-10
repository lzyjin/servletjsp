<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>useBean 이용하기</title>
</head>

<body>

	<h3>서블릿에서 보낸 객체 가져오기</h3>
	
	<jsp:useBean id="p" class="com.jspaction.model.vo.Person" scope="request"/>
	
	이름 : <jsp:getProperty name="p" property="name"/>
	나이 : <jsp:getProperty name="p" property="age"/>
	주소 : <jsp:getProperty name="p" property="addr"/>
	
	
	
	<h3>useBean으로 기본 생성된 객체에 데이터를 넣기</h3>
	
	<jsp:setProperty name="p" property="name" value="장희웅"/>
	<jsp:setProperty name="p" property="age" value="27"/>
	<jsp:setProperty name="p" property="addr" value="서울시"/>
	
	이름 : <jsp:getProperty name="p" property="name"/>
	나이 : <jsp:getProperty name="p" property="age"/>
	주소 : <jsp:getProperty name="p" property="addr"/>
	
	
	
	<h3>파라미터로 보낸 값을 useBean객체에 바로 대입하기</h3>
	
	<jsp:useBean id="p1" class="com.jspaction.model.vo.Person"/>
	
	<jsp:setProperty name="p1" property="name" param="name"/>
	<jsp:setProperty name="p1" property="age" param="age"/>
	<jsp:setProperty name="p1" property="addr" param="addr"/>
	
	이름 : <jsp:getProperty name="p1" property="name"/>
	나이 : <jsp:getProperty name="p1" property="age"/>
	주소 : <jsp:getProperty name="p1" property="addr"/>
	
</body>
</html>