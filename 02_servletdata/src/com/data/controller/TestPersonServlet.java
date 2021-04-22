package com.data.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/testperson.do")
public class TestPersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public TestPersonServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		// HttpServletRequest 객체와 HttpServletResponse 객체에 encoding 설정
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		
		
		// 클라이언트가 보낸 파라미터 값 받아오기
		
		// 1. 기본데이터 받기 
		// request.getParameter("key") : 단일값에 대해 가져올 때 사용한다 
		String name = request.getParameter("name");
		
		
		// 기본적으로 전송되는 데이터는 모두 문자열로 받기 때문에 다른 데이터타입으로 변환하려면 파싱처리해야한다
		int age = Integer.parseInt(request.getParameter("age"));
		double height = Double.parseDouble(request.getParameter("height"));
		
		
		// 라디오버튼도 단일값임 
		String color = request.getParameter("color");
		String animal = request.getParameter("animal");
		
		
		
		
		// 2. 다중값 데이터 받기
		// request.getParameterValues() : 문자열 배열로 반환 
		String[] foods = request.getParameterValues("foods");
		
		
		
		
		
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
		System.out.println("키 : " + height);
		System.out.println("색상 : " + color);
		
		// System.out.println("음식 : " + Arrays.asList(foods)); 
		
		
		System.out.print("음식 : ");
		for(String f : foods) {
			
			System.out.print(f + " ");
		}
		System.out.println();
		
		System.out.println("동물 : " + animal);
	
		
		
		
		// 3. key값을 모를때 key값 전체를 가져올 수 있는 메소드
		// getPrameterNames() : 클라이언트가 보낸 데이터의 key를 모두 보여주는 메소드
		
		System.out.println("---- 모든 key값 출력하기 ----");
		
		Enumeration<String> keys = request.getParameterNames(); // Enumeration = Iterator
	
		
		ArrayList<String[]> sendData = new ArrayList();
		
		while(keys.hasMoreElements()) {
			
			// System.out.println(keys.nextElement());
			
			String key = keys.nextElement();
			sendData.add(request.getParameterValues(key));
			
		}
		
		// System.out.println(sendData); // 배열의 주소값이 출력 
		
		for(String[] v : sendData) {
			
			for(int i=0; i < v.length; i++) {
				
				// System.out.println(v[i]); // value만 출력 
			}
		}
		
		
		
		
		
		// 4. 전송된 key value값을 한번에 받을 수 있는 기능
		// getParameterMap() : Map 객체를 이용해서 클라이언트가 전송한 값을 key, value로 출력
		
		
		Map<String, String[]> param = request.getParameterMap();
		// System.out.println(param);
		
		
		// Map 출력
		Set<Map.Entry<String, String[]>> entry = param.entrySet();
		
		Iterator<Map.Entry<String, String[]>> it = entry.iterator();
		
		while(it.hasNext()) {
			
			Map.Entry<String, String[]> p = it.next();
			
			System.out.println("key : " + p.getKey());
			
			for(String s : p.getValue()) {
				
				System.out.print(" value : " + s);
			}
			System.out.println();
		}
		
		

		
		
		// request 객체에 setAttribute로 넣은 값을 가져오기
		String msg = (String) request.getAttribute("msg");
		Date today = (Date) request.getAttribute("today");
		
		
		
		// 만약 attribute로 값을 가져올 때 key와 연결되어 있는 데이터가 없다면 null값을 반환한다 ( 에러 발생하지 않는다 )
		System.out.println("attribute : " + msg);
		System.out.println("attribute : " + today);
		
		
		
		
		
		
		// 기본 응답 페이지 작성해보자 
		// 응답하기위해서는 HttpServletResponse 객체를 이용한다 
		// 응답하기 위한 클라이언트의 정보를 가지고 있다 
		// ( request는 데이터를 관리, response는 응답을 관리 )
		
		// 1. 응답하는 방식을 결정. response.setContentType() -( 브라우저는 넘어오는 데이터를 mime타입으로 알 수 있다)
		// 전송되는 데이터 형식 (MIME type)과 인코딩방식을 결정
		response.setContentType("text/html;charset=utf-8");
		
		
		// 2. 클라이언트와 연결되는 문자열 Stream을 가져오는 메소드
		// getWriter() 
		PrintWriter out=response.getWriter();
		String html="<html>";
		if(msg != null) {
			
			html += "<h1>" + msg + "</h1>";
		}
		if(today != null) {
			
			html += "<h3>" + new SimpleDateFormat("yy-MM-dd").format(today) + "</h3>";
		}
		
		html += "<body>";
		html += "<h1>당신의 취향테스트 결과</h1>";
		html += "<h2>당신의 이름은 "+name+"이고, </h2>";
		html += "<h3>당신의 나이는 "+age+", 키는 "+height+"이네요<h3>";
		html += "<p>당신이 좋아하는 색은 <span style=\'color:"+color+"\'>"+color+"</span>이고, </p>";
		html += "<ul>좋아하는 음식";
		
		for(String f : foods) {
			String src="";
			switch(f) {
				case "콩불" : src="https://recipe1.ezmember.co.kr/cache/recipe/2019/03/30/ab3e044128060f6d78e9bb55c3f3e45e1.jpg";break;
				case "카레" : src="https://static.wtable.co.kr/image-resize/production/service/recipe/260/4x3/8e1380e6-d21e-46c7-8b56-b26e6c836bb1.jpg";break;
				case "마라탕" : src="http://img3.tmon.kr/cdn3/deals/2019/07/08/2228521646/original_2228521646_front_2ed86_1562548125production.jpg";break;
				default : src="https://lh3.googleusercontent.com/proxy/OzuosxyoQLdOJZ5izxixiOOw5ZtyTn__hbcJmCapqoZ1t_86Bmt-_JbP9lq6gSM1widGBLAak6D-mz6Jl4VwT1NsjjBuy21yc1FPdBPBzV9KPkkui3vNRZARNmgfpF4nLA";break;
			}
			html+="<li><img src='"+src+"' width='100px' height='100px'>"+f+"</li>";
		}
		html+="</ul>";
		html+="<p>좋아하는 동물은 "+animal+"</p>";
		html+="</body>";
		html+="</html>";
		
		out.write(html);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
