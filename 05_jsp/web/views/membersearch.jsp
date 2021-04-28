<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List, java.util.ArrayList, com.member.model.vo.Member"%> 
    <!-- 외부에 있는 java파일을 사용할 때 지시자 태그 안에 import를 작성 -->

<%

	// jsp는 컨트롤러 역할을 하는 중 
	// 이렇게는 잘 안쓰지만 가능하다는걸 보여주려고 이렇게 써보는 것 
	
	List<Member> members = new ArrayList();

	members.add(new Member("admin", "admin", "관리자", 40)); // 컨트롤러 역할
	members.add(new Member("user01", "user01", "유저일", 15));
	members.add(new Member("user02", "user02", "유저이", 20));
	members.add(new Member("user03", "user03", "유저삼", 35));
	
	request.setAttribute("members", members); // value는 Object타입이라서 list들어갈 수 있다
	
	RequestDispatcher rd = request.getRequestDispatcher("viewmember.jsp");
	rd.forward(request, response);
	
%>