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

	<h2>ajax를 이용한 자동완성 구현하기</h2>
	
	<input id="keyword" list="list" placeholder="찾을 이름을 입력하세요">
	<datalist id="list">
		
	</datalist>
	
	<script>
		$("#keyword").keyup( (e) => {
			$.ajax({
				url : "<%=request.getContextPath()%>/ajax/search.do",
				data : {
					"keyword" : $(e.target).val()
				},
				success : data => {
					
					// 리스트 비워줌 
					$("#list").html("");
					
					console.log(data);
					
					let names = data.split(",");
					
					$.each(names, (i,v) => {
						
						console.log(v);
						
						let op = $("<option>").attr("value", v).html(v);
						
						$("#list").append(op);
						
					})
				}
			})
			
		} );
	</script>
</body>
</html>