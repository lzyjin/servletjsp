package com.websocket.controller;

import java.util.*;

import javax.websocket.*;
import javax.websocket.server.*;

import com.google.gson.*;
import com.websocket.model.vo.*;

//일반 클래스를 웹소켓 서버로 등록하려면 
// @ServerEndpoint 어노테이션을 이용한다
// -> 클래스 선언부에 선언한다

@ServerEndpoint("/chatting")
public class ChattingServer {
	
	
			//	예시 : private Map<String, Session> clients = new HashMap<>();
	
	
	
	// 내부에 클라이언트가 요청하는 내용을 처리하는 메소드를 등록한다
	
	
	// 접속요청을 한 클라이언트와 접속되면 실행되는 메소드 
	@OnOpen
	public void open(Session session, EndpointConfig config) {
		
				System.out.println(session.getId()); // 0 출력됌 -> jsessionid와 다르다  
				System.out.println("클라이언트가 접속함");
		
		
	}
	
	
	// 클라이언트들이 보내는 메세지를 처리하는 메소드 
	@OnMessage
	public void message(Session session, String msg) { // 매개변수의 session은 보낸 클라이언트의 session임
		
		// session 메세지를 보낸 클라이언트의 session 객체 ( httprequestsession과 다른 세션임 )
		
		// msg : page에서 socket.send("데이터") 
		
				System.out.println(msg); // 클라이언트가 입력한 데이터가 출력된다
				
		Message data = new Gson().fromJson(msg, Message.class);
				
				System.out.println(data);
		
		
		// 클라이언트가 보낸 메세지 파싱하기 
		// String[] data = msg.split(",");
		// 인덱스0 : 보낸사람
		// 인덱스1 : 받는 사람 
		// 인덱스2 : 메세지
		
		
		// 클라이언트가 보낸 데이터를 세션에 저장할 수 있다 
		// session.getUserProperties().put("msg", data);
		session.getUserProperties().put("msg", data);
				
				
				
				
		// 클라이언트가 보낸 데이터를 접속한 다른 클라이언트에게 전송하기 
				
		// 1. 접속한 클라이언트 관리 
				
			// 1-1. Collection클래스를 이용하는 방법 
			// Map, List, Set 
			// 필드로 선언함
					
			// 1-2. session클래스에서 접속한 session을 알 수 있는 메소드를 제공
			// session.getOpenSessions() : 현재 웹소켓에 접속해서 session이 유지되고 있는 모든 session값을 반환한다
				
		Set<Session> clients = session.getOpenSessions();
	
		
		// 2. 접속 session객체를 이용해서 데이터를 전송할 수 있다 
		// session.getBasicRemote()메소드 : 접속한 세션과 연결되는 스트림을 가져온다
		
		for(Session s : clients) {
			
//			String[] clientData = (String[])s.getUserProperties().get("msg");
			
			Message clientData=(Message)s.getUserProperties().get("msg");
			
//			if(data[1].length() > 0) { // 0보다 크면 == 값이 있으면 
//				if( clientData[0].equals(data[1]) || clientData[0].equals(data[0]) ) {
//					
//					try {
//						s.getBasicRemote().sendText(msg); // 데이터를 접속한 클라이언트들에게 고대로 전송 -> jsp에서 데이터를 받으면 onmessage가 자동으로 실행된다
//					} catch (Exception e) {
//						e.printStackTrace();
//					
//				}
					
//			} else {
			
				if(!clientData.getReceiver().equals("")) {
					if(clientData.getSender().equals(data.getReceiver())) {
						try {
							s.getBasicRemote().sendText(msg); // 데이터를 접속한 클라이언트들에게 고대로 전송 -> jsp에서 데이터를 받으면 onmessage가 자동으로 실행된다
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} else {
					try {
						s.getBasicRemote().sendText(msg); // 데이터를 접속한 클라이언트들에게 고대로 전송 -> jsp에서 데이터를 받으면 onmessage가 자동으로 실행된다
					} catch (Exception e) {
						e.printStackTrace();
					}
// 			}
			
			
			}
			
			
		}
		
				
		// 3. session.getBasicRemote()로 가져온 객체의 sendText()메소드를 실행한다 
		// = 메세지를 클라이언트에게 전송
				
	}
	
}
