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
	
		try {
			
			prop.load(new FileReader(filePath));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	

	
	
	
	
	
	public List<Board> searchAllBoard(Connection conn, int cPage, int numPerPage) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		List<Board> list = new ArrayList<Board>();
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("searchAllBoard"));
			
			// 이부분 까먹지 말기 
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Board b = new Board();
				
				b.setBoardNo(rs.getInt("BOARD_NO"));
				b.setBoardTitle(rs.getString("BOARD_TITLE"));
				b.setBoardWriter(rs.getString("BOARD_WRITER"));
				b.setBoardContent(rs.getString("BOARD_CONTENT"));
				b.setBoardOriginalFilename(rs.getString("BOARD_ORIGINAL_FILENAME"));
				b.setBoardRenamedFilename(rs.getString("BOARD_RENAMED_FILENAME"));
				b.setBoardDate(rs.getDate("BOARD_DATE"));
				b.setBoardReadCount(rs.getInt("BOARD_READCOUNT"));
				
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


	
	
	
	
	
	public int countAllBoard(Connection conn) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("countAllBoard"));
			
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







	public Board boardView(Connection conn, int boardNo) {
		
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
				b.setBoardWriter(rs.getString("BOARD_WRITER"));
				b.setBoardContent(rs.getString("BOARD_CONTENT"));
				b.setBoardOriginalFilename(rs.getString("BOARD_ORIGINAL_FILENAME"));
				b.setBoardRenamedFilename(rs.getString("BOARD_RENAMED_FILENAME"));
				b.setBoardDate(rs.getDate("BOARD_DATE"));
				b.setBoardReadCount(rs.getInt("BOARD_READCOUNT"));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(rs);
			close(pstmt);
		}
		
		return b;
	}







	public int updateBoardReadCount(Connection conn, int boardNo) {
		
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("updateBoardReadCount"));
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(pstmt);
		}
		
		return result;
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






	// 댓글 달기
	public int insertBoardComment(Connection conn, BoardComment bc) {
		
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(prop.getProperty("insertBoardComment"));
			
			pstmt.setInt(1, bc.getBoardCommentLevel());
			pstmt.setString(2, bc.getBoardCommentWriter());
			pstmt.setString(3, bc.getBoardCommentContent());
			pstmt.setInt(4, bc.getBoardRef());
			
//			pstmt.setInt(5, bc.getBoardCommentRef());
//			pstmt.setInt( 5, bc.getBoardCommentRef() == 0 ? null : bc.getBoardCommentRef() ); int형은 null값을 저장할 수 없음 
			pstmt.setString( 5, bc.getBoardCommentRef() == 0 ? null : String.valueOf(bc.getBoardCommentRef()) );
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}







	public List<BoardComment> selectBoardComment(Connection conn, int boardRef) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		List<BoardComment> comments = new ArrayList<BoardComment>();
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("selectBoardComment"));
			
			pstmt.setInt(1, boardRef);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardComment bc = new BoardComment();
				
				bc.setBoardCommentContent(rs.getString("BOARD_COMMENT_CONTENT"));
				bc.setBoardCommentDate(rs.getDate("BOARD_COMMENT_DATE"));
				bc.setBoardCommentLevel(rs.getInt("BOARD_COMMENT_LEVEL"));
				bc.setBoardCommentNo(rs.getInt("BOARD_COMMENT_NO"));
				bc.setBoardCommentRef(rs.getInt("BOARD_COMMENT_REF"));
				bc.setBoardCommentWriter(rs.getString("BOARD_COMMENT_WRITER"));
				bc.setBoardRef(rs.getInt("BOARD_REF"));
				
				comments.add(bc);
			}
			
		} catch (SQLException e) {
	
			e.printStackTrace();
			
		} finally {
			
			close(rs);
			close(pstmt);
		}
		
		
		return comments;
	}

}
