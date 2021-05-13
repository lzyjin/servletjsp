<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>

<style>
	div#board-container
	{
		width:600px;
		margin:0 auto;
		text-align:center;
	}
	div#board-container h2
	{
		margin:10px 0;
	}
	table#tbl-board
	{
		width:500px;
		margin:0 auto;
		border:1px solid black;
		border-collapse:collapse;
	}
	table#tbl-board th
	{
		width:125px;
		border:1px solid;
		padding:5px 0;
		text-align:center;
	}
	table#tbl-board td
	{
		border:1px solid;
		padding:5px 0 5px 10px;
		text-align:left;
	}
</style>



	<div id='board-container'>
	
		<h2>게시판 작성</h2>
		
		<form action='<%=request.getContextPath() %>/board/insertBoard.do' method='post'  enctype="multipart/form-data"   onsubmit='return fn_form_validate();'>
		
			<table id='tbl-board'>
				<tr>
					<th>제목</th>
					<td><input type="text" name="boardTitle" id="boardTitle"></td>
				</tr>
				
				<tr>
					<th>작성자</th>
					<td><input type="text" name="boardWriter" id="boardWriter" value="<%=login_member.getMemberId()%>" readonly></td>
				</tr>
				
				<tr>
					<th>첨부파일</th>
					<td>
						<input type="file" name="uploadFile" id="uploadFile">
						<input type="file" name="uploadFile2" id="uploadFile2">
					</td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td><textarea rows="5" cols="40" name="content" id="content"></textarea></td>
				</tr>
				
				<tr>
					<th colspan="2">
						<!-- 등록, 취소  -->
						<input type="submit" value="등록">
						<input type="reset" value="취소" onclick="location.assign('<%=request.getContextPath()%>/board/boardList.do')">
					</th>
				</tr>
			</table>
		</form>
	</div>
	
	<script>
		/* 내용입력여부 확인 후 전송 */
		const fn_form_validate = () => {
			
			// 제목과 내용 입력했는지 확인
			const boardTitle = document.getElementById("boardTitle");
			const content = document.getElementById("content");
			
			if(boardTitle.value.length == 0 || content.value.length == 0) {
				
				alert("제목과 내용은 필수 입력사항입니다");
				
				return false; 
			}
			
			
		}
	</script>
	
	
<%@ include file="/views/common/footer.jsp" %>