package com.member.model.service;

import static com.member.common.JDBCTemplate.*;

import java.sql.*;
import java.util.*;

import com.member.model.dao.MemberDao;
import com.member.model.vo.Member;

import oracle.jdbc.proxy.annotation.Pre;

public class MemberService {
	
	// conn 객체 생성 

	public Member login(String id, String pw) {
		
		Connection conn = createConnection();
		
		MemberDao dao = new MemberDao();
		
		Member m = dao.login(conn, id, pw);
		
		close(conn);
		
		return m;
	}
	
	

	public int enroll(Member m) {
		
		Connection conn = createConnection();
		
//		MemberDao dao = new MemberDao();
		
		int result = new MemberDao().enroll(conn, m);
		
		if(result > 0) {
			
			commit(conn);
			
		} else {
			
			rollback(conn);
		}
		
		
		close(conn);
		
		return result;
		
	}



	public Member idCheck(String inputId) {
		
		Connection conn = null;
		
		conn = createConnection();
		
		MemberDao mDao = new MemberDao();
		Member m = mDao.idCheck(conn, inputId);
		
		close(conn);
		
		return m;
		
	}
	
	

}
