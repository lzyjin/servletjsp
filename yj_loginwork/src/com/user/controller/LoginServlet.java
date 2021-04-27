package com.user.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public LoginServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String pw = request.getParameter("pw");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		boolean result = false;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KH", "KH");
			
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PW = ?");
			
			pstmt.setString(1, userId);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			// 이렇게 하면 예외가 발생했을 때  아래 코드를 실행하지 않으니까 trycatch문 바깥에 작성해야한다 
//			if(rs.next()) {
//				
//				System.out.println("로그인 성공");
//				
//				HttpSession session = request.getSession();
//				
//				session.setAttribute("user", userId);
//				
//				response.sendRedirect("/mainpage.do");
//			
//			} else {
//				
//				System.out.println("로그인 실패");
//				
//			}
			
			if(rs.next()) {
				
				result = true;
			}
		
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		
		if(result) {
			
			
			System.out.println("로그인 성공");
			
			HttpSession session = request.getSession();
			
			session.setAttribute("user", userId);
			
			response.sendRedirect(request.getContextPath() + "/mainpage.do");
			
			
		} else {
			
			System.out.println("로그인 실패");
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
