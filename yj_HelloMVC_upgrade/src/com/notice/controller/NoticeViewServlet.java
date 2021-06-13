package com.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.notice.model.service.*;
import com.notice.model.vo.*;

@WebServlet("/notice/noticeView.do")
public class NoticeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public NoticeViewServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		// 해당 글을 db에서 가져오기 위해 pk인 notice_no를 프론트에서 가져와야함 
		// 쿼리스트링으로 받을 수 있음 
		
		String noticeNo = request.getParameter("noticeNo");
		
		NoticeService service = new NoticeService();
		
		Notice n = service.noticeView(noticeNo);
		
		request.setAttribute("notice", n);
		
		
		request.getRequestDispatcher("/views/notice/noticeView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
