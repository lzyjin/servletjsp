package com.basicservlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// 어노테이션 방식으로 서블릿을 매핑하기 
// @WebServlet("/anno.do") 
// 또는
@WebServlet(name = "annoservlet", urlPatterns = {"/anno.do"})

public class AnnotationServlet extends HttpServlet{

	private static final long serialVersionUID = 7194336576515816528L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("어노테이션으로 연결되니?");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.write("<html>");
		out.write("<body>");
		out.write("<h2>이건 어노테이션 방식으로 실행되니?</h1>");
		out.write("</body>");
		out.write("</html>");
	}

}
