package com.admin.controller;

import java.io.IOException;
import java.security.*;
import java.util.*;

import javax.crypto.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.service.*;
import com.common.*;
import com.member.model.vo.*;


@WebServlet("/admin/searchMember.do")
public class SearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SearchMemberServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 클라이언트가 보낸 값 가져오기
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		
		// String numPerPage = request.getParameter("numPerPage");
		
		
		
		System.out.println("SearchMemberServlet에서 테스트 searchType : " + searchType);
		System.out.println("SearchMemberServlet에서 테스트 searchKeyword : " + searchKeyword);
		
		
		
		// 숙제 1 
		// 관리자의 회원관리 게시판에서  조회결과를 페이징처리하기 
		
		int cPage;
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}
		
		
		int numPerPage;
		
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
			System.out.println("SearchMemberServlet에서 테스트 numPerPage : " + numPerPage);
		} catch (NumberFormatException e) {
			numPerPage = 10;
		}
		
//		int totalData = new AdminService().countMember();
		int totalData = new AdminService().countSearchMember(searchType, searchKeyword);
		
		System.out.println("SearchMemberServlet에서 테스트 totalData : " + totalData);
		
		int totalPage = (int) Math.ceil((double)totalData / numPerPage);
		
		
		int pageBarSize = 4;
		
		int pageNo =  ( ( cPage - 1 ) / pageBarSize ) * pageBarSize + 1 ;
		
		int pageEnd = pageNo + pageBarSize - 1;
		
		
		
//		List<Member> result = new AdminService().searchMember(searchType, searchKeyword);
		List<Member> result = new AdminService().searchMember(searchType, searchKeyword, cPage, numPerPage);

		
		
		
		String pageBar = "";
		
		// [이전]버튼 
		if(pageNo == 1) {
			
			pageBar += "<span> [이전] </span>";
			
		} else {
			
			pageBar += "<a href='" + request.getContextPath() 
					+ "/admin/searchMember.do?cPage=" + (pageNo-1) 
					+ "&numPerPage=" + numPerPage 
					+ "&searchType=" + searchType 
					+ "&searchKeyword=" + searchKeyword 
					+ "'> [이전] </a>";
			
		}
		
		// 페이지번호 
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			
			if(pageNo == cPage) {
				
				pageBar += "<span> "+ pageNo +" </span>";
				
			} else {
				
				pageBar += "<a href='" + request.getContextPath() 
				+ "/admin/searchMember.do?cPage=" + pageNo 
				+ "&numPerPage=" + numPerPage 
				+ "&searchType=" + searchType 
				+ "&searchKeyword=" + searchKeyword 
				+ "'> " + pageNo + " </a>";
				
			}
			
			pageNo++;
			
		}
		
		
		// [다음]버튼
		if(pageNo > totalPage) {
			
			pageBar += "<span> [다음] </span>";
			
		} else {
			
			pageBar += "<a href='" + request.getContextPath() 
			+ "/admin/searchMember.do?cPage=" + pageNo 
			+ "&numPerPage=" + numPerPage 
			+ "&searchType=" + searchType 
			+ "&searchKeyword=" + searchKeyword 
			+ "'> " + "[다음]" + " </a>";
			
		}
		
		request.setAttribute("pageBar", pageBar);
		
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("cPage", cPage);
	
		
		
		for(Member m : result) {
			try {
				
				m.setPhone(AESCryptor.decrypt(m.getPhone()));
				m.setEmail(AESCryptor.decrypt(m.getEmail()));
				
			} catch (Exception e) {
				
			}
			
		}
		
		
		
		request.setAttribute("memberlist", result);  
		// 꼭 memberlist여야함 (memberlist.jsp의 선언문에서 request.getAttribute("memberlist")로 값을 불러오기 때문에
		
		request.getRequestDispatcher("/views/admin/memberlist.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
