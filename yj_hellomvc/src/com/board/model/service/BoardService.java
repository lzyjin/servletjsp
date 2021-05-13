package com.board.model.service;

import java.sql.*;
import java.util.*;

import com.board.model.dao.*;
import com.board.model.vo.*;

import static com.common.JDBCTemplate.createConnection;
import static com.common.JDBCTemplate.commit;
import static com.common.JDBCTemplate.rollback;
import static com.common.JDBCTemplate.close;

public class BoardService {
	
	
	private BoardDao dao = new BoardDao();
	

	public int countAllBoard() {
		
		Connection conn = createConnection();
		
		int count = dao.countAllBoard(conn);
		
		close(conn);
		
		return count;
	}


	public List<Board> searchAllBoard(int cPage, int numPerPage) {
		
		Connection conn = createConnection();
		
		List<Board> list = dao.searchAllBoard(conn, cPage, numPerPage);
		
		close(conn);
		
		return list;
	}


	public Board selectBoard(int boardNo) {
		
		Connection conn = createConnection();
		
		Board b = dao.selectBoard(conn, boardNo);
		
		close(conn);
		
		return b;
	}


	public int insertBoard(Board b) {
		
		Connection conn = createConnection();
		
		int result = dao.insertBoard(conn, b);
		
		if(result > 0) commit(conn);
		else rollback(conn);

		close(conn);
		
		return result;
	}

}
