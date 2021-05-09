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
	
	
	
	
	
//	public ArrayList<Member> selectAllMember() {
	public ArrayList<Member> selectAllMember(int cPage, int numPerPage) {
		
		Connection conn = getConnection();
		
//		ArrayList<Member> list = dao.selectAllMember(conn);
		ArrayList<Member> list = dao.selectAllMember(conn, cPage, numPerPage);
		
		close(conn);
		
		return list;		
	}

	
	
	
	
	public int countMember() {
		
		Connection conn = getConnection();
		
		int countMember = dao.countMember(conn);
		
		close(conn);
		
		return countMember;
	}





	// public List<Member> searchMember(String searchType, String keyword) {
	public List<Member> searchMember(String searchType, String keyword, int cPage, int numPerPage) {
		
		Connection conn = getConnection();
		
		// List<Member> list = dao.searchMember(conn, searchType, keyword);
		List<Member> list = dao.searchMember(conn, searchType, keyword, cPage, numPerPage);
		
		close(conn);
		
		return list;
	}
	
	
	

}
