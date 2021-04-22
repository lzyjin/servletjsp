package com.data.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("get방식으로 처리하기");
		
		
		// 클라이언트가 보낸 데이터 확인
		// 클라이언트가 front에서 보낸 데이터를 parameter라고 지칭한다.
		// 클라이언트가 보낸 데이터는 HttpServletRequest 객체에 저장된다.
		// getParameter("key" 또는 input태그의 name속성의 값 ) 메소드를 사용해서 데이터를 가져온다. 
		
		// 글자 깨짐 현상을 방지하기 위해 이 코드 추가함 ( 파라미터를 가져오기 전에 인코딩 설정해야함 ) 
		request.setCharacterEncoding("utf-8");
		
		// getParameter()의 반환타입은 String
		String userId = request.getParameter("userId");
		String pw = request.getParameter("pw");
		
		System.out.println( "아이디 : " + userId + ", 패스워드 : " +  pw);
		
		// 페이지에서 로그인하면 콘솔에 아이디와 패스워드가 출력된다 
		
		
		
		response.setContentType("text/html;charset=utf-8"); // 이 코드를 적지 않으면 글자가 깨질 수 있다 
		response.setCharacterEncoding("utf-8");             // 그럴경우 이 코드를 적으면 된다 

		String html = "<html>";
		html += "<body>";
		html += "<h1>당신이 입력한 아이디는 : " + userId + "이고</h1>";
		html += "<h3>비밀번호는 " + pw.substring(0, 2) + "***</h3>";
		html += "</body>";
		html += "</html>";
		
		PrintWriter out = response.getWriter();
		out.write(html);
		
		// 영어나 숫자를 제외한 데이터를 입력할 경우 
		// get방식으로 전송되는 데이터는 별도의 인코딩이 없어도 깨지지 않는데
		// post방식으로 전송되면 byte단위로 깨져있던 문자가 합쳐지지 않고 그대로 출력된다 ( 글자 깨짐 현상 발생 ) 
		// 글자 깨짐을 해결하기 위해 setCharacterEncoding("utf-8")메소드를 이용한다 
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// System.out.println("post방식으로 처리하기");
		
		// 서버측에서는 get방식이건 post방식이건 똑같기 때문에 한가지 방법으로 처리해버린다. 
		// default방식이 get방식이기때문에 doPost()메소드 안에서 doGet() 메소드를 호출한다 
		doGet(request, response);
		
		// 페이지에서 로그인하면 콘솔에 아이디와 패스워드가 출력된다  ( doGet() 메소드를 실행했기때문에 ) 
	}

	
}
