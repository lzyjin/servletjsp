package com.test.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.test.model.vo.Member;



public class TestDao {
	
	public Member login(Connection conn, String userId, String pwd) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PWD = ?";
		
		Member m = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, pwd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				m = new Member();
				
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setAge(rs.getInt("age"));
				
			}
				
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			
			try {
				
				rs.close();
				pstmt.close();
				
			} catch(SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return m;
	
		
	}

}
