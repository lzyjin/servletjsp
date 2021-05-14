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
	
	<h1>Ajax를 이용한 파일 업로드 처리하기</h1>
	
	
	
</body>
</html>