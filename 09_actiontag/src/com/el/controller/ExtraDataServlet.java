package com.el.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/extraData")
public class ExtraDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExtraDataServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie c = new Cookie("test","오늘여기까지");
		
		c.setMaxAge(24*60*60);

		response.addCookie(c);
		
		request.getRequestDispatcher("/views/eltest/extraData.jsp").forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
