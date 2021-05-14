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
	<h2>xml데이터 가져오기</h2>
	<button id="xml">xml데이터 가져오기</button>
	
	<div id="fiction">
		<h2>소설</h2>	
		<table id="fiction-info">
			
		</table>
	</div>
	
	<div id="it">
		<h2>프로그래밍</h2>	
		<table id="it-info">
			
		</table>
	</div>
	
	<script>
		$("#xml").click( (e) => {
			
			$.ajax({
				
				url : "books.xml", // 같은 위치에 있어서 이렇게 적음. 다른 위치라면 절대경로로 적어야함
				
				success : data => {
					
					let ficheader = "<tr><th>제목</th><th>저자</th></tr>";
					let itheader = ficheader;
					
						console.log(data);
					
					let entity = $(data).find(":root"); // 루트태그 찾기
					
						console.log(entity);
					
					let books = $(entity).find("book");
					
						console.log(books);
						
					books.each( (i,v) => {
						
						let info = "<tr><td>" + $(v).find("title").text() + "</td><td>" + $(v).find("author").text() + "</td></tr>";
						
						if( $(v).find("subject").text() == "소설" ) {
							
							ficheader += info;
						} else {
							itheader += info;
						}
						
							console.log(ficheader);
							console.log(itheader);
						
					} );
			
					$("#fiction-info").html(ficheader);
					$("#it-info").html(itheader);
				}
				
			})
			
		} );
	</script>
</body>
</html>