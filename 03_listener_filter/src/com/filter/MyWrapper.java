package com.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

// wrapper 클래스 만들기

// HttpServletRequestWrapper 클래스를 상속받아야한다

// 상속받아서 구현하면, 반드시 매개변수가 있는 생성자 하나를 선언해야한다 

// 생성자에서 HttpServletRequest 객체를 매개변수로 받아야한다 

public class MyWrapper extends HttpServletRequestWrapper{
	
	
	// 반드시 선언해야하는 생성자 
	public MyWrapper(HttpServletRequest request) {
		
		super(request);
		
	}

	
	// 개발자의 로직대로 구현할 메소드를 재정의하면 된다 
	
	@Override
	public String getParameter(String key) {
		
		// HttpServletRequest.getParameter() 의 값을 가져와서 -yj-를 붙여서 리턴하는 메소드로 재정의함 
		
		return super.getParameter(key) + "-yj-";
	}
	
	
}
