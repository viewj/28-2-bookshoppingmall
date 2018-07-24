<!-- 2018.07.24 28기 전재현 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>수정처리 실행</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("UTF-8");
			
			String bookName = request.getParameter("bookName");
			String bookAuthor = request.getParameter("bookAuthor");
			String bookOut = request.getParameter("bookOut");
			int bookNo = Integer.parseInt(request.getParameter("bookNo"));
			int bookCodeNo = Integer.parseInt(request.getParameter("bookCodeNo"));
			int publisherNo = Integer.parseInt(request.getParameter("publisherNo"));
			int bookPrice = Integer.parseInt(request.getParameter("bookPrice"));
			int bookPoint = Integer.parseInt(request.getParameter("bookPoint"));
			int bookAmount = Integer.parseInt(request.getParameter("bookAmount"));
			System.out.println(bookOut +"bookOUt");
			
			BookService bookService = new BookService();
			Book book = new Book();
			
			book.setBookName(bookName);
			book.setBookAuthor(bookAuthor);
			book.setBookOut(bookOut);
			book.setBookNo(bookNo);
			book.setBookCodeNo(bookCodeNo);
			book.setPublisherNo(publisherNo);
			book.setBookPrice(bookPrice);
			book.setBookPoint(bookPoint);
			book.setBookAmount(bookAmount);
			
			boolean updateResult = bookService.updateBook(book);
			
			if(updateResult == true) {
				System.out.println("수정 완료 되었습니다");
				response.sendRedirect("./bookManager.jsp");
			}else {
				System.out.println("수정 실패 되었습니다");
				response.sendRedirect("./updateBookForm.jsp");
			}
		%>
	</body>
</html>