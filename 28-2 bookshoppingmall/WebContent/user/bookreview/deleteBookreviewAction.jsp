<!-- 2018.07.27 28기 전재현 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>댓글 삭제 처리</title>
	</head>
	<body>
		<% 
			request.setCharacterEncoding("UTF-8");
		
			String bookreviewContent = request.getParameter("bookreviewContent");
			int bookNo = Integer.parseInt(request.getParameter("bookNo"));
			
			System.out.println(bookreviewContent +"<-bookreviewContent deleteBookreviewAction.jsp");
			System.out.println(bookNo +"<-bookNo deleteBookreviewAction.jsp");
			
			BookreviewService bookreivewService = new BookreviewService();
			
			bookreivewService.deleteBookreview(bookreviewContent);
			
			response.sendRedirect("./../book/listBookDetail.jsp?bookNo="+bookNo);
		%>
	</body>
</html>