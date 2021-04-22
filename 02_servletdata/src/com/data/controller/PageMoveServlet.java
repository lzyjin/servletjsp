package com.data.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pagemove.do")
public class PageMoveServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    public PageMoveServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 특정 데이터를 HttpServletRequest 객체에 보관 
		// 응답기능을 하는 서블릿을 호출하여 응답처리하는 로직을 구현한다
		
		// 서버 내에서 다른 서블릿의 메소드를 실행시키는 방법 
		
		// 1. HttpServletRequest 객체에 원하는 데이터 넣기
		// setAttribute("키값", Object) => HttpServletRequest 객체에 특정 데이터를 저장한다
		
		request.setAttribute("msg", "점심 맛있게 먹었나요?");
		request.setAttribute("today", new Date());
		
		// 만약 key가 중복되면?
		request.setAttribute("msg", "반가워요 YJ웹에 오신걸 환영해요");
		// 덮어쓰기된다 => 즉 key는 중복될 수 없다 
		
		
		
		// 2. HttpServletRequest 객체에 저장되어있는 데이터를 가져오기
		// getAttribute("키값") => HttpServletRequest 객체에 저장되어있는 데이터를 가져오는 기능
		String msg = (String) request.getAttribute("msg");
		Date today = (Date) request.getAttribute("today");
		
		System.out.println("msg : " + msg);
		System.out.println("today : " + today);
		
		
		
		
		
		
		// 페이지를 다른 서블릿으로 전환하자
		// 다른 서블릿을 호출해보자
		
		// getRequestDispatcher() 메소드를 이용 
		// RequestDispatcher 객체가 생성되고 이 객체의 forword()메소드를 이용하면 지정된 서블릿이 호출된다 
		
		// getRequestDispatcher( "서블릿 매핑 주소" 또는 "jsp주소" ) 
 		// RequestDispatcher.forword(request, response)
		
		RequestDispatcher rd = request.getRequestDispatcher("testperson.do");
		
		rd.forward(request, response); // 지정된 서블릿을 호출해서 실행한다
		
		// forward()의 매개변수가 TestPersonServlet의 doGet()메소드의 인자로 들어가서 메소드가 실행되는것 
		
		// 결국 응답은 testperson.do가 함 -> TestPersonServlet 이 실행된다 
		
		// 클라이언트가 최초로 요청한 주소는 변경이 안된다 ( url주소창의 주소는 변하지 않는다 )
		
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
