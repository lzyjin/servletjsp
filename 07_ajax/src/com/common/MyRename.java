package com.common;

import java.io.*;
import java.text.*;
import java.util.*;

import com.oreilly.servlet.multipart.*;

public class MyRename implements FileRenamePolicy{

	@Override
	public File rename(File oldFile) {
		
		File newFile = null;
		
		do {
			
			// rename 규칙 설정
			// 절대로 중복되지 않는 값으로 설정된다 
			long currentTime = System.currentTimeMillis();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
			
			int rndNum = (int)(Math.random()*1000+1);
			
			String oriName = oldFile.getName();
			
			// 기존 파일명에서 확장자 빼냄 
			
			String ext = "";
			
			int dot = oriName.lastIndexOf(".");
			
			if(dot != -1) {
				
				ext = oriName.substring(dot);
			}
			
			// 새이름으로 설정하기 
			
			String newName = sdf.format(new Date(currentTime)) + "_" + rndNum + ext;
			
			newFile = new File(oldFile.getParent(), newName);
			
			
			
		} while( !createNewFile(newFile) );	
		
		return newFile;
		
	}
	
	
	
	private boolean createNewFile(File newFile) {
		
		try {
			return newFile.createNewFile();
		} catch (IOException e) {
			
		}
		return false;
	}
	
	

}
