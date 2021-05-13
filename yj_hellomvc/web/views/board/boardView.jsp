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
						<a href="<%=request.getContextPath()%>/board/boardFileDownload.do"><img src="<%=request.getContextPath()%>/images/file.png" width="16px"></a>
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
   
    </div>

<%@ include file="/views/common/footer.jsp" %>