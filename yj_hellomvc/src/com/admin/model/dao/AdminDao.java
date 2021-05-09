package com.admin.model.dao;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.member.model.dao.*;
import com.member.model.vo.*;
import static com.common.JDBCTemplate.close;

public class AdminDao {
	
	
	// 필드
	private Properties prop = new Properties();
	
	
	// 생성자 
	public AdminDao() {
		
		
			String filePath = MemberDao.class.getResource("/sql/admin_sql.properties").getPath();
			
			try {
				
				prop.load(new FileReader(filePath));
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
		
	}
	
	// 메소드 

//	public List<Member> memberList(Connection conn) {
//		
//		PreparedStatement pstmt = null;
//		
//		List<Member> list = new ArrayList<Member>();
//		
//		ResultSet rs = null;
//		
//		try {
//			
//			pstmt = conn.prepareStatement(prop.getProperty("selectAllMember"));
//			
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				
//				Member m = new Member();
//				
//				m.setMemberId(rs.getString("userid"));
//				m.setPassword(rs.getString("password"));
//				m.setUserName(rs.getString("username"));
//				m.setGender(rs.getString("gender"));
//				m.setAge(rs.getInt("age"));
//				m.setEmail(rs.getString("email"));
//				m.setPhone(rs.getString("phone"));
//				m.setAddress(rs.getString("address"));
//				m.setHobby(rs.getString("hobby"));
//				m.setEnrollDate(rs.getDate("enrolldate"));
//				
//				list.add(m);
//				
//			}
//			
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//			
//		} finally {
//			
//			close(rs);
//			close(pstmt);
//		}
//		
//		return list;
//	}
	
	
	
	public List<Member> memberList(Connection conn, int cPage, int numPerPage) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		List<Member> list = new ArrayList<Member>();
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("selectAllMember"));
			
//			pstmt.setString(1, 시작데이터번호 );
//			pstmt.setString(2, 끝데이터번호 );
			
			pstmt.setInt(1, ( cPage -1 )*numPerPage + 1);
			pstmt.setInt(2, cPage * numPerPage );
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Member m  = new Member();
				
				m.setMemberId(rs.getString("userid"));
				m.setUserName(rs.getString("username"));
				m.setGender(rs.getString("gender"));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enrolldate"));
				
				list.add(m);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	
	
	public int countAllMember(Connection conn) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("countAllMember"));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				result = Integer.parseInt(rs.getString(1));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	public List<Member> searchMember(Connection conn, String searchType, String searchKeyword) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		List<Member> list = new ArrayList<Member>();
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("searchMember").replace("@", searchType));
			
			pstmt.setString(1, "%" + searchKeyword + "%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Member m = new Member();
				
				m.setMemberId(rs.getString("userid"));
				m.setUserName(rs.getString("username"));
				m.setGender(rs.getString("gender"));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enrolldate"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(rs);
			close(pstmt);
		}
		
		
		
		return list;
	}

	

}
