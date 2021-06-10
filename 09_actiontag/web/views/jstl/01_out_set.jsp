<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- JSTL을 이용하기 위해서는 페이지 상단에 JSTL을 선언해야한다 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl 이용하기</title>
</head>

<body>

	<h3>set/out태그 활용하기</h3>
	
	<p>c:set  :  변수를 선언하여 활용하는 태그</p>
	
	<ul>
		c:set 속성
		
		<li>var : 변수명</li>
		<li>value : 변수에 들어갈 값</li>
		<li>scope : 변수가 선언되는 위치 ( 생략하면 pageContext에 저장 ) </li>
	</ul>
	
	<p>c:out  :  EL구분, 변수를 출력해주는 태그 ( EL표현식, &lt;%=&gt;와 동일  )</p>
	
	<ul>
		c:out 속성
		
		<li>value : 출력할 값( EL표현식, 리터럴값 )</li>
		<li>default : 변수를 가져왔을 때 그 값이 없는 경우 출력될 기본값 </li>
		<li>escapeXml : 태그형식으로 value를 작성했을 때, 태그로 해석할지 결정하는 속성 (true, false )</li>
	</ul>
	
	
	<h3>page에서 사용할 변수 선언하기</h3>
	
	<!-- 지금 이 페이지내에서 사용가능한 변수 name이 생긴 것 -->
	<c:set var = "name" value = "유병승"/> <!-- 닫기태그 필수 -->
	
	<h4> c:set으로 선언한 변수는 el표현식과 c:out으로 출력할 수 있다</h4>
	
	<p>c:set으로 선언한 변수를 el표현식으로 : ${ name }</p>
	<p>c:set으로 선언한 변수를 c:out으로 : <c:out value="${ name }"></c:out></p>
	<!-- c:out으로 접근하는게 보안상 더 좋다 -->
	
	
	<%-- <c:set var="addr" value="경기도 시흥시"/> --%>
	<c:set var="addr" value="경기도 시흥시" scope="request"/>
	
	<p>addr ${ addr }</p>
	<p>pageScope.addr ${ pageScope.addr }</p>
	<p>requestScope.addr ${ requestScope.addr }</p>
	
	<%-- <a href="${ pageContext.request.contextPath }/test">페이지 연결</a> --%>
	<!-- 매번 이렇게 쓰기 너무 기니까 변수로 선언해서 쓸 수 있다 -->
	<a href="${ path }/test">페이지 연결</a>
	
	
	
	
	<h4>c:out</h4>
	리터럴 사용 : <c:out value="안녕하세요, 오늘은 수요일"/><br>
	EL표현식 사용 : <c:out value="${ addr }"/><br>
	
	
	
	<%
		String hobby = "code";
	%>
	
	
	출력식 : <c:out value="<%=hobby %>"/><br>
	출력식 : <c:out value='<%=request.getAttribute("addr")%>'/>
	
	
	
	<h3>
		<c:out value="태그안에 쓸 수 있을까?"/> <!-- 가능  -->
	</h3>
	
	
	
	<c:out value="<h3>이건 가능할까?</h3>"/> <!-- 안되네 -->
	
	<!-- 
		value속성값은 무조건 문자열임 
	
		해결 방법 : escapeXml속성값을 false로 설정한다 ( default는 true )
	-->
	
	<c:out value="<h3>이건 가능할까?</h3>" escapeXml="true"/>
	<c:out value="<h3>이건 가능할까?</h3>" escapeXml="false"/>
	
	<%
		String test = "<script>alert('호잇')</script>";
		request.setAttribute("test", test);
	%>
	
	<c:set var="script" value="<script>alert('호잇')</script>"/>
	
	<%-- ${ script } 작동함--%>
	<c:out value="${ script }"/> <!-- 태그로 해석하지 않음 -->
	<c:out value="${ script }" escapeXml="false"/><!-- 태그로 해석함 -->
	
	
	<!-- 이렇게도 쓸 수 잇음 -->
	<input type="text" value="<c:out value='이건 c:out으로 작성'/>">
	
	
	
</body>
</html>