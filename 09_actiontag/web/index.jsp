<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표준 액션 태그</title>
</head>

<body>

	<h2>표준 액션 태그 -> jsp:태그명</h2>
	
	
	
	
	<h3>jsp:include 태그</h3>
	
	<p>
		외부의 페이지를 불러와 사용하는 태그
		&lg; %@ include % &gt; 태그와 비슷하다
	</p>
	
	<p>사용법 : &lg; jsp:include page="페이지명" &gt; </p>
	
	<a href="<%=request.getContextPath()%>/views/includeTest.jsp">include테스트</a>
	
	
	
	
	<h3>jsp:useBean 태그</h3>
	
	<p>
		vo객체를 jsp태그로 이용하는 것, 
		param값이나 request에 저장된 vo를 자동 매핑시켜준다
	</p>
	
	<a href="<%=request.getContextPath()%>/useBeanTest">useBean활용하기</a>
	
	<form action="<%=request.getContextPath()%>/views/useBeanTest.jsp">
		이름<input type="text" name="name"><br>
		나이<input type="text" name="age"><br>
		주소<input type="text" name="addr"><br>
		<input type="submit" value="전송">
	</form>
	
	
	
	
	<h3>jsp:forward 이용하기</h3>
	
	<p>
		jsp페이지를 다른 페이지로 이동시키는 태그
		이동시킬때 parameter값을 전송할 수 있다
		
	</p>
	
	<a href="views/forwardTest.jsp">페이지이동설정</a>
	
</body>
</html>