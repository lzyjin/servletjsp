package com.session.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/checksession.do")
public class CheckSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CheckSessionServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// session 에 있는 값 확인하기 
		
		// session을 가져올 때 getSession(true || false) 
		
		// true ->  session이 있으면 그 세션을 가져오고, 없으면 생성
		// false -> session이 있으면 그 세션을 가져오고, 없으면 null을 반환
		
		 HttpSession session = request.getSession(false);
//		HttpSession session = request.getSession(true);
		
		System.out.println(session);
		
		if(session != null) {
			
			String msg = (String)session.getAttribute("checktest");
			
			System.out.println(msg);
			
		} else {
			
			System.out.println("session 없다 ~ ");
		}
		
		
		response.sendRedirect(request.getContextPath());
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
