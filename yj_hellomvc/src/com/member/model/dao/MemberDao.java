package com.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import static com.member.common.JDBCTemplate.close;

import com.member.model.vo.Member;

public class MemberDao {

	public Member login(Connection conn, String id, String pw) {
		
		Properties prop = new Properties();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Member m = null;
		
		
		
		String filePath = MemberDao.class.getResource("/sql/member_sql.properties").getPath();
		
		try {
			
			m = new Member();
			
			prop.load(new FileReader(filePath));
			
			String sql = prop.getProperty("selectMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
//				m = new Member(); // 위치 상관 없네 하하하ㅏ 
				
				m.setMemberId(rs.getString("userid"));
				m.setPassword(rs.getString("password"));
				m.setUserName(rs.getString("username"));
				m.setGender(rs.getString("gender"));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				
				
			}
			
		} catch (IOException | SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(rs);
			close(pstmt);
		}
		
		
		return m;
		
	}
	
	

}
