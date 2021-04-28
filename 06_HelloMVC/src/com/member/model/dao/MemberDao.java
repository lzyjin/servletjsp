package com.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.common.JDBCTemplate.close;
import com.member.model.vo.Member;

public class MemberDao {
	
	Properties prop = new Properties();
	
	public MemberDao() {
		
		String filePath = MemberDao.class.getResource("/sql/sql.properties").getPath();
	
		try {
			
			prop.load(new FileReader(filePath));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
			
		
	}
	

	public Member login(Connection conn, String userId, String password) {
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// Member m = new Member();
		Member m = null;
		
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("selectMember"));
			
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				m = new Member();
				
				m.setMemberId(rs.getString("userid")); // rs가 쿼리 결과니까 테이블의 컬럼명으로 탐색
				m.setPassword(rs.getString("password"));
				m.setUserName(rs.getString("username"));
				m.setGender(rs.getString("gender"));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enrolldate"));
				
				System.out.println("fsdfdfsdfsd");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(rs);
			close(pstmt);
		}
	
		
		return m;
	}

}
