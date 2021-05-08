package com.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.member.model.service.MemberService;
import com.member.model.vo.Member;


@WebServlet(name="loginservlet", urlPatterns ="/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("userId");
		String pw = request.getParameter("password");
		
		System.out.println("LoginServlet에서 pw : " + pw);
		
		MemberService service = new MemberService();
		
		Member m = service.login(id, pw);
		
		
		// 아이디 저장 기능 
		// 1. header.jsp의 아이디저장 체크박스의 값 가져오기 
		String saveId = request.getParameter("saveId");
		
		System.out.println("체크박스 값 : " + saveId); // on : 아이디를 입력하기만 하면 on이 된다. 
		
		// 클라이언트쪽에서 저장해도 상관없는 데이터들은 쿠키에 저장한다 
		
		// 체크박스 체크하면 on이 된다. 체크안하면 null이 된다 
		// 결국 saveId가 null이 아니면 쿠키에 사용자아이디를 저장한다
		
		if(saveId != null) {
			
			// 쿠키에 아이디 저장 
			Cookie cookie = new Cookie("saveId", id);
			
			// 쿠키 유효기간 7일로 설정하자
			cookie.setMaxAge(60*60*24*7);
			
			// 생성된 쿠키는 클라이언트에게 저장요청한다
			response.addCookie(cookie);
		} 
		
		
		
		
		
		if(m != null) {
			
			System.out.println("m : " + m);
			
			// 로그인 성공하면 
			
			// 1. 다른 버튼 눌러도 로그인상태가 유지될 수 있게 세션에 저장하자 
			// 2. 사용자 이름 띄우고 로그인버튼 없애고 로그아웃 버튼 만들기
			
			HttpSession session = request.getSession();
			
			session.setAttribute("login_member", m);
			
		
			
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
