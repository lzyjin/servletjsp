<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/views/common/header.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	/* Member m = (Member)request.getAttribute("member"); */
%>

	<!--  mapping 주소 : /memverView.do -->

<style>
		section#enroll-container input[readonly]{
		
			background-color : lightgray;
		}
</style>

	<section id=enroll-container>
	
			<h2>회원 정보 수정</h2>
			<form action="" id="memberFrm" method="post" onsubmit="return fn_update_validate()">
			<!-- post방식으로 한글을 전송하면 깨진다  => 서블릿에서 인코딩설정 필요 -->
				<table>
					<tr>
						<th>아이디</th>
						<td>
							<input type="text" name="userId" id="userId_" value="${ requestScope.member.userid }" readonly>
							<!-- 아이디는 바뀌면 안된다  -> disabled ? readonly? -> disabled : 서버에 데이터를 전송하지 않음, readonly는 보냄 -> readonly 사용  -->
						</td>
					</tr>
					
					<tr>
						<th>이름</th>
						<td>	
						<input type="text"  name="userName" id="userName" required value="${ requestScope.member.username }" required="required"><br>
						</td>
					</tr>
					<tr>
						<th>나이</th>
						<td>	
						<input type="number" name="age" id="age" value="${ requestScope.member.age }"><br>
						</td>
					</tr> 
					<tr>
						<th>이메일</th>
						<td>	
							<input type="email" placeholder="abc@xyz.com" name="email" id="email" value="${ requestScope.member.email }" required="required"><br>
						</td>
					</tr>
					<tr>
						<th>휴대폰</th>
						<td>	
							<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" value="${ requestScope.member.phone }"><br>
						</td>
					</tr>
					<tr>
						<th>주소</th>
						<td>	
							<input type="text" placeholder="" name="address" id="address" value="${ requestScope.member.address }"><br>
						</td>
					</tr>
					<tr>
						<th>성별 </th>
						<td>
							<!-- DB정보에 따라 분기처리할것 -->
							
							<%-- <% if(m.getGender().equals("M")) {  %>
							
								<input type="radio" name="gender" id="gender0" value="M" checked>
								<label for="gender0">남</label>
								<input type="radio" name="gender" id="gender1" value="F">
								<label for="gender1">여</label>
								
							<% } else { %>
							
								<input type="radio" name="gender" id="gender0" value="M" >
								<label for="gender0">남</label>
								<input type="radio" name="gender" id="gender1" value="F" checked>
								<label for="gender1">여</label>
							
							<% } %>	 --%>
						
							<input type="radio" name="gender" id="gender0" value="M" ${ requestScope.member.gender eq 'M'  ? 'checked' : '' } >
							<label for="gender0">남</label>
							
							<input type="radio" name="gender" id="gender1" value="F" ${ requestScope.member.gender eq 'F'  ? 'checked' : '' }>
							<label for="gender1">여</label>
							
							
						</td>
					</tr>
					<tr>
						<th>취미 </th>
						<td>
		
							<input type="checkbox" name="hobby" id="hobby0" value="운동" ${ fn:contains(requestScope.member.hobby, "운동") ? "checked" : "" } ><label for="hobby0">운동</label>
							<input type="checkbox" name="hobby" id="hobby1" value="등산" ${ fn:contains(requestScope.member.hobby, "등산") ? "checked" : "" } ><label for="hobby1">등산</label>
							<input type="checkbox" name="hobby" id="hobby2" value="독서" ${ fn:contains(requestScope.member.hobby, "독서") ? "checked" : "" } ><label for="hobby2">독서</label><br />
							<input type="checkbox" name="hobby" id="hobby3" value="게임" ${ fn:contains(requestScope.member.hobby, "게임") ? "checked" : "" } ><label for="hobby3">게임</label>
							<input type="checkbox" name="hobby" id="hobby4" value="여행" ${ fn:contains(requestScope.member.hobby, "여행") ? "checked" : "" } ><label for="hobby4">여행</label><br />
							
						</td>
					</tr>
				</table>
				
				<input type="button" value="정보수정" onclick="fn_update_member();">
				<!-- <button>정보수정 btn</button> --> <!-- form안에 있는 button태그는 submit기능을 한다 -> submit기능 없이 하려면 button태그 안에 type=button적어야함  -->
				
				<button type="button" onclick="fn_password_update();">비밀번호변경</button>
				
				<input type="button" value="탈퇴" onclick="fn_delete_member();"> 
			</form>
		</section>
		
		<form name="updatePassword">
			
			<input type="hidden" name="userId">
		
		</form>
		
	<script>
	
		// 비밀번호 확인
		$("#password_2").blur( (e) => {
			
			const pw = $("#password_").val();
			const pwck = $("#password_2").val();
			
			if(pw != pwck) {
				
				alert("비밀번호가 일치하지 않습니다");
				$("#password_").focus();
				
			} 
		} );
		
		const fn_update_validate = () => {
			
			// 정규표현식 .. 
			
			return true;
		}
		
		const fn_update_member = () => {
			
			// 폼 가져오기 
			
			const frm = $("#memberFrm");
			
					console.log(frm);
			
			// 폼태그의 action속성이 설정되어있지 않아서 설정하고, submit함수를 호출 
			
			// action값이 설정되어있지 않으면 함수로 동적으로 form으로 전송되는 servlet을 고정할 수 있다. 
			frm.attr("action", "<%=request.getContextPath()%>/memberUpdate.do");
			
			frm.submit(); //form태그의 onsubmit="" 을 실행시키는 함수 
			
		}
		
		const fn_delete_member = () => {
			
			// 회원 탈퇴 
			if(confirm("정말 탈퇴하시겠습니까?")) {
				
				// 탈퇴 
				
				<%-- const frm = $("#memberFrm");
				
				frm.attr("action", "<%=request.getContextPath()%>/memberDelete.do");
				
				frm.submit();
				 --%>
				
				
				
				// 선생님 풀이 
				
				location.replace("<%=request.getContextPath()%>/deleteMember?userId=" + "<%=loginMember.getUserid()%>");
				/* 쿼리스트링쓰는 경우 표현식을 통해 작성한 문자열은 ""로 꼭 감싸야함. 그렇지 않으면 변수로 인식해서 에러  */
				/* loginMember 변수 사용 가능한 이유 : header에서 선언했기 때문에 include한 이 jsp에서도 사용 가능하다 */
				/* id만 필요하고 굳이 form을 넘길 필요가 없어서 이렇게 한다 */
				
			
			}
		
		}
		
		
		const fn_password_update = () => {
				
				<%--
				const url = "<%=request.getContextPath()%>/updatePassword.do?userId=" + "<%=loginMember.getMemberId()%>";
				
				const name = "updatePasswordPage";
				
				// 새창의 크기 : width 400, height=210 left 500 top 200
				
				const spec = "width=400, height=210, left=500, top=200";
				
				window.open("", name, spec);
				
				
				//hidden form태그 만들어서 거기에 비밀번호 담아서 보냄 
				
				// form태그 name = updatePassword
				updatePassword.userId = "<%=m.getMemberId()%>";
				
				updatePassword.method = "post";
				
				updatePassword.action = url;
				
				updatePassword.target = name;
				
				updatePassword.submit();
				--%>
				
				
				// 선생님 풀이 
				const url = "<%=request.getContextPath()%>/updatePassword.do?userId=${ requestScope.member.userid }";
				
				const name = "updatePasswordPage";
				const spec = "width=400, height=210, left=500, top=200";
				
				window.open(url,"_blank", name, spec);
		}
		
	</script>	
		
		

<%@ include file="/views/common/footer.jsp" %>