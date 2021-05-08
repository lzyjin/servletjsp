<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경 페이지</title>

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

    <div id="updatePassword-container">
    
		<form name="updatePwdFrm" action="<%=request.getContextPath()%>/updatePasswordEnd.do" method="post" >
			
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
							<input type="submit" value="변경" />&nbsp;
							<input type="button" value="닫기" onclick="window.close();">						
						</td>
					</tr>
					
					<input type="hidden" value=<%=request.getParameter("userId") %> id="userId" name="userId">			
					
				</table>
			
		</form>
		
	</div>


	<script>
	
		// 아이디, 현재비번, 새비번을 servlet으로 넘겨서 db에 확인과 수정 ( action으로 )
		

		// 새비번과 비번확인이 일치하지 않으면 알림창 띄우도록 

		document.getElementById("password_chk").blur( (e) => {

			const pw = document.getElementById("password_new");
			const pwck = e.target;

			if(pw != pwck) {
				
				alert("비밀번호가 일치하지 않습니다");
			}

		} );

		
		// 닫기버튼누르면 닫히도록 
		
	</script>


</body>
</html>