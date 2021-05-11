package com.notice.controller;

import java.io.*;

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


@WebServlet("/notice/noticeUpdateEnd.do")
public class NoticeUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public NoticeUpdateEndServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( !ServletFileUpload.isMultipartContent(request) ) { // ???
			
			request.setAttribute("msg", "잘못된 접근 방식 입니다");
			request.setAttribute("loc", "/notice/noticeUpdate.do?no=" + request.getParameter("noticeNo"));
			
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			
			return;
			
		} 
		
		
		String path = getServletContext().getRealPath("/upload/notice/");
		
		int maxSize = 1024*1024*10;
		
		MultipartRequest mr = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		Notice n = new Notice();
		
		n.setNoticeTitle(mr.getParameter("noticeTitle"));
		n.setNoticeWriter(mr.getParameter("noticeWriter"));
		n.setNoticeContent(mr.getParameter("content"));
		
		String filePath = mr.getFilesystemName("up_file");
		
		
		
		// File객체를 이용해서 파일이 있는지 없는지 확인 
		
		File f = mr.getFile("up_file");
		
		
		
		// f가 null이면 파일이 없음, file.length()가 0이면 파일이 없음 
		
		if( f != null && f.length() > 0 ) {
			
			// 새로 추가된 파일이 있음 
			
			// 이전 파일을 삭제 
			
			File deleteFile = new File(path + mr.getParameter("oriFile"));
			
			if( deleteFile.exists() ) {
				
				// deleteFile.delete(); // 지워지면 false? 
				
				System.out.println(deleteFile.delete());
			}
			
		} else {
			
			// 새로 추가된 파일이 없음 
			
			filePath = mr.getParameter("oriFile");
		}
		
		n.setFilePath(filePath);
		n.setNoticeNo(mr.getParameter("noticeNo"));
		
		int result = new NoticeService().noticeUpdate(n);
		
		String msg = "";
		String loc = "";
		
		if( result > 0 ) {
			msg = "공지사항 수정 성공";
			loc = "/notice/noticeList.do";
		} else {
			msg = "공지사항 수정 실패";
			loc = "/notice/noticeUpdate.do?no=" + n.getNoticeNo();
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
