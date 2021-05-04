package com.common.filter;

import java.io.IOException;
import java.net.http.*;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

// 로그인 서블릿, 회원가입 서블릿, 회원정보수정 서블릿

// 필터링의 기준은 주소에 매핑만 하는것이 아니라 서블릿 이름으로도 가능 

//@WebFilter("/PasswordEncrypt.do")
@WebFilter(servletNames = {
		
		"loginservlet", "memberupdateservlet", "memberenrollendservlet", "updatePasswordEnd"
		
})
public class PasswordEncryptFilter implements Filter {

    
    public PasswordEncryptFilter() {
       
    }

	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		PasswordEncryptWrapper  pew = new PasswordEncryptWrapper((HttpServletRequest)request);
		
		
		
		chain.doFilter(pew, response);
		
		
		
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
