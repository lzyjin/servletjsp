package com.admin.controller;

import java.io.IOException;
import java.util.*;

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
		
		
		String searchKeyword = request.getParameter("searchKeyword");
		String searchType = request.getParameter("searchType");
		
		List<Member> result = new AdminService().searchMember(searchType, searchKeyword);
		
		for(Member m : result) {
			
			try {
				
				m.setPhone(AESCryptor.decrypt(m.getPhone()));
				m.setEmail(AESCryptor.decrypt(m.getEmail()));
				
			} catch (Exception e) {
				
			}
			
			
		}
		
		request.setAttribute("memberlist", result);
		
		request.getRequestDispatcher("/views/admin/memberlist.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
