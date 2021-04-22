package com.listener.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/sessiontest.do")
public class SessionListenerTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SessionListenerTestServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession(); // 세션 생성 ( 세션 아이디가 부여된다 ) 단, 이미 세션아이디가 있는 상태에서는 생성되지 않는다
		
		session.setAttribute("loginUser", "user01");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
