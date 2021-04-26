<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="javax.servlet.RequestDispatcher"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 사용하기</title>
</head>

<body>

	 <h1>내장 객체 출력하기</h1>
	 
	 <!-- 객체의 주소값이 출력된다 -->
	 
	 <h2><%=request%></h2>
	 <h2><%=session%></h2>
	 <h2><%=application%></h2>
	 <h2><%=response%></h2>
	 
	 <%-- <h2><%=myObj%></h2> --%> 
	<!-- 내장 객체가 아닌 객체를 출력하면 에러난다 -->
	
	<%
		/* response.sendRedirect(request.getContextPath() + "/");   */
		
		// response.sendRedirect("/"); // 안됌 
		
		// response와 request를 불러온적도 없는데 바로 사용할 수 있다
		
		// 주소값이 바뀌면서 ( request가 바뀜 ) 새로운 페이지로 이동
		// url이 
		// http://localhost:9090/05_jsp/index.jsp 에서
		// http://localhost:9090/05_jsp/ 로 바뀜 -> 메인페이지가 뜸 ( 화면이 그대로인것처럼 보임 )
		
		// 위 코드를 주석처리하면 정상적으로 views/innerObj.jsp 로 이동된다
	%>
	
	<%
	
		request.setAttribute("hobby", new String[]{"코딩", "정처기", "운동", "차마시기", "영화"});
		session.setAttribute("userId", "sanghyun");
		application.setAttribute("email", "admin@admin");
	
		
		RequestDispatcher rd = request.getRequestDispatcher("/"); // 메인으로 이동 
		rd.forward(request, response);
		
		
		
	%>
	 
</body>
</html>