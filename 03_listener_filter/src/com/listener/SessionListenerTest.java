package com.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListenerTest implements HttpSessionListener, HttpSessionAttributeListener{

	
	
	
			// HttpSessionListener 의 메소드 재정의 
	
	
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		
		
				// request.getSession() -> 메소드가 실행되면 session이 생성된다 
		
		System.out.println("세션 생성");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
		
				// session.invalidate() -> 메소드가 실행되면 실행 
		
		System.out.println("세션 소멸");
		
	}
	
	
	// ------------------------------------------------------------
	
	
	
	
			// HttpSessionAttributeListener 의 메소드 재정의 
	
	
	

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		
		System.out.println("session 생성 됨 ");
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

}
