package com.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.service.*;
import com.board.model.vo.*;


@WebServlet("/board/boardView.do")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardViewServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardNo = Integer.parseInt(request.getParameter("no"));
		
		// 글번호로 db에 서치해서 뽑아옴 -> jsp에 보여줄 용도 -> jsp로 이동 
		
		BoardService service = new BoardService();
		
		Board b = service.selectBoard(boardNo);
		
		request.setAttribute("board", b);
	
		request.getRequestDispatcher("/views/board/boardView.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
