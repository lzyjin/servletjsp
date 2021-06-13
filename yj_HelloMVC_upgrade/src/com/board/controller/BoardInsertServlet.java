package com.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.*;

import com.board.model.service.*;
import com.board.model.vo.*;
import com.common.*;
import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;


@WebServlet("/board/boardInsert.do")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardInsertServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		// 파일 업로드 처리 
		
		if( !ServletFileUpload.isMultipartContent(request) ) {
			
			request.setAttribute("msg", "잘못된 접근이네요 ^^");
			request.setAttribute("loc", "/views/board/boardList.jsp");
			
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			
			return;
		}
		
		
		
		String filePath = request.getServletContext().getRealPath("/upload/board");
		
		int maxPostSize = 1024*1024*100;
		
		String encode = "utf-8";
		
		
		
//		MultipartRequest mr = new MultipartRequest(request, filePath, maxPostSize, encode, new DefaultFileRenamePolicy());
		MultipartRequest mr = new MultipartRequest(request, filePath, maxPostSize, encode, new MyRename());
		
		

		Board b = new Board();
		

		
		b.setBoardTitle(mr.getParameter("boardTitle"));
		b.setBoardWriter(mr.getParameter("boardWriter"));
		b.setBoardContent(mr.getParameter("content"));
		b.setBoardOriginalFilename(mr.getOriginalFileName("up_file")); // 사용자에게 원본파일이름을 보여줘야할 필요가 있을 때 저장함 
		b.setBoardRenamedFilename(mr.getFilesystemName("up_file"));
		
		
				System.out.println("BoardInsertServlet에서 테스트, b : " + b);
		
				
		BoardService service = new BoardService();
		
		
		// db에 데이터 삽입 
		int result = service.insertBoard(b);
		
		
		String msg = "";
		String loc = "";
		
		
		if(result > 0) {
			
			msg = "게시글 등록 성공";
			loc = "/board/boardList.do";
			
		} else {
			
			msg = "게시글 등록 실패";
			loc = "/views/board/boardForm.jsp";
		}
		
		
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
