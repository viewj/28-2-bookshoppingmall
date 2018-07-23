<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>User Log out</title>
	</head>
	<body>
		<%
			session.invalidate();
			
			response.sendRedirect(request.getContextPath() + "/user/index.jsp");
		%>
	</body>
</html>