package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.service.*;
import com.member.model.vo.*;


@WebServlet("/checkDuplicateId")
public class CheckDuplicateIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CheckDuplicateIdServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 클라이언트가 전송한 id값이 db에 있는지 없는지 확인해주는 서비스
		String userId = request.getParameter("userId");
		
		// db에서 확인한 로직 
		Member m = new MemberService().checkDuplicateId(userId); // 받을 값은 boolean으로 해도 된다 
		
		
		// 결과에서 m이 null이면 중복되지않는 아이디 / m이 null이 아니면 중복되는 아이디 
		request.setAttribute("result", m);
		request.setAttribute("userId", userId);
		
		request.getRequestDispatcher("/views/member/checkDuplicateId.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
