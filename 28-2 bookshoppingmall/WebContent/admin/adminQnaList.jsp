<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>AdminQnaList</title>
	</head>
	<body>
		<%
			System.out.println("");
			System.out.println("location : user/index.jsp");
			
			// 세션 정보 받아오기
			Integer sessionAdminNo = (Integer)session.getAttribute("sessionAdminNo");
			String sessionAdminId = (String)session.getAttribute("sessionAdminId");
			
			
		%>
	</body>
</html>