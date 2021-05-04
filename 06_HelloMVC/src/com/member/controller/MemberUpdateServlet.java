package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.service.*;
import com.member.model.vo.*;


//@WebServlet("/memberUpdate.do")
@WebServlet(name="memberupdateservlet", urlPatterns="/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MemberUpdateServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		request.setCharacterEncoding("utf-8");
		
		// 클라이언트가 보낸 회원정보 수정 
		String userId = request.getParameter("userId");
		
//		String pw = request.getParameter("password");
		
		String userName = request.getParameter("userName");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String hobby = String.join(",", request.getParameterValues("hobby"));
		
		
		// 받아온 데이터를 db에 수정하기 
		Member m = new Member();
		
		m.setMemberId(userId);
		
//		m.setPassword(pw);
		m.setPassword("");
		
		m.setUserName(userName);
		m.setAge(age);
		m.setGender(gender);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		m.setHobby(hobby);
		
		int result = new MemberService().updateMember(m);
		
		// result의 결과가 1이상이면 수정 완료, 0이면 수정 실패 
		String msg = result > 0 ? "회원정보 수정 완료" : "회원정보 수정 실패";
		
		// 메인페이지로 이동 
		// String loc = "/";
		
		// 회원정보수정 페이지로 이동 
		String loc = "/memberView.do?userId=" + userId;
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
