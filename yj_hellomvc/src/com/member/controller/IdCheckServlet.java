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
		
		// 사용자가 입력한 값으로 db에 있는지 없는지 체크 
		String userId = request.getParameter("hiddenuserId"); // hidden 폼안에 있는 input태그의 값을 불러옴 
		
		System.out.println(userId);
		
		Member m = new MemberService().idCheck(userId);
		
		// m을 request에 저장해놓기 -> 다른페이지에서 쓸거야 
		request.setAttribute("result", m);
		
		// 분기처리를 jsp에서 한다
		if(m == null) {
			
			// 없는 아이디 -> 사용 가능 ! 
		} else {
			
			// 있는 아이디 -> 사용 불가능 ! 
		}
		
		request.getRequestDispatcher("/views/member/idcheck.jsp").forward(request, response);
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
