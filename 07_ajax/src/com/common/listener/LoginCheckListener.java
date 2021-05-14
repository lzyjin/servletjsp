package com.common.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener
public class LoginCheckListener implements HttpSessionListener, HttpSessionAttributeListener {

	private static int count;
	
	public static int getCount() {
		
		return count;
	}
	
    
    public LoginCheckListener() {
        
    }

	
    public void sessionCreated(HttpSessionEvent arg0)  { 
    	
    	// 로그인하면 ( 세션이 생성되면 )
    	
//    	count++;
//    	System.out.println("session 생성 : " + count);
    	
    	// 메인페이지 jsp를 불러올때마다 내장객체 session이 생성되므로 
    	// 정확한 측정 불가 
        
    }

	
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	
    	// 로그아웃하면 ( 세션이 소멸되면 )
    	
//    	count--;
//    	System.out.println("session 소멸 : " + count);
    	
    	
       
    }

	
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
    	
    	count++;
    	System.out.println("session 생성 : " + count);
    	System.out.println("arg0.getValue() : " + arg0.getValue());
       
    }

    
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
    	
    	count--;
    	System.out.println("session 소멸 : " + count);
    	System.out.println("arg0.getValue() : " + arg0.getValue());
         
    }

	
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         
    }
	
}
