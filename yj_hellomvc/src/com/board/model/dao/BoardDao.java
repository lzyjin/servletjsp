package com.board.model.dao;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.board.model.vo.*;

import static com.common.JDBCTemplate.close;

public class BoardDao {
	
	
	
	private Properties prop = new Properties();
	
	
	
	public BoardDao() {
		
		String filePath = BoardDao.class.getResource("/sql/board_sql.properties").getPath();
		
				System.out.println("BoardDao에서 테스트, filePath : " + filePath);
				//  /Users/yejin/servletJsp/yj_hellomvc/web/WEB-INF/classes/sql/board_sql.properties
		
		try {
			
			prop.load(new FileReader(filePath));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	
	
	public int countAllBoard(Connection conn) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		int count = 0;
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("countAllBoard"));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(rs);
			close(pstmt);
		}
		return count;
		
	}



	public List<Board> searchAllBoard(Connection conn, int cPage, int numPerPage) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		List<Board> list = new ArrayList<Board>();
		
		Board b = null;
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("selectAllBoard"));
			
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				b = new Board();
				
				b.setBoardNo(rs.getInt("BOARD_NO"));
				b.setBoardTitle(rs.getString("BOARD_TITLE"));
				b.setBoardContent(rs.getString("BOARD_CONTENT"));
				b.setBoardDate(rs.getDate("BOARD_DATE"));
				b.setBoardOriginalFilename(rs.getString("BOARD_ORIGINAL_FILENAME"));
				b.setBoardReadCount(rs.getInt("BOARD_READCOUNT"));
				b.setBoardRenamedFilename(rs.getString("BOARD_RENAMED_FILENAME"));
				b.setBoardWriter(rs.getString("BOARD_WRITER"));
				
				list.add(b);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(rs);
			close(pstmt);
		}
		
		return list;
	}



	public Board selectBoard(Connection conn, int boardNo) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		Board b = null;
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("selectBoard"));
			
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				b = new Board();
				
				b.setBoardNo(rs.getInt("BOARD_NO"));
				b.setBoardTitle(rs.getString("BOARD_TITLE"));
				b.setBoardContent(rs.getString("BOARD_CONTENT"));
				b.setBoardDate(rs.getDate("BOARD_DATE"));
				b.setBoardOriginalFilename(rs.getString("BOARD_ORIGINAL_FILENAME"));
				b.setBoardReadCount(rs.getInt("BOARD_READCOUNT"));
				b.setBoardRenamedFilename(rs.getString("BOARD_RENAMED_FILENAME"));
				b.setBoardWriter(rs.getString("BOARD_WRITER"));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(rs);
			close(pstmt);
		}
		
		return b;
	}



	public int insertBoard(Connection conn, Board b) {
		
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("insertBoard"));
			
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardWriter());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getBoardOriginalFilename());
			pstmt.setString(5, b.getBoardRenamedFilename());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}

}
