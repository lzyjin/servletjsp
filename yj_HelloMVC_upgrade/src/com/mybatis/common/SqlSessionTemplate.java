package com.mybatis.common;

import java.io.*;

import org.apache.ibatis.session.*;

import org.apache.ibatis.io.Resources;

public class SqlSessionTemplate {
	
	public static SqlSession getSession() {
		
		SqlSession session = null;
		
		String resource = "/mybatis-config.xml";
		
		try {
			
			InputStream is = Resources.getResourceAsStream(resource);
			
			SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
			
			SqlSessionFactory ssf = sfb.build(is);
			
			session = ssf.openSession(false);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return session;
		
		
	}

}
