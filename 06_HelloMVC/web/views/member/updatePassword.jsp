<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.model.vo.Member" %>
<%
	Member result = (Member)request.getAttribute("result");
System.out.println("is this error? "+result);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호변경</title>

<style>
    
		    div#updatePassword-container{
		        background:red;
		    }
		    div#updatePassword-container table {
		        margin:0 auto;
		        border-spacing: 20px;
		    }
		    div#updatePassword-container table tr:last-of-type td {
		        text-align:center;
		    }
		    
    </style>

</head>

<body>

		<!-- 새창의 크기 : width 400, height=210 left 500 top 200 -->

    

    <div id="updatePassword-container">
    
		<form name="updatePwdFrm" action="<%=request.getContextPath()%>/updatePasswordEnd.do?userId=<%=request.getAttribute("userIds") %>" method="post" >
		()
			<table>
				<tr>
					<th>현재 비밀번호</th>
					<td><input type="password" name="password" id="password" required></td>
				</tr>
				
				<tr>
					<th>변경할 비밀번호</th>
					<td>
						<input type="password" name="password_new" id="password_new" required>
					</td>
				</tr>
				
				<tr>
					<th>비밀번호 확인</th>
					<td>	
						<input type="password" id="password_chk" required><br>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<input type="submit" value="변경" onsubmit="return fn_check();"/>&nbsp;
						<input type="button" value="닫기" onclick="window.close()"/>						
					</td>
				</tr>
				
			</table>
			
			<!-- 선생님 풀이 ) 비밀번호 변경 기능 만들면서 필요해서 추가한 태그  -->
			<%-- <input type="hidden" name="userId" value="<%=request.getParameter("userId")%>"> --%>
			
		</form>
		
	</div>
	
	
	
	
	
	<script>

		const fn_check = () => {
			$(function() {
				
				// 비밀번호 확인 	
				$("#password_chk").blur( (e) => {
				
					const pw = $("#password_new").val();
					const pwck = $(e.target).val();
					
					if(pw != pwck) {
						
						alert("비밀번호가 일치하지 않습니다");
						$("#password_new").focus();
					}
				
				} );
				
			}
		
			// 기존 비밀번호가 맞는지 확인 
		
				
				<%-- 
				const oldPw = "<%=result.getPassword()%>";
				
				console.log(oldPw);
				
				const oldPwck = $(e.target).val();
				
				if(oldPw != oldPwck) {
					
					alert("현재 비밀번호가 틀립니다");
					$("#password").focus();
					return false;
				} else {
					
					return true;
				}
				
				 --%>
				 
				 
				/*  현재 비밀번호입력폼과 DB와 확인 */
			
		}
			
		<%-- 
		
		
			
		})
		 --%>
		
		
		
		// 선생님 풀이
		/* 
		const pwck=document.getElementById("password_chk");
		
		pwck.addEventListener("blur",(e)=>{
			
			let pw=document.getElementById("password_new").value;
			
			let pwckVal=e.target.value;
			
			if(pw!=pwckVal){
				alert("비밀번호가 일치하지않습니다.");
				document.getElementById("password_new").focus();
			}
		});
		*/
		
		
		
		
		
	
	</script>
	

</body>
</html>