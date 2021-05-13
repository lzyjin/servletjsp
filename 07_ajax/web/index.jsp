<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax 알아보기</title>
</head>
<body>

	<h2>자바스크립트를 이용한 ajax 통신</h2>
	<p>js를 통해서 비동기식으로 서버와 통신하는 기술</p>
	
	<button onclick="ajaxTest1();">javascript ajax 테스트 - get방식</button>
	<button onclick="ajaxTest2();">javascript ajax 테스트 - post방식</button>
	
	<div id="target">
	
	</div>
	
	<script>
	
		const ajaxTest1 = () => {
			
			// ajax로 서버와 통신하기 
			
			// 1. XMLHttpRequest 객체 생성하기
			const xhr = new XMLHttpRequest();
			
			// 2. XMLHttpRequest 객체에 값을 설정하기
			// open() 함수 이용해서 통신값을 설정한다
			// open( 통신방식(get || post), 요청주소(url), [동기식 || 비동기식을 설정] ) 세번쨰 매개변수를 생략하면 비동기식으로 설정된다
			
			// 클라이언트가 보낸ㄴ 값을 쿼리스트링으로 전송한다
			
			xhr.open("get", "<%=request.getContextPath()%>/ajaxTest.do?id=admin");
			
			// 3. 요청에 대한 서버 응답을 처리할 함수를 지정한다
			// callback 함수 
			
			
			// 3-1. xhr 객체 내부의 상태를 관리하는 속성
			// 3-1-1. readyState :전송상태관리 0, 1, 2, 3, 4, 
			// 3-1-2. status : 응답결과(코드) 관리 200, 404, 500, 403  
			
			
			
			//xhr객체의 onreadystateonchange 속성에 함수를 대입한다
			xhr.onreadystatechange = () => {
				
				if(xhr.readyState == 4) {
					
					// 통신 완료
					if(xhr.status == 200) {
						
						// 응답메시지를 정상적으로 수신
						// alert("서버와 통신 완료");
						
						// 서버가 응답한 내용은 xhr객체의 responseText 속성에 자동으로 대입된다
						console.log(xhr.responseText);
						
						document.getElementById("target").innerHTML += "<h3>" + xhr.responseText + "</h3>";
				
					}
					
					/* if(xhr.status == 404) {
						
						alert("찾는 페이지가 없어");
					} */
						
				}
				
			}
			
			// 모든 값에 대해 설정한 뒤 요청을 전송한다
			// send() 함수를 이용
			xhr.send();
		}
		
		
		const ajaxTest2 = () => {
			
			const xhr = new XMLHttpRequest();
			
			xhr.open("post", "<%=request.getContextPath()%>/ajaxTest.do"); 
			
			xhr.onreadystatechange = () => { // 콜백함수 주기
				
				if(xhr.readyState == 4) {
					
					if(xhr.status == 200) {
						document.getElementById("target").innerHTML += "<h4>" + xhr.responseText + "</h4>";
					
					}
				}
			}
		
			// post방식에서 데이터를 전송할 경우에는 
			// url에 데이터의 내용을 작성하지 않고, send()함수의 매개변수로 작성한다 
			// 문자열 매개변수로 "key=value"
			
			// 전송방식도 수정해야한다
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); // 주석하면 id가 null로 나오는데 에러는 발생하지 않는다
			xhr.send("id=user01");
		}
		
		
		
	</script>
</body>
</html>