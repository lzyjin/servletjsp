package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.member.model.service.*;


@WebServlet("/deleteMember")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MemberDeleteServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		request.setCharacterEncoding("utf-8");
		
		// 
		String userId = request.getParameter("userId"); // 쿼리스트링 = input태그에 쓴것과 동일하므로 name값을 불러오는 이 코드와 같다 
		
		int result = new MemberService().deleteMember(userId);
		
		if(result > 0) {
			
			// 탈퇴 성공 
			// 로그아웃도 시켜야 함 
			// alert띄우고 메인페이지로 이동 
			
			String msg = "탈퇴 성공";
			String loc = "/logout";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			
			// 로그아웃 시키기위해 세션 소멸시키기
			HttpSession session = request.getSession();
			
			session.invalidate();
			
			
			
		} else {
			
			// 탈퇴 실패 
			
			String msg = "탈퇴 실패";
			String loc = "/memberView.do";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
		}
		
		
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
