<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 jsp 첫 페이지</title>
</head>

<body>
	<h1>안녕 나의 첫 jsp 패이지</h1>
	
	<h2>jsp태그에 대해 알아보자</h2>
	
	<p>
		jsp태그는 html문서에서 자바 코드를 사용할 수 있게 하는 태그로
		&lt;%&gt; : 태그에 %가 있으면 자바코드로 인식<br>
		&lt;%&gt; : 기본적인 자바코드를 작성할때는 스크립트립태그를 이용해서 작성<br>
		 예) 지역변수, 조건문, 반복문, 매소드 호출(get/set)을 사용할때 사용!<br>
		&lt;%! &gt; : jsp로 생성되는 클래스의 멤버변수, 메소드 등을 선언할때는 지시자태그를 이용<br>
		&lt;%=&gt; : 자바코드로 작성된 변수를 html화면에 출력하려면 표현식태그<br>
	</p>
	
	
	
	<h3>선언문 태그 이용하기</h3>
	<p>멤버변수(필드), 메소드, 생성자 선언</p>
	
	  
	<%!
		//클래스선언부에 작성되는 코드를 작성할 수 있다.
		
		String name="유병승";//default 접근제한자를 갖는 멤버변수
		
		private int age=19;
		
		/* if(name.equals("유병승")){
			System.out.println("안녕");
		} 
		클래스선언부에 들어가는 코드로 조건문, 반복문을 사용할 수 없다!*/
		
		/* for(int i=0;i<10;i++){
			
		} */
		
		// public void test(){
		public String test(){
			
			System.out.println("하하 호호");
			
			return "점심은 맛있어!";
		}
		
	%>
	
	<h3>선언된 자바코드 화면에 출력하기 : 표현식</h3>
	
	<h4>당신의 이름은 <%=name %></h4> <!-- 당신의 이름은 유병승 -->
	<h4>당신의 나이는 <%=age %></h4> <!-- 당신의 나이는 19 -->
	<h4>메소드 실행 <%=test()%></h4>  <!-- 메소드 실행 점심은 맛있어! -->
	
	
	
	
	<h3>스크립트립 태그 이용하기</h3>
	
	<p>지역변수 선언, 조건문, 반복문 사용 가능</p>
	
	<%
		String addr = "경기도 시흥시";
	
		// 지역변수라서 접근제한자 사용 불가
		
		// private double height = 180.5;
		
		String[] names = {"유병승", "김상현", "김두호", "김지한"};
		
	%>
	
	<ul>
	
		<% for(int i=0; i < names.length; i++) { 
			
			if(names[i].equals(name)) { %>
			
			<li><%= names[i] %></li>
			
			<%}
			
		 } %>
		
	</ul>
	
	
	<% if(age > 18) { %>
		
		<h1>당신은 18세 이상이네요</h1>
		
	<% } else { %>
	
		<h1>미성년이네요</h1>
		
	 <% } %>
	 
	 <a href="views/innerObj.jsp">innerObj.jsp</a>
	 
	 
	 
	 <h2>내장 객체에 있는 값 출력하기</h2>
	 <p>내장객체 종류 : request, response, out, session, application, page, exception</p>
	 
	 <%
	 
	 	String[] hobby = (String[])request.getAttribute("hobby"); // Object타입으로 리턴하므로 형변환 필요
	 	String userId = (String)session.getAttribute("userId");
	 	String adminEmail = (String)application.getAttribute("email");
	 	
	 %>
	 
	 
	 
	 <% if(hobby != null) { %>
	 
	 <h2>당신의 취미는</h2>
	 
	 <ul>
	 	<% for(String h : hobby) { %>
	 	
	 	<li><%=h %></li>
	 	<% } %>
	 </ul>
	 <% } %>
	 
	 
	 
	 <h2>session저장값은 <%=userId %></h2>
	 <h2>application저장값은 <%=adminEmail %></h2>
	 
	
</body>
</html>