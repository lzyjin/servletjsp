package com.board.model.service;

import java.sql.*;
import java.util.*;

import com.board.model.dao.*;
import com.board.model.vo.*;

import static com.common.JDBCTemplate.getConnection;
import static com.common.JDBCTemplate.close;
import static com.common.JDBCTemplate.commit;
import static com.common.JDBCTemplate.rollback;

public class BoardService {
	
	
	
	private BoardDao dao = new BoardDao();
	
	

	public List<Board> searchAllBoard(int cPage, int numPerPage) {
		
		Connection conn = getConnection();
		
		List<Board> list = dao.searchAllBoard(conn, cPage, numPerPage);
		
		close(conn);
		
		return list;
	}



	public int countAllBoard() {
		
		Connection conn = getConnection();
		
		int result = dao.countAllBoard(conn);
		
		close(conn);
		
		return result;
	}


	

//	public Board boardView(int boardNo) {
	public Board boardView(int boardNo, boolean readFlag) {
		
		Connection conn = getConnection();
		
		int result = 0;
		
		Board b = dao.boardView(conn, boardNo);
		
		// 조회수 증가시키기 
		
		// 오라클배울때 트리거 마지막부분에서 
		// UPDATE PRODUCT SET STOCK = STOCK + 1 WHERE A112
		
		
		
//		if(b != null) {
		if( !readFlag && b != null ) {
			
			// 조회수 증가 
			result = dao.updateBoardReadCount(conn, boardNo);
		}
		
		if(result > 0) {
			
			commit(conn);
			
		} else {
			
			rollback(conn);
		}
		
		// 게시글 상세페이지에서 새로고침하면 조회수가 계속 증가한다 
		// 해결방법 : 회원이 이 글을 읽었는지 아닌지 쿠키에 저장해서 분기처리 한다 
		
		close(conn);
		
		return b;
	}



	public int insertBoard(Board b) {
		
		Connection conn = getConnection();
		
		int result = 0;
		
		result = dao.insertBoard(conn, b);
		
		if(result > 0) {
			
			commit(conn);
			
		} else {
			
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}


	// 댓글 달기 기능 
	public int insertBoardComment(BoardComment bc) {
		
		Connection conn = getConnection();
		
		int result = dao.insertBoardComment(conn, bc);
		
		if(result > 0) {
			
			commit(conn);
			
		} else {
			
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}



	public List<BoardComment> selectBoardComment(int boardRef) {
		
		Connection conn = getConnection();
		
		List<BoardComment> comments = dao.selectBoardComment(conn, boardRef);
		
		return comments;
	}
	

}
