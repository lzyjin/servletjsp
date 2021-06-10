<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	System.out.println("여기 왔다감");

	request.setCharacterEncoding("utf-8");
%>

<jsp:forward page="useBeanTest.jsp">
	<jsp:param name="name" value="김상현"/>
	<jsp:param name="age" value="29"/>
</jsp:forward> 

<!-- 주소는 forwardTest.jsp인 상태 그대로 useBeanTest.jsp페이지로 이동 -->

<h1>이건 출력 안된다</h1>