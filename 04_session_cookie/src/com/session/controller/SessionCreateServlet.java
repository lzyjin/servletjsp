package com.session.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/sessiontest.do")
public class SessionCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SessionCreateServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// session은 session서버에 저장되는 데이터로 
		// request 객체에서 getSession() 메소드로 생성되고
		// 클라이언트에게는 JSESSIONID로 생성되어 session 객체의 id값을 전송한다
		// 다시 사이트에 접속할 때 cookies에 있는 JSESSIONID 값을 가져와 session값을 확인할 수 있다 
		
		HttpSession session = request.getSession();
		
		session.setMaxInactiveInterval(10); // 10초
		
		session.setAttribute("checktest", "있음"); 
		
		
		
		response.sendRedirect(request.getContextPath()); // 현재 어플리케이션(프로젝트)로 이동
		
		// 세션이 생성되었는지 크롬 개발자 도구에서 확인 가능
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
