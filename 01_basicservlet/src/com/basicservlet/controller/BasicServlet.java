package com.basicservlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 일반클래스를 서블릿클래스로 만들기
// 서블릿 규약을 준수하면 된다 -> HttpServlet클래스를 상속받으면 된다
// javax.servlet.http.HttpServlet클래스임
// 상속 받은 후 Servlet으로 역할을 수행하기 위해 두 개의 메소드를 구현한다
// doGet(), doPost() 메소드를 재정의해서 서비스를 구현한다
// 위 메소드 재정의시에는 규약에 의해 ServletException 처리를 해야한다 

// 정리
// 1. 클래스 생성
// 2. 클래스에 HTTPServlet클래스를 상속
// 3. doGet(), doPost() 메소드를 재정의(오버라이드)

public class BasicServlet extends HttpServlet{

	private static final long serialVersionUID = -6904805013454881996L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		
		System.out.println("서버야 요청이 들어왔다. 이거 니가 실행해?");
		
		// HttpServletRequest : 사용자(클라이언트)가 보낸 데이터를 저장하는 객체 -> 데이터 관련된 기능을 제공하는 객체 
		// HttpServletResponse : 사용자(클라이언트)에게 응답할 정보를 가지고 있는 객체
		
		// HttpServletResponse 객체를 이용해서 응답 메세지 작성하기
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.write("<html>");
		out.write("<body>");
		out.write("<h1>내가 만든 서블릿에서 반환한 내용</h1>");
		out.write("</body>");
		out.write("</html>");
		
	}
	
	
	

}
