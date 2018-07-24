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
			Integer sessionAdminNo = (Integer)session.getAttribute("sessionAdminNo");
			String sessionAdminId = (String)session.getAttribute("sessionAdminId");
			
			// 세션 정보 보내기
			session.setAttribute("sessionAdminId", sessionAdminId);
			session.setAttribute("sessionAdminNo", sessionAdminNo);
			
			// 값 테스트
			System.out.println("sessionAdminNo & sessionAdminId : " + sessionAdminNo + " & " + sessionAdminId);
		%>
		
		<div id="quickNav" align="right">
			<%
				if(sessionAdminId == null){
			%>
					<a href="<%= request.getContextPath() %>/admin/adminLoginForm.jsp">Sign in</a> | <a href="<%= request.getContextPath() %>/admin/adminSignUpForm.jsp">Sign Up</a> | Cart | My page 
			<%
				} else {
			%>
					Hello! <%= sessionAdminId %>  | <a href="<%= request.getContextPath() %>/admin/adminLogout.jsp">Sign out</a> | Cart | My page 
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
				<li>Bookcode Manager</li>
				<li>Publisher Manager</li>
			</ul>
		</div>
		<br><br>
		<%
			System.out.println("");
			System.out.println("End of admin/index.jsp");
		%>
	</body>
</html>