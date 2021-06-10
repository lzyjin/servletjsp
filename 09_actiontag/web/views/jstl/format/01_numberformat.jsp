<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숫자 표현하기</title>
</head>

<body>

	<h1>기본 숫자 표현하기</h1>
	
	<c:set var="numtest" value="12345678"/>
	<c:set var="numtest1" value="12340000"/>
	<c:set var="numtest2" value="1"/>
	<c:set var="numtest3" value="123.456"/>
	
	
	<!-- 출력하는 2가지 방법 ( 숫자 그대로를 출력한다 ) -->
	
	<h3>기본 숫자 출력 :  ${ numtest }</h3>
	<h3>기본 숫자 출력 :  <c:out value=" ${ numtest }"/> </h3>
	
	
	
	<h2>fmt:formatNumber 태그를 이용해서 숫자를 출력하기</h2>
	
	<h3><fmt:formatNumber value="${ numtest }"/>원</h3> <!-- 12,345,678원 -->
	<h3><fmt:formatNumber value="12344568799"/>원</h3> <!-- 12,344,568,799 -->
	
	
	
	
	<h2>쉼표가 붙는 이유 : groupingUsed 속성의 default값 때문에 ( default가 true )</h2>
	
	<h3><fmt:formatNumber value="${ numtest }" groupingUsed="true"/></h3> <!-- 12,345,678 -->
	<h3><fmt:formatNumber value="${ numtest }" groupingUsed="false"/></h3> <!-- 12345678 -->
	
	
	
	
	<h2>화폐 단위를 출력할 때는 </h2>
	
	<p>type속성을 이용해서 설정 -> type="currency"</p>
	
	<h3><fmt:formatNumber value="${ numtest }" type="currency"/></h3>
	<!-- ₩12,345,678 -->
	
	<!--  자동으로 ₩가 붙는 이유 : 톰캣이 locail 이라는걸 가지고있어서, 나라를 알 수 있음 -->
	
	<h3>현재 로케일 : ${ pageContext.request.locale }</h3> <!-- ko_KR -->
	<h3><fmt:formatNumber value="${ numtest }" type="currency" currencySymbol="$"/></h3> <!-- $12,345,678 -->
	
	
	
	
	<h2>퍼센트 표시하기</h2>
	<p>
		type속성에 percent값을 대입한다
		value는 0부터 1까지 가능 
		0은 0%, 1은 100% 
		0.1은 10% 
	</p>
	
	<h3><fmt:formatNumber type="percent" value="${ numtest2 }"/></h3> <!-- 100% -->
	
	<h3><fmt:formatNumber type="percent" value="0.05"/></h3> <!-- 5% -->
	
	
	
	
	
	<h2>패턴으로 숫자 표현하기</h2>
	<p>
		pattern 속성을 이용한다
		0 : pattern으로 지정한 자리에 수가 없으면 0으로 표시한다 
		# : pattern으로 지정한 자리에 수가 없으면 출력하지 않는다(공란)
	</p>
	<h3><fmt:formatNumber value="1234.5678" pattern="##,###.00000"/></h3>
	<!-- 1,234.56780 -->
	
	<h3> 0 : ${ numtest3 } -> <fmt:formatNumber value="${ numtest3 }" pattern="0,00.0"/></h3>
	<!-- 0 : 123.456 -> 1,23.5 -->
	
	<h3> # : ${ numtest3 } -> <fmt:formatNumber value="${ numtest3 }" pattern="#,##.######"/></h3>
	
	
	
	
	
	<h2>소수점 처리하기</h2>
	
	<p>
		minFractionDigits : 최소소수점자리
		maxFractionDigits : 최대소수점자리
	</p>
	
	<h3><fmt:formatNumber value="123.13" minFractionDigits="5" pattern="#,###.##"/></h3> <!-- 123.13000 -->
	<h3><fmt:formatNumber value="123.13" minFractionDigits="3" pattern="#,###.##"/></h3> <!-- 123.130 -->
	<h3><fmt:formatNumber value="123.13" minFractionDigits="1" pattern="#,###.##"/></h3> <!-- 123.13 -->
	
	<h3><fmt:formatNumber value="123.12345" maxFractionDigits="3" pattern="#,###.##"/></h3> <!-- 123.123 반올림해서 소수점자리 최대3자리로 맞춤 -->
	
	
	
	<h2>정수자리수 처리하기</h2> <!-- 반올림이 아니라 잘라버림  -->
	
	<p>
		minIntegerDigits : 최소정수자리수 
		maxIntegerDigits : 최대정수자리수 
	</p>
	
	<h3><fmt:formatNumber value="12.13" minIntegerDigits="5" pattern="#,###.##"/></h3> <!-- 00,012.13 -->
	<h3><fmt:formatNumber value="5230.13" maxIntegerDigits="3" pattern="#,###.000"/></h3> <!-- 230.130 -->
	
</body>
</html>