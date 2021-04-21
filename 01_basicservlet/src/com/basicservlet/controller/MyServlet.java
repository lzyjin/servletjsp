package com.basicservlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet{

	private static final long serialVersionUID = -2035811487182963152L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.write("<html>");
		out.write("<head>");
		out.write("<style>");
		out.write("h1{ background-color : green;");
		out.write("color : white; }");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>");
		out.write("<h1>안녕 나는 김예진이야 반가워 나 서블릿 잘 만들지?</h1>");
		out.write("</body>");
		out.write("<html>");
		
	}

}
