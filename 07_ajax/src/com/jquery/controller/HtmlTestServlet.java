package com.jquery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ajax/htmlTest.do")
public class HtmlTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public HtmlTestServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setAttribute("msg", "토요일은 정처기 시험날 ㅎ ");
		
		request.getRequestDispatcher("/views/result/htmlTest.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
