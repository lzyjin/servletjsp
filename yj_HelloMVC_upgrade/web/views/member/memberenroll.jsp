<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>

<section id=enroll-container>

        <h2>회원 가입 정보 입력</h2>
        
        <form name="memberEnrollFrm" action="${ pageContext.request.contextPath }/memberenrollend.do" method="post" onsubmit="return fn_enroll_validate();" > <!-- 폼은 페이지 전송용  -->
        <table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" placeholder="4글자이상" name="userId" id="userId_" required>
					
					<!-- 폼안에 버튼이 있으면 반드시 제출이 되버리므로 input타입버튼으로 해야한다  -->
					<input type="button" value="중복검사" onclick="fn_id_duplicate();">
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
    
    
    <!-- 0503 -->
    <form action="" name="checkDuplicateId">
    
    	<input type="hidden" name="userId">
    
    
    </form>
    
    
    
    
    <script>

		
    
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
	
	const fn_id_duplicate = () => {
		
		// alert("열기"); // 이벤트 연결 확인용
		
		// 1. 연결할 주소
		const url = "${ pageContext.request.contextPath }/checkDuplicateId"; /* 아이디 입력하면 db에 있는지 확인하는 코드가 이 안에 있어야 함  */
		
		// 2. 열 창의 이름 ( 이 이름으로 창을 지칭 )
		const title = "checkDuplicateIdPage";
		
		// 3. 창의 크기와 위치 
		const status = "left=500px, top=100px, width=300px, height=200px";
		
		
		window.open("", title, status); // 확인용 
		
		
		// form안에 있는 hidden인 input값을 채우고, 윈도우(새 창)에서 그 결과를 받는 로직으로 설정 
		
		// form 태그는 name속성을 사용하면 그 name속성으로 직접접근이 가능하다 
		console.log(checkDuplicateId);
		
		// form의 name속성값으로 필요한 데이터 세팅하기 
		checkDuplicateId.userId.value = $("#userId_").val(); // 직접접근 가능해서 
		
		checkDuplicateId.method = "post";
		
		checkDuplicateId.action = url;
		
		// 여기서 말하는 form = checkDuplicateId
		
		// 이 form태그가 제출되는 윈도우를 지정 
		checkDuplicateId.target = title;
		
		
		// 제출 ( 전송 )
		checkDuplicateId.submit();
	}
    

    
    </script>

<%@ include file="/views/common/footer.jsp"%>


