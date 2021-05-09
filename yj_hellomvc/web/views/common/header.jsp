<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.member.model.vo.Member" %>   
 
<%
	 /* Member m = (Member)request.getAttribute("user");  이걸로 불러오는게 아니라 세션으로 불러와야해 */ 
	 /* Member m = (Member)request.getAttribute("login_member"); session에 저장했는데 request로 불러오면 안되지요 */
	 
	 Member login_member = (Member)session.getAttribute("login_member");
	 
	 // 쿠키 불러오기 
	 Cookie[] cookies = request.getCookies();
	 
	 String saveId = null;
	 
	 // 쿠키가 있으면 ( null이 아니면 ) saveId에 아이디를 저장 -> 쿠키가 있으면 saveId가 null이 아님 
	 
	 if(cookies != null) {
		 
		 for(Cookie cookie : cookies) {
			 
			 if(cookie.getName().equals("saveId")) {
				 
				 saveId = cookie.getValue();
				 break;
			 }
			 
		 }
		 
	 }

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>yj_hello mvc</title>
<!-- <link rel="stylesheet"  type="text/css"  href="css/style.css"> 상대경로로 적었더니 css파일을 못잡아서 적용이 안됐음 -->
<link rel="stylesheet"  type="text/css"  href="<%=request.getContextPath()%>/css/style.css">

<script src = "<%=request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>

<!-- 이걸로 써도 되긴 됌 <script src = "/js/jquery-3.6.0.min.js"></script> -->
<%-- 내가 잘못 쓴 코드 <script src ="<%=request.getContextPath() %>">js/jquery-3.6.0.min.js</script> --%>

</head>

<body>

	<div id="container">
	
		<header>
		
			<h1>hello mvc</h1>
			
			<div class="login-container">
			
			<% if(login_member == null) { %>
											<!-- 절대경로로 적기 위해 getContextPath 사용 -->
					<form id="loginFrm" action="<%=request.getContextPath()%>/login.do" method="post" onsubmit="return fn_login_validate()">
				
						<table>
						
								<tr>
									<td><input type="text" name="userId" id="userId" placeholder="아이디를 입력하세요" value=<%= saveId != null ? saveId : "" %>></td>
								</tr>
								
								<tr>
									<td><input type="password" name="password" id="password" placeholder="비밀번호를 입력하세요"></td> 
									<td><input type="submit" value="로그인"></td>
								</tr>
							
								<tr>
									<td colspan="2">
										<input type="checkbox" name="saveId" id="saveId" <%=saveId != null ? "checked" : "" %>><label for="saveId">아이디저장</label>
										<input type="button" value="회원가입" onclick="location.assign('<%=request.getContextPath()%>/enroll.do')">
									</td>
								</tr>
							
						</table>
					
					</form>
					
				<% } else { %>
				
				
					<table>
					
						<tr>
							<td><%=login_member.getUserName()%>님</td>
						</tr>
						
						<tr>
							<td><input type="button" value="내정보보기" onclick="location.assign('<%=request.getContextPath()%>/mypage.do?userId=<%=login_member.getMemberId()%>')"></td>
						</tr>
						
						<tr>
							<td><input type="button" value="로그아웃" onclick="location.assign('<%=request.getContextPath()%>/logout.do')"></td>
							
							<%-- <td><input type="button" value="로그아웃" onclick="location.assign('<%=request.getContextPath()%>/logout')"></td> --%>
						</tr>
					
					
					</table>
				
				
				<% } %>
				
			</div>
			
			
			<nav>
			
				<ul  class="main-nav">
				
					<li class="home"><a href="">home</a></li>
					<li class="notice"><a href="">공지사항</a></li>
					<li class="board"><a href="">게시판</a></li>
					
					<% if(login_member != null && login_member.getMemberId().equals("admin")) { %>
					
						<li class="admin-memberlist"><a href="<%=request.getContextPath()%>/admin/memberlist.do">회원관리</a></li>
				
					<% } %>
				
				</ul>
			
			</nav>
			
			
		</header>
		
		
		
		<script>
		
			const fn_login_validate = () => {
				
				// id가 input태그안에 값이 있으면,  4글자 이상 이면 
				const userId = $("#userId").val();
					console.log("outter");
					if(userId.trim().length < 4) {

						console.log("inner");
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
		