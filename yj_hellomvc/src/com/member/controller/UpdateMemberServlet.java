package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.service.*;
import com.member.model.vo.*;


@WebServlet(name="updatememberervlet", urlPatterns ="/updateMember.do")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateMemberServlet() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 여기에서 인코딩처리하는 대신 인코딩하는 filter 생성 
		
		String userId = request.getParameter("userId");
//		String password = request.getParameter("password"); 비밀번호변경기능을 따로 만들어서 주석처리함
		String userName = request.getParameter("userName");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String hobby = String.join(",", request.getParameterValues("hobby"));
		
//		Member m = new Member(userId, password, userName, gender, age, email, phone, address, hobby, null);
		Member m = new Member(userId, "", userName, gender, age, email, phone, address, hobby, null);
		
		int result = new MemberService().updateMember(m);
		
//		System.out.println("UpdateMemberServlet에서 테스트 result : " + result);
//		System.out.println("UpdateMemberServlet에서 테스트 m : " + m);
		
		String msg = "";
		String loc = "";
		
		
		if(result > 0 ) {
			
			// 회원정보 수정 성공 
			// 알림창 -> 내정보보기 페이지로 이동 ( 내정보보기페이지를 열려면 mypageservlet을 거쳐야함 ) 
			msg = "회원정보 수정 성공!";
		} else {
			
			// 수정 실패 
			msg = "회원정보 수정 실패";
		}
		
		loc = "/mypage.do?userId=" + userId;
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/views/common/alertmsg.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
