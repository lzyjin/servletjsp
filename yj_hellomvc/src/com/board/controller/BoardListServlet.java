package com.board.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.service.*;
import com.board.model.vo.*;


@WebServlet("/board/boardList.do")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardListServlet() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 페이징처리 
		// db에서 뽑아와서 jsp에 넘겨주는 역할 
		
		BoardService service = new BoardService();
		
		int cPage;
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}
		
		int numPerPage;
		
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		} catch (NumberFormatException e) {
			numPerPage = 5;
		}
		
		int totalData = service.countAllBoard();
		int totalPage = (int) Math.ceil((double)totalData/numPerPage);
		
				System.out.println("BoardListServlet에서 totalData : " + totalData);
				System.out.println("BoardListServlet totalPage : " + totalPage);
		
		int pageBarSize = 5;
		
		int pageNo = ((cPage -1)/pageBarSize)*pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize -1;
		
		String pageBar = "";
		
		
		// 이전 버튼 
		if(pageNo == 1) {
			
			pageBar += "<span> [이전] </span>";
			
		} else {
			
			pageBar += "<a href='" + request.getContextPath() 
					+ "/board/boardList.do?cPage=" + (pageNo-1)
					+ "&numPerPage=" + numPerPage 
					+ "'> [이전] </a>";
		}
		
		
		// 페이지 번호
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			
			if(pageNo == cPage) {
				
				pageBar += "<span> "+ pageNo +" </span>";
				
			} else {
				
				pageBar += "<a href='" + request.getContextPath() 
				+ "/board/boardList.do?cPage=" + pageNo
				+ "&numPerPage=" + numPerPage 
				+ "'> " + pageNo + " </a>";
			}
			
			pageNo++;
		}
		
		
		// 다음 버튼 
		if(pageNo > totalPage) {
			
			pageBar += "<span> [다음] </span>";
			
		} else {
			
			pageBar += "<a href='" + request.getContextPath() 
			+ "/board/boardList.do?cPage=" + pageNo
			+ "&numPerPage=" + numPerPage 
			+ "'> [다음] </a>";
			
		}
		
		
		List<Board> list = service.searchAllBoard(cPage, numPerPage);
		
		
		request.setAttribute("boardList", list);
		request.setAttribute("pageBar", pageBar);
		
		request.getRequestDispatcher("/views/board/boardList.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
