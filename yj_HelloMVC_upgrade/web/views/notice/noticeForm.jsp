<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/views/common/header.jsp" %>

<style>
	    section#notice-container{width:600px; margin:0 auto; text-align:center;}
	    section#notice-container h2{margin:10px 0;}
	    table#tbl-notice{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
	    table#tbl-notice th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
	    table#tbl-notice td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
</style>

	  <div id="notice-container">
	  
	    <form action="<%=request.getContextPath()%>/notice/noticeWriteEnd.do" method="post" enctype="multipart/form-data">
	    
	        <table id="tbl-notice">
		        
			        <tr>
			            <th>제 목</th>
			            <td><input type="text" name="noticeTitle" id="noticeTitle" required></td>
			        </tr>
			        
			        <tr>
			            <th>작성자</th>
			            <!-- 반드시 있어야하고 로그인한 사용자 아이디가 들어가야함. 작성못하게! -->
			            <td><input type="text" name="noticeWriter" id="noticeWriter" value="<%=loginMember.getUserid()%>" readonly required></td>
			        	<!-- 로그인하지않고 글쓰기버튼 클릭하면 NullPointerException 발생 => 로그인 필터 만듦  -->
			        </tr>
			        
			        <tr>
			            <th>첨부파일</th>
			            
			            <!-- <td><input type="file" name="up_file"></td> -->
			            
			            <!-- <td><input type="file" name="up_file" multiple="multiple"></td> -->
			            
			           <!--  multiple 지원하지 않음 ( 마지막 파일명만 저장됌 ) -->
			            
			            <td>
				            <input type="file" name="up_file"><br>
				            <input type="file" name="up_file2">
			            </td>
			            
			            <!-- 
				            파일이 있으면 
				            1. form의 method속성은 무조건 post로 해야함 
				            2. form의 enctype속성은 "multipart/form-data"로 해야 바이너리파일로 넘어간다
				            	( 파일 받을 때 cos.jar라는 라이브러리 이용 )
			            -->
			        </tr>
			        
			        <tr>
			            <th>내 용</th>
			            <td><textarea rows="5" cols="50" name="content"></textarea></td>
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
    	
    	const fn_notice_validate = () => {
    		
    		
    	}
    
    </script>


<%@ include file="/views/common/footer.jsp" %>