package com.member.controller;

import java.io.IOException;
import java.security.*;
import java.util.Date;

import javax.crypto.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.*;
import com.member.model.service.MemberService;
import com.member.model.vo.Member;


//@WebServlet("/memberenrollend.do")
@WebServlet(name="memberenrollendservlet", urlPatterns="/memberenrollend.do")
public class MemberEnrollEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MemberEnrollEndServlet() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8");
		
		// id, pw, name, age, email, phone, address, gender, hobby 
		String id = request.getParameter("userId");
		String pw = request.getParameter("password");
		String name = request.getParameter("userName");
		int age = Integer.parseInt(request.getParameter("age"));
		
		
		
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		try {
			
			// 0506
			// 암호화 처리 
			email = AESCryptor.encrypt(email);
			phone = AESCryptor.encrypt(phone);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		// String hobby = request.getParameter("hobby");
		String[] hobby = request.getParameterValues("hobby");
		
		// Member m = new Member(id, pw, name, gender, age, email, phone, address, hobby, null);
//		Member m = new Member(id, pw, name, gender, age, email, phone, address, String.join(",", hobby), null);
		
		Member m = Member.builder().userid(id)
								   .password(pw)
								   .gender(gender)
								   .age(age)
								   .email(email)
								   .phone(phone)
								   .address(address)
								   .hobby(String.join(",", hobby)).build();
		
				System.out.println(m);
		
		MemberService service = new MemberService();
		
		int result = service.insertMember(m);
		
		if(result > 0) {
			
			request.setAttribute("msg", "회원가입 성공");
			request.setAttribute("loc", "/");
			
			RequestDispatcher rd = request.getRequestDispatcher("/views/common/msg.jsp");
			rd.forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
