package com.test.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.model.service.TestService;
import com.test.model.vo.Member;


@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 클라이언트가 보낸 데이터 가져오기
		String userId = request.getParameter("userId");
		String pwd = request.getParameter("pwd");
		
		
		// DB에 userId, pwd가 일치하는 회원이 있는지 확인
		TestService service = new TestService();
		
		Member m = service.login(userId, pwd);
		
		
		// DB에서 가져온 결과를 가지고 로그인 처리 한다
		if(m != null) {
			
			// 로그인 성공
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", m);
			// 세션에 Member객체 m을 저장 
			
		} else {
			
			// 로그인 샐패
		}
		
		
		// 응답 페이지 설정 
		RequestDispatcher rs = request.getRequestDispatcher("");
		
		rs.forward(request, response);
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
