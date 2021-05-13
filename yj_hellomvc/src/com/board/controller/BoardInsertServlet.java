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
import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;


@WebServlet("/board/insertBoard.do")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BoardInsertServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파일업로드처리 먼저 해야함 
		
		// cos.jar 라이브러리가 제공하는 클래스 이용 
		
		// 1. 바이너리 파일로 넘어왔는지 확인 ( 사용자가 보낸 데이터가 multipart 형식인지 확인 ) 
		if( !ServletFileUpload.isMultipartContent(request) ) {
			
			request.setAttribute("msg", "파일 등록 오류");
			request.setAttribute("loc", "/board/boardList.do");
			
			request.getRequestDispatcher("/views/common/alertmsg.jsp").forward(request, response);
			
			return; // 메소드의 실행을 완전히 중단하기 위해 
			
		}
		
		// 2. 파일업로드를 위한 값을 설정하기 
		// 2-1. 파일을 업로드할 위치 (절대경로로)
		// String path = getServletContext().getRealPath("/"); //  내 어플리케이션의 context root (path : web폴더 위치) 
		String path = getServletContext().getRealPath("/upload/board/");
		
				System.out.println("BoardInsertServlet에서 테스트, path : " + path);
		
		// 2-2. 저장할 파일의 최대크기 
		int postMaxSize = 1024*1024*10;
		
		
		
		// 2-3. 문자열 인코딩
		// String 으로 들어오는게 아니라 바이너리이기 때문에 파싱할때 깨질수 있으므로 
		String encode = "utf-8";
		
		// 2-4 업로드된 파일의 이름을 재정의 rename
		// 여러 사용자가 올린 파일이름이 중복되면 덮어쓰기 되기때문에 
		// 새 파일 이름은 직접 작성할수도 있고, 클래스를 사용할 수도 있다 : DefaultFileRenamePolicy
		
		
		// 3. 파일 업로드 
		// MultipartRequest 클래스를 생성해서 
		// request로 전송된 데이터가 지정한 경로에 저장된다 
		// (HttpServletRequest, 파일경로, 파일최대크기, 인코딩, rename규칙 )
		MultipartRequest mr = new MultipartRequest(request, path, postMaxSize, encode, new DefaultFileRenamePolicy());
		
		// 서버 설정에 가서 Server Options에서 Save modules without publishing 체크하기
		
		
		Board b = new Board();
		
		b.setBoardTitle(mr.getParameter("boardTitle"));
		b.setBoardWriter(mr.getParameter("boardWriter"));
		b.setBoardContent(mr.getParameter("content"));
		b.setBoardOriginalFilename(mr.getFilesystemName("uploadFile"));
		
				System.out.println("BoardInsertServlet에서 테스트, b : " + b);
		
		int result = new BoardService().insertBoard(b);
		
		String msg = "";
		String loc = "/board/boardList.do";
		
		if(result >0) {
			msg = "게시글 등록 성공";
		} else {
			msg = "게시글 등록 실패";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/views/common/alertmsg.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
