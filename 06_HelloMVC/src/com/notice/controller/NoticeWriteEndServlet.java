package com.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.*;

import com.notice.model.service.*;
import com.notice.model.vo.*;
import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;


@WebServlet("/notice/noticeWriteEnd.do")
public class NoticeWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public NoticeWriteEndServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
//				String noticeTitle = request.getParameter("noticeTitle");
//				String noticeWriter = request.getParameter("noticeWriter");
//				String filePath = request.getParameter("up_file");
//				String noticeContent = request.getParameter("content");
		
//		Notice n = new Notice(null, noticeTitle, noticeContent, null, filePath);
//		Notice n = new Notice();
		
//				n.setNoticeTitle(noticeTitle);
//				n.setNoticeWriter(noticeWriter);
//				n.setNoticeContent(noticeContent);
//				n.setFilePath(filePath);
		
		
		
		
		
		
		
		// 파일 업로드 처리
		// cos.jar 라이브러리에서 제공하는 클래스를 이용한다 
		
		// 바이너리 파일로 넘어온것을 분기처리해서 String으로 받아올 수 있게, 파일은 폴더에 업로드 될 수 있게 
		
		
		// 1. 클라이언트가 보낸 데이터의 인코딩방식이 multipart방식인지 확인하기
		
			if( !ServletFileUpload.isMultipartContent(request) ) {
				
				// 잘못된 요청이기 때문에 
				request.setAttribute("msg", "공지사항 작성 오류 [form:enctype] 관리자에게 문의하세요 :(");
				request.setAttribute("loc", "/notice/noticeList.do");
				
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	
				return;
			}
		
			
			
			
		// 2. 파일 업로드 처리에 필요한 값을 설정하기 
		
		// 2-1. 파일 업로드 위치는 절대경로로 가져와야한다 
			
			// String path = request.getServletContext().getRealPath("/");  // 기본 Context Root
			
			// System.out.println("NoticeWriteEndServlet 에서 테스트 path : " + path);
			// NoticeWriteEndServlet 에서 테스트 path : /Users/yejin/servletJsp/06_HelloMVC/web/
			
			
			String path = request.getServletContext().getRealPath("/upload/notice/"); 
			
			System.out.println("NoticeWriteEndServlet 에서 테스트 path : " + path);
			// NoticeWriteEndServlet 에서 테스트 path : /Users/yejin/servletJsp/06_HelloMVC/web/upload/notice/
			
			
		
		// 2-2. 저장할 파일에 대한 최대 크기를 설정하기 
		
			int maxSize = 1024*1024*10; // 10MB 
			
			
			
		// 3. 문자열을 인코딩할 방식 지정하기
			
			String encode = "utf-8";
			
			
			
		// 4. 업로드된 파일의 이름을 재정의하기 (rename)
		// 해야하는 이유 : 파일이름이 중복되면 덮어쓰기 되니까 
		// 개발자가 직접 작성할 수도 있고, 
		// 기본으로 제공하는 클래스(DefaultFileRenamePolicy)를 이용할 수도 있다. 
		
			
		
		// 5. 파일 업로드 하기 
		// MultipartRequest 클래스를 생성해서 request로 전송된 데이터가 지정한 경로에 저장한다 
		// MultipartRequest 클래스의 생성자는 5개의 매개변수를 가진다
		// HttpServletRequest, 파일 경로, 파일 최대 크기, 인코딩, rename 규칙 
			
			MultipartRequest mr = new MultipartRequest(request, path, maxSize, encode, new DefaultFileRenamePolicy());
		
		
			
		// 글쓰기에서 파일첨부했더니 web/upload/notice폴더 안에 내가 첨부한 파일이 저장되었다 !! 
			
			
		
			Notice n = new Notice();
			
			n.setNoticeTitle(mr.getParameter("noticeTitle"));
			n.setNoticeWriter(mr.getParameter("noticeWriter"));
			n.setNoticeContent(mr.getParameter("content"));
			
			
			
		// 파일명을 DB에 저장해야한다 
		// rename된 파일을 가져오려면 
			
//			n.setFilePath(mr.getParameter("up_file"));
			n.setFilePath(mr.getFilesystemName("up_file"));
			System.out.println(mr.getFilesystemName("up_file2"));
			
			
		
		
		System.out.println("NoticeWriteEndServlet에서 테스트 n : " + n);
		
		NoticeService service = new NoticeService();
		
		int result = service.writeNotice(n);
		
		String msg = "";
		String loc = "";
		
		if(result > 0) {
			
			msg = "글쓰기 성공";
//			loc = "/views/notice/noticeList.jsp"; 이게 아니지ㅠㅠㅠㅠ 
			loc = "/notice/noticeList.do";
			
		} else {
			
			msg = "글쓰기 실패";
//			loc = "/views/notice/noticeForm.jsp";
			loc = "/notice/noticeWrite.do";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
