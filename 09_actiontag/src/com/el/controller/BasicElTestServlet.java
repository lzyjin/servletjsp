package com.el.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspaction.model.vo.*;


@WebServlet("/basicEl")
public class BasicElTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BasicElTestServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Person> list = Arrays.asList(new Person[] { new Person("장희웅", 27, "종로"), 
														new Person("양호준", 27, "은평"), 
														new Person("김두호", 28, "서산") });
		
		Person p = new Person("이진주",23, "광주");
		
		request.setAttribute("list", list);
		request.setAttribute("p", p);
		
		request.setAttribute("name", "멍멍이");
		
		request.getSession().setAttribute("name", "유병승");
		getServletContext().setAttribute("email", "prince0324@naver.com");
		
		request.getRequestDispatcher("/views/eltest/dataTest.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
