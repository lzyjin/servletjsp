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

	public ArrayList<Member> selectAllMember(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<Member> list = new ArrayList<Member>();
		
		Member m = null;
		
		String sql = prop.getProperty("selectAllMember");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
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

}
