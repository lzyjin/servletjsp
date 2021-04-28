<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.model.vo.Member" %>
    
<%

	// 서버에서 전송된 request의  loginMember를 가져오기
	/* Member loginMember = (Member)request.getAttribute("loginMember"); */
	
	Member loginMember = (Member)session.getAttribute("loginMember");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloMVC</title>
<link rel="stylesheet" type="text/css" href="css/style.css">

<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>

</head>
<body>

		<div id="container">
		
		<header>
		
			<h1>Hello MVC</h1>
			
			
			<div class="login-container">
			
			<% if(loginMember == null) { %>
			
				
				<form id="loginFrm" action="<%=request.getContextPath()%>/login"  method="post" onsubmit="return fn_login_validate();"> <!-- 함수 결과가 true면 submit -->
					
						<table>
							
							<tr>
							
								<td>
								
									<input type="text" id="userId" name="userId" placeholder="아이디">
									
								</td>
								
								
								
							</tr>
							
							<tr>
							
								<td>
								
									<input type="password" id="password" name="password" placeholder="비밀번호">
									
								</td>
								
								<td>
								
									<input type="submit" value="로그인">
								
								</td>
								
							</tr>
							
							<tr>
							
								<td colspan="2">
								
									<input type="checkbox" name="saveId" id="saveId">
									
									<label for="saveId">아이디저장</label>
									
									<input type="button" value="회원가입" onclick="">
								
								</td>
								
							</tr>
							
							
								
						</table>
					
				</form>
				
				
				<% } else {  System.out.println("header" + loginMember.getUserName()); %>
			
				<table>
				
					<tr>
					
						<td><%= loginMember.getUserName() %>님, 환영합니다</td>
					
					</tr>
					
				</table>
			
			<% }  %>
				
				
			</div>
			
			
			
			<nav>
			
				<ul class="main-nav">
				
					<li class="home"><a href="">Home</a></li>
					<li class="notice"><a href="">공지사항</a></li>
					<li class="board"><a href="">게시판</a></li>
					
				
				</ul>
			
			</nav>
			
			
		</header>
		
		
		
		<script>
			
			// 유효성 검사 처리
		
			const fn_login_validate = () => {
				
				// userId input태그에 값이 있으면, 값이 4글자 이상이면
				const userId = $("#userId").val();
				
				if(userId.trim().length < 4) {
					
					alert("아이디를 4글자 이상 입력하세요");
					return false;
				}
				
				// password가 공란이 아닐때만 전송
				const pw = $("#password").val();
				
				if(pw.trim().length == 0) {
					
					alert("비밀번호를 입력하세요");
					return false;
					
				}
			}
		
		</script>
		
