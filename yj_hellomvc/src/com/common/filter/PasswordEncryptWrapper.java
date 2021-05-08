package com.common.filter;

import java.nio.charset.*;
import java.security.*;
import java.util.*;

import javax.servlet.http.*;

public class PasswordEncryptWrapper extends HttpServletRequestWrapper {

	public PasswordEncryptWrapper(HttpServletRequest request) {
		
		super(request);
		
	}
	
	@Override
	public String getParameter(String name) {
		
		String value = "";
		
		// 패스워드만 암호화하기 위해서 
		// name속성값이 password인 것만 암호화하는 기능
		
//		if(name.equals("password")) { // 로그인할 때 입력하는 패스워드의 name값은 password
//		if(name.equals("password") || name.equals("password_") ) {  // 회원가입시 입력하는 패스워드의 입력창의 name값은 password_ 이므로 조건에 추가해야함
		if(name.equals("password") || name.equals("password_") || name.equals("password_new") ) {	// 변경한 비밀번호도 암호화되도록
			
					System.out.println("암호화 전 password : " + super.getParameter(name));
			
			value = getSHA512(super.getParameter(name));
			
					System.out.println("암호화 후 password : " + value);
			
		} else {
			
			// 비밀번호가 아닐경우 원래대로 값을 받아옴 
			value = super.getParameter(name); 
		}
		
		return value;
		
	}

	private String getSHA512(String val) { // val가 super.getParameter(name)
		
		// 반환할 인코딩된 비밀번호 변수
		String encPwd = "";
		
		// 암호화처리 객체 선언 
		MessageDigest md = null;
		
		try {
			
			md = MessageDigest.getInstance("SHA-512");
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			
		}
		
		// 암호화는 bit연산이다
		byte[] bytes = val.getBytes(Charset.forName("UTF-8"));
		
		md.update(bytes); // 암호문으로 바뀜 
		
		// bit 연산한 것을 String 으로 변환 
		encPwd = Base64.getEncoder().encodeToString(md.digest());
		
		
		return encPwd;
	}

}
