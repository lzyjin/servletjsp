package com.data.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/share.do")
public class ShareDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ShareDataServlet() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
				// 데이터를 저장할 수 있는 객체를 가져오기 
				
				// 1. ServletContext 객체 불러오기
				// ServletContext 는 서버 전역에 이용할 수 있는 데이터를 저장할 수 있는 객체이다
				// 저장내용의 유효기간 : 서버 runtime부터 서버 shutdown 까지 
				
				// ServletContext context = request.getServletContext(); 아래 코드랑 동일함 
		
		ServletContext context = getServletContext();
		
		context.setAttribute("context", "contextData");
		
		
		
		
		
				// 2. HttpSession 객체 불러오기 
				// 개발자가 지정한 기간동안 클라이언트별로 데이터를 저장할 수 있는 객체 
				// 저장내용의 유효기간 : session 생성 순간부터 session 소멸시까지 ( invalidate()메소드로 소멸시킨다 ) 
		
		
		HttpSession session = request.getSession(); // 클라이언트에게 session id값이 부여된다
		
		session.setAttribute("session", "sessionData");
		
		
		
		
		
				// 3. HttpServletRequest 객체 
				// 클라이언트가 request를 보내면 생성되는 객체
				// 요청(request)에 대한 응답(response)을 하면 종료된다 
				// 요청 = 주소에 뭔가를 적음 -> 응답을 받으면 요청한 주소와 데이터가 사라짐 
		
		request.setAttribute("request", "requestData");
		
		
		
		
		
		
				// RequestDispatcher를 이용해서 처리하기 
		
		RequestDispatcher rd = request.getRequestDispatcher("usedata.do");
		
		rd.forward(request, response); // 다른 서블릿으로 전환 ( 응답한 상태가 아니다 )
		
		
		
				// sendRedirect로 이동하면?
		
//		response.sendRedirect("usedata.do");
		// response를 썼다는것 = 응답했다 = request가 유지되지 않는다  -> requestData 가 null이 나온다 
		
		// session은 지울때까지, context는 서버가 꺼질때까지 살아있기 때문에 데이터가 유지된다 
		
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

	
	
}
