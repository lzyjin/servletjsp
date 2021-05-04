package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.service.*;
import com.member.model.vo.*;


@WebServlet("/updatePassword.do")
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UpdatePasswordServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 화면전환용 서블릿 
		
		String userId = request.getParameter("userId");
		
		Member m = new MemberService().checkDuplicateId(userId);
		
		if(m != null) {
			
			request.setAttribute("result", m);
			// m이 null이 아니면 있는 회원, null이면 없는 회원 
		}
		
		request.setAttribute("userIds", userId);

		request.getRequestDispatcher("/views/member/updatePassword.jsp").forward(request, response);
		
		
		
		// 선생님 풀이 
		
//		request.getRequestDispatcher("/views/member/updatePassword.jsp").forward(request, response);
//		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
