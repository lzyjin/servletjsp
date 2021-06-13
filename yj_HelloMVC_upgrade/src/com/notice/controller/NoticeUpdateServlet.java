package com.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.notice.model.service.*;
import com.notice.model.vo.*;


@WebServlet("/notice/noticeUpdate.do")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public NoticeUpdateServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 페이지 전환용 서블릿 
		
		// 사용자가 수정할 공지사항글의 번호 noticeNo 를 받아옴 
		
		String no = request.getParameter("no");
		
		Notice n = new NoticeService().noticeView(no);
		
		request.setAttribute("notice", n);
		
		
		request.getRequestDispatcher("/views/notice/noticeUpdate.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
