package com.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;


// @WebFilter("*.do") // 패턴으로 필터링
@WebFilter(servletNames = {"FilterTest2"}) // 서블릿 이름으로 필터링

public class TestFilter implements Filter {

   
    public TestFilter() {
        
    }

	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		
		if( request.getParameter("userId") == null || !request.getParameter("userId").equals("admin") ) {
			
				
			
				HttpServletResponse res = (HttpServletResponse)response;
				
				res.setContentType("text/html;charset=utf-8");
				
				PrintWriter out = res.getWriter();
				
				String html = "<html>";
				
				html += "<body>";
				html += "<h1 style='color:red;'>잘못된 경로로 접근했습니다</h1>";
				html += "</body>";
				html += "</html>";
				
				out.print(html);
			
			
			
		} else {
			
			
				chain.doFilter(request, response);
				
				// 필터는 chain처럼 서로 연결되어 있다.
				// 인터페이스 FilterChain은 연결되어있는 필터를 순차적으로 doFilter()메소드를 이용해서 실행시킨다
				// 마지막 필터가 실행된 후에는 service()메소드를 실행시켜 서블릿의 메소드(doGet(), doPost())를 실행시킨다 
				
				// 여기서는 주소가 .do로 끝나지 않으면 필터에 걸리지 않아서 
				// 매핑된 서블릿 FilterTest2Servlet 의 메소드가 실행된다 
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
