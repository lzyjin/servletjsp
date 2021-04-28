package com.member.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static com.common.JDBCTemplate.getConnection;
import static com.common.JDBCTemplate.close;
import com.member.model.dao.MemberDao;
import com.member.model.vo.Member;

public class MemberService {
	
	
	MemberDao dao = new MemberDao();
	
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
	
	

}
