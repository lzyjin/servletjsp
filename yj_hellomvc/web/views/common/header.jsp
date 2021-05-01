<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.member.model.vo.Member" %>   
 
<%
	 /* Member m = (Member)request.getAttribute("user");  이걸로 불러오는게 아니라 세션으로 불러와야해 */ 
	 
	 /* Member m = (Member)request.getAttribute("login_member"); session에 저장했는데 request로 불러오면 안되지요 */
	 
	 Member login_member = (Member)session.getAttribute("login_member");
	 
	 System.out.println(login_member);

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>yj_hello mvc</title>
<link rel="stylesheet"  type="text/css"  href="css/style.css">
<script src = "<%=request.getContextPath()%>">/js/jquery-3.6.0.min.js</script>
</head>

<body>

	<div id="container">
	
		<header>
		
			<h1>hello mvc</h1>
			
			<div class="login-container">
			
			<% if(login_member == null) { %>
											<!-- 절대경로로 적기 위해 getContextPath 사용 -->
					<form id="loginFrm" action="<%=request.getContextPath()%>/login.do" method="post" onsubmit="return fn_login_validate();">
				
						<table>
						
								<tr>
									<td><input type="text" name="userId" placeholder="아이디를 입력하세요"></td>
								</tr>
								
								<tr>
									<td><input type="password" name="password" placeholder="비밀번호를 입력하세요"></td> 
									<td><input type="submit" value="로그인"></td>
								</tr>
							
								<tr>
									<td colspan="2">
										<input type="checkbox" name="saveId" id="saveId"><label for="saveId">아이디저장</label>
										<input type="button" value="회원가입" onclick="">
									</td>
								</tr>
							
						</table>
					
					</form>
					
				<% } else { %>
				
				
					<table>
					
						<tr>
							<td><%=login_member.getUserName()%></td>
						</tr>
						
						<tr>
							<td><input type="button" value="로그아웃" onclick=""></td>
						</tr>
					
					
					</table>
				
				
				<% } %>
				
			</div>
			
			
			<nav>
			
				<ul  class="main-nav">
				
					<li class="home"><a href="">home</a></li>
					<li class="notice"><a href="">공지사항</a></li>
					<li class="board"><a href="">게시판</a></li>
				
				</ul>
			
			</nav>
			
			
		</header>
		
		
		<script>
		
			const fn_login_validate = () => {
				
				// id가 input태그안에 값이 있으면,  4글자 이상 이면 
				const userId = $("#userId").val();
				if(userId.trim().length < 4) {
					
					alert("아이디를 4글자 이상 입력하세요");
					return false;
					
				}
				
				// password가 공란이 아니면 전송 
				const pw = $("#password").val();
				if(pw.trim().length == 0) {
					
					alert("비밀번호를 입력하세요");
					return false;
				}
			}
		
		</script>
		