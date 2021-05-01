<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
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
		
		<!-- 페이지 전환 방법 : a태그,window.open, form으로, js location으로 ( assign, replace) -->
		
		<!-- 자동으로 패이지를 전환하게 --> 
		
		<%-- location.replace("<%=request.getContextPath()%>"); --%>
		
		<%-- location.replace('<%=request.getAttribute("loc")%>');  --%>// 이렇게 쓸 시 응답페이지 주소가 localhost:9090  가 된다 -> 404오류 
		
		// location.replace() 를 하면 /가 붙기 때문에 요청주소가 /가 된다 = / 는 서버 이름을 지칭 
		
		// request.getContextPath() + "/login"   =>   /06_HelloMVC/login  -> localhost:9090/06_HelloMVC/login ( 절대주소가 된다 )
		
		
		
		// 정상적으로 작성하면
		location.replace("<%=request.getContextPath()%><%=request.getAttribute("loc")%>");
		<%-- <%=request.getAttribute("loc")%> = /  --%>
		
		// 로그인 실패시 http://localhost:9090/06_HelloMVC/login 에서 alert() 띄우고 
		// 메인인 http://localhost:9090/06_HelloMVC/ 로 이동함 
		
		
	</script>	
	
	
	
	
</body>
</html>