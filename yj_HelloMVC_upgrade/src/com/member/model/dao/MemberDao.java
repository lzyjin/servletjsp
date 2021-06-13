package com.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.session.*;

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
	
	
	public int insertMember(SqlSession session, Member m) {
		
		return session.insert("member.insertMember", m);
	}

	
	
	public Member login(SqlSession session, Member idpw) {
		
		return session.selectOne("member.login", idpw);
	}

	
	public Member checkDuplicateId(SqlSession session, String userId) {
		
		return session.selectOne("member.checkDuplicateId", userId);
	}
	
	public int deleteMember(SqlSession session, String userId) {
		
		return session.delete("member.deleteMember", userId);
	}

	
	public int updateMember(SqlSession session, Member m) {
		
		return session.update("member.updateMember", m);
	}

	
	public int updatePassword(SqlSession session, String userId, String newPw) {
		
		Member m = Member.builder().userid(userId).password(newPw).build();
			int result = session.update("member.updatePassword", m);
			System.out.println(m);
			System.out.println("myBatisDao"+result);
		return result;
	}
	
	
//	public int updatePassword(Connection conn, String oldPw, String newPw) {
//		
//		PreparedStatement pstmt = null;
//		
//		int result = 0;
//		
//		try {
//			
//			pstmt = conn.prepareStatement(prop.getProperty("updateMemberPw"));
//			
//			pstmt.setString(1, newPw);
//			pstmt.setString(2, oldPw);
//			
//			result = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//			
//		} finally {
//			
//			close(pstmt);
//		}
//		
//		return result;
//		
//		
//	}


	
	
	/*
	// 선생님 풀이
	public int updatePassword(Connection conn, String userId, String newPw) {
		
		PreparedStatement pstmt = null;
		
		int result = 0;
		
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
	 */

}
