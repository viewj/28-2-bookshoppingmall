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
					<a href="<%= request.getContextPath() %>/user/userLoginForm.jsp">Sign in</a> | <a href="<%= request.getContextPath() %>/user/signUpForm.jsp">Sign Up</a> | Cart | My page 
			<%
				} else {
			%>
					Hello! <%= sessionMemberId %>  | <a href="<%= request.getContextPath() %>/user/userLogout.jsp">Sign out</a> | Cart | My page 
			<%
				}
			%>
		</div>
		<br><br>
		<div id="mainTitle" align="center">
			<h1>The Lives of Others</h1>
		</div>
		<br><br>
		<%
			System.out.println("");
			System.out.println("End of user/index.jsp");
		%>
	</body>
</html>