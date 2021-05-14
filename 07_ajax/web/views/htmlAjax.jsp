<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>

	<h3>Ajax로 html파일을 받아오기</h3>
	
	<button id="htmlAjax">html문서 받기</button>
	
	<div id="area">
		
	</div>
	
	<script>
		
		$(function() {
			$("#htmlAjax").click( e => {
				$.ajax({
					url : "<%=request.getContextPath()%>/ajax/htmlTest.do",
					type : "get", 
					dataType : "html", 
					success : data => {
						console.log(data);
						/* $("#area").text(data); */
						$("#area").html(data);
					}
				})
				
			} );
		});
	
	
	
	</script>
	
</body>
</html>