<%@page import="com.common.AESCryptor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.model.vo.Member, java.util.*, com.common.*" %>

<%@ include file="/views/common/header.jsp" %>

<%
	List<Member> memberlist = (List<Member>)request.getAttribute("memberlist");

	// 0510
	String searchType = request.getParameter("searchType") == null ? "" : request.getParameter("searchType"); // 가능 ( 주소url에 쿼리스트링으로 들어있으니까 )
	String searchKeyword = request.getParameter("searchKeyword") == null ? "" : request.getParameter("searchKeyword");
	
	out.print(searchType + " : " + searchKeyword);
	
	String cPage = request.getParameter("cPage") == null ? "" : request.getParameter("cPage");
	String numPerPage = request.getParameter("numPerPage") == null ? "" : request.getParameter("numPerPage");
%>

<style type="text/css">
	
	    section#memberList-container {
	    	text-align:center;
	    }
	    
	    section#memberList-container table#tbl-member {
	    	width:100%; border:1px solid gray; border-collapse:collapse;
	    }
	    
	    section#memberList-container table#tbl-member th, table#tbl-member td {
	    	border:1px solid gray; padding:10px; 
	    }
	    
	    section#memberList-container>div#pageBar>*{
	    	margin-right:20px
	    }
	    
	    /* 검색창에 대한 스타일 */
	    div#search-container {
	    	margin:0 0 10px 0; padding:3px; 
	    	background-color: rgba(0, 188, 212, 0.3);
	    }
	    
	  	div#search-userId{
	  		display:inline-block;
	  	}
	  	
	    div#search-userName{
	    	display:none;
	    }
	    
	    div#search-gender{
	    	display:none;
	    } 
	    
	    div#numPerpage-container{
	    	float:right;
	    }
	    
	    form#numperPageFrm{
	    	display:inline;
	    }
	    
