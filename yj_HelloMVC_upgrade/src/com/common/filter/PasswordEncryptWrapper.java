package com.common.filter;

import java.nio.charset.*;
import java.security.*;
import java.security.MessageDigest;
import java.util.*;

import javax.servlet.http.*;

import org.apache.tomcat.util.net.openssl.ciphers.*;

public class PasswordEncryptWrapper extends HttpServletRequestWrapper {

	
	public PasswordEncryptWrapper(HttpServletRequest request) {
		
		super(request);
		
	}
	
	
	// getParameter로 password값을 호출하면 암호화해서 전송하기 
	
	 @Override
	 public String getParameter(String name) {
		 
		 // 매개변수로 들어오는 name의 값이 "password"일 때는 암호화 처리하자 
		 // 지금까지 비밀번호입력창의 name속성값은 다 password 였다 
		 
		 String value="";
		 
		 if(name.equals("password") || name.equals("password_new")) { // name속성값이 password인 애만 이니까 password_new 도 추가해야 새 비밀번호도 필터를 적용할 수 있음
			 
			 System.out.println("암호화 전 : " + super.getParameter(name));
			 
			 // 암호화시켜서 반환
			 value = getSHA512(super.getParameter(name));
			 
			 System.out.println("암호화 후 : " + value );
			 
		 } else {
			 
			 value = super.getParameter(name);
		 }
		 
		 // return super.getParameter(name);
		 return value;
		 
	 }
	
	
	private String getSHA512(String val) {
		
		
		String encPwd = "";
		
		// 암호화 처리 객체 선언 
		MessageDigest md = null;
		
		try {
			
			// 암호화 알고리즘을 선택해서 객체를 생성함 
			md = MessageDigest.getInstance("SHA-512");
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		
		// 암호화는 bit연산 
		byte[] bytes = val.getBytes(Charset.forName("UTF-8"));	
		md.update(bytes);
		
		// bit연산한 것을 String 값으로 변한해야함 
		encPwd = Base64.getEncoder().encodeToString(md.digest());
		
	    return encPwd;
	}
	

}
