package com.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout.do")
public class UserLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UserLogoutServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 로그아웃  = session에 있는 데이터를 삭제하는 것 
		
		// 로그인 = 인증받은 값을 유지하는 것 
		
		
		// HttpSession session = request.getSession(); // = 디폴트가 true ( 세션이 없으면 만들기 때문에  소멸시킬거면 이렇게 쓰는게 비효율적이다 ) 
		
		HttpSession session = request.getSession(false); 
		
		if(session != null) { // null이 아닐때만 세션 소멸시키기 ( session이 null일때 소멸시키면 nullpointerexecption이 뜨기때문에 분기처리 필요 ) 
			
			session.invalidate();
		}
		
		// 로그아웃 시키고 메인화면으로 이동시키기 
		response.sendRedirect(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
