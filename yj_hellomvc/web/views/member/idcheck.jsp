<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.model.vo.Member" %>
<%
	Member result = (Member)request.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 확인</title>
<style>
	div#checkId-container {
		
		text-align: center;
		padding-top: 50px
	}

	span#duplicated{

		color: red;
		font-weight: bolder;
	}
</style>
</head>
<body>

	<!-- 1. 해더풋터가 필요할까? - 필요없어 
	2. 화면내용 -->
	
	<div id="checkId-container">
	
			<% if(result == null) { %>
		
			
					[<span><%=request.getParameter("hiddenuserId")%></span>]는 사용가능합니다.	
					
					<br><br>
					
					<button type="button" onclick="fn_close();">닫기</button>
					
			
			<% } else { %>
			

					[<span id="duplicated"><%=request.getParameter("hiddenuserId")%></span>]는 사용중입니다.
					<br><br>
					
					<!-- 아이디 재입력창 구성 -->
					<form action="<%=request.getContextPath()%>/idcheck.do" method="post">
					
						<!-- <input type="text" name="userId_" id="userId_"> -->
						<input type="text" name="hiddenuserId" id="userId_"> <!-- name이 같아야함  -->
						<input type="submit" value="중복검사" >
						
					</form>
			
			<% } %>
		
	</div>
	
	<script>
		
		const fn_close = () => {
			
			opener.enrollForm.userId_.value = "<%=request.getParameter("hiddenuserId")%>";
			
			window.close();
		}
	
	</script>
	
</body>
</html>