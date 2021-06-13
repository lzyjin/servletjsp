package com.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.service.*;
import com.board.model.vo.*;


@WebServlet("/board/insertBoardComment.do")
public class InsertBoardCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InsertBoardCommentServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int boardRef = Integer.parseInt(request.getParameter("boardRef"));
		int boardCommentRef = Integer.parseInt(request.getParameter("boardCommentRef"));
		int boardCommentLevel = Integer.parseInt(request.getParameter("boardCommentLevel"));
		
		String commentWriter = request.getParameter("commentWriter");
		String content = request.getParameter("content");
		
		
		BoardComment bc = new BoardComment();
		
		bc.setBoardCommentContent(content);
		bc.setBoardCommentLevel(boardCommentLevel);
		bc.setBoardCommentRef(boardCommentRef);
		bc.setBoardCommentWriter(commentWriter);
		bc.setBoardRef(boardRef);
		
		
		int result = new BoardService().insertBoardComment(bc);
		
		
		String msg = "";
		String loc = "/board/boardView.do?no=" + boardRef; // 쿼리스트링으로 no넣어줘야함 ( 보드뷰 서블릿에서 확인 가능 );
		
		
		if(result > 0) {
			
			// 댓글데이터를 댓글 테이블에 삽입 성공
			msg = "댓글 등록 성공";
			
		} else {
			
			msg = "댓글 등록 실패";
		}
		
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
