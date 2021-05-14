package com.jquery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.*;

import com.jquery.model.vo.*;


@WebServlet("/json/basicData")
public class JsonBasicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public JsonBasicServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 서버에서 JSON으로 응답하기 위해서는 라이브러리를 사용해야한다 
		
		// json-simple 이용해서 응답한다 
		
		// 단일객체와 다수객체(List)를 JSON방식으로 보낼 수 있게 클래스가 구성되어 있다
		
		// 단일객체 : JSONObject 클래스 이용
		// 다수객체 : JSONArray 클래스 이용 ( JSONArray는 JSONObject를 담는 객체임 )
		
		Member m = new Member("박보검", "01012311234", "parkBogum.jpg");
		
		JSONObject jo = new JSONObject();
		
		jo.put("name", m.getName()); // 이 키는 내가 정하는 것 
		jo.put("phone", m.getPhone());
		jo.put("profile", m.getProfile());
		
		jo.put("age", new Integer(29));
		jo.put("height", new Double(185.5));
		
		response.setContentType("application/json; charset=utf-8");
		
		response.getWriter().print(jo); // {phone: "01012311234", profile: "parkBogum.jpg", name: "박보검"}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
