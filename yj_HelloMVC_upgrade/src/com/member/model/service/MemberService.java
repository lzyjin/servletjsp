package com.member.model.service;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.session.*;

import static com.common.JDBCTemplate.getConnection;
import static com.common.JDBCTemplate.close;
import static com.common.JDBCTemplate.commit;
import static com.common.JDBCTemplate.rollback;

import com.member.model.dao.MemberDao;
import com.member.model.vo.Member;

import static com.mybatis.common.SqlSessionTemplate.getSession;

public class MemberService {

	
	
	private MemberDao dao = new MemberDao();
	
	
	
	public MemberService() {
		
	}
	
	

	
	public Member login(Member idpw) {
		
		SqlSession session = getSession();
		
		Member m = dao.login(session, idpw);
		
		session.close();
		
		return m;
		
	}

	
	
	
	public int insertMember(Member m) {
		
		SqlSession session = getSession();
		
		int result = dao.insertMember(session, m);
		
		if(result > 0 ) {
			session.commit();
		} else {
			session.rollback();
		}
		return result;
		
		
	}

	
	 
	public Member checkDuplicateId(String userId) {
		
		SqlSession session = getSession();
		
		Member m = dao.checkDuplicateId(session, userId);
		
		session.close();
		
		return m;
	}
	
	

	public int deleteMember(String userId) {
		
		SqlSession session = getSession();
		
		int result = dao.deleteMember(session, userId);
		
		if(result > 0 ) {
			session.commit();
		} else {
			session.rollback();
		}
		return result;
	}
	
	
	

	public int updateMember(Member m) {
		
		SqlSession session = getSession();
		
		int result = dao.updateMember(session, m);
		
		if(result > 0 ) {
			session.commit();
		} else {
			session.rollback();
		}
		return result;
	}

	
	public int updatePassword(String userId, String newPw) {
		
		SqlSession session = getSession();
		
		int result = dao.updatePassword(session, userId, newPw);
		
		if(result > 0 ) {
			session.commit();
		} else {
			session.rollback();
		}
		return result;
	}

	/* 
	public int updatePassword(String oldPw, String newPw) {
		
		Connection conn = getConnection();
		
		int result = dao.updatePassword(conn, oldPw, newPw);
		
		if(result > 0) {
			
			commit(conn);
			
		} else {
			
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	*/
	
	/*
	// 선생님 풀이 
	public int updatePassword(String userId, String newPw) {
		
		Connection conn = getConnection();
		
		int result = dao.updatePassword(conn, userId, newPw);
		
		if(result > 0) {
			
			commit(conn);
			
		} else {
			
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	*/
	
	

}
