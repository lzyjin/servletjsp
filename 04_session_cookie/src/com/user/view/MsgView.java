package com.user.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/msg.do")
public class MsgView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MsgView() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=utf-8");
		
		String html = "<html>";
		
		html += "<body>";
		html += "<script>";
		html += "alert('" + request.getAttribute("msg") + "');";
		html += "location.replace('" + request.getContextPath() + "/mainpage.do');";
		html += "</script>";
		html += "</body>";
		html += "</html>";
		
		response.getWriter().write(html);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
