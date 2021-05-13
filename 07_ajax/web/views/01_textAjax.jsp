<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<h2>서버에 있는 텍스트파일의 내용을 읽어오기</h2>
	<button id="testAjax">실행하기</button>
	<div id="area"></div>
	
	<script>
		
		$(function() {
			
			$("#testAjax").click( e => {
				
				$.ajax( {
					
					url : "sample.txt", // 요청할 주소 
					
					type : "get", // 요청방식 ( get, post )
					
					// data : , // parameter 서버에 전송하는 값 -> js 객체로 대입한다
					
					dataType : "text", // 응답받은 데이터의 타입을 지정, 지정하지 않아도 자동으로 설정된다
					
					
					success : data => {  // 서버와 통신이 정상적으로 완료된 후 실행되는 함수, -> 매개변수에 서버가 보낸값이 들어온다
						console.log(data);
						$("#area").html(data);
					}, 
					
					error : (r,e,m) => {
						console.log(r);
						console.log(e);
						console.log(m);
						
					}, // 서버와 통신이 정상적으로 완료되지 않았을 때 실행되는 함수
					
				} );
				
			});
			
		});
		
	</script>
</body>
</html>