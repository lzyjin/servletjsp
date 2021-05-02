<%@page import="com.member.controller.IdCheckServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String checkEndId = (String)session.getAttribute("checkEndId");

	System.out.println("checkEndId : " + checkEndId);
%>
    
<%@ include file="/views/common/header.jsp" %>

	<!-- 팝업창 --> 

	<h1>아이디 중복 검사 </h1>
	
	<div>
		<form action="<%=request.getContextPath()%>/idcheck.do"> <!-- form으로 전송하지 않으면 다른창에서 request로 값에 접근할 수 없다 --> 
			<table>
			
				<tr>
				
					<!-- <td><input type="text" name="inputidCheck_" id="inputidCheck_"></td> -->
					<td><input type="text" name="inputidCheck_" id="inputidCheck_"></td>
					
					<td><%-- <input type="button" name="checkid" id="checkid" value="중복확인" onclick="location.replace('<%=request.getContextPath()%>/idcheck.do')"> --%>
							<input type = "submit" value ="중복확인" name = "checkid" id = "checkid">
					</td>
				</tr>
				
				
			</table>
		</form>
	
	</div>
	
	
	<script>
	
		
		document.getElementById("inputidCheck_").value = opener.document.userId_.value;
	
		<% if(checkEndId != null) { %>
		
			/* window.opener.document.getElementById("userId_").value = checkEndId; */
			window.close();
		
		<% } %>
		
	
	</script>

<%@ include file="/views/common/footer.jsp" %>