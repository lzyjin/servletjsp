<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!-- 템플릿으로 만들어놓으면 편하다  -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그 외 코어 태그들</title>
</head>

<body>



	<h3>c:import 이용하기</h3>
	
	<p>
		페이지에서 다른 페이지를 불러오는 것
		c:import는 페이지를 불러와서 바로 출력하지 않고 변수에 저장한다
	</p>
	
	<ul>
		c:import 속성 
		<li>var : 페이지를 저장하는 변수명</li>
		<li>url : 불러올 페이지의 위치</li>
	</ul>
	
	
	
	
	<c:import var="header" url="../includeTest.jsp"/>
	
	<div>
		<c:out value="${ pageScope.header }" escapeXml="false"/>
	</div>
	
	<div>
		${ pageScope.header }
	</div> 
	
	
	
	
	
	
	
	<!-- 파라미터로 검색어를 보낼 수 있다 -->
	<c:url var="addr" value="https://search.naver.com/search.naver">
		<c:param name="query" value="양호준"/>
	</c:url>
	
	<a href="${ addr }">naver 검색</a>
	
	
	
	
	
	
	<h3>c:redirect 태그 이용하기 </h3>
	
	<p>페이지를 변경해주는 태그</p>
	
	<!-- 
		response.sendRedirect()와 동일한 기능
		 mvc1패턴에서 쓴다, mvc2패턴에서 페이지 이동은 controller(서블릿)에서 하기 때문
	 -->
	
	<%-- <c:redirect url="${ addr }">
		<c:param name="query" value="코로나"/>
	</c:redirect> --%>
	
	
	
	
	
	
	<h4> c:catch 태그 이용해서 예외처리하기</h4>
	
	<%
		String test = null;
		request.setAttribute("test", test);
	%>
	
	<%-- <%= test.charAt(0) %> 이대로는 NullPointerException --%> 
	
	<c:catch var="e">
		<%= test.charAt(0) %>
	</c:catch>
	
	<c:out value="${ e }"/>
	
</body>
</html>