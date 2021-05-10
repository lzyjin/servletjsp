package com.notice.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.notice.model.service.*;
import com.notice.model.vo.*;

import oracle.net.aso.*;


@WebServlet("/notice/noticeList.do")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public NoticeServlet() {
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		// 페이징처리 
		
		int cPage;
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}
		
					System.out.println("NoticeServlet test / cPage : " + cPage);
		
		int numPerPage;
		
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		} catch (NumberFormatException e) {
			numPerPage = 5;
		}
		
					System.out.println("NoticeServlet test / numPerPage : " + numPerPage);
		
					
					
		NoticeService service = new NoticeService();
							
					
		int totalData = service.countAllNotice();
		
					System.out.println("NoticeServlet test / totalData : " + totalData);
		
					
					
		int totalPage = (int) Math.ceil((double)totalData / numPerPage);
		
					System.out.println("NoticeServlet test / totalPage : " + totalPage);
		
					
					
		int pageBarSize = 5;
		
		int pageNo = ((cPage -1)/pageBarSize)*pageBarSize+1;
		
		int pageEnd = cPage*pageBarSize;
		
		
		List<Notice> list = service.searchAllNotice(cPage, numPerPage);
		
		
		String pageBar = "";
		
		
		// [이전]버튼
		if(pageNo == 1) {
			pageBar += "<span> [이전] </span>";
		} else {
			pageBar += "<a href='" + request.getContextPath() 
					+ "/noticeList.do?cPage=" + ( pageNo -1 )
					+ "&numPerPage=" + numPerPage + "'> [이전] </a>";
		}
		
		// 페이지번호 1 2 3 4 5 
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			
			if(cPage == pageNo) {
				pageBar += "<span>" + pageNo + "</span>";
			} else {
				pageBar += "<a href='" + request.getContextPath() 
				+ "/noticeList.do?cPage=" + pageNo
				+ "&numPerPage=" + numPerPage + "'> " 
				+ pageNo +" </a>";
			}
			pageNo++;
		}
		
		// [다음]버튼 
		if(pageNo > totalPage) {
			pageBar += "<span> [다음] </span>";
		} else {
			pageBar += "<a href='" + request.getContextPath() 
					+ "/noticeList.do?cPage=" + pageNo
					+ "&numPerPage=" + numPerPage + "'> [다음] </a>";
		}
		
		
		
		request.setAttribute("pageBar", pageBar);
		
		
		
		request.setAttribute("noticeList", list);
		
		
		request.getRequestDispatcher("/views/notice/noticeList.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
