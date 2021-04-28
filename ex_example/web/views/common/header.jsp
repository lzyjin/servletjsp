<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.test.model.vo.Member"  %>
    
<%

	Member loginMember = (Member)session.getAttribute("loginMember");
	
	/* 로그인하면 (Member)session.getAttribute("loginMember")의 값이 null이 아님
	-> 이걸로 분기처리해서 로그인 성공인지 실패인지 처리 가능 */

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    ul>li{
        display: inline-block;
        margin-right: 30px;
        font-size: 30px;
        font-weight: bolder;
    }
    ul>li>a{
        text-decoration: none;
        color: cyan;
   }
   header{
       display: flex;
       justify-content: space-between;
   }
</style>
</head>

<body>
    <header>

        <div>

        </div>

        <div>
            <ul>
                <li><a href="">메인화면</a></li>
                <li><a href="">게시판</a></li>
                <li><a href="">갤러리</a></li>
                <li><a href="">자료실</a></li>
            </ul>
        </div>

        <div>
        	<% if(loginMember == null)  {  %>
            <form action="login.do" method="post">
                <table>
                    <tr>
                        <td>아이디</td>
                        <td><input type="text" name="userId"></td>
                    </tr>
                    <tr>
                        <td>비밀번호</td>
                        <td><input type="password" name="pwd"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="로그인"></td>
                        <td><input type="reset" value="취소"></td>
                    </tr>
                </table>
            </form>
            
            <% } else { %>
            	
            	<h3> <%=loginMember.getMemberName()%>님, 환영합니다</h3>
            	
            <% } %>
        </div>
    </header>
</body>
</html>