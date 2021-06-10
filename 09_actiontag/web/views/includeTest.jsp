<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// 한글로 된 값을 넘기려면 인코딩처리 필요
	request.setCharacterEncoding("utf-8");
%>
    
<%-- <%@ include file="header.jsp" %> --%>

<%-- <jsp:include page="header.jsp"/> --%>
<!-- 
	jsp:include태그를 이용해서 페이지를 불러오면, 
	불러오는 페이지에 원하는 값을 전송할 수 있다. 
	=> jsp:param태그를 이용
-->
<jsp:include page="header.jsp">
	<jsp:param name="title" value="메인화면"/>
</jsp:include>


<section>
	<h1>여기가 본문 부분</h1>
	<%-- <p>당신의 아이디는 <%=memberId %> 입니다.</p> <%@ include file="header.jsp" %>로 쓸때만 이렇게 사용 가능 --%>
</section>