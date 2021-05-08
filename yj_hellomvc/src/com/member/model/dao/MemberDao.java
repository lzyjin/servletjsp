package com.member.model.dao;

import static com.common.JDBCTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.member.model.vo.Member;

public class MemberDao {
	
	
	// 필드 
	Properties prop = new Properties();
	
	
	
	// 생성자 
	public MemberDao() {
		
		
			String filePath = MemberDao.class.getResource("/sql/member_sql.properties").getPath();
			
			try {
				
				prop.load(new FileReader(filePath));
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}

	}
	
	

	// 메소드 
	public Member login(Connection conn, String id, String pw) {
		

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		Member m = null;
		
		
		//		m = new Member(); 안돼 
		
		try {

			//			m = new Member(); 안돼 
			
			
			String sql = prop.getProperty("selectMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			
			
//			m = null;
			
//			split point
			// result (query)
			while(rs.next()) {
				
				
				m = new Member(); // while문 바깥에 이 코드를 작성하면 m객체가 이미 생성한 상태라서 
								  // 쿼리의 결과가 없을 때도 m객체는 이미 있어서 
								  // servlet에서 m이 null인지 아닌지로 로그인 성공 여부를 따지기 때문에
								  // 로그인이 안되어야 하는데 null이 아니여서 로그인을 시켜버림 
								  // => null님 이 뜨는것 
				
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
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(rs);
			close(pstmt);
		}
		
		
		return m;
		
	}

	
	
	public int enroll(Connection conn, Member m) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
	
		String sql= prop.getProperty("insertMember");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
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



	public Member idCheck(Connection conn, String userId) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectMemberId");
		
		Member m = null;
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				m = new Member();
				
				m.setMemberId(rs.getString("userid"));
				m.setPassword(rs.getString("password")); // 여기서부터 
				m.setUserName(rs.getString("username"));
				m.setGender(rs.getString("gender"));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby")); // 여기까지 안썼더니 회원정보불러오는 jsp에서 nullpointerexception 발생함..ㅎ 
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(rs);
			close(pstmt);
		}
		
		return m;
		
		
		
		
	}



	public int updateMember(Connection conn, Member m) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		int result = 0;
		
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("selectMemberId"));
			
			System.out.println(" dao m : " + m);
			
			pstmt.setString(1, m.getMemberId());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // rs.next()의 반환은 boolean이니까 조건문에 쓸 수 있어
				
				pstmt = conn.prepareStatement(prop.getProperty("updateMember"));
				
//				pstmt.setString(1, m.getPassword());
//				pstmt.setString(2, m.getUserName());
//				pstmt.setString(3, m.getGender());
//				pstmt.setInt(4, m.getAge());
//				pstmt.setString(5, m.getEmail());
//				pstmt.setString(6, m.getPhone());
//				pstmt.setString(7, m.getAddress());
//				pstmt.setString(8, m.getHobby());
//				pstmt.setString(9, m.getMemberId());
				
//				pstmt.setString(1, m.getPassword());
				pstmt.setString(1, m.getUserName());
				pstmt.setString(2, m.getGender());
				pstmt.setInt(3, m.getAge());
				pstmt.setString(4, m.getEmail());
				pstmt.setString(5, m.getPhone());
				pstmt.setString(6, m.getAddress());
				pstmt.setString(7, m.getHobby());
				pstmt.setString(8, m.getMemberId());
				
				result = pstmt.executeUpdate();
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		} finally {
			
			close(rs);
			close(pstmt);
			
		}
		
		
		// 1. db에 이 회원이 있는지 pk인 id로 확인 
		
		
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



	public int updatePassword(Connection conn, String userId, String newPw) {
		
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		// updatePassword = UPDATE MEMBER SET PASSWORD = ? WHERE USERID = ?
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("updatePassword"));
			
			pstmt.setString(1, newPw);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}

	
	

}
