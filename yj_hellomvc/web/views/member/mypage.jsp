<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>

<%
	Member m = (Member)request.getAttribute("member");
%>


	<section id=enroll-container>
	
		<h2>회원 정보 수정</h2>
		<form id="memberFrm" method="post" onsubmit="return fn_update_validate();">
		
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="userId" id="userId_" value="<%=m.getMemberId()%>" readonly>
					</td>
				</tr>
				<%-- <tr>
					<th>패스워드</th>
					<td>
						<input type="password" name="password" id="password_" value="<%= m.getPassword()%>">
					</td>
				</tr>
				<tr>
					<th>패스워드확인</th>
					<td>	
						<input type="password" id="password_2"><br>
					</td>
				</tr>   --%>
				<tr>
					<th>이름</th>
					<td>	
					<input type="text"  name="userName" id="userName" value="<%=m.getUserName()%>" required ><br>
					</td>
				</tr>
				<tr>
					<th>나이</th>
					<td>	
					<input type="number" name="age" id="age" value="<%=m.getAge()%>"><br>
					</td>
				</tr> 
				<tr>
					<th>이메일</th>
					<td>	
						<input type="email" placeholder="abc@xyz.com" name="email" id="email" value="<%=m.getEmail()%>"><br>
					</td>
				</tr>
				<tr>
					<th>휴대폰</th>
					<td>	
						<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" value="<%=m.getPhone()%>"><br>
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>	
						<input type="text" placeholder="" name="address" id="address" value="<%=m.getAddress()%>"><br>
					</td>
				</tr>
				<tr>
					<th>성별 </th>
					<td>
						<!-- DB정보에 따라 분기처리할것 -->
							<input type="radio" name="gender" id="gender0" value="M" <%= m.getGender().equals("M") ? "checked" : "" %>>
							<label for="gender0">남</label>
							<input type="radio" name="gender" id="gender1" value="F" <%= m.getGender().equals("F") ? "checked" : "" %>>
							<label for="gender1">여</label>
						
						
					</td>
				</tr>
				<tr>
					<th>취미 </th>
					<td>
					
						<input type="checkbox" name="hobby" id="hobby0" value="운동" <%= m.getHobby().contains("운동") ? "checked" : "" %>><label for="hobby0">운동</label>
						<input type="checkbox" name="hobby" id="hobby1" value="등산" <%= m.getHobby().contains("등산") ? "checked" : "" %>><label for="hobby1">등산</label>
						<input type="checkbox" name="hobby" id="hobby2" value="독서" <%= m.getHobby().contains("독서") ? "checked" : "" %>><label for="hobby2">독서</label><br />
						<input type="checkbox" name="hobby" id="hobby3" value="게임" <%= m.getHobby().contains("게임") ? "checked" : "" %>><label for="hobby3">게임</label>
						<input type="checkbox" name="hobby" id="hobby4" value="여행" <%= m.getHobby().contains("여행") ? "checked" : "" %>><label for="hobby4">여행</label><br />
						

					</td>
				</tr>
			</table>
			<input type="button" value="정보수정" onclick="fn_update_member()">
			<button type="button" onclick="fn_update_password();">비밀번호변경</button>
			<input type="button" value="탈퇴" onclick="fn_delete_member();">
		</form>
	</section>
	
	<script>
	
		
		$("#password_2").blur( (e) => {
			
			const pw = $("#password_");
			const pwck = $("#password_2");
			
			if(pw.val() != pwck.val()) {
				
				alert("비밀번호가 일치하지 않습니다");
				pwck.focus();
			}
			
		} );
		
		
		
		
		const fn_update_validate = () => {
			
			// 유효성 검사 
			
		}
		
		
		
		
		// '정보수정' 버튼을 클릭했을 때 실행하는 함수
		const fn_update_member = () => {
			
			// submit할 form태그의 action속성을 함수 안에서 설정해줌 
			
			const frm = $("#memberFrm");
			
			// form태그의 action값을 동적으로 설정 
			frm.attr("action", "<%=request.getContextPath()%>/updateMember.do");
			
			frm.submit();
		}
		
		
		
		const fn_delete_member = () => {
			
			if(confirm("정말로 탈퇴하시겠습니까?")) {
				
				location.assign('<%=request.getContextPath()%>/memberdelete.do?userId=' + '<%=m.getMemberId()%>');
				
			/* 	alert("탈퇴 성공"); */
				
			} else {
				
				alert("탈퇴 실패");
			}
			
		}
		
		const fn_update_password = () => {
			
			
			const url = "<%=request.getContextPath()%>/updatePassword.do?userId=<%=m.getMemberId()%>";
			const name = "updatePasswordPage"; // 대신 _blank 써도 됌 (default)
			const spec = "width=400px, height=210px, left=500px, top=200px";
			
			window.open(url, name, spec);
		}
		
		
	
	</script>
	
	
<%@ include file="/views/common/footer.jsp" %>