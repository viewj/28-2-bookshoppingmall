<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Index</title>
	</head>
	<body>
		<%
			System.out.println("");
			System.out.println("location : user/index.jsp");
		
			// 세션 정보 받아오기
			Integer sessionMemberNo = (Integer)session.getAttribute("sessionMemberNo");
			String sessionMemberId = (String)session.getAttribute("sessionMemberId");
			
			// 값 테스트
			System.out.println("sessionMemberNo & sessionMemberId : " + sessionMemberNo + " & " + sessionMemberId);
		%>
		
		<div id="quickNav" align="right">
			<%
				if(sessionMemberId == null){
			%>
					<a href="<%= request.getContextPath() %>/user/userLoginForm.jsp">로그인</a> | 
					<a href="<%= request.getContextPath() %>/user/signUpForm.jsp">회원 가입</a>  
			<%
				} else {
			%>
					안녕! <%= sessionMemberId %>  | 
					<a href="<%= request.getContextPath() %>/user/userLogout.jsp">로그아웃</a> | 
					<a href="<%=request.getContextPath() %>/user/shoppingcart/shoppingcartList.jsp">장바구니</a> | 
					<a href="#내정보">내 정보</a>
			<%
				}
			%>
		</div>
		<br><br>
		<div id="mainTitle" align="center">
			<h1>The Lives of Others</h1>
		</div>
		<div id="mainNav">
			<ul>
				<li><a href="<%=request.getContextPath()%>/user/book/bookList.jsp">판매도서</a></li>
				<li><a href="<%=request.getContextPath()%>/user/userQnaList.jsp">Q &amp; A</a></li>
				<%
					if(sessionMemberId != null){
				%>
						<li><a href="<%= request.getContextPath() %>/user/">주문 내역</a></li>
				<%
					}
				%>
			</ul>
		</div>
		<br><br>
		<%
			System.out.println("");
			System.out.println("End of user/index.jsp");
		%>
	</body>
</html>