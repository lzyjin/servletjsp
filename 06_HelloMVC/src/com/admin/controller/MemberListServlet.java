package com.admin.controller;

import java.io.IOException;
import java.security.*;
import java.util.*;

import javax.crypto.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.admin.model.service.*;
import com.common.*;
import com.member.model.vo.*;


@WebServlet("/admin/memberlist.do")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MemberListServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		// 관리자가 아닌 사용자가 접근했을 때 (주소창에서 바로 회원관리 페이지로 이동한 경우 ) 예외처리하기
		HttpSession session = request.getSession(false);
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		if( session == null || loginMember == null || !loginMember.getMemberId().equals("admin") ) {
			
			// 에러메세지 : 잘못된 경로로 접근했습니다. 이 페이지의 사용권한이 없습니다. 
			request.setAttribute("msg", "잘못된 경로로 접근했습니다. 이 페이지의 사용권한이 없습니다. ");
			request.setAttribute("loc", "/");
			
			RequestDispatcher rd = request.getRequestDispatcher("views/common/msg.jsp");
			rd.forward(request, response);
			
		} else {
			
			
			// 클릭시 전체 회원에 대한 정보를 조회해서 가져옴
			
			AdminService service = new AdminService();
			
			ArrayList<Member> list = service.selectAllMember();
			
			
			
			// 양방향 암호화했을 때 암호화된 정보들을 복호화해서 출력하기위해 
			for(Member m : list) {
				
				try {
					
					m.setPhone(AESCryptor.decrypt(m.getPhone()));
					m.setEmail(AESCryptor.decrypt(m.getEmail()));
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
			
				
			request.setAttribute("memberlist", list);
			
			
			request.getRequestDispatcher("/views/admin/memberlist.jsp").forward(request, response);
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
