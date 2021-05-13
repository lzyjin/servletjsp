<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.board.model.vo.Board, java.util.*, com.board.model.vo.BoardComment" %>  
  
<%@ include file="/views/common/header.jsp" %>

<%
	Board b = (Board)request.getAttribute("board");  // BoardViewServlet에서 
	
	List<BoardComment> comments = (List<BoardComment>)request.getAttribute("comments"); // BoardViewServlet에서 
%>

	<style>
	    section#board-container{width:600px; margin:0 auto; text-align:center;}
	    section#board-container h2{margin:10px 0;}
	    table#tbl-board{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
	    table#tbl-board th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
	    table#tbl-board td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
	    
	    div#comment-container button#btn-insert{width:60px;height:50px; color:white;
	    background-color:#3300FF;position:relative;top:-20px;}
	    div#comment-container{text-align:center;}
	    
	    /*댓글테이블*/
	    table#tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both; } 
	    table#tbl-comment tr td{border-bottom:1px solid; border-top:1px solid; padding:5px; text-align:left; line-height:120%;}
	    table#tbl-comment tr td:first-of-type{padding: 5px 5px 5px 50px;}
	    table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}
	    table#tbl-comment button.btn-reply{display:none;}
	    table#tbl-comment button.btn-delete{display:none;}
	    table#tbl-comment button.btn-update{display:none;}
	    
	    table#tbl-comment tr:hover {background:lightgray;}
	    
	    table#tbl-comment tr:hover button.btn-reply{display:inline;}
	    table#tbl-comment tr:hover button.btn-delete{display:inline;}
	    table#tbl-comment tr:hover button.btn-update{display:inline;}
	    
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
					 	<!-- 첨부파일이 있으면 이미지출력 / 없으면 공란 -->
					 	
					 	<a href="javascript:fn_fileDownload('<%=b.getBoardOriginalFilename()%>', '<%=b.getBoardRenamedFilename()%>')">
						
						<% if(b.getBoardOriginalFilename() != null) { %>
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/file.png" width="16px" height="16px"></a>
							<%=b.getBoardOriginalFilename() %>
						<% } %>
						</a>
					</td>
				</tr>
				
				<tr>
					<th>내 용</th>
					<td><%=b.getBoardContent() %></td>
				</tr>
				
				<%--글작성자/관리자인경우 수정삭제 가능 --%>
				
					<tr>
						<th colspan="2">
							<button type="button" onclick="location.assign('<%=request.getContextPath()%>/board/boardList.do')">목록</button>
							
							<% if( loginMember != null && ( loginMember.getMemberId().equals("admin") || loginMember.getMemberId().equals(b.getBoardWriter()) )) { %>
								<button type="button">수정</button>
								<button type="button">삭제</button>
							<% } %>	
						</th>
					</tr>
				
			</table>
			
			
			
			<!-- 댓글창 -->
			
			<%-- <% if(loginMember != null) { %>  함수로 하기 --%>
			
		    	<div id="comment-container">
		    	
		    		<div class="comment-editor">
		    		
		    			<form action="<%=request.getContextPath() %>/board/insertBoardComment.do" method="post" >
		    			
			    				<textarea name="content" rows="3" cols="55"></textarea>
			    				
			    				<!-- 서버에는 작성자, 어떤게시글인지, 답글일경우 어떤 댓글에 쓴건지를 보내야함  -->
			    				
			    				<input type="hidden" name="boardRef" value="<%=b.getBoardNo()%>">
			    				<input type="hidden" name="commentWriter" value="<%=loginMember != null ? loginMember.getMemberId() : "" %>"> <!-- 주의: 댓글쓴 사람은 로그인한 사람임  -->
			    				<input type="hidden" name="boardCommentLevel" value="1"> 
			    				
			    				
			    				 <!-- 참조할 댓글이 없을 때 값이 0이면 안된다.  시퀀스는 1부터 시작하므로  -->
			    				 <!-- null이라고 적어도 안된다. number타입이어야하는데 문자열이 되므로 -->
			    				 <!-- dao에서 처리 -->
			    				<input type="hidden" name="boardCommentRef" value="0">
			    				
			    				
			    				<button type="submit" id="btn-insert">등록</button>
			    				
		    			</form>
		    			
		    		</div>
		    		
		    	</div>
		    	
		   <%--  <% } else { %>
	    		
	    		<br><br>
	    		
	    	<% } %> --%>
	    	
	    	
	    	<table id="tbl-comment">
	    	
	    		<!-- 댓글 보여주는 창 --> 
	    		<% if(!comments.isEmpty()) {  %>
	    		
		    		<% for(BoardComment bc : comments) { %>
		    		
		    			<% if(bc.getBoardCommentLevel() == 1) { %>
		    		
			    			<tr class="level1">
			    			
			    				<td>
			    					<sub class="comment-writer"><%=bc.getBoardCommentWriter() %></sub>
			    					<sub class="comment-date"><%=bc.getBoardCommentDate() %></sub><br>
			    					<%=bc.getBoardCommentContent() %>
			    				</td>
			    				
			    				<td>
			    					
			    					<% if(loginMember != null) { %>
			    						<button class="btn-reply" value="<%=bc.getBoardCommentNo()%>">답글</button ><!--  // value인 댓글번호는 답글달때 사용 --> 
			    					<% } %>
			    					
			    					<!-- 삭제는 댓글쓴 사람만 가능해야함  -->
			    					
			    					<% if(loginMember != null && ( loginMember.getMemberId().equals(bc.getBoardCommentWriter()) || loginMember.getMemberId().equals("admin")) ) { %>
			    						<button class="btn-update">수정</button>
			    						<button class="btn-delete">삭제</button>
			    					<% } %>
			    				</td>
			    			</tr>
		    			
		    			<% } else { %>
		    			
		    				<tr class="level2">
			    				<td>
			    					<%-- <sub class="comment-writer"><%=bc.getBoardCommentWriter() %></sub>
			    					<sub class="comment-date"><%=bc.getBoardCommentDate() %></sub><br> --%>
			    					<sub><%=bc.getBoardCommentWriter() %></sub>
			    					<sub><%=bc.getBoardCommentDate() %></sub><br>
			    					<%=bc.getBoardCommentContent() %>
			    				</td>
			    				
			    				<td>
			    				</td>
			    			</tr>
		    			
		    			<% } %>
		    			
		    		<% } %>
		    		
	    		<% } %>
	    	</table>
	    	
	    	
   
    	</div>
    	
		
    	
	<script>
	
		const fn_fileDownload = (oriname, rename) => { // oriname은 한글으로 된 파일명일수 있으므로 인코딩 필요 
			
			url = "<%=request.getContextPath()%>/board/fileDownload.do";
			
			const encoriname = encodeURIComponent(oriname);
			
			location.assign(url + "?oriname=" + encoriname + "&rename=" + rename);
			
		}
		
		
		$(function() {
			
			$(".btn-reply").click( e => {
				
				const tr = $("<tr>");
				
				const form = $(".comment-editor>form").clone();
				
					form.find("textarea").attr("rows", "1");
					
					/* form.find("[name=boardCommentLevel]").attr("value", "2"); */
					form.find("[name=boardCommentLevel]").val("2"); /* 이렇게 해도 된다  */
					
					form.find("[name=boardCommentRef]").val( $(e.target).val() );
					form.find("button").removeAttr("id").addClass("btn-insert2");
					
					console.log(form);
					
				const td = $("<td>").attr("colspan", "2");
				
				tr.append(td.append(form));
				
				tr.find("td").css("display", "none");
				
				tr.insertAfter( $(e.target).parents("tr")).children("td").slideDown(800);
				
				$(e.target).off("click");
				
			
			} );
			
			
			// 로그인해야 댓글입력할수있게 
			$(".comment-editor textarea").click( e => {
				
				if(<%=loginMember == null%>) {
					alert("로그인 후 이용하실 수 있습니다");
					$("#userId").focus();
				}
			} );
			
			
			
		});
	
	</script>
    	
    	

<%@ include file="/views/common/footer.jsp" %>