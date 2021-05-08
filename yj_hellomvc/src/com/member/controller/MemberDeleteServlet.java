package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.service.*;


@WebServlet("/memberdelete.do")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MemberDeleteServlet() {
   
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		
		// System.out.println(userId);
		
		int result = new MemberService().deleteMember(userId);
		
		String msg = result > 0 ? "탈퇴 성공" : "탈퇴 실패";
		String loc = result > 0 ? "/logout.do" : "/mypage.do";
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/views/common/alertmsg.jsp").forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
