<%@page import="com.jspaction.model.vo.Person"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List, 
				java.util.ArrayList, 
				com.jspaction.model.vo.Person,
				java.util.Arrays" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL표현식 활용하기</title>
</head>

<body>

	<%
		String name = "유병승";
		int age = 19;
		
		// 이 과정이 있어야만 EL로 값에 접근 가능
		request.setAttribute("name", name);
		session.setAttribute("age", age);
	%>
	
	<h3>EL을 출력해보자</h3>
	
	<p>
		EL로 작성한 명칭은 기본적으로 내장객체의 키값으로 인식한다
		즉, 일반 변수로 선언한 변수의 값은 불러올 수 없다. 
	</p>
	
	<h2>${ name } ${ age }</h2>
	
	
	
	
	<h2>EL을 이용해서 연산처리하기</h2>
	<p>
		EL표현식 내부에는 산술, 논리, 비교, 삼항연산을 처리할 수 있다
	</p>
	
	<%
		request.setAttribute("num", 20);
		request.setAttribute("num2", 30);
		request.setAttribute("userId", "admin");
		
		List<String> list = Arrays.asList(new String[]{"1", "2", "3"});
		// List.of() : 선언과 동시에 초기화 가능
		
		request.setAttribute("list", list);
		
		Person p = new Person("양호준", 27, "서울시");
		request.setAttribute("p", p);
		
		List<Person> persons = Arrays.asList(new Person[]{ new Person("김상현", 29, "구로구로"),
															new Person("김예진", 26, "강남강남")
														  });
		request.setAttribute("persons", persons);
	%>
	
	
	
	
	<h4>산술연산처리</h4>
	
	<p>num + num2 : ${ num + num2 }</p>
	<p>num - num2 : ${ num - num2 }</p>
	<p>num * 3 : ${ num * 3 }</p>
	<p>num / 3 : ${ num / 3 } ${ num div 3 }</p>
	<p>num2 % 7 : ${ num2 % 7 } ${ num2 mod 7 }</p>
	<p>(age + num) * num2 / 4 + age : ${ (age + num) * num2 div 4 + age }</p>





	<h4>비교연산처리</h4>
	
	<p>num &lt; num2 : ${ num < num2 }</p>
	<p>num &gt; num2 : ${ num > num2 }</p>	
	<p>num &lt; num2 : ${ num lt num2 }</p>
	<p>num &gt; num2 : ${ num gt num2 }</p>

	<p>num &lt;= num2 : ${ num <= num2 }</p>
	<p>num &gt;= num2 : ${ num >= num2 }</p>
	<p>num &lt;= num2 : ${ num le num2 }</p>
	<p>num &gt;= num2 : ${ num ge num2 }</p>
	
	<p>"admin" == userId : ${ "admin" == userId } ${ "admin" eq userId }</p>

	<p>"admin" != userId : ${ "admin" != userId } ${ "admin" ne userId }</p>

	<p>list == null : ${ list == null } ${  empty list }</p> <!-- list.isEmpty() -->
	<p>list != null : ${ list != null } ${ not empty list }</p>
	
	<p> age>19 && userId = "admin" : %{ age>19 && userId = "admin"}</p>
	<p> age>19 && userId = "admin" : %{ age>19 and userId = "admin"}</p>
	
	<p> age>19 || userId = "admin" : %{ age>19 || userId = "admin"}</p>
	<p> age>19 || userId = "admin" : %{ age>19 or userId = "admin"}</p>






	<h4>삼항연산자 사용하기</h4>
	
	<p>${ age > 19 ? "성인입니다" : "미성년입니다" }</p>
	
	
	
	
	
	
	<h4>메소드 호출하기</h4>
	
	<p>${ list.size() }</p>
	
	
	
	
	
	<h4>객체 내용 출력하기</h4>
	
	<p>Person정보 출력하기 : ${ p.name }</p>
	<!-- 
		조건 : getter에 대한 내용을 준수해야한다, 
		name으로 된 getter를 찾아 불러옴 
		즉, getName을 찾는다
	-->
	<p>List 정보 출력하기 : ${ list.get(0) }</p>
	<p>persons 정보 출력 : ${ persons.get(0).name } ${ persons.get(0).getName() }</p>
	
	
	
	
	<h4>서블릿에서 저장한 객체를 EL로 불러오기</h4>
	<a href="<%=request.getContextPath()%>/basicEl">서블릿에서 저장한 데이터 출력</a>
	<a href="${ pageContext.request.contextPath }/basicEl">서블릿에서 저장한 데이터 출력</a>
	
	
	
	<h3>파라미터로 전송한 값 받아오기</h3>
	
	<form action="02_parameter.jsp">
	
		이름 : <input type="text" name="name"><br>
		나이 : <input type="text" name="age"><br>
		취미 : 
		<label><input type="checkbox" name="hobby" value="등산">등산</label>
		<label><input type="checkbox" name="hobby" value="게임">게임</label>
		<label><input type="checkbox" name="hobby" value="골프">골프</label>
		<input type="submit" value="전송">
		
	</form>
	
	<!-- 210609 추가한 코드 -->
	<h3>파라미터로 전송한 값을 c:forEach문으로 받아오기</h3>
	<form action="${ pageContext.request.contextPath }/views/jstl/03_foreach.jsp">
	
		이름 : <input type="text" name="name"><br>
		나이 : <input type="text" name="age"><br>
		취미 : 
		<label><input type="checkbox" name="hobby" value="등산">등산</label>
		<label><input type="checkbox" name="hobby" value="게임">게임</label>
		<label><input type="checkbox" name="hobby" value="골프">골프</label>
		<input type="submit" value="전송">
		
	</form>
	
</body>
</html>