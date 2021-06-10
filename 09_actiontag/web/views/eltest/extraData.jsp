<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>

	<h3>cookie값 가져오기</h3>
	
	<p>
		cookie 객체를 통해서 데이터를 가져온다
	</p>
	
	<h4>${ cookie }</h4>
	<h4>${ cookie.test.name }</h4> <!--  test  -->
	<h4>${ cookie.test.value }</h4> <!-- 오늘여기까지 -->
	
	
	
	
	<h3>header값 가져오기</h3>
	
	<p>
		header 객체를 통해서 데이터를 가져온다
	</p>
	
	<h4>${ header["User-Agent"] }</h4>
	
	
	<h3>이전페이지에 대한 정보</h3>
	<h4>${ header["Referer"] }</h4>
	
	
</body>
</html>