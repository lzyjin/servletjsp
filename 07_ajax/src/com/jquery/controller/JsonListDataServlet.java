package com.jquery.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.*;

import com.admin.model.service.*;
import com.google.gson.*;
import com.member.model.vo.*;


@WebServlet("/json/listData")
public class JsonListDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public JsonListDataServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String keyword = request.getParameter("keyword");
		
		List<Member> list = new AdminService().searchMember("username", keyword, 1, 10);
		
		// List로 받아온 데이터를 JSON으로 전환하여 넘기려면 
		// JSONArray 클래스를 이용한다 
		
		
//			JSONArray jArray = new JSONArray();
//			
//			for(Member m : list) {
//				
//				JSONObject jo = new JSONObject();
//				
//				jo.put("userId", m.getMemberId());
//				jo.put("username", m.getUserName());
//				jo.put("age", m.getAge());
//				jo.put("email", m.getEmail());
//				// jo.put("enrolldate", m.getEnrollDate()); Date타입 -> 클래스는 파싱해주지 않음 
//				
//				jArray.add(jo);
//			}
		
		
		response.setContentType("application/json;charset=utf-8");
		
		// response.getWriter().print(jArray);
		
		// gson 사용
		new Gson().toJson(list, response.getWriter());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
