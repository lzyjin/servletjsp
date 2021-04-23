package com.user.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/mainpage.do")
public class MainPageView extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
   
    public MainPageView() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 메인페이지를 응답해주는 서블릿
		
		response.setContentType("text/html;charset=utf-8");
		
		String html = "<html>";
		
		html += "<head>";
		html += "<style>";
		html += "ul>li{display : inline-block; margin-right : 20px; font-size : 25px; font-weight : border; }";
		html += "</style>";
		html += "</head>";
		
		HttpSession session = request.getSession();
		
		if(session != null && session.getAttribute("loginUser") != null) {
			
			
			html += "<body>";
			html += "<h1>YJ홈페이지에 오신것을 환영합니다</h1>";
			html += "<ul>";
			html += "<li>메인화면</li>";
			html += "<li>갤러리</li>";
			html += "<li>게시판</li>";
			html += "</ul>";
			html += "<button onclick='location.assign(\"" + request.getContextPath() + "/logout.do\");'>로그아웃</button>";
			
			
			
		} else {
			
			html += "<form action='login' method='post'>";
			html += "아이디<input type='text' name='userId'><br>";
			html += "비밀번호<input type='text' name='userId'><br>";
			html += "<input type='submit' value='로그인'>";
			html += "</form>";
			
		}
		
		html += "</body>";
		html += "</html>";
		
		response.getWriter().print(html);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
