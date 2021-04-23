package com.filter.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/wrapper.do")
public class WrapperTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public WrapperTestServlet() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
			System.out.println("test utf-8");
			System.out.println("git test");
		
		String info = request.getParameter("info");
		
		String msg = request.getParameter("msg");
		
		
		System.out.println("info : " + info);
		System.out.println("msg : " + msg);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
