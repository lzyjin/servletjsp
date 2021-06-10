<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조건문</title>
</head>

<body>

	<h2>c:if문 활용하기</h2>
	
	<p>
		자바의 if문과 동일
		스크립트립에서 &lt;% if() {&gt; &lt;}%&gt;
	</p>
	
	<p>
		작성방법
		&lt; c:if test = "비교연산" &gt; 
		로직
		&lt; /c:if&gt;
	</p>
	
	<ul>
		<li>test : true 또는 false가 나오는 연산이나 변수 등을 대입</li>
		<li>var : if문의 test속성의 결과를 저장하는 변수</li>
	</ul>
	
	<c:set var="num" value="19"/>
	<c:set var="age" value="19"/>
	
	<c:if test = "${ num == 20 }">
		<h3>값은 20임</h3>
	</c:if>
	
	<!-- 스크립트립을 쓰면 -->
	<% int num = 20; %>
	<% if(num == 20) { %>
		<h3>값은 20임</h3>
	<% } %>
	
	<form action="ifResult.jsp">
	
		<input type="text" name="su">
		<input type="text" name="su2">
		<select name="color">
			<option value="red">빨강</option>
			<option value="blue">파랑</option>
			<option value="yellow">노랑</option>
		</select>
		
		<input type="submit" value="계산하기">
		
	</form>
	
	
	
	<h3>c:choose 태그 이용하기</h3>
	
	<p>java의 switch문의 문법과 비슷하다</p>
	<p>작성법 : &lt; c:choose&gt; 
				&lt;c:when test=""&gt; 
				&lt;/c:when&gt; 
				&lt;c:otherwise&gt;
				&lt;/c:otherwise&gt;
				&lt;/c:choose&gt;</p>
	
	<h1>경품뽑기</h1>
	<form action="chooseTest.jsp">
		숫자를 입력하세요<input type="number" name="num">
		<input type="submit" value="제출">
	</form>
	
	
</body>
</html>