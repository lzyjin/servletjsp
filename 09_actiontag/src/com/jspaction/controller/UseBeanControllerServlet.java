package com.jspaction.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspaction.model.vo.*;


@WebServlet("/useBeanTest")
public class UseBeanControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UseBeanControllerServlet() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Person p = new Person();
		
		p.setName("김예진");
		p.setAge(26);
		p.setAddr("서울시");
		
		request.setAttribute("person", p);
		
		request.getRequestDispatcher("/views/useBeanTest.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
