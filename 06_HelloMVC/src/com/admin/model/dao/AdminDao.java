package com.admin.model.dao;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.member.model.vo.*;
import static com.common.JDBCTemplate.*;

public class AdminDao {
	
	private Properties prop = new Properties();
	
	
	public AdminDao() {
		
		String filePath = AdminDao.class.getResource("/sql/admin_sql.properties").getPath();
		
		try {
			
			prop.load(new FileReader(filePath));
			
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}

//	public ArrayList<Member> selectAllMember(Connection conn) {
	public ArrayList<Member> selectAllMember(Connection conn, int cPage, int numPerPage) {	
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<Member> list = new ArrayList<Member>();
		
		Member m = null;
		
		String sql = prop.getProperty("selectAllMember");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			// 0507 페이징처리부분 추가한 코드 
//			pstmt.setInt(1, 1);
//			pstmt.setInt(2, 5);
//			pstmt.setInt(1, 6);
//			pstmt.setInt(2, 10);
			pstmt.setInt( 1, ( cPage - 1 ) * numPerPage + 1 );
			pstmt.setInt( 2, cPage * numPerPage);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				m = new Member();
				
				
				m.setAddress(rs.getString("address"));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setEnrollDate(rs.getDate("enrolldate"));
				m.setGender(rs.getString("gender"));
				m.setHobby(rs.getString("hobby"));
				m.setMemberId(rs.getString("userid"));
//				m.setPassword(rs.getString("password"));
				m.setPhone(rs.getString("phone"));
				m.setUserName(rs.getString("username"));
				
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

	public int countMember(Connection conn) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		int countMember = 0;
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("countMember"));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				// 알아두기
				countMember = rs.getInt(1);
				// 또는 count(*)에 별칭부여해서 그걸 사용해도 됌 
				
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		} finally {
			
			close(pstmt);
		}
		
		return countMember;
	}

	
	
	public List<Member> searchMember(Connection conn, String searchType, String keyword) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
//		String sql = "";
		String sql = prop.getProperty("searchMember");
		
		List<Member> list = new ArrayList();
		
		switch (searchType) {
//			case "userId":
//				sql = "searchMemberId";
//			break;
//			case "userName":
//				sql = "searchMemberUserName";
//			break;
//			case "gender":
//				sql = "searchMemberGender";
//			break;
		}
		
		try {
			
//			pstmt = conn.prepareStatement(prop.getProperty(sql));
			pstmt = conn.prepareStatement(sql.replace("@", searchType));
			
//			pstmt.setString(1, keyword);
			pstmt.setString(1, "%" + keyword + "%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Member m = new Member();
				
				m.setAddress(rs.getString("address"));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setEnrollDate(rs.getDate("enrolldate"));
				m.setGender(rs.getString("gender"));
				m.setHobby(rs.getString("hobby"));
				m.setMemberId(rs.getString("userid"));
				m.setPhone(rs.getString("phone"));
				m.setUserName(rs.getString("username"));
				
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
