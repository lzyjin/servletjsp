package com.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;


//@WebListener
public class RequestListenerTest implements ServletRequestListener, ServletRequestAttributeListener {

	private int count;
    
    public RequestListenerTest() {
      
    }

	
    public void requestDestroyed(ServletRequestEvent arg0)  { 
         
    	System.out.println("요청이 사라짐");
    }

	
    public void attributeRemoved(ServletRequestAttributeEvent arg0)  { 
         
    }

    
    public void requestInitialized(ServletRequestEvent sre)  { 
        
    	
    	System.out.println("요청이 들어옴");
    	
    	
    	
    			// 어떤 어플리케이션에 대한 요청인지 확인 가능 
    	
    	System.out.println( sre.getServletContext().getContextPath() ); // 어플리케이션(프로젝트) 이름
    	
    	
    			// 요청한 url주소를 알 수 있음
    	
    	System.out.println( ((HttpServletRequest) sre.getServletRequest()).getRequestURL() );
    	
    	String url = new String( ((HttpServletRequest) sre.getServletRequest()).getRequestURL() );
    	
    	
    	if(url.contains("login.do")) {
    		
    		count++;
    	}
    	
    	sre.getServletRequest().getParameterMap();
    	
    }

	
    public void attributeAdded(ServletRequestAttributeEvent arg0)  { 
         
    }

    public void attributeReplaced(ServletRequestAttributeEvent arg0)  { 
         
    }
	
}
