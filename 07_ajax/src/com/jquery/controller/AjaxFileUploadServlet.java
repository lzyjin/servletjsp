package com.jquery.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;


@WebServlet("/fileUpload")
public class AjaxFileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AjaxFileUploadServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String path = getServletContext().getRealPath("/upload/");
		
		MultipartRequest mr = new MultipartRequest(request, path, 1024*1024*10, "utf-8", new DefaultFileRenamePolicy());
		
				System.out.println(mr.getParameter("name"));
				
				
		
		// 다중업로드(multiple)한 파일 이름 받아오기 
		Enumeration e = mr.getFileNames();
		// upfile1 :111
		// upfile2 :222
		
		
		List<String > fileNames = new ArrayList<String>();
		
		while(e.hasMoreElements()) {
			fileNames.add(mr.getFilesystemName((String)e.nextElement()));
		}
		
				System.out.println(fileNames); 
				// 사진 3개 동시에 업로드하면 [juliaRoberts.jpg, mattDamon.jpg, parkBogum.jpg]
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
