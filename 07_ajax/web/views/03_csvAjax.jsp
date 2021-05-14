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
	<h2>Ajax로 서버에서 데이터만 가져오기 -csv-</h2>
	
	<p>
		csv란 데이터를 특수기호로 나눠서 표현한 문자열을 의미한다
		예) 김떙떙,서울,25|이땡떙,경기,30|박떙떙,인천,23
	</p>
	
	<button id="btn">csv데이터 받아오기</button>
	
	<div id="area">
	
	</div>
	
	
	<script>
		$("#btn").click( (e) => {
			$.ajax({
				url : "<%=request.getContextPath()%>/ajax/csvTest.do",
				dataType : "text",
				success : data => {
					
					console.log(data);
					
					const table = $("<table>");
					const members = data.split("\n"); // 0번 1번 2번 인덱스에 멤버가 차례로 들어감
					
					console.log(members);
					
					for(let i=0; i < members.length; i++) {
						
						let data = members[i].split(","); // 멤버가 각 row가 된다
						
						console.log(data);
						
						let tr = $("<tr>");
						
						let name = $("<td>").html(data[0]);
						let phone = $("<td>").html(data[1]);
						/* let profile = $("<td>").html(data[2]); */
						let profile = $("<td>").append($("<img>").attr( {"src" : "<%=request.getContextPath()%>/images/" + data[2], "width" : "100px", "height" : "100px"}));
						
						tr.append(name).append(phone).append(profile);
						table.append(tr);
					}
					$("#area").html(table);
				}
			})
			
		} );
	</script>
</body>
</html>