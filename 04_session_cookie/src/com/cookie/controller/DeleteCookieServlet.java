package com.cookie.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/deletecookie.do")
public class DeleteCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DeleteCookieServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 저장된 쿠키를 삭제하기 
		
		// 쿠키의 key값으로 쿠키의 유효기간을 조정하여 삭제한다 
		
		// 즉, 동일한 key값의 cookie를 생성해서 유효기간 설정을 0으로 하면 cookie가 삭제된다
		
		Cookie c = new Cookie("userId", "");
		
		c.setMaxAge(0); // 유효기간 0초 = 유효기간 없다 
		
		response.addCookie(c);
		
		// 크롬 개발자창에서 확인 가능 
		
		// 쿠키 삭제 후 바로 메인페이지로 이동
		
		response.sendRedirect(request.getContextPath()); 
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
