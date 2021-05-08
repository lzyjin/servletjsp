package com.common.filter;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;


@WebFilter(
		servletNames = {"loginservlet", "enrollendservlet", "updatememberervlet", "updatepasswordendservlet" }
		)

// 사용자 정보가 입력될 때 암호화할 필요가 있는 서블릿들
public class PasswordEncryptFilter implements Filter {

   
    public PasswordEncryptFilter() {
       
    }

	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// 래퍼 적용 
		PasswordEncryptWrapper pew = new PasswordEncryptWrapper((HttpServletRequest)request);
		
		chain.doFilter(pew, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