</style>
    
    <section id="memberList-container">
    
        <h2>회원관리</h2>
        
        <div id="search-container">
        
        	검색타입 :
        	<select id="searchType">
        	
        		
        		<!-- <option value="userId">아이디</option>
        		<option value="userName">회원이름</option>
        		<option value="gender">성별</option> -->
        		
        		<!-- 검색한 조건이 유지되도록  -->
        		<option value="userId" <%= searchType.equals("userId") ? "selected" : "" %>>아이디</option>
        		<option value="userName" <%= searchType.equals("userName") ? "selected" : "" %>>회원이름</option>
        		<option value="gender" <%= searchType.equals("gender") ? "selected" : "" %>>성별</option>
        		
        	</select>
        	
        	<div id="search-userId">
        		<form action="<%=request.getContextPath()%>/admin/searchMember.do">
        			<input type="hidden" name="searchType" value="userId"> <!-- hidden 사용 : 클라이언트에게는 필요없고 서버에는 필요해서  --> 
        			<input type="hidden" name="numPerPage" value="">
        			
        			
        			<!-- <input type="text" name="searchKeyword" size="25" placeholder="검색할 아이디를 입력하세요"> -->
        			<!-- 검색한 조건이 유지되도록  -->
        			<input type="text" name="searchKeyword" size="25" placeholder="검색할 아이디를 입력하세요" value="<%= searchType.equals("userId") ? searchKeyword : "" %>">
        			<button type="submit">검색</button>
        		</form>
        	</div>
        	
        	<div id="search-userName">
        		<form action="<%=request.getContextPath()%>/admin/searchMember.do">
        			<input type="hidden" name="searchType" value="userName">
        			<input type="hidden" name="numPerPage" value="">
        			
        			
        			<%-- <input type="text" name="searchKeyword" size="25" placeholder="검색할 회원이름을 입력하세요"> --%>
        			<!-- 검색한 조건이 유지되도록  -->
        			<input type="text" name="searchKeyword" size="25" placeholder="검색할 회원이름을 입력하세요" value="<%= searchType.equals("userName") ? searchKeyword : "" %>">
        			<button type="submit">검색</button>
        		</form>
        	</div>
        	
        	<div id="search-gender">
        		<form action="<%=request.getContextPath()%>/admin/searchMember.do">
        		
        			<input type="hidden" name="searchType" value="gender">
        			<input type="hidden" name="numPerPage" value="">
        			
        			<%-- <label><input type="radio" name="searchKeyword" size="25" value="M">남</label>
        			<label><input type="radio" name="searchKeyword" size="25" value="F">여</label> --%>
        			
        			<!-- 검색한 조건이 유지되도록  -->
        			<label><input type="radio" name="searchKeyword" size="25" value="M"
        					<%=searchType.equals("gender")&searchKeyword.equals("M") ? "checked" : "" %>>남</label>
        					
        			<label><input type="radio" name="searchKeyword" size="25" value="F"
        					<%=searchType.equals("gender")&searchKeyword.equals("F") ? "checked" : "" %>>여</label>
        					
        			<button type="submit">검색</button>
        		</form>
        	</div>
        	
        </div>
        
        
        <div id="numPerPage-container">
        	페이지 당 회원 수 :
        	<%-- <form id="numPerPageFrm" name="" action="<%=request.getContextPath()%>/admin/memberlist.do" method=""> --%>
        	
        	<!-- 선생님풀이대로 -->
        	<form id="numPerPageFrm" name="" action="" method="">
        		<!-- <input type="hidden" name="numPerPage" value=""> -->
        		<select name="numPerPage" id="numPerPage">
        		
        			<!-- <option value="10">10</option>
        			<option value="5">5</option>
        			<option value="3">3</option> -->
        			
        			<option value="10" <%= numPerPage.equals("10") ? "selected" : ""%>>10</option>
        			<option value="5" <%= numPerPage.equals("5") ? "selected" : ""%>>5</option>
        			<option value="3" <%= numPerPage.equals("3") ? "selected" : ""%>>3</option>
        			
        		</select>
        	</form>
        
        </div>
        
        <script>
        
        /* 0514 aJax 사용  */
        
        $("#numPerPage").change( (e) => {
        	
        	$.ajax({
        		
        		url : "<%=request.getContextPath()%>/admin/memberlistAjax.do",
        		
        		data : {
        			
        			"cPage" : <%=request.getParameter("cPage")%>,  // 요청한주소의 서블릿으로 데이터가 넘어감 
        			"numPerPage" : $(e.target).val()
        			
        		},
        		
        		success : data => {
        			
        			console.log(data);
        			
        		}
        		
        	})
        	
        } );
        
        
        
        
        
        	// numPerPage 부분 -------------- 
        
	       /*  $("#numPerPage").change( (e) => {
	    		
	    		// 검색용 
	    		const numPerPageFrm = $("#numPerPageFrm");
	    		
	    		const numPerPage = $(e.target).val();
	    		
	    		const targetTag = $("div>form>input:nth-child(2)");
	    		
	    		targetTag.attr("value", numPerPage);
	    		
	    		
	    		// 전체 조회용 
	    		$("form#numPerPageFrm>input").attr("value", numPerPage); 
	    		
	    		numPerPageFrm.submit();
	    		
	    	} );   */
        	
        	
        	
        	// 선생님 풀이 
        /*
        	 $("#numPerPage").change( (e) => {
        		 
        		 const numPerPageFrm = $("#numPerPageFrm");
        		 
        		 // numPerPageFrm 의 action을 분기처리 !!! 
        		 // 검색했을 때, 검색하지 않았을 때 
        		 
        		 /* $("input[name=searchKeyword]").each( (i, v) => {
        			 
        			 console.log(v.length);
        		 } ); */
        	 
        	<%-- 	 if(<%=searchKeyword.equals("")%>) {
        			 
	        			 // 검색 안함 
	        			//  alert("검색안함");
	        			 
	        			 numPerPageFrm.attr("action", "<%=request.getContextPath()%>/admin/memberlist.do");
	        			 
	        			 numPerPageFrm.append( $("<input>").attr( {
	        				 
	        				 	type : "hidden", 
	        				 	name : "cPage", 
	        				 	value : "<%=request.getParameter("cPage")%>"
	        			 } ) );
	        			 
	        			 numPerPageFrm.append( $("<input>").attr( {
	        				 
	     				 	type : "hidden", 
	     				 	name : "numPerPage", 
	     				 	value : "<%=request.getParameter("numPerPage")%>"
	     			 	} ) );
        			 
        		 } else {
        			 
	        			 // 검색함 
	        			 // alert("검색함");
	        			 
	        			 numPerPageFrm.attr("action", "<%=request.getContextPath()%>/admin/searchMember.do");
	        			 
	        			 numPerPageFrm.append( $("<input>").attr( {
	        				 
	     				 	type : "hidden", 
	     				 	name : "cPage", 
	     				 	value : "<%=request.getParameter("cPage")%>"
	     			 	} ) );
	     			 
		     			 numPerPageFrm.append( $("<input>").attr( {
		     				 
		  				 	type : "hidden", 
		  				 	name : "numPerPage", 
		  				 	value : "<%=request.getParameter("numPerPage")%>"
		  			 	} ) );
		     			 
		     			 numPerPageFrm.append( $("<input>").attr( {
		     				 
			  				 	type : "hidden", 
			  				 	name : "searchType", 
			  				 	value : "<%=request.getParameter("searchType")%>"
			  			 	} ) );
		     			 
		     			 numPerPageFrm.append( $("<input>").attr( {
		     				 
			  				 	type : "hidden", 
			  				 	name : "searchKeyword", 
			  				 	value : "<%=request.getParameter("searchKeyword")%>"
			  			 	} ) );
	     			 
        		 }
        		 
        		 
        		 
        		 numPerPageFrm.submit();
        		 
        	 });
        	 --%>
        	
	    	
        	
        	// searchType 부분 ------------------------
	        
	        
	        // 선생님 풀이
	        
	        $("#searchType").change( (e) => {
	        	
	        	const userId = $("#search-userId");
	        	const userName = $("#search-userName");
	        	const gender = $("#search-gender");
	        	
	        	const value = $(e.target).val();
	        	
	        	// $(e.target).val() 은 select태그의 value값은 option태그의 value가 되므로 
	        	
	        	userId.css("display", "none");
	        	userName.css("display", "none");
	        	gender.css("display", "none");
	        	
	        	$("#search-" + value).css("display", "inline-block");
	        	
	        } );

        	
        	
	        // 검색타입이 유지되는것은 jsp에서 select를 선택해서가 아니라 서블릿에서 request.getParameter()로 가져온 값으로 설정했기 때문에 
	        // 그래서 검색타입이 바뀐다고 해서 입력창도 바뀌지는 않았다
	        // 해결방법 : 위에서 만든 change될때 발생하는 함수를 onload로 호출시킨다 
	        // 이름으로 검색했을때 검색타입은 유지되는데 입력창은 유지되지 않는 문제 해결 
	        $(function() {
	        	$("#searchType").change();
	        });
	        
        </script>
        
        
        
        <table id="tbl-member">
            <thead>
            
            <tr>
                    <th>아이디</th>
				    <th>이름</th>
				    <th>성별</th>
				    <th>나이</th>
				    <th>이메일</th>
				    <th>전화번호</th>
				    <th>주소</th>
				    <th>취미</th>
				    <th>가입날짜</th>
             </tr>
            
            
           <%--  <% if(memberlist != null) { %> --%>
            <% if( !memberlist.isEmpty() ) { %>
            
           		<% for(Member m : memberlist)  { %>
            
            
                <tr>
                   
				    <td><%=m.getMemberId() %></td>
				    <td><%=m.getUserName() %></td>
				    <td><%=m.getGender() %></td>
				    <td><%=m.getAge() %></td>
				  	<td><%=m.getEmail() %></td> 
				    <td><%=m.getPhone() %></td> 
				    <td><%=m.getAddress() %></td>
				    <td><%=m.getHobby() %></td>
				    <td><%=String.valueOf(m.getEnrollDate()) %></td>
                </tr>
                
                <% } %>
                
            <% } else { %>
            
            	<tr>
            		<td colspan="9" align="center">검색결과가 없습니다</td>
            	</tr>
            
            <% } %>
            
            </thead>
            <tbody>
            
		       	 <!--  조회된 결과가 없으면 한줄(row)로 결과가 없습니다 출력하고
			    조회된 결과가 있으면 각 객체를 tr로 출력하는 구문을 작성할것 -->
            </tbody>
            
        </table>
        
        
        <!-- 0507 -->
        
        <div id="pageBar">
        	<%=request.getAttribute("pageBar") %>
        </div>
        
        
    </section>

<%@ include file="/views/common/footer.jsp" %>