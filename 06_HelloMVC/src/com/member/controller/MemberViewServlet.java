package com.member.controller;

import java.io.IOException;
import java.security.*;

import javax.crypto.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.*;
import com.member.model.service.*;
import com.member.model.vo.*;


@WebServlet("/memberView.do")
public class MemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MemberViewServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// header의 내 정보보기 버튼을 클릭하면 여기로 이동 
		
		// 클라이언트가 보낸 아이디를 기준으로 회원 정보를 가져옴 
		String userId = request.getParameter("userId");
		
		Member m = new MemberService().checkDuplicateId(userId); // 이미 만들어놓은 서블릿을 활용 
		
		
		// 0506 
		// 암호화된 이메일과 주소를 복호화 
		try {
			
			m.setPhone(AESCryptor.decrypt(m.getPhone()));
			m.setEmail(AESCryptor.decrypt(m.getEmail()));
			
		} catch (Exception e) {
			
			// e.printStackTrace();
			// 주석처리해서 암호화처리하지 않은 계정으로 로그인했을때 뜨는 예외를 보여주지 않게 
		}
		
		
		
		// m == null 
		// -> 일치하는 회원이 없어서 회원정보 수정 불가 
		// -> msg.jsp로 이동해서 알람메세지 출력하고 로그인 취소시킴 
		// -> 메인화면으로 이동 
		
		// m != null -> 
		// 회원정보 수정 페이지로 이동
		
		
		String view = "";
		String loc = "/logout";
		String msg = "";
		
		if(m != null) {
			
			// db에 일치하는 회원 있음 
			
			view = "/views/member/memberView.jsp";
			
			
		} else {
			
			// db에 일치하는 회원 없음
			
			view = "/views/common/msg.jsp";
			msg = "가입된 회원이 아닙니다";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.setAttribute("member", m);
		
		
		request.getRequestDispatcher(view).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
