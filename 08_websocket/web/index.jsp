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
	<input type="text" id="sender" size="10"placeholder="보내는사람이름">
	<input type="text" id="receiver" size="10" placeholder="받는 사람">
	<input type="text" id="msg" placeholder="전송할 메세지 입력">
	<button onclick="sendMsg();">전송</button>
	
	<div id="msgContainer"></div>
	
	<script>
	
		// 웹소켓 서버에 연결하기
		
		// 1. WebSocket객체 생성
		
		// 매개변수로 들어가는 주소는 정해진 형식이 있다
		// http프로토콜로 통신하는 주소는 ws:주소
		// https프로토콜로 통신하는 주소는 wss:주소
		const socket = new WebSocket("ws://localhost:9090/<%=request.getContextPath()%>/chatting");
		
		
		// 2. socket 속성 설정 
		
		// 접속 후 실행되는 이벤트 핸들러 등록
		socket.onopen = e => {
			
			console.log("웹소켓 서버에 접속 성공!");
			console.log(e); // 자기의 정보를 전송하는 경우가 많다
		}
		
		
		
		
		// 웹소켓 서버에서 sendText 또는 sendObject메소드를 실행하면 실행되는 함수
		socket.onmessage = e => {
			
					console.log("메세지 수신");
			
			// 수신된 데이터를 받으려면 이벤트객체의 data속성을 이용한다
					console.log(e);
					console.log(e.data);
			
			
			
			
			// 발신자 이름과 내용을 분기처리
			// 0 : 보낸 사람 
			// 1 : 받는 사람
			// 2 : 내용
			
			// let msg = e.data.split(",");
			
			if(msg[0] == $("#sender").val()) {
				// $("#msgContainer").append($("<p>").text("<" + msg[0] + ">" + msg[2]).css("text-align", "right"));
			} else {
				// $("#msgContainer").append(  $("<p>").text("<" + msg[0] + ">" + msg[2]).css("text-align", "left")  );
			}
			
			
			
			// 주된 로직은 메세지처리에 대한 로직이다. 
			
			/* $("#msgContainer").append($("<p>").text(e.data)); */
		}
	
		
		
		
		
		// 웹소켓 서버에 메세지를 전송하는 함수 
		const sendMsg = () => {
			
			// 전송할 메세지를 전처리한다
			
			// 전처리한 메세지를 전송하는 방법 : socket.send("데이터 전송");
			// socket.send( $("#sender").val() + "," + $("#receiver").val() + "," + $("#msg").val() ); // 메세지보내는 형식을 정해놔야 파싱 가능 
		
			msg = new Message( $("#sender").val(), $("#receiver").val(), $("#msg").val() );
			
			// javascript 객체를 String으로 변환해서 전송 
			// JSON.stringfy(objaect)함수를 이용
			socket.send(JSON.stringify(msg));
		}
		
		
		function Message(sender, receiver, msg) {
			this.sender = sender;
			this.receiver = receiver;
			this.msg = msg;
		}
		
		
		
		
	</script>
	
		
</body>
</html>