package com.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.member.model.vo.Member;

public class MemberDao {

	public Member login(Connection conn, String id, String pw) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member m = null;
		
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PWD = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id); //첫번째 위치홀더 ? 에  string값을 지정해주는건데 그게 id변수안에 담겨있으니까 이렇게 작성
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				
				m = new Member();
				
				m.setId(rs.getString("MEMBER_ID")); // 테이블의 컬럼명
				m.setPw(rs.getString("MEMBER_PWD"));
				m.setName(rs.getString("MEMBER_NAME"));
				m.setAge(rs.getInt("AGE"));
			
				
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				rs.close();
				pstmt.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
			
		
		return m;
		
	}
}
	
	


