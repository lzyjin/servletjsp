package com.notice.model.vo;

import java.util.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notice {
	
	private String noticeNo;
	private String noticeTitle;
	private String noticeWriter;
	private String noticeContent;
	private Date noticeDate;
	private String filePath;
	private String status;
	
	
}
