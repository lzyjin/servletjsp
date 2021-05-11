package com.notice.model.service;

import java.sql.*;
import java.util.*;

import com.notice.model.dao.*;
import com.notice.model.vo.*;
import static com.common.JDBCTemplate.getConnection;
import static com.common.JDBCTemplate.close;
import static com.common.JDBCTemplate.commit;
import static com.common.JDBCTemplate.rollback;

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

	public int writeNotice(Notice n) {
		
		Connection conn = getConnection();
		
		int result = dao.writeNotice(conn, n);
		
		
		if(result > 0) {
			
			commit(conn);
		} else {
			
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int noticeUpdate(Notice n) {
		
		Connection conn = getConnection();
		
		int result = dao.noticeUpdate(conn, n);
		
		if(result > 0) {
			
			commit(conn);
		} else {
			
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

}
