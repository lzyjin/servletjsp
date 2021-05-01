<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>

<section id=enroll-container>

        <h2>회원 가입 정보 입력</h2>
        
        <form action="<%=request.getContextPath()%>/memberenrollend.do" method="post" onsubmit="return fn_enroll_validate();" > <!-- 폼은 페이지 전송용  -->
        <table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" placeholder="4글자이상" name="userId" id="userId_" required>
				</td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td>
					<input type="password" name="password" id="password_" required><br>
				</td>
			</tr>
			<tr>
				<th>패스워드확인</th>
				<td>	
					<input type="password" id="password_2" required><br>
				</td>
			</tr>  
			<tr>
				<th>이름</th>
				<td>	
				<input type="text"  name="userName" id="userName" ><br>
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
					<input type="radio" name="gender" value="M" id="gender0"  >
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
    
    <script>

		/*
    
    const fn_enroll_validate = () => {
    	
    	String pw = $("#password_").val();
		String pw2 = $("#password_2").val();

		if(pw != pw2) {

			alert("비밀번호를 다시 확인해주세요");
			return false;
		}
		
		String id = $("#userId_").val();

		if(id.trim().length < 4) {

			alert("아이디는 4글자 이상 입력해야합니다");
			return false;
		} 

    } */
    
    // 선생님 풀이 
    $(function() {
    
		// 패스워드와 패스워드확인이 같은지 확인 

    	$("#password_2").blur( (e) => {
    		
    		const pwck = $(e.target).val();
    		const pw = $("#password_").val();

			if(pwck != pw) {

				alert("패스워드가 일치하지 않습니다");
				$("#password_").focus();
			}
    		
    	} );    	
    	
    });

	// 아이디가 4글자 미만이면 전송 금지 
	const fn_enroll_validate = () => {
		
		const userId = $("#userId_");

		alert("test");
		
		if(userId.val().length < 4) {

			alert("아이디는 4글자 이상 입력해야합니다");
			userId.focus();
			return false;
		} 
		
		

	}
    

    
    </script>

<%@ include file="/views/common/footer.jsp"%>


