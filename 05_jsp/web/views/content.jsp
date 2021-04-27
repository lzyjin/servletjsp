<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ include file="/views/common/header.jsp" %>

   <section>
   		<h2><%=name%>님의 이메일은 <%=email%>입니다</h2> <!-- header.jsp에서 선언한 자바 변수를 include한 페이지에서 사용 가능 -->
       <h1>내용이 나오는 부분입니다</h1>
       <p>welcome to JSP</p>
       <table>
           <tr>
               <td>이름</td>
               <td>나이</td>
               <td>성별</td>
               <td>키</td>
           </tr>
           <tr>
               <td>유병승</td>
               <td>19</td>
               <td>남</td>
               <td>180.5</td>
           </tr>
           <tr>
               <td>김상현</td>
               <td>19</td>
               <td>남</td>
               <td>195.5</td>
           </tr>
       </table>
   </section>

  <%@ include file="/views/common/footer.jsp" %>