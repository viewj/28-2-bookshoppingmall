<!-- 2018.07.23 28기 전재현 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>책 등록 실행</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("UTF-8");
		
			String bookName = request.getParameter("bookName");
			String bookAuthor = request.getParameter("bookAuthor");
			String bookOut = request.getParameter("bookOut");
			int bookCodeNo = Integer.parseInt(request.getParameter("bookCodeNo"));
			int publisherNo = Integer.parseInt(request.getParameter("publisherNo"));
			int bookPrice = Integer.parseInt(request.getParameter("bookPrice"));
			int bookPoint = Integer.parseInt(request.getParameter("bookPoint"));
			int bookAmount = Integer.parseInt(request.getParameter("bookAmount"));
			System.out.println(publisherNo +"<-publisherNoi");
			System.out.println(bookCodeNo +"<-bookCodeNo");
			
			Book book = new Book();
			
			book.setBookName(bookName);
			book.setBookAuthor(bookAuthor);
			book.setBookPrice(bookPrice);
			book.setBookPoint(bookPoint);
			book.setBookAmount(bookAmount);
			book.setBookOut(bookOut);
			book.setBookCodeNo(bookCodeNo);
			book.setPublisherNo(publisherNo);
			
			BookService bookService = new BookService();
			
			boolean insertCheck = bookService.addBook(book);
			
			if(insertCheck == true) {
				response.sendRedirect("./../user/index.jsp");
				System.out.println("등록 완료 되었습니다");
				
			}else {
				response.sendRedirect("../inseertBookForm.jsp");
				System.out.println("등록 실패 되었습니다");
			}
		
		%>
	</body>
</html>