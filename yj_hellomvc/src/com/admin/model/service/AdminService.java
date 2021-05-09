package com.admin.model.service;

import java.sql.*;
import java.util.*;

import com.admin.model.dao.*;
import static com.common.JDBCTemplate.createConnection;
import com.member.model.vo.*;

public class AdminService {
	
	
	
	private AdminDao dao = new AdminDao();
	

	
//	public List<Member> memberList() {
//		
//		Connection conn = createConnection();
//		
//		List<Member> list = dao.memberList(conn);
//		
//		return list;
//	}
	
	
	
	public List<Member> memberList(int cPage, int numPerPage) {
		
		Connection conn = createConnection();
		
		List<Member> list = dao.memberList(conn, cPage, numPerPage);
		
		return list;
	}

	
	

	public int countAllMember() {
		
		Connection conn = createConnection();
		
		int result = dao.countAllMember(conn);
		
		return result;
	}




	public List<Member> searchMember(String searchType, String searchKeyword) {
		
		Connection conn = createConnection();
		
		List<Member> list = dao.searchMember(conn, searchType, searchKeyword);
		
		return list;
	}


	

}
