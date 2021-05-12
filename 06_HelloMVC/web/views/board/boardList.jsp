<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.board.model.vo.Board, java.util.*"%>
<%@ include file="/views/common/header.jsp" %>

<%
	List<Board> boardList = (List<Board>)request.getAttribute("boardList");

	String pageBar = (String)request.getAttribute("pageBar");
%>

	<style>
		section#board-container{width:600px; margin:0 auto; text-align:center;}
		section#board-container h2{margin:10px 0;}
		table#tbl-board{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
		table#tbl-board th, table#tbl-board td {border:1px solid; padding: 5px 0; text-align:center;} 
		
		/*글쓰기버튼*/
		input#btn-add{float:right; margin: 0 0 15px;}
		
		/*페이지바*/
		div#pageBar{margin-top:10px; text-align:center; background-color:rgba(0, 188, 212, 0.3);}
		div#pageBar span.cPage{color: #0066ff;}
	</style>
	
	<section id="board-container">
	
		<h2>게시판 </h2>
		
		<input type="button" value="글쓰기" onclick="fn_moveBoardForm();">
			
		<table id="tbl-board">
		
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>첨부파일</th>
				<th>조회수</th>
			</tr>
			
			<% if( !boardList.isEmpty() ) { %>
			
				<% for(Board b : boardList) { %>
			
					<tr>
						<td><%=b.getBoardNo() %></td>
						<td>
							<!-- 타이틀을 누르면 상세화면으로 이동 -->
							<a href='<%=request.getContextPath()%>/board/boardView.do?no=<%=b.getBoardNo()%>'><%=b.getBoardTitle() %></a>
						</td>
						<td><%=b.getBoardWriter() %></td>
						<td><%=b.getBoardDate() %></td>
						<td>
							<!-- 첨부파일이 있으면 이미지출력 / 없으면 공란 -->
							<% if(b.getBoardOriginalFilename() != null) { %>
								<img alt="첨부파일" src="<%=request.getContextPath() %>/images/file.png" width="16px" height="16px">
							<% } else { %>	
								
							<% } %> 
						</td>
						<td><%= b.getBoardReadCount() %></td>
					</tr>
			
				<% } %>
				
			<% } %>
			
		</table>

		<!-- 페이징처리 -->
		<div id="pageBar">
			<%= pageBar %>
		</div>
		
		
	</section>
	
	 <script>
    
    	const fn_moveBoardForm = () => {
    		
    		location.assign("<%=request.getContextPath()%>/board/boardForm.do");
    	}
    
    </script>

<%@ include file="/views/common/footer.jsp" %>