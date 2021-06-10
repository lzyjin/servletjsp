<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
	
</style>
</head>

<body>

	<h1>이미지 첨부파일 미리보기</h1>
	
	<img id="profile" src="https://www.pngitem.com/pimgs/m/150-1503945_transparent-user-png-default-user-image-png-png.png" style=" width:100px; height:100px; border-radius:50px;">
	
	<input type="file" name="up_file" src="" style="display:none">
	
	<div id="images"></div>
	
	<script>
		
		$("#profile").click( e  => {
			$("[name=up_file]").click();
		});
	
	
		$("[name=up_file]").change( e => {
			
			/* console.log(e.target); */
			
			console.dir(e.target);
			
			// 미리보기 처리 -> FileReader 객체 필요
			
			if(e.target.files[0].type.includes("image")) {
				
				let reader = new FileReader();
				
				reader.onload = function(e){
					
					const img = $("<img>").attr( {
						
						"src" : e.target.result,
						"width" : "100px",
						"height" : "100px"
					} );
					
					$("#images").append(img);
					$("#profile").attr("src", e.target.result);
				}
				
				reader.readAsDataURL(e.target.files[0]);
			}
			
			
		} );
	</script>
	
	
	<h2>다중 이미지 미리보기</h2>
	
	<input name="upfile2" type="file" multiple>
	<div id="target"></div>
	
	<script>
	
		$("[name = upfile2]").change( e  => {
			
			for(let i=0; i < e.target.files.length; i++) {
				
				if(e.target.files[i].type.includes("image")) {
					
					let reader = new FileReader();
					
					reader.onload = e => {
						
						let img = $("<img>").attr( { 
							
							"src" : e.target.result,
							"height" : "100px", 
							"width" : "100px"
						} );
						$("#target").append(img);
					}
					
					reader.readAsDataURL(e.target.files[i]);
				}
			}
		});
	</script>
	
	
	<!-- 0519 보강 -->
	<h1>Ajax를 이용한 파일 업로드 처리하기</h1>
	
	<input type="file" id="upfile" multiple> <!-- multiple이면 files에 배열형식으로 여러개 들어간다 --> 
	
	<button id="sendFile">파일 전송</button>
	
	<script>
		$("#sendFile").click( e => {
			
			//ajax는 데이터를 객체로 전송한다
			
			// FormData객체를 이용해서 파일을 전송할 수 있다.
			
			let form = new FormData();
			
					// $(e.target).prev()[0] : input태그
			
			/* form.append("upfile", $(e.target).prev()[0].files[0]); */
			// form안에 input태그가 하나 추가되었다고 생각하면 된다.
			
			
			
					/* console.log($(e.target).prev()[0].files[0]); */
			
			$.each($(e.target).prev()[0].files, (i,v) => {
				
				console.log(i,v);
				
				form.append("upfile"+i, v);
			})
			
			form.append("name", "유병승");

			// title, content
			//
			
			console.dir($(e.target).prev()[0]);
			
			$.ajax({
				url : "<%=request.getContextPath()%>/fileUpload",
				data : form,
				type : "post",
				processData : false, 
				contentType : false, 
				success : data => {
					
					alert("업로드 성공");
					
					// 파일 갱신 ( 페이지에 보이는 파일선택창옆에 뜨는 파일명 리셋 )
					$("#upfile").val("");
				}
			})
			
		} );
	</script>
	
	
	
	
	
	
	
</body>
</html>