package com.member.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.member.model.service.*;
import com.member.model.vo.*;


@WebServlet("/idcheck.do")
public class IdCheckServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
   
    public IdCheckServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String inputId = request.getParameter("inputid");
		String inputId = request.getParameter("inputidCheck_");
		
		MemberService service = new MemberService();
		
		Member m = service.idCheck(inputId);
		
		System.out.println("m : " + m);
		System.out.println("inputidCheck_ : " + inputId);
	
		
		
//		if(m.getMemberId() != null) { 이렇게 하면 resultset이 없을때 = 그 아이디를 가진 회원이 없을 때 m이 null이라서 null의 id를 참조하려해서 nullpointerexception이 발생한다 
		if(m != null) {
			
			
			// 이미 있는 아이디입니다
			System.out.println("이미 있는 아이디입니다");
			
			request.setAttribute("msg", inputId + "는 이미 사용중인 아이디입니다");
			request.setAttribute("loc", "/views/member/idcheck.jsp");
			
			RequestDispatcher rd = request.getRequestDispatcher("/views/common/alertmsg.jsp");
			rd.forward(request, response);
			
		} else {
			
			// 없는 아이디입니다 -> 사용 가능 
			System.out.println("없는 아이디입니다. 사용 가능합니다");
			
			
			HttpSession session = request.getSession();
			
			
			request.setAttribute("msg", inputId + "는 없는 아이디입니다. 사용 가능합니다");
			request.setAttribute("loc", "/views/member/idcheck.jsp");
			
//			session.setAttribute("checkEndId", inputId);
			request.setAttribute("checkEndId", inputId);
			

			RequestDispatcher rd = request.getRequestDispatcher("/views/common/alertmsg.jsp");
			rd.forward(request, response);
			
			
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
