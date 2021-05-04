<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	
%>

<%@ include file="/views/common/header.jsp" %>

	<section id="enroll-container">

        <h2>회원 가입 정보 입력</h2>
        
        <form name="enrollForm" action="<%=request.getContextPath()%>/enrollend.do" method="post" onsubmit="return fn_enroll_validate();" > <!-- 폼은 페이지 전송용  -->
        
	        <table>
	        
				<tr>
					<th>아이디</th>
					<td>
					
						
					
						<input type="text" placeholder="4글자이상" name="userId_" id="userId_" required>
						
						<%-- <input type="button" value="아이디 중복 확인" onclick="location.assign('<%=request.getContextPath()%>/idcheck.do')"> --%>
						<!-- <input type="button" value="아이디 중복 확인" onclick="window.open('idcheck.jsp', 'pop', 'width=500', 'height=500')"> -->
						<input type="button" value="아이디 중복 확인" onclick="fn_idcheck();">
					
					</td>
				</tr>
				
				<tr>
					<th>패스워드</th>
					<td>
						<input type="password" name="password_" id="password_" required><br>
					</td>
				</tr>
				
				<tr>
					<th>패스워드확인</th>
					<td>	
						<input type="password_" id="password_2" required><br>
					</td>
				</tr>  
				
				<tr>
					<th>이름</th>
					<td>	
					<input type="text"  name="userName" id="userName" required><br>
					</td>
				</tr>
				
				<tr>
					<th>나이</th>
					<td>	
					<input type="number" name="age" id="age"><br>
					</td>
				</tr> 
				
				<tr>
					<th>이메일</th>
					<td>	
						<input type="email" placeholder="abc@xyz.com" name="email" id="email" required><br>
					</td>
				</tr>
				
				<tr>
					<th>휴대폰</th>
					<td>	
						<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" required><br>
					</td>
				</tr>
				
				<tr>
					<th>주소</th>
					<td>	
						<input type="text" placeholder="" name="address" id="address"><br>
					</td>
				</tr>
				
				<tr>
					<th>성별 </th>
					<td>
						<input type="radio" name="gender" value="M" id="gender0">
						<label for="gender0">남</label>
						<input type="radio" name="gender" value="F" id="gender1">
						<label for="gender1">여</label>
					</td>
				</tr>
				
				<tr>
					<th>취미 </th>
					<td>
						<input type="checkbox" name="hobby" id="hobby0" value="운동"><label for="hobby0">운동</label>
						<input type="checkbox" name="hobby" id="hobby1" value="등산"><label for="hobby1">등산</label>
						<input type="checkbox" name="hobby" id="hobby2" value="독서"><label for="hobby2">독서</label><br />
						<input type="checkbox" name="hobby" id="hobby3" value="게임"><label for="hobby3">게임</label>
						<input type="checkbox" name="hobby" id="hobby4" value="여행"><label for="hobby4">여행</label><br />
					</td>
				</tr>
				
			</table>
			
			<input type="submit" value="가입" >
			<input type="reset" value="취소">
			
        </form>
        
    </section>

	
	<form name="idcheck">
		
		<!-- 사용자가 입력한 아이디를 받아올 폼 -->
		<input type="hidden" name="hiddenuserId" id="" >

	</form>

	<script>
		   
			  const fn_enroll_validate = () => {

					// 아이디는 4글자 이상
					const userId = $("input#userId_");
					
					if(userId.val().trim().length < 4) {

						alert("아이디를 4글자 이상 작성해주세요");

						userId.focus();

						return false;
					}
					

					// 패스워드도 4글자 이상 
					const password = $("#password_");
					
					if(password.val().trim().length < 4) {

						alert("비밀번호를 4글자 이상 작성해주세요");
						password.focus();

						return false;
					}

				}
				
				// 패스워드 확인
				$( function() {
					
					$("#password_2").blur( (e) => {
						
						if($("#password_").val() != $(e.target).val()) {

							alert("비밀번호가 일치하지 않습니다");
							$("#password_").focus();
							
						}
						
					});
					
				} );
				
				
				const fn_idcheck = () => {
					
					// window.open(url, name, features, replace);
					
					// 회원가입창에서 입력한 아이디를 받아와서 검사해야함
					

					const url = "<%=request.getContextPath()%>/idcheck.do";
					const name = "idcheck_page";
					const features = "width=300, height=300, left=500, top=100";
					
					window.open(url,name, features);
					
					
					
					// hidden속성의 input태그를 가진 form태그를 이용하는 방법 
					// 이 form태그안에 있는 input에 사용자가 입력한 아이디를 넣는다
					
					idcheck.hiddenuserId.value = $("#userId_").val();
					idcheck.method = "post";
					idcheck.action = url;

					// 이  form이 제출(submit)되는 윈도우 지정하기 
					// = form의 정보를 가지고 servlet에서 이용할 것 
					// = 팝업창의 주소 = 서블릿의 주소 
					idcheck.target = name;
					
					// 제출
					idcheck.submit();
					
				}			  
		
	</script>
    
    

<%@ include file="/views/common/footer.jsp"%>