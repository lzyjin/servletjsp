package com.notice.model.dao;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.notice.model.vo.*;
import static com.common.JDBCTemplate.close;

public class NoticeDao {
	
	
	
	private Properties prop = new Properties();
	
	
	public NoticeDao() {
		
		
			String filePath = NoticeDao.class.getResource("/sql/notice_sql.properties").getPath();
			
			try {
				
				prop.load(new FileReader(filePath));
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
	}
	
	



	public int countAllNotice(Connection conn) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("countAllNotice"));
			
		
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				result = rs.getInt(1);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(rs);
			close(pstmt);
		}
		
		
		
		return result;
	}

	
	public List<Notice> searchAllNotice(Connection conn, int cPage, int numPerPage) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Notice> list = new ArrayList<Notice>();
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("searchAllNotice"));
			
			
			
			pstmt.setInt(1, (cPage-1)*numPerPage+1 );
			pstmt.setInt(2, cPage*numPerPage);
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Notice n = new Notice();
				
				n.setNoticeNo(rs.getString("notice_no"));
				n.setNoticeTitle(rs.getString("notice_title"));
				n.setNoticeWriter(rs.getString("notice_writer"));
				n.setNoticeContent(rs.getString("notice_content"));
				n.setNoticeDate(rs.getDate("notice_date"));
				n.setFilePath(rs.getString("filepath"));
				
				list.add(n);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(rs);
			close(pstmt);
		}
		
		return list;
	}





	public Notice noticeView(Connection conn, String noticeNo) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		Notice n = null;
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("noticeView"));
			
			pstmt.setString(1, noticeNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				n = new Notice();
				
				n.setNoticeTitle(rs.getString("notice_title"));
				n.setNoticeContent(rs.getString("notice_content"));
				n.setNoticeDate(rs.getDate("notice_date"));
				n.setNoticeWriter(rs.getString("notice_writer"));
				n.setFilePath(rs.getString("filepath"));
				n.setNoticeNo(rs.getString("notice_no"));
				
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(rs);
			close(pstmt);
		}
		
		
		return n;
	}


}
