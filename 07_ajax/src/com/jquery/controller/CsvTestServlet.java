package com.jquery.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.model.vo.*;


@WebServlet("/ajax/csvTest.do")
public class CsvTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CsvTestServlet() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ajax요청에 csv방식으로 데이터를 응답해준다
		List<Member> list = new ArrayList<Member>();
		
		list.add(new Member("박보검", "01045662345", "parkBogum.jpg"));
		list.add(new Member("줄리아 로버츠", "01009873456", "juliaRoberts.jpg"));
		list.add(new Member("맷 데이먼", "01023457890", "mattDamon.jpg"));
		
		// 각 항목은 ,로 구분하고 각 멤버는 \n으로 구분
		
		String csv = "";
		
		for(int i=0; i < list.size(); i++) {
			
			if(i != 0) {
				csv += "\n";
			}
			csv += list.get(i);
		}
		
		System.out.println(csv);
		
		response.setContentType("text/csv; charset=utf-8");
		response.getWriter().print(csv);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
