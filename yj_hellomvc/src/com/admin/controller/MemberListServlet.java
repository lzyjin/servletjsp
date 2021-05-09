package com.admin.controller;

import java.io.IOException;
import java.security.*;
import java.util.*;

import javax.crypto.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.admin.model.service.*;
import com.common.*;
import com.member.model.vo.*;


@WebServlet("/admin/memberlist.do")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MemberListServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 아이디가 admin인 사람만 접근할 수 있게 해야함 
		// 로그인이 안되었을 때도 접근 불가능하게 해야함 
		
		// session을 가져올 때 getSession(true || false) 
		// true ->  session이 있으면 그 세션을 가져오고, 없으면 생성
		// false -> session이 있으면 그 세션을 가져오고, 없으면 null을 반환
		
		HttpSession session = request.getSession(false);
		
		
		Member login_member = (Member) session.getAttribute("login_member");
		
		
		if(session == null 
				|| session.getAttribute("login_member") == null 
				|| !login_member.getMemberId().equals("admin")) {
			
			
			request.setAttribute("msg", "잘못된 경로로 접근하셨습니다.");
			request.setAttribute("loc", "/");
			
			request.getRequestDispatcher("/views/common/alertmsg.jsp").forward(request, response);
			
			
		} else {
						
			
			
			
			// cPage 현재 페이지
			int cPage;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage")); // java.lang.NumberFormatException: null
			} catch (NumberFormatException e) {
				cPage = 1;
			}
			
			
			// numPerPage 한 페이지당 데이터 개수 
			int numPerPage;
			try {
				numPerPage = Integer.parseInt(request.getParameter("numPerPage")); // java.lang.NumberFormatException: null
			} catch (NumberFormatException e) {
				numPerPage = 5;
			}
			
			
			
			
			// totalData 전체 데이터 수
			int totalData = new AdminService().countAllMember();
			
					System.out.println("MemberListServlet에서 테스트 totalData : " + totalData); // 113 
			
					
					
					
			// totalPage 전체 페이지 수 
			int totalPage = (int) Math.ceil( (double)totalData / numPerPage ); // int 나누기 int하면 int로 나오니까 소수점아래가 잘리니까 그걸 살리려고 double로 형변환 
			
					
					System.out.println("MemberListServlet에서 테스트 totalPage : " + totalPage); // 23
			
			
				
//				List<Member> list = new AdminService().memberList();
			
				List<Member> list = new AdminService().memberList(cPage, numPerPage);
				
				System.out.println("test2 _ is it run???");
				
				
				// pageBarSize 페이지바에 보이는 페이지 개수 : < 1 2 3 4 5 > < 5 6 7 8 9 >
				
				int pageBarSize = 5;
				
				
				
				// pageNo 페이지바에서 처음페이지 번호  
				
				int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
				
				
				
				// pageEnd 페이지바에서 마지막페이지 번호
				
				int pageEnd = pageNo+pageBarSize-1;
				
				
			
				String pageBar = "";
				
				// < 버튼 
				
				if(pageNo == 1) { // cPage가 1, 2, 3, 4, 5일 때 
					
					pageBar += "<span> < </span>";
					
				} else {
					
					pageBar += "<a href='" +request.getContextPath() 
							+ "/admin/memberlist.do?cPage=" + (pageNo -1) 
							+ "&numPerPage=" + numPerPage + "'> < </a>";
				}
				
				
				
				// 페이지번호 1 2 3 4 5 
				System.out.println("test3 _ is it run???");
				while( pageNo <= pageEnd && pageNo <= totalPage) {
					System.out.println("is this error point? isn't it?");
					if(pageNo == cPage) {
						
						pageBar += "<span style='color:red'>" + pageNo + "</span>";
						
					} else  {
						
						pageBar += "<a href='" +request.getContextPath() 
						+ "/admin/memberlist.do?cPage=" + pageNo 
						+ "&numPerPage=" + numPerPage + "'>" + pageNo + "</a>";
					}
					
					pageNo++;
				}
				
				
				
				
				
				System.out.println("test4 _ is it run???");
				
				
				
				// > 버튼 
		
				
				if(pageNo > totalPage) { // 페이지바에 번호가 마지막페이지 하나일 경우 
					
					pageBar += "<span> > </span>";
					
				} else {
					
					pageBar += "<a href='" +request.getContextPath() 
							+ "/admin/memberlist.do?cPage=" + pageNo
							+ "&numPerPage=" + numPerPage + "'> > </a>";
				}
				
				
				
				request.setAttribute("pageBar", pageBar);
				
				
				// 암호화된 전화번호와 이메일을 복호화해서 저장 
				for(Member m : list) {
					
					try {
						
						m.setPhone(AESCryptor.decrypt(m.getPhone()));
						m.setEmail(AESCryptor.decrypt(m.getEmail()));
						
					} catch (Exception e) {
						
					}
				}
				
				
				// jsp에 전달 
				
				request.setAttribute("memberlist", list);
				
				System.out.println("is it runn?? _ servlet");
				
				request.getRequestDispatcher("/views/admin/memberlist.jsp").forward(request, response);
				
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
