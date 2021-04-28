package com.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Properties;

public class JDBCTemplate {
	

	public static Connection getConnection() {
		
		Properties prop = new Properties();
		
		Connection conn = null;
		
		String filePath = JDBCTemplate.class.getResource("/driver/driver.properties").getPath();
		
		
		try {
			
			prop.load(new FileReader(filePath));
			
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String pw = prop.getProperty("pw");
			
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, pw);
			
			conn.setAutoCommit(false);
			
			
			
		} catch (IOException | ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return conn;
	}
	
	
		public static void close(Connection conn) {
		
			try {
				
				if(conn != null && !conn.isClosed()) {
					
					conn.close();
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	
		public static void close(ResultSet rs) {
		
			try {
				
				if(rs != null && !rs.isClosed()) {
					
					rs.close();
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

		public static void close(PreparedStatement pstmt) {
	
			try {
				
				if(pstmt != null && !pstmt.isClosed()) {
					
					pstmt.close();
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	
	
		}

}
