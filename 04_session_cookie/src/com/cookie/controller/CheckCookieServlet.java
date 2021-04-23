package com.cookie.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/checkcookie.do")
public class CheckCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CheckCookieServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 클라이언트가 보낸 cookie값 확인하기
		
		// cookie값은 request 객체의 getCookies()메소드를 이용해서 가져온다
		
		// getCookies()메소드의 반환형은 Cookies[] 배열 ( 서버에서 저장되는 Cookie값이 한개가 아니라서 ) 
		
		Cookie[] cookies = request.getCookies();
		
		// 저장된 쿠키가 없으면 null을 반환한다
		
		
		response.setContentType("text/html;charset=utf-8");
		
		String html = "<html>";
		html += "<body>";
		html += "<ul>cookie 확인";
		
		
		if(cookies != null) {
			
			for(Cookie c : cookies) {
				
				
				// Cookie값을 확인하려면 getName(), getValue() 메소드를 이용한다 
				
				System.out.println( "키 : " + c.getName() + ", 값 : " + c.getValue() );
				
				
				
				html += "<li>" + c.getName() + " : " + c.getValue() + "</li>";
				
				
			}
			
			html += "</ul>";
			html += "<h2><a href='deletecookie.do'>쿠키 삭제</a><h2>";
			html += "<h2><a href='" + request.getContextPath() + "'>메인으로</a><h2>";
			html += "</body>";
			html += "</html>";
			
			response.getWriter().print(html);
			
			
		}
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
