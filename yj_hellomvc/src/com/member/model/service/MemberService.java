package com.member.model.service;

import static com.member.common.JDBCTemplate.createConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.member.model.dao.MemberDao;
import com.member.model.vo.Member;

public class MemberService {
	
	// conn 객체 생성 

	public Member login(String id, String pw) {
		
		Connection conn = createConnection();
		
		MemberDao dao = new MemberDao();
		
		Member m = dao.login(conn, id, pw);
		
		return m;
	}
	
	

}
