package com.ajax.controller;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.service.*;
import com.member.model.vo.*;


@WebServlet(name="ajaxMmeber", urlPatterns="/memberInfo")
public class AjaxMemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AjaxMemberInfoServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		Member m = new MemberService().login(userId, password);
		
		PrintWriter out = response.getWriter();
		
		out.append(m.getMemberId() + m.getPassword() + m.getUserName() + m.getAge());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
