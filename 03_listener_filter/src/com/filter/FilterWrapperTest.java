package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter("/wrapper.do")
public class FilterWrapperTest implements Filter {

	
    public FilterWrapperTest() {
        
    }

	
    
	public void destroy() {
		
	}

	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		

		// 내가 만든 wrapper 적용하기
		
		MyWrapper myRequest = new MyWrapper( (HttpServletRequest)request );
		
		
		// chain.doFilter(request, response);
		chain.doFilter(myRequest, response); // myRequest에는 래퍼로 가공된 값이 저장되어있다 
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
