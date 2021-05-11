package com.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter({ "/notice/*", "/admin/*" })
public class LoginCheckFilter implements Filter {

    
    public LoginCheckFilter() {
        
    }

	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		if(session == null || session.getAttribute("loginMember") == null) {
			
			request.setAttribute("msg", "로그인 후 이용할 수 있습니다");
			request.setAttribute("loc", "/");
			
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
		} else {
			
			chain.doFilter(request, response);
		}
 		
		
	}

	
	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
