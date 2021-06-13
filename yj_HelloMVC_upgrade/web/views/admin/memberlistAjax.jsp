<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.member.model.vo.Member" %>
<%
	List<Member> memberlist = (List<Member>)request.getAttribute("memberlist");
%>
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
                   
				    <td><%=m.getUserid() %></td>
				    <td><%=m.getUsername() %></td>
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