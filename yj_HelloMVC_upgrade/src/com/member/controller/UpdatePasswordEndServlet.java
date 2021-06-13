package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.service.*;
import com.member.model.vo.*;


//@WebServlet("/updatePasswordEnd.do")
@WebServlet(name="updatePasswordEnd", urlPatterns = "/updatePasswordEnd.do")

public class UpdatePasswordEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UpdatePasswordEndServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		String oldPw = request.getParameter("password");
		String newPw = request.getParameter("password_new");
	
		
		System.out.println(oldPw);
		System.out.println(newPw);
				//		String userId = request.getParameter("userId");
		
		
		// db에 있는 회원테이블의 패스워드 수정
		
				//		Member m = new MemberService().checkDuplicateId(userId);
				//		
			
		int result = new MemberService().updatePassword(oldPw, newPw);
		
		String msg = "";
		String loc = "";
		
		if(result > 0) {
			
			// 변경 성공 
			msg = "비밀번호 변경 성공";
			loc = "/";
			
			
			
		} else {
			
			// 변경 실패 
			msg = "비밀번호 변경 실패";
//			loc = "/views/member/memberView.jsp?userId="+request.getAttribute("userId");
			loc = "/updatePassword.do";
			System.out.println(loc);
			
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		*/
		
		
		
		// 선생님 풀이
		
		
		
		String userId = request.getParameter("userId");
		String curPw = request.getParameter("password");
		String newPw = request.getParameter("password_new");
		
		
		
			System.out.println("ServletMybatis:"+userId+":"+curPw+":"+newPw);
		// 1. 아이디가 있는지, 현재 패스워드가 일치하는지 확인 

		Member idpw = Member.builder().userid(userId).password(curPw).build();
		
		Member loginResult = new MemberService().login(idpw);
		
		String msg = "";
		String loc = "";
		
		String script = "";
		
		if(loginResult != null) {
			
			// 비밀번호 수정 로직 실행
			int result = new MemberService().updatePassword(userId, newPw);
			
			msg = result > 0 ? "비밀번호 수정 완료" : "비밀번호 수정 실패";
			loc = "/";
			
			script = "window.close()";
			
		} else {
			
			// 다시 비밀번호 수정 페이지로 돌아감 
			
			msg = "현재 비밀번호가 일치하지 않아 비밀번호를 수정할 수 없습니다";
			loc = "/updatePassword.do?userId=" + userId;
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.setAttribute("script", script);
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
