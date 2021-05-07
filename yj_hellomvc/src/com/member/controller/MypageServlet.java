package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.member.model.service.*;
import com.member.model.vo.*;


@WebServlet("/mypage.do")
public class MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MypageServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId"); // 쿼리스트링으로 대입한 값을 이렇게 받아올 수 있다
					System.out.println("MypageServlet 에서 userId : " + userId);
		
		Member m = new MemberService().idCheck(userId); // 아이디로 검색하는 로직이라서 이 메소드 사용 
					System.out.println("MypageServlet 에서 m : " + m);
		
		
		String view = "";
		String msg = "";
		String loc = "";
		
		if(m != null) {
			
			// 회원이 있다 -> 회원정보 출력
			
			view = "/views/member/mypage.jsp";
//			request.setAttribute("member", m);
			
			
		} else {
			
			// 회원이 없다 -> 알림 띄우고 회원정보 수정 페이지로 이동
			view = "/views/common/msg.jsp";
			msg = "가입된 회원이 아닙니다";
			loc = "/logout.do";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.setAttribute("member", m);
		
		
		request.getRequestDispatcher(view).forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
