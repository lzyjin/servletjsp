<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.model.vo.Member" %>
<%
	Member m = (Member)request.getAttribute("result");
	/* String userId = m.getMemberId(); */
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 확인</title>

<style>

	div#checkId-container{
	
		text-align : center;
		padding-top : 50px;
	}
	
	span#duplicated{
		
		color : red;
		font-weight : bolder;
	}
	
</style>

</head>
<body>

<!-- 
	1. 해더풋터가 필요할까?  - 필요없어 
	2. 화면내용 
-->


	<div id="checkId-container">
	
		<% if( m == null) { %>
		
	
				<%-- [<span><%=m.getMemberId()%></span>]는 사용가능합니다. --%>	
				<!-- 이렇게 하면 nullPointerException 발생함  -->
				
				[<span><%=request.getParameter("userId")%></span>]는 사용가능합니다.
				<!-- 클라이언트로 받은 요청을 서블릿에서 받아서 다시 이  jsp페이지로 다시 요청했으므로 클라이언트가 입력한 값을 이렇게 쓸 수 있음 !!  --> 
				
				<br><br>
				<button type="button" onclick="fn_close();">닫기</button>
				
		<% } else { %>
	
				[<span id="duplicated"><%=request.getParameter("userId")%></span>]는 사용중입니다.
				
				<br><br>
				
				<!-- 아이디 재입력창 구성 -->
				<form action="<%= request.getContextPath()%>/checkDuplicateId" method="post"> <!-- 서블릿 재귀호출같은 느낌 .. -->
					<input type="text" name="userId" id="userId_"> <!-- 회원가입창의 아이디입력창 name속성값과 동일해야한다  -->
					<input type="submit" value="중복검사" >
				</form>
				
		<% } %>
				
				
	</div>
	
	<script>
	
		const fn_close = () => {
			
			/* window.opener.document.getElementById("userId_").value = document.getElementById("userId_").value;
			window.close(); */
			
			
			
			// 선생님 풀이
			// 1. 현재 값(request에 저장되어있는 아이디 입력값 )을 부모창의 userId_에 대입
			const userId = "<%=request.getParameter("userId")%>"; // 클라이언트가 입력한값(요청보낼때 사용한 값) 
			
			/* opener : 부모 윈도우 객체  */
			// 2. ( 부모창의 ) 회원가입창의 아이디 입력창의 값에 
			opener.memberEnrollFrm.userId.value = userId;
			
			// 3. 비밀번호 창으로 포커스 주기
			opener.memberEnrollFrm.password.focus();
			
			window.close();
		}
		
		
	</script>

</body>
</html>