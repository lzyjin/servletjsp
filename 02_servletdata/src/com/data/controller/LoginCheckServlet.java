package com.data.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logincheck.do")
public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginCheckServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		// 사용자가 보낸 데이터를 가져오기
		
		String id = request.getParameter("userId");
		String pw = request.getParameter("password");
		
		
		
		// 가져온 데이터의 id가 admin, pw가 1234이면 가져온 데이터를 session에 저장하기, 아니면 저장하지 않음 
		
		if(id.equals("admin") && pw.equals("1234")) {
			
			System.out.println(id + " " + pw);
			
			HttpSession session = request.getSession();
			
			session.setAttribute("userId", id);
		
		} else {
			
			System.out.println(id + " " + pw);
		}
		
		
		// 화면 출력 서블릿으로 전환 
		// printmain.do
		
		RequestDispatcher rd = request.getRequestDispatcher("printmain.do");
		
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
