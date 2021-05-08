package com.member.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.service.MemberService;
import com.member.model.vo.Member;


@WebServlet(name="enrollendservlet", urlPatterns="/enrollend.do")
public class EnrollEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EnrollEndServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		// 사용자가 회원가입창에 입력한 값 가져오기 
		
				String userId = request.getParameter("userId_");
				String password = request.getParameter("password_");
				String userName = request.getParameter("userName");
				int age = Integer.parseInt(request.getParameter("age"));
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				String address = request.getParameter("address");
				String gender = request.getParameter("gender");
				String[] hobbys = request.getParameterValues("hobby");
				
//				String hobby = "";
//				
//				for(String h : hobbys) {
//					
//					hobby += h;
//				}
				
				Member m = new Member(userId, password, userName, gender, age, email, phone, address, String.join(",", hobbys), new Date());
				
				System.out.println("회원가입창 입력값 : " + m);
				
				MemberService service = new MemberService();
				
				int result = service.enroll(m);
				
				System.out.println("result : " + result);
				
				if(result > 0) {
					
					// 회원가입 성공 
					request.setAttribute("msg", "회원가입 성공 !");
					request.setAttribute("loc", "/");
					
					RequestDispatcher rd = request.getRequestDispatcher("/views/common/alertmsg.jsp");
					rd.forward(request, response);
					
				} 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
