package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout.do")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LogoutServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그아웃 
		// 로그인 된 상태라면 세션에 저장되어있는 속성이 null이 아닐겨
		// 저장되어있는 세션을 삭제해야해 
		HttpSession session = request.getSession();
		
		System.out.println("logout : " + session);
		
		if(session != null) {
			
			session.invalidate();
		}
		
		response.sendRedirect(request.getContextPath());
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
