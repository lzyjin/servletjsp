package com.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.service.MemberService;
import com.member.model.vo.Member;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		MemberService service = new MemberService();
		
		Member m = service.login(userId, password);
		
		System.out.println("login 로직 실행");
		
		
		
		
		
		
		// 로그인을 했을 때 아이디 저장을 체크한 경우 전송된 아이디값을 유지하는 로직 만들기
		// 아이디값을 유지하려면 session, (context 잘 안쓰므로 없는셈 치자 ), cookie 중 하나에 보관해야한다 
		
		// 쿠키에 저장되는것은 클라이언트가 조작하더라도 프로그램실행에 아무 영향이 없는 데이터 ( 장바구니, 최근본 상품 .. )
		// 세션에 저장되는것은 프로그램 실행에 영향이 있는 데이터 ( 상품의 가격, 사용자가 가지고 있는 아이템의 종류 .. )
		
		// 결론 : 아이디 저장은 쿠키 이용 ! 
		
		
		
		// 1. 클라이언트가 아이디 저장 체크박스를 체크했는지 확인
		// checkbox에 대한 값을 가져와서 확인하기
		
				String saveId = request.getParameter("saveId");
				
				System.out.println("checkbox 값 : " + saveId); // 체크 하고 로그인하면 on, 체크 안하고 로그인하면 null 
		
		// 2. check했으면 "on", check하지 않았으면 "null"
		
				// 유효기간 7일 
				
				if(saveId != null) {
					
					Cookie c = new Cookie("saveId", userId);
					
					c.setMaxAge(60*60*24*7);
					
					response.addCookie(c);
					
				} else {
					
//					Cookie cookies[] = request.getCookies();
//					
//					for(Cookie c : cookies) {
//						
//						c.setMaxAge(0);
//					}
					
					// 선생님 풀이
					Cookie c = new Cookie("saveId", "");
					
					c.setMaxAge(0);
					
					response.addCookie(c);
					
				}
		
		
		
		
		if(m != null) {
			
			// 로그인 성공 
			// 로그인 성공에 대한 데이터를 유지하기 위해 데이터를 보관 
			// 보관 객체 request.session.context
			
			// request.setAttribute("loginMember", m); // 이걸로 저장하면 데이터가 사라져서 안됌 
			HttpSession session = request.getSession();
			
			session.setAttribute("loginMember", m);
			
			
			// session에는 session객체에 대한 설정 및 정보를 가져오는 메소드가 있다
			
			// getCreateTime() : 세션이 생성된 시간
			
			// getLastAccessedTime() : 마지막 요청시간 
			
			// getMaxInactiveInterval() : 최대 허용 시간 
			
			System.out.println("최대 유지 시간 : " + session.getMaxInactiveInterval()); // 1800 = 30분 ( 디폴트가 30분이라서 )
			
			// session.setMaxInactiveInterval() : 세션 최대 이용시간 설정 ( 초 단위 )
			
			// session.setMaxInactiveInterval(3);
			
			System.out.println("최대 유지 시간 : " + session.getMaxInactiveInterval());
			
			
			
			
			
			
			System.out.println(m);
			
			response.sendRedirect(request.getContextPath());
			
			
		} else {
			
			// 로그인 실패
			
			// 로그인 실패에 대한 에러메세지를 띄워주고 메인화면으로 이동 
			
			// 1. 에러메세지를 출력해주는 별도의 msg출력 전용 페이지(jsp)를 구성한다 
			
			// ( 이 페이지에 로그인시도의 결과를 보내야함 )
			// ( 저장공간 request, session, context 중에 하나에 담아서 보냄 ) 
			// 에러메세지는 일시적인 기능이기때문에 = 유지되는게 아니라서 request에 담는다 
			
			
			
			// 2. 에러메세지 출력 후 메인화면으로 전환한다
			
			
			
			// 3. 로그인 실패에 대한 에러메세지는 요청에 대한 응답으로 종료되는 데이터이다 = request
			
			request.setAttribute("msg", "로그인 실패, 아이디/패스워드를 확인하세요");
			
			
			
			
			// 전환할 페이지 주소값도 같이 전달한다 ( 공통 jsp인 msg.jsp는 저장되어있는 주소값으로만 이동할 수 있게 공용이니까!) 
			
			request.setAttribute("loc", "/"); // ""로 하면 null이 되기떄문에 "/"로 메인을 표현 
			
			
			
			
			
			// request의 값을 다른페이지에서 이용하려면 request.getRequestDispatcher() 써야함 
			RequestDispatcher rd =  request.getRequestDispatcher("/views/common/msg.jsp");
			
			rd.forward(request, response);
			
			
		}
		
		// 페이지 전환
		// 로그인 여부 상관없이 메인페이지로 이동
		// request를 이용하기 위해 requestDispatcher() 사용 
		
//		RequestDispatcher rd = request.getRequestDispatcher("");
//		rd.forward(request, response);
		
		
		// ~ 하기 위해
//		response.sendRedirect(request.getContextPath());
		// 로그인실패시 dispatcher로 보내므로 여기에 이 코드가 있으면 안됌
		// 한번만 응답해야함 
		// 로그인 성공시 실행할 블럭으로 이 코드를 이동 
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
