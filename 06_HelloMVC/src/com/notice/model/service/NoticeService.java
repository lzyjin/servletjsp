package com.notice.model.service;

import java.sql.*;
import java.util.*;

import com.notice.model.dao.*;
import com.notice.model.vo.*;
import static com.common.JDBCTemplate.getConnection;

public class NoticeService {
	
	
	private NoticeDao dao = new NoticeDao();
	

	
	public int countAllNotice() {
		
		Connection conn = getConnection();
		
		int result = dao.countAllNotice(conn);
		
		return result;
	}
	
	public List<Notice> searchAllNotice(int cPage, int numPerPage) {
		
		Connection conn = getConnection();
		
		List<Notice> list = dao.searchAllNotice(conn, cPage, numPerPage);
		
		return list;
	}

	public Notice noticeView(String noticeNo) {
		
		Connection conn = getConnection();
		
		Notice n = dao.noticeView(conn, noticeNo);
		
		return n;
		
	}

}
