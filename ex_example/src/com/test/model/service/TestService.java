package com.test.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.test.model.dao.TestDao;
import com.test.model.vo.Member;

public class TestService {
	
	private TestDao dao = new TestDao();
	
	public TestService() {
		
	}
	
	public Member login(String userId, String pwd) {
		
		
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			
		
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		
		Member m = dao.login(conn, userId, pwd);
		
		try {
			
			conn.close();
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return m;
	}

}
