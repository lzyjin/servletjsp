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
	<h2>JSON을 이용하여 데이터 받아오기</h2>
	<p>서버의 응답내용이 javascript의 Object로 받아지는 것</p>
	
	<button id="basicjson">기본 JSON 받아오기</button>
	<div id="area"></div>
	
	<input type="text" id="name">
	<button id="listJson">리스트 형식의 데이터 받아오기</button>
	<div id="listarea"></div>
	
	<script>
	
		$("#listJson").click( e  => {
			$.ajax({
				url : "<%=request.getContextPath()%>/json/listData",
				data : {
					"keyword" : $("#name").val()
				},
				success : data => {
					
					console.log(data);
					
					const table = $("<table>");
					const header = $("<tr>").append( $("<th>").html("아이디")).append( $("<th>").html("이름") ).append( $("<th>").html("나이") ).append( $("<th>").html("이메일") );
					
					table.append(header);
					
					for(let i=0; i < data.length; i++) {
						
						let user = data[i];
						let tr = $("<tr>");
						
						// gson을 쓸거면 Member의 필드이름과 동일하게 해야함 
						tr.append( $("<td>").html(user["memberId"]) ).append( $("<td>").html(user["userName"]) ).append( $("<td>").html(user["age"]) ).append( $("<td>").html(user["email"]) );
						
						table.append(tr);
					}
					
					$("#listarea").html(table);
				}
			})
			
		})
	
	
		$("#basicjson").click( e => {
			
			$.ajax({
				
				url : "<%=request.getContextPath()%>/json/basicData",
				
				dataType : "json",
				
				success : data => {
					
					console.log(data);
					
					const ul = $("<ul>");
					
					let li1 = $("<li>").html(data["name"]);
					let li2 = $("<li>").html(data["phone"]);
					let li3 = $("<li>").html(data["profile"]);
					let li4 = $("<li>").html(data["age"]);
					let li5 = $("<li>").html(data["height"]);
					
					ul.append(li1).append(li2).append(li3).append(li4).append(li5); // ??
					
					$("#area").html(ul);
				}
			})
		} );
	</script>
</body>
</html>