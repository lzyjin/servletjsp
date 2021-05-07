package com.admin.controller;

import java.io.IOException;
import java.security.*;
import java.util.*;

import javax.crypto.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.service.*;
import com.common.*;
import com.member.model.vo.*;


@WebServlet("/admin/searchMember.do")
public class SearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SearchMemberServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 클라이언트가 보낸 값 가져오기
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("searchKeyword");
		
		List<Member> result = new AdminService().searchMember(searchType, keyword);
		
//		request.setAttribute("members", result);
		
		
		
		for(Member m : result) {
			
			try {
				
				m.setPhone(AESCryptor.decrypt(m.getPhone()));
				m.setEmail(AESCryptor.decrypt(m.getEmail()));
				
			} catch (Exception e) {
				
			}
			
			
		}
		
		
		
		request.setAttribute("memberlist", result);  
		// 꼭 memberlist여야함 (memberlist.jsp의 선언문에서 request.getAttribute("memberlist")로 값을 불러오기 때문에
		
		request.getRequestDispatcher("/views/admin/memberlist.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
