package com.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.board.model.service.*;
import com.board.model.vo.*;


@WebServlet("/board/boardView.do")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardViewServlet() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		int boardNo = Integer.parseInt(request.getParameter("no"));
		
		BoardService service = new BoardService();
		
		
		
		
		// 게시글 상세페이지에서 새로고침하면 조회수가 계속 증가한다 
		// 해결방법 : 회원이 이 글을 읽었는지 아닌지 쿠키에 저장해서 분기처리 한다 
		
		// boardRead : 쿠키에 저장할 값
		// 읽은 게시글의 번호를 저장
		
		
		Cookie[] cookies = request.getCookies();
		
		String boardRead = "";
		
		boolean readFlag = false; // 글 읽지 않은 상태
		
		if(cookies != null) {
			
			for(Cookie c : cookies) {
				
				String name = c.getName(); // 쿠키의 key값 
				String value = c.getValue(); // 쿠키의 value값
				
				if(name.equals("boardRead")) {
					
					boardRead = value;
					
					if(value.contains("|" + boardNo + "|")) { // 이렇게 쓰는 이유 : 사용자가 본 글의 번호를 알기 쉽게 
						// 이렇게 안하면 123456121678 이런식으로 저장돼서 숫자 구분이 안되기때문에
						// |1| |2| 이런식으로 쓰겠다는 얘기 
						
						readFlag = true;
						
						break;
					}
					
				}
			}
			
		} 
		
		// 게시글을 안읽었을 때 돌아갈 실행문 
		// 그래서 조건을 !붙여서 true로 만들어준것 뿐 
		if( !readFlag ) {
			
			// 안읽었다면 기존 boardRead에 추가
			Cookie c = new Cookie("boardRead", boardRead + "|" + boardNo + "|"); // 누적연산 
			
			// 쿠키 유지기간 설정 
			c.setMaxAge(60*60*24);
			
			response.addCookie(c);
		}
		
		
		
		
		
//		Board b = service.boardView(boardNo);
		Board b = service.boardView(boardNo, readFlag);
		
		

		
		request.setAttribute("board", b);
		
		request.getRequestDispatcher("/views/board/boardView.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
