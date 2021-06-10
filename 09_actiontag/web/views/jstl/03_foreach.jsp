<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.jspaction.model.vo.Person"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>반복문 활용하기</title>
</head>

<body>

	<h3>반복문 : c:forEach태그를 이용</h3>
	
	<p>두가지 방법으로 사용 가능</p>
	
	<p>1. 기본 for문으로 사용 가능</p>
	<p>2. forEach문처럼 사용 ( 배열이나 collection을 iterating하는것처럼 )</p>



	<ul>
		c:forEach 의 속성
		
		<li>begin : 초기값(시작하는 값) *기본for문</li>
		<li>end : 마지막값 ( 끝내는 값 ) *기본for문</li>
		<li>step : 증감식 ( 초기값에서 step값만큼 증가 또는 감소 ) *기본for문</li>
		
		<li>var : 배열이나 컬렉션에 있는 값을 순차적으로 보관하는 변수</li>
		<!-- for(String s : names) { 에서 s가 var  -->
		
		<li>items : 배열이나 컬렉션을 선언 *EL표현식으로 가져온다</li>
		
		<li>varStatus : index, 반복수, 시작값, 마지막값을 저장한 객</li>
	</ul>




	
	<h3>기본 반복문 활용하기</h3>
	
	<h4>1부터 10까지 출력하기</h4>
	
	<c:forEach var="i" begin="1" end="10" step="1"><!--  for(int i=1; i <=10; i++) { -->
		<%-- <c:out value="${ i }"/> --%>
		<p>이거 몇번 실행?</p>
	</c:forEach>
	
	<c:forEach var="i" begin="1" end="10" step="2">
		<%-- <c:out value="${ i }"/> --%>
		<h3>이거는?</h3>
	</c:forEach>
	
	
	
	
	<c:forEach var="i" begin="1" end="6"> 
		<h${ i }>이건 어떻게 출력될까???</h${ i }>
	</c:forEach>
	
	
	
	
	<h4>배열, List를 c:forEach태그로 이용하기</h4>
	
	<%
		request.setAttribute("names", new String[]{"유병승", "양호준", "곽승혁", "김두호", "김상현", "김태희"});
		
		List<Person> list = Arrays.asList(new Person[]{ new Person("유병승", 19, "경기도 시흥시"), new Person("김예진", 26, "서울시 강남구"), new Person("김지한", 26, "서울시"), new Person("권설영", 20, "서울시 강남구") });
		request.setAttribute("list", list);
	%>
	
	
	<ul>
		<c:forEach var="n" items="${ names }">
			<li><c:out value="${ n }"/></li>
		</c:forEach>
	</ul>
	
	
	<c:if test="${ not empty list }"> <!-- if(list != null) { -->
		<ul>
			<c:forEach var="p" items="${ list }">
				<li><c:out value="${ p }"/></li> <!-- 출력문 -->
			</c:forEach>
		</ul>
	</c:if>
	
	
	
	<h3>list에 있는 객체를 테이블 형식으로 출력하기</h3>
	
	<c:if test="${ not empty list }">
	
		<table>
		
			<tr>
				<th>이름</th>
				<th>나이</th>
				<th>주소</th>
			</tr>
			
			<c:forEach var="p" items="${ list }">
			
				<tr>
					<td><c:out value="${ p.name }"></c:out></td>
					<td><c:out value="${ p.age }"></c:out></td>
					<td><c:out value="${ p.addr }"></c:out></td>
				</tr>
				
			</c:forEach>
			
		</table>
		
	</c:if>
	
	
	
	
	<c:forEach var="p" items="${ list }" varStatus="vs">
		인덱스 : ${ vs.index }
		반복수 : ${ vs.count }
		첫번째 값 : ${ vs.first }
		마지막 값 : ${ vs.last }
		<br>
	</c:forEach>
	
	
	
	<c:if test="${ not empty list }">
	
		<table>
		
			<tr>
				<th>이름</th>
				<th>나이</th>
				<th>주소</th>
			</tr>
			
			<c:forEach var="p" items="${ list }" varStatus="vs">
			
				<c:if test="${ vs.count < 3 }">
				
					<tr>
						<td><c:out value="${ p.name }"></c:out></td>
						<td><c:out value="${ p.age }"></c:out></td>
						<td><c:out value="${ p.addr }"></c:out></td>
					</tr>
					
				</c:if>
				
			</c:forEach>
			
		</table>
		
	</c:if>
	
	
	
	
	
	<table>
	
		<c:forEach var="p" items="${ list }" varStatus="vs">
		
			<c:if test="${ vs.first }">
			
				<tr>
					<th>이름</th>
					<th>나이</th>
					<th>주소</th>
				</tr>
				
			</c:if>
			
			<tr>
			
				<td><c:out value="${ p.name }"/></td>
				<td><c:out value="${ p.age }"/></td>
				<td><c:out value="${ p.addr }"/></td>
				
			</tr>
			
			<c:if test="${ vs.last }">
			
				<tr>
					<td>총인원수</td>
					<td colspan="2"><c:out value="${ vs.count }"/></td>
				</tr>
				
			</c:if>
			
		</c:forEach>
		
	</table>
	
	
	
	
	
	<h3>파라미터로 받은 값을 c:forEach문으로 출력하기</h3>
	
	<c:forEach var="h" items="${ paramValues.hobby }">
		<c:out value="${ h }"/>
	</c:forEach>
	
	
	
	
	<h4>c:forTokens 활용하기</h4>
	
	<p>
		StringTokenizer 객체와 비슷한 기능 
		문자열을 특정 구분자로 분리하여 반복문으로 출력
	</p>
	
	<ul>
		c:forTokens 속성 
		
		<li>var : 값을 받는 변수</li>
		<li>delims : 분리 기준이 되는 문자</li>
		<li>items : 분리할 문자열</li>
	</ul>
	
	
	<c:set var="hobby" value="코딩,등산,독서,쿵푸,낮잠"/>
	
	<h4><c:out value="${ hobby }"/></h4>
	
	<ul>
		<c:forTokens var="h" items="${ hobby }" delims=",">
		
			<c:if test="${ h eq '등산' or h eq '낮잠' }">
			
				<li><c:out value="${ h }"/></li>
				
			</c:if>
			
		</c:forTokens>
	</ul>
	
	
	
</body>
</html>