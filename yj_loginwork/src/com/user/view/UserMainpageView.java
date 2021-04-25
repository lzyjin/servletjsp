package com.user.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/mainpage.do")
public class UserMainpageView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UserMainpageView() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		String html = "<html>";
		html += "<body>";
		html += "<h1> YJ 공식 홈페이지 입니다. 환영합니다.</h1>";
		html += "<button onclick = 'location.assign(\"" + request.getContextPath() + "/logout.do\")'>로그아웃</button>";
		html += "<button onclick = 'location.assign(\"index.html\")'>메인페이지로 이동</button>";
		html += "</body>";
		html += "</html>";
		
		response.getWriter().write(html);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
