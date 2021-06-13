<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.notice.model.vo.Notice" %>

<%@ include file="/views/common/header.jsp" %>

<%
	List<Notice> noticeList = (List<Notice>)request.getAttribute("noticeList");

	String pageBar = (String)request.getAttribute("pageBar");
%>

 <style>
    section#notice-container{width:600px; margin:0 auto; text-align:center;}
    section#notice-container h2{margin:10px 0;}
    table#tbl-notice{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse;}
    table#tbl-notice th, table#tbl-notice td {border:1px solid; padding: 5px 0; text-align:center;} 
	input#btn-add{float:right;margin:0 0 15px;}
	table#tbl-notice{width:100%;margin:0 auto;border:1px solid black;border-collapse:collapse;clear:both;}
</style>


<section id="notice-container">

        <h2>공지사항</h2>
        <!-- 0511 -->
        <!-- 공지사항 글쓰기 기능 -->
        <input type="button" value="글쓰기" id="btn-add" onclick="fn_noticeWrite();">
        <table id="tbl-notice">
            <tr>
            
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>첨부파일</th>
                <th>작성일</th>
            </tr>
            
	<!-- 내용출력할것
		첨부파일 있으면 이미지, 없으면 공란으로 표시
		이미지파일은 web/images/file.png에 저장 
	-->
	
		<% if(!noticeList.isEmpty()) { %>
		
			<% for(Notice n : noticeList) { %>
		
			 	<tr>
	                <td><%=n.getNoticeNo() %></td>
	                
	                <td><a href="<%=request.getContextPath()%>/notice/noticeView.do?noticeNo=<%=n.getNoticeNo()%>"><%=n.getNoticeTitle() %></a></td>
	                
	                <td><%=n.getNoticeWriter() %></td>
	                
	                <td>
	                	<% if(n.getFilePath() != null ) { %>
	                	
	                		<img src="<%=request.getContextPath() %>/images/file.png" width="16px" height="16px"> 
	                		
	                		
	                	<% } else { %>
	                		
	                		첨부파일없음
	                	
	                	<% } %>
	                </td>
	                
	                <td><%=n.getNoticeDate() %></td>
	            </tr>
	            
	            <% } %>
	            
	    	<% } else { %>
	    	
	    		<tr>
	    			<td>공지사항이 없습니다</td>
	    		</tr>
	    	
	    	<% }  %>
	    	
        </table>
        
        <div>
        	<%=pageBar %>
        </div>
        
    </section>


	<script>
	
		// 공지사항 글쓰기 버튼에 클릭이벤트에 연결된 함수 
		const fn_noticeWrite = () => {
			
			location.assign("<%=request.getContextPath()%>/notice/noticeWrite.do");
		}
	
	</script>


<%@ include file="/views/common/footer.jsp" %>