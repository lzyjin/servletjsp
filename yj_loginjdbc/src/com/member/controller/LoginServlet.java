package com.member.controller;

import java.io.IOException;
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
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberService service = new MemberService();
		
		Member m = service.login(id, pw);
		
		if(m != null) {
			
			// 로그인 성공
			// 로그인상태를 유지시키려면 m을 세션에 저장해야해 
			HttpSession session = request.getSession();
			
			session.setAttribute("userMember", m);
			
			
			
		} else {
			
			// 로그인 실패
				
			
			
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
