package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/updatePassword.do")
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UpdatePasswordServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 화면 연결용 서블릿 
		
		// String userId = request.getParameter("userId"); 쿼리스링으로 보낸 아아디값 ( 안써도 되는 코드 )  
		
		// updatePassword.jsp 에서 request.getParameter("userId")로 userId를 쓸 수 있다 
		
		request.getRequestDispatcher("/views/member/updatePassword.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
