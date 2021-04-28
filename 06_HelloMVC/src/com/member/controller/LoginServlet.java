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


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		MemberService service = new MemberService();
		
		Member m = service.login(userId, password);
		
		if(m != null) {
			
			// 로그인 성공 
			// 로그인 성공에 대한 데이터를 유지하기 위해 데이터를 보관 
			// 보관 객체 request.session.context
			
			// request.setAttribute("loginMember", m); // 이걸로 저장하면 데이터가 사라져서 안됌 
			HttpSession session = request.getSession();
			
			session.setAttribute("loginMember", m);
			
			System.out.println(m);
			
		} else {
			
			// 로그인 실패
		}
		
		// 페이지 전환
		// 로그인 여부 상관없이 메인페이지로 이동
		// request를 이용하기 위해 requestDispatcher() 사용 
		RequestDispatcher rd = request.getRequestDispatcher("");
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
