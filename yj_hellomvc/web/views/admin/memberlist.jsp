<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>

<%@ include file="/views/common/header.jsp" %>

<%
	List<Member> memberlist = (List<Member>)request.getAttribute("memberlist");
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
        		<option value="userId">아이디</option>
        		<option value="userName">회원이름</option>
        		<option value="gender">성별</option>
        	</select>
        	
        	<div id="search-userId">
        		<form action="<%=request.getContextPath()%>/admin/searchMember.do">
        			<input type="hidden" name="searchType" value="userId"> <!-- hidden 사용 : 클라이언트에게는 필요없고 서버에는 필요해서  --> 
        			<input type="text" name="searchKeyword" size="25" placeholder="검색할 아이디를 입력하세요">
        			<button type="submit">검색</button>
        		</form>
        	</div>
        	
        	<div id="search-userName">
        		<form action="<%=request.getContextPath()%>/admin/searchMember.do">
        			<input type="hidden" name="searchType" value="userName">
        			<input type="text" name="searchKeyword" size="25" placeholder="검색할 회원이름을 입력하세요">
        			<button type="submit">검색</button>
        		</form>
        	</div>
        	
        	<div id="search-gender">
        		<form action="<%=request.getContextPath()%>/admin/searchMember.do">
        			<input type="hidden" name="searchType" value="gender">
        			<label><input type="radio" name="searchKeyword" size="25" value="M">남</label>
        			<label><input type="radio" name="searchKeyword" size="25" value="F">여</label>
        			<button type="submit">검색</button>
        		</form>
        	</div>
        	
        </div>
        
        <script>
        
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
        
        
        </script>
        
        
        <div id="numPerPage-container">
        	페이지 당 회원 수 :
        	<form id="" name="" action="" method="">
        		<select name="numPerPage" id="numPerPage">
        			<option value="10">10</option>
        			<option value="5">5</option>
        			<option value="3">3</option>
        		</select>
        	</form>
        
        </div>
        
        
        
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
                
                <% if(!memberlist.isEmpty()) { %>
	                
	           		<% for(Member m : memberlist) { %>
	            
		                <tr>
				            <td><%=m.getMemberId() %></td>
						    <td><%=m.getUserName() %></td>
						    <td><%=m.getGender()%></td>
						    <td><%=m.getAge() %></td>
						    <td><%=m.getEmail() %></td>
						    <td><%=m.getPhone() %></td>
						    <td><%=m.getAddress()%></td>
						    <td><%=m.getHobby() %></td>
						    <td><%=m.getEnrollDate()%></td>
		                </tr>
                
                	<% } %>
                
                <% } else { %>
                
                	<tr>
                		<td>결과가 없습니다</td>
                	</tr>
                
                <% } %>
                
            </thead>
            <tbody>
       	    <!-- 조회된 결과가 없으면 한줄(row)로 결과가 없습니다 출력하고
	    조회된 결과가 있으면 각 객체를 tr로 출력하는 구문을 작성할것 -->
            </tbody>
        </table>
        
        <div id="pageBar">
        	<%=request.getAttribute("pageBar") %>
        </div>
        
    </section>

<%@ include file="/views/common/footer.jsp" %>