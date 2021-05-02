package com.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.service.MemberService;
import com.member.model.vo.Member;


@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("userId");
		String pw = request.getParameter("password");
		
		MemberService service = new MemberService();
		
		Member m = service.login(id, pw);
		
		if(m != null) {
			
			System.out.println("m : " + m);
			
			// 로그인 성공하면 
			
			// 1. 다른 버튼 눌러도 로그인상태가 유지될 수 있게 세션에 저장하자 
			// 2. 사용자 이름 띄우고 로그인버튼 없애고 로그아웃 버튼 만들기
			
			HttpSession session = request.getSession();
			
			session.setAttribute("login_member", m);
			
			System.out.println("login : " + session);
		
			
			RequestDispatcher rd = request.getRequestDispatcher("/");
			
			rd.forward(request, response);
			
		} else {
			
			request.setAttribute("msg", "로그인 실패. 다시 시도하세요.");
			request.setAttribute("loc", "/");
			
			RequestDispatcher rd = request.getRequestDispatcher("/views/common/alertmsg.jsp");
			
			rd.forward(request, response);
			
			
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
