package com.admin.controller;

import java.io.IOException;
import java.security.*;
import java.util.*;

import javax.crypto.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.admin.model.service.*;
import com.common.*;
import com.member.model.vo.*;

import oracle.net.aso.*;


@WebServlet("/admin/memberlist.do")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MemberListServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 주말 숙제 : 이부분 필터로 빼기 
		
		// 관리자가 아닌 사용자가 접근했을 때 (주소창에서 바로 회원관리 페이지로 이동한 경우 ) 예외처리하기
		
		HttpSession session = request.getSession(false);
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		
		
		
		if( session == null || loginMember == null || !loginMember.getUserid().equals("admin") ) {
			
			// 에러메세지 : 잘못된 경로로 접근했습니다. 이 페이지의 사용권한이 없습니다. 
			
			
				request.setAttribute("msg", "잘못된 경로로 접근했습니다. 이 페이지의 사용권한이 없습니다. ");
				request.setAttribute("loc", "/");
				
				
				RequestDispatcher rd = request.getRequestDispatcher("views/common/msg.jsp");
				
				rd.forward(request, response);
				
			
			
			
		} else {
			
			
			// 0507 페이징처리
			
			// 데이터를 가져올 때 원하는 구역(페이지)를 가져오기 
			
			// 1. cPage = 현재페이지 
			// 사용자가 원하는 페이지(현재 페이지)를 가져옴 
			
//			int cPage = 1;
			int cPage;
			
			
				try {
					
					cPage = Integer.parseInt(request.getParameter("cPage")); // java.lang.NumberFormatException: null
					
				} catch (NumberFormatException e) {
					
					cPage = 1; // 디폴트로 설정한 현재페이지
				}
				
			
			// 2. numPerPage : 페이지 당 데이터의 수 
			// ( 사용자가 설정함 )
				
			// int numPerPage = 5;
			int numPerPage;
			
				try {
					
					numPerPage = Integer.parseInt(request.getParameter("numPerPage")); // java.lang.NumberFormatException: null
					
				} catch (NumberFormatException e) {
					
					numPerPage = 10; // 디폴트로 설정한 페이지당 데이터의 수 
				}
			
			// 예외를 발생하지 않게 하기 위해서는 header에서 cPage와 numPerPage를 정해서 요청받거나
			// try catch문으로 감싸거나 
			
			
			
			
			
			
			// 클릭시 전체 회원에 대한 정보를 조회해서 가져옴
			
			AdminService service = new AdminService();
			
//			ArrayList<Member> list = service.selectAllMember();
			ArrayList<Member> list = service.selectAllMember(cPage, numPerPage);
			
			
			// 양방향 암호화했을 때 암호화된 정보들을 복호화해서 출력하기위해 
			for(Member m : list) {
				
				try {
					
					m.setPhone(AESCryptor.decrypt(m.getPhone()));
					m.setEmail(AESCryptor.decrypt(m.getEmail()));
					
				} catch (Exception e) {
					
				
				}
			}
			
			
			
			// 사용자가 원하는 페이지를 호출할 수 있게 pageBar를 구성해보자 !
			
			// 1. 전체 페이지에 대한 수
			// 전체 데이터 수에서 페이지당 데이터의 수를 나누면 된다 
			// 결과가 실수가 나올 경우 결과를 무조건 올림처리 한다 ( 그렇지 않으면 사라지는 데이터가 있으므로 )
			
			int totalData = service.countMember();
			
			int totalPage = (int) Math.ceil((double)totalData/numPerPage);
			
			System.out.println("MemberListServlet에서 테스트 totalData : " + totalData);
			System.out.println("MemberListServlet에서 테스트 totalPage : " + totalPage);
			
			
			
			
			// html방식으로 pageBar 구성하기 
			int pageBarSize = 4; // pageBar에 출력될 페이지번호의 개수 
			
			// pageNo :  페이지바의 시작페이지번호
			
			// ex
			// cPage = 1, pageNo = 1
			// cPage = 2, pageNo = 1
			// cPage = 3, pageNo = 1
			// cPage = 4, pageNo = 1
			
			// cPage = 5, pageNo = 5
			// cPage = 6, pageNo = 5
			// cPage = 7, pageNo = 5
			// cPage = 8, pageNo = 5
			
			// cPage = 9, pageNo = 9
					
			int pageNo = ( ( cPage - 1 ) / pageBarSize ) * pageBarSize + 1;
			
			// 계산시 주의할 점 : ( cPage - 1 ) / pageBarSize 의 결과는 int형임. 0.5 -> 0으로 처리 
					
			// pageEnd :  페이지바의 끝페이지번호
			
			// ex
			// cPage = 1, pageEnd = 5
			// cPage = 5, pageEnd = 8
			// cPage = 9, pageEnd = 12
			
			int pageEnd = pageNo + pageBarSize -1 ;
			
			
			
			
			
			
			// 사용자가 클릭할 수 있는 페이지바  : [이전] 1 2 3 4 [다음]
			
			// 1. [이전] 버튼 
			
			String pageBar = "";
			
			if(pageNo == 1) { // 클릭안되는 [이전] 버튼 
				
				
					pageBar += "<span>[이전]</span>";
					
				
			} else { // 클릭하며 해당 페이지로 이동하는 [이전] 버튼
				
				
					pageBar += "<a href = '" + request.getContextPath() 
						+ "/admin/memberlist.do?cPage=" 
						+ (pageNo - 1) + "&numPerPage = " + numPerPage + "'>[이전]</a>";
					
					// cPage = (pageNo - 1) 라고 쓰는 이유 
					// : pageNo(페이지바의 시작페이지번호)가 1이 아닐때 이전버튼을 누르면 전 페이지로 이동해야하니까 
					// 여기서 전 페이지란 ? 이전 페이지바의 마지막페이지번호 
				
			}
			
			
			
			
			
			
			// 2. 페이지바의 번호(숫자)를 출력하는 반복문
			
			// while( ( pageNo <= pageEnd && pageNo <= totalPage ) ) { 와 같다
			
			while( !( pageNo > pageEnd || pageNo > totalPage ) ) { // ||는 특정조건에 만족하면 뒤조건을 안읽어도 되니까 이방법을 쓴다 
					
				
				
					if(cPage == pageNo) { // 지금 사용자가 보고있는 페이지는 번호를 클릭해도 페이지이동하지않게 
						
						
						pageBar += "<span style='background-color:pink'>" + pageNo + "</span>";
						
					
					} else {
						
						
						pageBar += "<a href = '" + request.getContextPath() 
								+ "/admin/memberlist.do?cPage=" + pageNo  // 자기자신 (현재 서블릿) 호출
								+ "&numPerPage=" + numPerPage + "'>" + pageNo + "</a>";
					
					}
					
					pageNo++; 
				
			} // while문
			
			
			
			// 3. [다음] 버튼 
			
			if(pageNo > totalPage) { 
				
				pageBar += "<span>[다음]</span>"; // 클릭해도 페이지이동하지 않는 [다음]버튼 
				
			} else { // pageNo <=  totalPage
				
				pageBar += "<a href = '" + request.getContextPath() 
				+ "/admin/memberlist.do?cPage=" +pageNo 
				+ "&numPerPage=" + numPerPage + "'>[다음]</a>";
			}
			
			
			
			
			
			
			request.setAttribute("pageBar", pageBar);
			
			request.setAttribute("numPerPage", numPerPage);
			request.setAttribute("cPage", cPage);
			
			
					
			// 가져온 값을 jsp페이지에 전달	
			
			request.setAttribute("memberlist", list);
			
			// 꼭 memberlist여야함 
			// (memberlist.jsp의 선언문에서 request.getAttribute("memberlist")로 값을 불러오기 때문에
			
			
			request.getRequestDispatcher("/views/admin/memberlist.jsp").forward(request, response);
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
