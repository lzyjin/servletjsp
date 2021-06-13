package com.notice.controller;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/notice/notice/fileDownload.do")
public class NoticeFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public NoticeFileDownloadServlet() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파일 다운로드 기능 
		
		// 1. 클라이언트가 쿼리스트링으로 보낸 파일명 가져오기 
		
		// 2. 클라이언트가 보낸 파일명과 일치하는 파일을 서버의 저장경로에서 가져오기 ( InputStream 이용 ) 
		
		// 3. 클라이언트에게 보낼 파일명을 인코딩처리하기 ( 한글파일명이 깨지지 않게 ) 
		
		// 4. 응답 MIME 타입을 설정하기 ( header 설정 )
		
		// 5. 클라이언트와 연결된 스트림으로 파일을 전송

		// 6. 스트림 닫기 
		
		
		
		
		
		// 1. 클라이언트가 쿼리스트링으로 보낸 파일명 가져오기 
		
			String fileName = request.getParameter("fname");
			
			
		
		// 2. 클라이언트가 보낸 파일명과 일치하는 파일을 서버의 저장경로에서 가져오기 ( InputStream 이용 ) 
			
			String path = request.getServletContext().getRealPath("/upload/notice/");
			
			String filePath = path + fileName;
			
			FileInputStream fis = new FileInputStream(filePath);
			
			BufferedInputStream bis = new BufferedInputStream(fis);
			
				
		// 3. 클라이언트에게 보낼 파일명을 인코딩처리하기 ( 한글파일명이 깨지지 않게 ) 
			
			String fileRname = "";
			
			String header = request.getHeader("user-agent");
			
			boolean isMSIE = header.indexOf("MSIE") != -1 || header.indexOf("Trident") != -1;
			
			if( isMSIE ) {
				
				fileRname = URLEncoder.encode(fileName, "utf-8").replace("\\+", "%20");
				
			} else {
				
				fileRname = new String(fileName.getBytes("utf-8"), "ISO_8859_1");
			}
			
				
		// 4. 응답 MIME 타입을 설정하기 ( header 설정 ) ??
			
		// 응답 메세지 작성
			
			response.setContentType("application/octet-stream");
			
			response.setHeader("Content-disposition", "attachment; filename=" + fileRname);
				
			
		// 5. 클라이언트와 연결된 스트림으로 파일을 전송
			
			ServletOutputStream sos = response.getOutputStream();
			
			BufferedOutputStream bos = new BufferedOutputStream(sos);
				
			int read = -1; // 아무 의미 없는 값 
			
			while( (read = bis.read()) != -1 ) {
				
				bos.write(read);
			}
			
			
		// 6. 스트림 닫기 
			
			bis.close();
			bos.close();
			
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
