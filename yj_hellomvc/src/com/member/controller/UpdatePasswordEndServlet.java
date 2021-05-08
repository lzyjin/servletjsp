package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.service.*;
import com.member.model.vo.*;


@WebServlet(name="updatepasswordendservlet", urlPatterns="/updatePasswordEnd.do")
public class UpdatePasswordEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UpdatePasswordEndServlet() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password"); // 현재비밀번호 
		String newPw = request.getParameter("password_new");
		
		// userId, password를 가진 회원이 있는지 조회 
		Member m = new MemberService().login(userId, password);
		
		String msg = "";
		String loc = "";
		String script = "";
		
		if(m != null) {
			
			// 있는 회원
			// 비번 변경하기 
			
			int result = new MemberService().updatePassword(userId, newPw);
			
			if(result > 0) {
				
				// 비번 변경 성공 // 팝업창 닫아야함 
				msg = "비밀번호 변경 성공";
				loc = "/";
				script = "window.close();";
				
			}
			
		} else {
			
			// 없는 회원 
			// 현재 비밀번호가 일치하지 않습니다
			// 팝업창 그대로 보여주기 ( 주의 : 이 때 jsp로 이동하기위해서는 userId를 보내줘야함 ) 
			
			msg = "현재 비밀번호가 일치하지 않습니다";
			loc = "/views/member/updatePassword.jsp?userId=" + userId;
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.setAttribute("script", script);
		
		request.getRequestDispatcher("/views/common/alertmsg.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
