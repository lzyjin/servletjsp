package com.admin.model.service;

import java.sql.*;
import java.util.*;

import com.admin.model.dao.*;
import com.member.model.vo.*;

import static com.common.JDBCTemplate.*;


public class AdminService {
	
	
	
	private AdminDao dao = new AdminDao();

	public AdminService() {
		
	}
	
	public ArrayList<Member> selectAllMember() {
		
		Connection conn = getConnection();
		
		ArrayList<Member> list = dao.selectAllMember(conn);
		
		close(conn);
		
		return list;		
	}

}
