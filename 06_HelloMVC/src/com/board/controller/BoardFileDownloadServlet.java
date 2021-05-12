package com.board.controller;

import java.io.*;
import java.net.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/board/fileDownload.do") // 파일다운로드도 하나의 메소드로 묶을 수 있음 
public class BoardFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardFileDownloadServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String rename = request.getParameter("rename");
		String oriname = request.getParameter("oriname");
		
		String path = getServletContext().getRealPath("/upload/board/");
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path + rename)); // 실제파일이름은 rename된 이름이기 때문
		
		
		// 한글 인코딩 
		
		String header = request.getHeader("user-agent");
		boolean isMS = header.contains("Trident") || header.contains("MSIE");
		
		String reFile = "";
		if(isMS) {
			
			reFile = URLEncoder.encode(oriname, "utf-8");
			reFile = oriname.replaceAll("\\+", "%20");
			
		} else {
			
			reFile = new String(oriname.getBytes("utf-8"), "ISO-8859-1");
		}
		
		
		response.setContentType("application/octet-stream");		
		response.setHeader("Content-Disposition", "attachment;filename=" + reFile);
		
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		
		int read = -1;
		
		while((read = bis.read()) != -1) {
			
			bos.write(read);
		}
		
		bos.close();
		bis.close();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
