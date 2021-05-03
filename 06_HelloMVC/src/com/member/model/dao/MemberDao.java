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
	
	private int result;
	
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


	public int insertMember(Connection conn, Member m) {
		
		PreparedStatement pstmt = null;
		
		boolean flag = false;
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("insertMember"));
			
			// result = pstmt.executeUpdate(); 이 위치 아님...
			
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			result = pstmt.executeUpdate();
			
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(pstmt);
			
		}
		return result;
		
		
	}


	public Member checkDuplicateId(Connection conn, String userId) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Member m = null;
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("selectMemberId"));
			
			pstmt.setString(1, userId);
			
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
			
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(rs);
			close(pstmt);
		}
		
		return m;
	}


	public int memberInfoUpdate(Connection conn, Member m) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("updateMember"));
			
//			pstmt.setString(1, x);
//			pstmt.setString(1, x);
//			pstmt.setString(1, x);
//			pstmt.setString(1, x);
//			pstmt.setString(1, x);
//			pstmt.setString(1, x);
//			pstmt.setString(1, x);
//			pstmt.setString(1, x);
//			pstmt.setString(1, x);
//			pstmt.setString(1, x);
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return result;
	}


	public int updateMember(Connection conn, Member m) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("updateMember"));
			
			pstmt.setString(1, m.getPassword());
			pstmt.setString(2, m.getUserName());
			pstmt.setString(3, m.getGender());
			pstmt.setInt(4, m.getAge());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getPhone());
			pstmt.setString(7, m.getAddress());
			pstmt.setString(8, m.getHobby());
			pstmt.setString(9, m.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}


	public int deleteMember(Connection conn, String userId) {
		
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("deleteMember"));
			
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(pstmt);
		}
		
		
		return result;
	}

}
