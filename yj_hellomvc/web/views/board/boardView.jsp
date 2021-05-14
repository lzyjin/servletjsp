<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.board.model.vo.Board" %>
    
<%@ include file="/views/common/header.jsp" %>

<%
	Board b = (Board)request.getAttribute("board");
%>

	<style>
	    section#board-container{width:600px; margin:0 auto; text-align:center;}
	    section#board-container h2{margin:10px 0;}
	    table#tbl-board{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
	    table#tbl-board th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
	    table#tbl-board td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
	    
	    /*댓글입력창*/
	     div#comment-container button#btn-insert{width:60px;height:50px; color:white;
    	background-color:#3300FF;position:relative;top:-20px;}
       
        /*댓글테이블*/
	    table#tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both; } 
	    table#tbl-comment tr td{border-bottom:1px solid; border-top:1px solid; padding:5px; text-align:left; line-height:120%;}
	    table#tbl-comment tr td:first-of-type{padding: 5px 5px 5px 50px;}
	    table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}
	    table#tbl-comment button.btn-reply{display:none;}
	    table#tbl-comment button.btn-delete{display:none;}
	    table#tbl-comment tr:hover {background:lightgray;}
	    table#tbl-comment tr:hover button.btn-reply{display:inline;}
	    table#tbl-comment tr:hover button.btn-delete{display:inline;}
	    table#tbl-comment tr.level2 {color:gray; font-size: 14px;}
	    table#tbl-comment sub.comment-writer {color:navy; font-size:14px}
	    table#tbl-comment sub.comment-date {color:tomato; font-size:10px}
	    table#tbl-comment tr.level2 td:first-of-type{padding-left:100px;}
	    table#tbl-comment tr.level2 sub.comment-writer {color:#8e8eff; font-size:14px}
	    table#tbl-comment tr.level2 sub.comment-date {color:#ff9c8a; font-size:10px}
	    
	    /*답글관련*/
	    table#tbl-comment textarea{margin: 4px 0 0 0;}
	    table#tbl-comment button.btn-insert2{width:60px; height:23px; color:white; background:#3300ff; position:relative; top:-5px; left:10px;}
	 </style>
   
		<div id="board-container">
		<h2>게시판</h2>
		<table id="tbl-board">
			<tr>
				<th>글번호</th>
				<td><%=b.getBoardNo() %></td>
			</tr>
			<tr>
				<th>제 목</th>
				<td><%=b.getBoardTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=b.getBoardWriter() %></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td><%=b.getBoardReadCount() %></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<!-- 있으면 이미지출력 없으면 공란, 클릭하면 다운로드할 수 있게 구현 -->
					<% if(b.getBoardOriginalFilename() != null) { %>
						<a href="<%=request.getContextPath()%>/board/boardFileDownload.do?fileName=<%=b.getBoardOriginalFilename()%>"><img src="<%=request.getContextPath()%>/images/file.png" width="16px"></a>
					<% } %>
				</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td><%=b.getBoardContent() %></td>
			</tr>
			<%--글작성자/관리자인경우 수정삭제 가능 --%>
			
			<tr>
				<th colspan="2">
					<!-- 수정하기, 삭제하기 버튼 -->
					<button type="button" onclick="">수정하기</button>
					<button type="button" onclick="">삭제하기</button>
				</th>
			</tr>
		</table>
		
		<!-- 댓글입력 -->
		<div id="comment-table">
		
			<div id="comment-editor">
				
				<form>
					<textarea rows="3" cols="55"></textarea>
					<button type="submit" id="btn-insert">등록</button>
				</form>
				
			</div>
		
		</div>
		
		
		
		
		<table id="tbl-comment">
			<tr>
				<td>
					<sub><%= %></sub>
				</td>
			</tr>
		</table>
   
    </div>

<%@ include file="/views/common/footer.jsp" %>