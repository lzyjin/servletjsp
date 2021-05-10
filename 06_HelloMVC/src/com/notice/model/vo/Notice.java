package com.notice.model.vo;

import java.util.*;

public class Notice {
	
	
	
	
	private String noticeNo;
	private String noticeTitle;
	private String noticeWriter;
	private String noticeContent;
	private Date noticeDate;
	private String filePath;
//	private String status;
	
	
	
	
	public Notice() {
		
	}

	
	
	
	public Notice(String noticeNo, String noticeTitle, String noticeContent, Date noticeDate, String filePath) {
		
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeDate = noticeDate;
		this.filePath = filePath;
	}


	
	
	
	public String getNoticeNo() {
		return noticeNo;
	}


	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}


	public String getNoticeTitle() {
		return noticeTitle;
	}


	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	

	public String getNoticeWriter() {
		return noticeWriter;
	}




	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}




	public String getNoticeContent() {
		return noticeContent;
	}


	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}


	public Date getNoticeDate() {
		return noticeDate;
	}


	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}




	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeWriter=" + noticeWriter
				+ ", noticeContent=" + noticeContent + ", noticeDate=" + noticeDate + ", filePath=" + filePath + "]";
	}


	
	
	
}
