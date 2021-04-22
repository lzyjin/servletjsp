package com.data.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/printmain.do")
public class PrintMainViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PrintMainViewServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
				// 로그인을 했을 때와 하지 않았을 때의 페이지가 다르도록 하기 
		
		HttpSession session = request.getSession();
		
		String sessionId = (String) session.getAttribute("userId");
		
		String html = "";
		
		html += "<html>";
		html += "<body>";
	
		
		if(sessionId != null) {
			
				// 로그인 되었을 때의 화면 
			
			html += "<h1>" + sessionId + "님, 환영합니다</h1>";
			html += "<button onclick='location.replace(\"" + request.getContextPath() + "\");'>로그아웃</button>";
			
		} else {
			
				// 로그인이 되지 않았을 때 화면
			
			html += "<h1 style='color:red;'>Warnning : 로그인을 해야 볼 수 있습니다</h1>";
			html += "<button onclick='location.replace(\"" + request.getContextPath() + "/views/login.html\");'>로그아웃</button>";
		}
		
		html += "</body>";
		html += "</html>";
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(html);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
