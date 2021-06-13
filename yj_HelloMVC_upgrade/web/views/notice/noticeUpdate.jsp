<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.notice.model.vo.Notice" %>    
    
<%@ include file="/views/common/header.jsp" %>

<%
	Notice n =(Notice)request.getAttribute("notice");
%>
	
<style>
	section#notice-container{width:600px; margin:0 auto; text-align:center;}
	section#notice-container h2{margin:10px 0;}
	table#tbl-notice{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
	table#tbl-notice th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
	table#tbl-notice td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
	span#fname{position:absolute; left:80px; top:7px; width:285px; background-color:#F5F5F5}
</style>

	  <div id="notice-container">
	  
	    <form action="<%=request.getContextPath()%>/notice/noticeUpdateEnd.do" method="post" enctype="multipart/form-data">
	    
	    	<input type="hidden" name="noticeNo" value="<%=n.getNoticeNo()%>">
	    
	        <table id="tbl-notice">
		        
			        <tr>
			            <th>제 목</th>
			            <td><input type="text" name="noticeTitle" id="noticeTitle" required value="<%=n.getNoticeTitle()%>"></td>
			        </tr>
			        
			        <tr>
			            <th>작성자</th>
			            <td><input type="text" name="noticeWriter" id="noticeWriter" value="<%=n.getNoticeWriter()%>" readonly required></td>
			        	
			        </tr>
			        
			        <tr>
			            <th>첨부파일</th>
			            <td style="position:relative;">
			            
			            	<% if(n.getFilePath() != null) { %>
			            	
					            <input type="file" name="up_file"><br>
					            <input type="hidden" name="oriFile" value="<%=n.getFilePath()%>"><br>
					            <span id="fname"><%=n.getFilePath() %></span>
					            
					        <% } else { %>
					        
					            <input type="file" name="up_file">
					            
					        <% } %>
					        
			            </td>
			        </tr>
			        
			        <tr>
			            <th>내 용</th>
			            <td><textarea rows="5" cols="50" name="content"><%=n.getNoticeContent()%></textarea></td>
			        </tr>
			        
			        <tr>
			            <th colspan="2">
			                <input type="submit" value="등록하기" onclick="return fn_notice_validate();">
			            </th>
			        </tr>
		        
	    	</table>
	    	
	    </form>
	    
    </div>
    
    <script>
    
    	$(function() {
    		// 값이 바뀔 때 파일명을 보여줄 수 있게
    		$("input[name=up_file]").change( (e) => {
    			
    			// 파일을 선택했을 때 
    			if($(e.target).val() == "") {
    				
    				$("#fname").show();
    			} else {
    				
    				$("#fname").hide();
    			}
    		} );
    	});
    </script>
    
    

<%@ include file="/views/common/footer.jsp" %>