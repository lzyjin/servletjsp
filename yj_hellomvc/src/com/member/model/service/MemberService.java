package com.member.model.service;

import static com.common.JDBCTemplate.*;

import java.sql.*;
import java.util.*;

import com.admin.controller.*;
import com.member.model.dao.MemberDao;
import com.member.model.vo.Member;

import oracle.jdbc.proxy.annotation.Pre;

public class MemberService {
	
	private MemberDao dao = new MemberDao();
	
	// conn 객체 생성 

	public Member login(String id, String pw) {
		
		Connection conn = createConnection();
		
		Member m = dao.login(conn, id, pw);
		
		close(conn);
		
		return m;
	}
	
	

	public int enroll(Member m) {
		
		System.out.println("service m : " + m);
		
		Connection conn = createConnection();
				
		int result = dao.enroll(conn, m);
		
		if(result > 0) {
			
			commit(conn);
			
		} else {
			
			rollback(conn);
		}
		
		
		close(conn);
		
		return result;
		
	}



	public Member idCheck(String userId) {
		
		Connection conn = createConnection();

		Member m = dao.idCheck(conn, userId);
		
		close(conn);
		
		return m;
		
	}



	public int updateMember(Member m) {
		
		Connection conn = createConnection();
		
		int result = dao.updateMember(conn, m);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}



	public int deleteMember(String userId) {
		
		Connection conn = createConnection();
		
		int result = dao.deleteMember(conn, userId);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
		
		
	}



	public int updatePassword(String userId, String newPw) {
		
		Connection conn = createConnection();
		
		int result = dao.updatePassword(conn, userId, newPw);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		
		return result;
	}



	
	

}
