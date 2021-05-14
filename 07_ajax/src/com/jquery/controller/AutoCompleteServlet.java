package com.jquery.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.service.*;
import com.member.model.vo.*;


@WebServlet("/ajax/search.do")
public class AutoCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AutoCompleteServlet() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String keyword = request.getParameter("keyword");
		
		List<Member> list = new AdminService().searchMember("username", keyword, 1, 5);
		
		String csv = "";
		
		for(int i=0; i < list.size(); i++) { 
			if( i != 0 ) {
				csv += ",";
			}
			csv += list.get(i).getUserName();
		}
		
		System.out.println(csv);
		
		response.setContentType("text/csv;charset=utf-8");
		response.getWriter().write(csv);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
