package com.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.common.*;


@WebListener
public class ContextListener implements ServletContextListener {

    
    public ContextListener() {
       
    }

	
    public void contextDestroyed(ServletContextEvent arg0)  { 
        
    }

	
    public void contextInitialized(ServletContextEvent arg0)  { 
       
    	// 0506
    	// 암호화 객체 생성하기
    	new AESCryptor(); // 서버올라갈때 객체 생성됌 
    			
    }
	
}

