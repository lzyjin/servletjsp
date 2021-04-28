<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List, com.member.model.vo.Member" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 출력하기</title>
</head>

<body>
	
	<!-- 요청이 끝나지 않고 이쪽으로 넘어왔기 때문에 request 객체 사용 가능  -->

	<h2>저장된 회원정보 출력</h2>
	
	<!-- 테이블 형식으로 해당 항목 출력하기 -->
	
	<%
		
		/* String memberId = (String)request.getAttribute("memberId");
		String memberPwd = (String)request.getAttribute("memberPwd");
		String memberName = (String)request.getAttribute("memberName");
		String memberAge = (String)request.getAttribute("age");
		 */
		 
		 List<Member> members = (List<Member>)request.getAttribute("members");
		
	%>
	
	
	
	<%-- <table>
	
	
	<tr>
			
			<% for(List<Member> m : members) { %>
				 
				<td><%=m%></td>
				
			<%  } %>
	</tr>
		
	</table> --%>
	
	<table>
	
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>나이</th>
		</tr>
		
			<!-- 
				반복문 안에 <tr>을 쓰는 이유 : 반복될때마다 row(데이터)가 새로 생성되어야 하기 때문  
				한개의 row(<td>태그)가 한 개의 Member
			-->
			
			<% for(Member m : members) { %>
			
			<tr>
			
				<td><%= m.getMemberId() %></td>
				<td><%= m.getMemberPwd() %></td>
				<td><%= m.getMemberName() %></td>
				<td><%= m.getAge() %></td>
				
			</tr>
			
			<% } %>
		
	</table>
	
	
</body>
</html>