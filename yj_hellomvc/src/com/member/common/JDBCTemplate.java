package com.member.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {
	
	// 드라이버 불러오기, conn 객체 생성할 준비, conn 객체 close, commit, rollback 메소드  (이 클래스는 공용 메소드를 가지고 있다 )
	
	/*
	  
	      / : 루트 ( 최상위 ) 

   		 ./ : 현재 위치

   		../ : 현재 위치의 상단 폴더

		ex) index.php가 C:\index\a에 위치한다면,

      			여기서 / 는 C:

               	./ 는 a

              	../ 는 index라는 것.
              	
          - 3가지를 간단히 정리하자면, 
          
				   1  '/'    -> 가장 최상의 디렉토리로 이동한다.(Web root)
				   2  './'   -> 파일이 현재 디렉토리를 의미한다.
				   3  '../'  -> 상위 디렉토리로 이동한다.
				
			- 만약 두단계 상위 디렉토리로 이동하려면
				   
				   '../../' 이렇게 사용하면 된다.

	 */
	
	public static Connection createConnection() {
		
		Connection conn= null;
		
		Properties prop = new Properties();
		
		String filePath = JDBCTemplate.class.getResource("/driver/driver.properties").getPath(); 
		
		// System.out.println(filePath);
		
		// filePath = 루트폴더 아래에서 driver 폴더 안에 있는 driver.properties 의 절대경로
		
		
		try {
			
			prop.load(new FileReader(filePath));
			
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("userId");
			String password = prop.getProperty("userPw");
			
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, password);
			
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
	
	
	
	public static void close(PreparedStatement pstmt) {
		
		try {
			
			if(pstmt != null && !pstmt.isClosed()) {
				
				pstmt.close();
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
	
	public static void commit(Connection conn) {
		
		try {
			
			conn.commit();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		
		try {
			
			conn.rollback();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	
//	public static void main(String[] args) {
//		
//		createConnection();
//		
//		
//	}

}
