<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>

	<h3>날짜를 간편하게 표현해보자</h3>
	
	<c:set var="today" value="<%=  new java.util.Date() %>"/>
	<c:out value="${ today }"/> <!-- Thu Jun 10 09:12:34 KST 2021 -->

	<p>
		fmt:formatDate 태그를 이용해서 날짜에 대해 시간, 일자 등을 원하는 방식대로 출력할 수 있다
		type속성을 이용해서 시간과 날짜를 나누어서 출력할 수 있다 
	</p>

	
	
	
	

	<h4>type="time"</h4>
	<fmt:formatDate type="time" value="${ today }"/> 
	<!-- 오전 9:15:31 -->
	
	
	<h4>type="date"</h4>
	<fmt:formatDate type="date" value="${ today }"/> 
	<!-- 2021. 6. 10. -->

	
	<h4>기본 날짜 포맷 출력 : type="both" </h4>
	<fmt:formatDate type="both" value="${ today }"/> 
	<!-- 2021. 6. 10. 오전 9:17:13 -->





	<h4>dateStyle 속성</h4>
	
	<p>
		dateStyle 속성값으로는 default, short, medium, long, full 이 있다. 
	</p>




	<p>default : <fmt:formatDate dateStyle="default" value="${ today }"/></p>
	<!-- 2021. 6. 10. -->

	<p>default : <fmt:formatDate type="both" dateStyle="default" value="${ today }"/></p>
	<!-- 2021. 6. 10. 오전 9:21:35 -->

	<p>short : <fmt:formatDate dateStyle="short" value="${ today }"/></p>
	<!-- 21. 6. 10. -->
	
	<p>medium : <fmt:formatDate dateStyle="medium" value="${ today }"/></p>
	<!-- 2021. 6. 10.   :   default랑 똑같네 -->

	<p>long : <fmt:formatDate dateStyle="long" value="${ today }"/>
	<!-- 2021년 6월 10일 -->
	
	<p>full : <fmt:formatDate dateStyle="full" value="${ today }"/>
	<!-- 2021년 6월 10일 목요일 -->




	<h4>timeStyle 속성</h4>
	<p>
		timeStyle 속성값으로는 short, medium, long, full
	</p>

	<p>short : <fmt:formatDate timeStyle="short" type="time" value="${ today }"/></p>
	<!-- 오전 9:27 -->

	<p>medium : <fmt:formatDate timeStyle="medium" type="time" value="${ today }"/></p>
	<!-- 오전 9:28:10  : default이다 -->

	<p>long : <fmt:formatDate timeStyle="long" type="time" value="${ today }"/></p>
	<!-- 오전 9시 28분 48초 KST -->

	<p>full : <fmt:formatDate timeStyle="full" type="time" value="${ today }"/></p>
	<!-- 오전 9시 29분 32초 대한민국 표준시 -->


	<p>이렇게 쓰려면 type="both"여야한다</p>
	<p>combine(short, short) : <fmt:formatDate dateStyle="short" timeStyle="short" type="both" value="${ today }"/></p>
	<!-- 21. 6. 10. 오전 9:30 -->

	<p>combine(long, short) : <fmt:formatDate dateStyle="long" timeStyle="short" type="both" value="${ today }"/></p>
	<!-- 2021년 6월 10일 오전 9:31 -->



	<h4>패턴으로 날짜 표현하기</h4>
	<p>
		SimpleDateFormat에서 사용한 format으로 패턴을 지정한다
		y : 년
		M : 월 
		d : 일
		h : 시
		m : 분 
		s : 초
		sss : 밀리세컨초
		E : 요일
		
		pattern 속성에 넣으면 된다
	</p>


	<p><fmt:formatDate value="${ today }" pattern="yy/MM/dd"/></p>
	<!-- 21/06/10 -->

	<p><fmt:formatDate value="${ today }" pattern="hh:mm:ss"/></p>
	<!-- 09:35:37 -->

	<p><fmt:formatDate value="${ today }" pattern="yy/MM/dd hh:mm:ss:sss"/></p>
	<!-- 21/06/10 09:35:59:059 -->

	<p><fmt:formatDate value="${ today }" pattern="yyyy-MM-dd (E) hh:mm:ss"/></p>
	<!-- 2021-06-10 (목) 09:36:48 -->



	<h4>timeZone 속성</h4>
	<p>
		날짜를 글로벌하게 표현할 수 있다
		속성값 : GMT
	</p>

	<p>
		<fmt:timeZone value="GMT">
			<fmt:formatDate type="time" timeStyle="full" value="${ today }"/>
		</fmt:timeZone>
	</p>
	<!-- 오전 12시 42분 3초 그리니치 표준시 -->


	<p>
		<fmt:timeZone value="GMT+9">
			<fmt:formatDate type="time" timeStyle="full" value="${ today }"/>
		</fmt:timeZone>
	</p>
	<!-- 오전 9시 43분 0초 GMT+09:00 -->



	<h4>로케일 변경하기</h4>

	<%-- <fmt:setLocale value="ja_JP"/> --%>
	<%-- <fmt:setLocale value="en_US"/> --%>
	<fmt:setLocale value="fr_FR"/>
	
	<fmt:formatDate dateStyle="full" timeStyle="full" type="both" value="${ today }"/>
	<!-- 2021年6月10日木曜日 9時46分12秒 韓国標準時 -->
	<!--Thursday, June 10, 2021 at 9:49:05 AM Korean Standard Time -->
	<!-- jeudi 10 juin 2021 à 09:50:01 heure normale de la Corée -->

	<fmt:formatNumber value="1000000000" type="currency"/>
	<!-- ￥1,000,000,000 -->
	<!-- $1,000,000,000.00 -->
	<!-- 1 000 000 000,00 € -->
	

</body>
</html>