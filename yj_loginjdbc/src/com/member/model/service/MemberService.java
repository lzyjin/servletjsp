package com.member.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.member.model.dao.MemberDao;
import com.member.model.vo.Member;

public class MemberService {
	
	
	MemberDao dao = new MemberDao();
	
	
	public MemberService() {
		
	}
	
	
	public Member login(String id, String pw) {
		
		
		Connection conn = null;
		
		
		try {
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			
			
			
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
			
		}
		
		Member m = dao.login(conn, id, pw);
		
		
		
		try {
			
			conn.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return m;
		
	}

}
