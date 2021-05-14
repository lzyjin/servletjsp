package com.board.controller;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/board/boardFileDownload.do")
public class BoardFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardFileDownloadServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fileName = request.getParameter("fileName");
		
		// fileName과 일치하는 파일 서버의 저장경로에서 파일을 가져오기
		String path = getServletContext().getRealPath("/upload/board/");
		
		String filePath = path + fileName;
	
		
		// 스트림 열어야함 
		FileInputStream fis = new FileInputStream(filePath);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		// 클라이언트에게 보낼 파일명을 인코딩 처리 (한글깨지지않게 )
		// 파일 MIME 타입 설정, header 설정 
		String fileRename = "";
		String header = request.getHeader("user-agent");
		boolean isMSIE = header.indexOf("MSIE") != -1 || header.indexOf("Trident") != -1;
		
		if(isMSIE) {
			fileRename = URLEncoder.encode(fileName, "utf-8").replaceAll("\\+", "%20");
		} else {
			fileRename = new String(fileName.getBytes("utf-8"), "ISO_8859_1");
		}
		
		// 응답메세지 작성
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;filename=" + fileRename);
		
		// 클라이언트와 연결되어 스트림으로 파일을 전송하기
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		
		// 스트림 닫기
		int read = -1;
		while( (read=bis.read()) != -1) {
			bos.write(read);
		}
		bis.close();
		bos.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
