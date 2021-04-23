package com.user.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UserLoginServlet() {
        
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 1. 클라이언트가 보낸 데이터 받아오기
		
		String userId = request.getParameter("userId");
		String pw = request.getParameter("pw");
		
		
		// 2. 서비스로직 구현
		
		// 클라이언트가 보낸 userId값과 pw값이 DB에 저장되어있는 데이터인지 확인하고
		// 저장되어있으면 로그인 성공, 없으면 로그인 실패 
		
		// 호출 절차 : Servlet -> Service -> Dao -> 
		//				  컨트롤러           모델 
		
		
		// 지금은 간단히 보는거라서 mvc 나누지 않음 
		
		
				// 1. 드라이버 올리기
				// 2. Connection 정보 설정, Connection 객체 생성
				// 3. Connection 객체에서 PreparedStatement 객체 생성, 쿼리문(sql문)설정 
				// 4. ResultSet 에 결과 저장 -> executeQuery() 
				// 5. 그 결과를 가지고 처리
		
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		boolean result = false;
		
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			
			// 아이디가 userId이고 비밀번호가 pw 인 데이터 가져오기
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PWD = ?"); 
			
			pstmt.setString(1, userId); // 첫번째 ? 안에 클라이언트가 입력한 아이디 
			pstmt.setString(2, pw); // 두번째 ? 안에 클라이언트가 입력한 비밀번호 
			

			rs = pstmt.executeQuery();
			
			
			
			if(rs.next()) { // rs가 있으면 {}블럭 안의 코드를 실행하니까  = result라는 변수에 값을 정해서 나중에 그 값에 따라 로그인 여부를 판별
				
				result = true;
			} 
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		String path = "";
		
		
		if(result) {
			
			// 로그인 성공
			// 로그인이 성공하면 그 값을 유지하기 위해 session객체에 데이터를 대입
			
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", userId); // vo 객체
			
			path = "/mainpage.do";
			
			
		} else {
			
			
			// 로그인 실패
			
			request.setAttribute("msg", "로그인 실패! 다시 시도하세요");
			
			path = "/msg.do";
			
			
			
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		
		rd.forward(request, response);
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
