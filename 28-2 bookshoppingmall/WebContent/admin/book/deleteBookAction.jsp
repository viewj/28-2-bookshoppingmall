<!-- 2018.07.24 28기 전재현 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>삭제 처리 실행</title>
	</head>
	<body>
		<%
			int bookNo = Integer.parseInt(request.getParameter("bookNo"));
			
			BookService bookService = new BookService();
			
			boolean deleteResult = bookService.deleteBook(bookNo);
			
			if(deleteResult == true) {
				System.out.println("삭제 완료 되었습니다");
				response.sendRedirect("./bookManager.jsp");
			}else {
				System.out.println("삭제 실패 되었습니다");
				response.sendRedirect("./bookManager.jsp");
			}
		%>
	</body>
</html>