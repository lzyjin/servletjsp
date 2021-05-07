<%@page import="com.common.AESCryptor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.model.vo.Member, java.util.*, com.common.*" %>

<%@ include file="/views/common/header.jsp" %>

<%
	List<Member> memberlist = (List<Member>)request.getAttribute("memberlist");
%>
	<style type="text/css">
    section#memberList-container {text-align:center;}
    
    section#memberList-container table#tbl-member {width:100%; border:1px solid gray; border-collapse:collapse;}
    section#memberList-container table#tbl-member th, table#tbl-member td {border:1px solid gray; padding:10px; }
    section#memberList-container>div#pageBar>*{margin-right:20px}
    /* 검색창에 대한 스타일 */
    div#search-container {margin:0 0 10px 0; padding:3px; 
    background-color: rgba(0, 188, 212, 0.3);}
  div#search-userId{display:inline-block;}
    div#search-userName{display:none;}
    div#search-gender{display:none;} 
    div#numPerpage-container{float:right;}
    form#numperPageFrm{display:inline;}
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
        			<input type="hidden" name="searchType" value="userId">
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
        
        
        <script>
        
       /*  $("#searchType").change( (e) => {
        	
        	if( $("#searchType").val() == "userID" ) {
        	
	        	$("#search-userId").css("display", "inline-block");
	        	$("#search-userName").css("display", "none");
	        	$("#search-gender").css("display", "none");
        	
        	} else if( $("#searchType").val() == "userName") {
        		
        		$("#search-userId").css("display", "none");
	        	$("#search-userName").css("display", "inline-block");
	        	$("#search-gender").css("display", "none");
	        	
        	} else if( $("#searchType").val() == "gender") {
        		
        		$("#search-userId").css("display", "none");
	        	$("#earch-userName").css("display", "none");
	        	$("#search-gender").css("display", "inline-block");
        	}
        	
        } ); 
         */
        
        
        // 선생님 풀이
        
        $("#searchType").change( (e) => {
        	
        	const userId = $("#search-userId");
        	const userName = $("#search-userName");
        	const gender = $("#search-gender");
        	
        	const value = $(e.target).val();
        	
        	userId.css("display", "none");
        	userName.css("display", "none");
        	gender.css("display", "none");
        	
        	$("#search-" + value).css("display", "inline-block");
        	
        } );
        
        
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
            
            	<tr colspan="9" align="center">
            		<td>검색결과가 없습니다</td>
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