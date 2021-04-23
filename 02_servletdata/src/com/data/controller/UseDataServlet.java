package com.data.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/usedata.do")
public class UseDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UseDataServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
				// 데이터 저장 객체에 저장되어있는 데이터를 호출하기
		
		ServletContext context = request.getServletContext();
		
		HttpSession session = request.getSession();
		
		
		String requestData = (String) request.getAttribute("request"); // setAttribute()로 넣은 데이터는 getAttribute()로 가져온다 
		
		String contextData = (String) context.getAttribute("context");
		
		String sessionData = (String) session.getAttribute("session");

		
		
		
				// 응답 메세지 작성하기 
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		String html = "<html>";
		
		html += "<body>";
		html += "<ul>";
		html += "<li>requestData : " + requestData + "</li>";
		html += "<li>sessionData : " + sessionData + "</li>";
		html += "<li>contextData : " + contextData + "</li>";
		html += "</ul>";
		html += "<button onclick='location.assign(\"/02_servletdata/sessiondel.do\");'>session삭제</button>"; // 버튼 추가 -> 버튼 누르면 세션이 삭제된다 
		html += "</body>";  
		html += "</html>";
		
		out.print(html);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
