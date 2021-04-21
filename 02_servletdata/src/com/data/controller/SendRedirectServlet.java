package com.data.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/sendredirect.do")
public class SendRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SendRedirectServlet() {
       
    	
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("여기 들어오니?");
		
		//  서버에서 전환시키는 두번째 방법
		// HttpServletResponse 객체의 sendRedirect() 메소드를 이용하기 
		response.sendRedirect("testperson.do"); // 다시 요청된다.
		
		// 1. url주소창의 주소가 매개변수의 주소로 변경
		// request에서 있던 값이 다 소멸된다 ( 새로 서버에 요청하는거라서 ) 
		
		// requestDispatcher() : url주소창의 주소가 변경되지 않음 
		// sendRedirect() : 이전 로직을 수행하지 않아야 할 때 ( 로그아웃, 회원가입, 게시글 등록  ) 
		
		
		
		
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
