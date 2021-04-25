package com.cookie.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/cookietest.do")
public class CookieCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CookieCreateServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 쿠키를 생성해서 클라이언트에게 저장시키기
		
		// 쿠키 객체 생성시 저장할 key와 value를 생성자의 문자열 매개변수로 넣는다
		
		Cookie c = new Cookie("userId", "sanghyun");
		
		
		// 쿠키의 유효시간 설정
		
		c.setMaxAge(24*60*60); // 1일  = 60초 * 60 * 24 
		
		
		
		// 생성된 쿠키는 클라이언트에게 저장요청한다
		// response.addCookie() 메소드 
		
		response.addCookie(c);
		
		
		// 쿠키 추가 생성 ! 
		
		Cookie c2 = new Cookie("email", "prince0324@naver.com");
		
		c2.setMaxAge(10); // 유효기간 10초 ( 10초 후 자동 소멸 ) 
		
		response.addCookie(c2);
		
		
		
		
		
		
		
		// 응답페이지 작성하기 
		
		response.setContentType("text/html;charset=utf-8");
		
		String html = "<html>";
		html += "<body>";
		html += "<p>쿠키를 저장했습니다</p>";
		html += "<button onclick='location.assign(\"checkcookie.do\")'>저장된 쿠키 확인</button>";
		html += "</body>";
		html += "</html>";
		
		response.getWriter().write(html);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
