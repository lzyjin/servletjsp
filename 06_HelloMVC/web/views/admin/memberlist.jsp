<%@page import="com.common.AESCryptor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.model.vo.Member, java.util.*, com.common.*" %>

<%@ include file="/views/common/header.jsp" %>

<%
	ArrayList<Member> memberlist = (ArrayList<Member>)request.getAttribute("memberlist");
%>
	<style type="text/css">
    section#memberList-container {text-align:center;}
    
    section#memberList-container table#tbl-member {width:100%; border:1px solid gray; border-collapse:collapse;}
    section#memberList-container table#tbl-member th, table#tbl-member td {border:1px solid gray; padding:10px; }
    </style>
    
    <section id="memberList-container">
        <h2>회원관리</h2>
        
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
				  <%--  <td><%= AESCryptor.decrypt(m.getEmail()) %></td>  --%>
				   
				    <td><%=m.getPhone() %></td> 
				    <%-- <td><%= AESCryptor.decrypt(m.getPhone()) %></td>  --%>
				    
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
    </section>

<%@ include file="/views/common/footer.jsp" %>