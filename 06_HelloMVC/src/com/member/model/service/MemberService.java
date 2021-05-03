package com.member.model.service;

import java.sql.Connection;
import java.util.Properties;

import static com.common.JDBCTemplate.getConnection;
import static com.common.JDBCTemplate.close;
import static com.common.JDBCTemplate.commit;
import static com.common.JDBCTemplate.rollback;

import com.member.model.dao.MemberDao;
import com.member.model.vo.Member;

public class MemberService {
	
	
	
	
	
	private MemberDao dao = new MemberDao();
	
	public MemberService() {
		
	}


	public Member login(String userId, String password) {
		
		Connection conn = getConnection();
		
		Properties prop = new Properties();
		
		
		
		Member m = dao.login(conn, userId, password);
		
		
		if(conn != null) {	
			
			close(conn);
			
		}
		
		
		return m;
	}


	public int insertMember(Member m) {
		
		Connection conn = getConnection();
		
		Properties prop = new Properties();
		
		int result = dao.insertMember(conn, m);
		
		
		if(result>0) commit(conn); // 커밋 안하면 db에도 반영 안되고 로그인도 안되니까 꼭 커밋해야해 !!!!!
		
		else rollback(conn); // 커밋, 롤백은 service에서 하는것 /!! service에서 conn 객체 처리를 하는거라서 
		
		
		return result;
		
		
	}


	public Member checkDuplicateId(String userId) {
		
		Connection conn = getConnection();
		
		Member m = dao.checkDuplicateId(conn, userId);
		
		close(conn);
		
		return m;
	}



	public int updateMember(Member m) {
		
		Connection conn = getConnection();
		
		int result = dao.updateMember(conn, m);
		
		if(result > 0) {
			
			commit(conn);
			
		} else {
			
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}


	public int deleteMember(String userId) {
		
		Connection conn = getConnection();
		
		int result = dao.deleteMember(conn, userId);
		
		if(result > 0) {
			
			commit(conn);
			
		} else {
			
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	

}
