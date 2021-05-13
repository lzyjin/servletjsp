package com.javascript.controller;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ajaxTest.do")
public class JsAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public JsAjaxServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String userId = request.getParameter("id");
		
		response.setCharacterEncoding("utf-8");
		
		// ajax 요청에 대한 응답을 하는 서블릿
		PrintWriter out = response.getWriter();
		out.print("ajax요청에 대한 응답입니다 : " + userId);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
