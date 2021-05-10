<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.notice.model.vo.Notice" %>
<%@ include file="/views/common/header.jsp" %>
<%
	Notice n = (Notice)request.getAttribute("notice");
%>
<style>
	    section#notice-container{width:600px; margin:0 auto; text-align:center;}
	    section#notice-container h2{margin:10px 0;}
	    table#tbl-notice{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
	    table#tbl-notice th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
	    table#tbl-notice td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
	    h3{text-align:center;}
    </style>
    
    
	<div id="notice-container">
		<h3>공지사항 상세화면</h3>
	        <table id="tbl-notice">
	        <tr>
	            <th>제 목</th>
	            <td><%= n.getNoticeTitle()%></td>
	        </tr>
	        <tr>
	            <th>작성자</th>
	            <td><%= n.getNoticeWriter()%></td>
	        </tr>
	        <tr>
	            <th>첨부파일</th>
	            <td>
	            	<!-- 있으면 이미지출력하기 없으면 공란  -->
	            	<% if(n.getFilePath() != null) { %>
	            		<img src="<%=request.getContextPath() %>/images/file.png" width="16px" height="16px">
	            	<% } %>
	            </td>
	        </tr>
	        <tr>
	            <th>내 용</th>
	            <td><%=n.getNoticeContent() %></td>
	        </tr>
	        <tr>
	        	<!-- 숙제 : 사용자는 수정 삭제 안되게 하고, 관리자는 가능하게 --> 
	        	
	        	 <% if(loginMember != null && loginMember.getMemberId().equals("admin")) { %>
	            
	           		<th colspan="2">
	           
		                <input type="button" value="수정하기" onclick="">
		                <input type="button" value="삭제하기" onclick="">
	                
	                </th>
	                
	            <% } else { %>
	            
	            	
	            <% } %>
	            
	            
	        </tr>
	    </table>
	</div>


<%@ include file="/views/common/footer.jsp" %>