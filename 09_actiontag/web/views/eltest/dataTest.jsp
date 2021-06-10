<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	/* List<Person> list = (List<Person>)request.getAttribute("list"); */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>데이터 확인하기</title>
</head>

<body>

	<h2>서블릿에서 보낸 리스트</h2>
	
	<h4>${ list }</h4>
	<h5>${ list.get(0).name }</h5>
	
	<%-- <c:forEach var = "p" items="${ list }">
		
	</c:forEach> --%>
	
	<h5>${ p }</h5>
	
	
	
	<h2>session값</h2>
	<h5>request : ${ name }</h5> <!-- key값이 겹쳐서 request의 name키값을 가져와버렸다 -->
	<h5>session : <%=session.getAttribute("name") %></h5>

	
	<!-- 
	
		EL의 자주쓰는 공유객체 : request, session, context
		순회 -> 키값을 찾고 반환 
		범위가 넓은 순부터 좁은 순으로 : context, session, request, pageContext
		
		범위가 좁은것부터 순서대로 찾는다 -> session에 name이 있으면 request의 name을 찾지 않고 중단한다
		
		해결방법 : pageScope, requestScope, sessionScope, applicationScope 사용 
		
	-->
	
	<h5>request : ${requestScope.name }</h5>
	<h5>session : ${sessionScope.name }</h5>
	
	
	
	<h2>context값</h2>
	<h5>${ email }</h5>
	
	
</body>
</html>