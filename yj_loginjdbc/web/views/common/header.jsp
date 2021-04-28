<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<header>
	
		<h1>로그인 jsp, servlet, jdbc로 구현</h1> 
		
		<form action="login.do" method="post">
		
			<input type="text" id="id" name="id" placeholder="아이디 입력">
			<input type="password" id="password" name="password" placeholder="비밀번호 입력">
			<input type="submit" value="로그인">
			<input type="reset" value="취소">
		
		</form>
	</header>