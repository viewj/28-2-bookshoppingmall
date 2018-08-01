<!-- 2018.07.24 28기 전재현 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.rain.bookshop.service.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>장르 목록 추가</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("UTF-8");
		
			String bookcodeName = request.getParameter("bookcodeName");
			
			System.out.println(bookcodeName +"<- bookName");
			
			BookcodeService.addBookcode(bookcodeName);
			
			response.sendRedirect("./bookcodeManager.jsp");
		%>
	</body>
</html>