package com.data.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/sessiondel.do")
public class SessionDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SessionDelServlet() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
				// session 객체는 개발자가 로직에 의해 삭제할 때 까지 사라지지 않는다 
				// session 객체 삭제 : session.invalidate() 메소드를 호출 
		
		HttpSession session = request.getSession();
		
		session.invalidate(); // 세션 삭제 
		
		
		RequestDispatcher rd = request.getRequestDispatcher("usedata.do");
		
		rd.forward(request, response);
		
		
		
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doGet(request, response);
	}

}
