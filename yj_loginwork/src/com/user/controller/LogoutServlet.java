package com.user.controller;

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
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession(false);
		
		
		System.out.println(session);
		
		
		if(session != null) {
			
			session.invalidate(); // 서버에의 램 영역안에서 세션 객체를 지워 
		}
		
		
//		response.sendRedirect("index.html");
		response.sendRedirect(request.getContextPath());
		
		
		//session = request.getSession(false); // session 상태를 다시 확인하는 작업 필요해서 getSession() 메소드 한번 더 호출
		System.out.println(session); // 로직이 끝나기 전, 페이지가 전환되기 전이라서 session의 주소가 그대로 남아있는것같음 
		
		
	}

	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
