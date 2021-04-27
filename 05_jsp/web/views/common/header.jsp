<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String name = "유병승";
	String email = "prince0324@naver.com";
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    ul>li{
        display: inline-block;
        margin: right 30px;
        font-size: 30px;
        font-weight: bolder;
    }

    li>a{ /* 텍스트는 li태그안의 a태그 안에 있기 때문에... */
        text-decoration: none;
        color: pink;
    }
    table{
    	width : 300px;
    	height : 400px;
    	border : 1px solid lime;
    }
    table td{
    	border : 1px solid pink;
    }
</style>
</head>
<body>
<header>
        <ul>
            <li><a href="content.jsp">메인화면</a></li>
            <li><a href="board.jsp">게시판</a></li>
            <li><a href="">갤러리</a></li>
            <li><a href="">자료실</a></li>
        </ul>
    </header>
