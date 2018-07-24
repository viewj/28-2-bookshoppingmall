<!-- 2018.07.24 28기 전재현 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("UTF-8");
		
			String publisherName = request.getParameter("publisherName");
			String publisherWebsite = request.getParameter("publisherWebsite");
			
			PublisherService publisherService = new PublisherService();
			BookcodeService bookcodeService = new BookcodeService();
			
			
			bookcodeService.addBookcode();
			
			response.sendRedirect("./bookcodeManager.jsp");
		%>
	</body>
</html>