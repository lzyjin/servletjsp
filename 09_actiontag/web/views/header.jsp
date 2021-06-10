<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String memberId = "admin";

	// 호출한 곳에서 jsp:param으로 보낸 데이터는
	// request.getParameter()로 받을 수 있다. 
	String title = request.getParameter("title");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<h1><%=title %></h1>
		<ul>
			<li>메인</li>
			<li>공지사항</li>
			<li>게시판</li>
			<li>갤러리</li>
		</ul>
	</header>