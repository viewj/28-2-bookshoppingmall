<!-- 2018.07.24 28기 전재현 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>책 정보 상세 보기 </title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("UTF-8");
		
			int bookNo = Integer.parseInt(request.getParameter("bookNo"));
			
			BookService bookService = new BookService();
			
			BookDetail bookDetail = bookService.selectBookDatail(bookNo);
			
			System.out.println(bookDetail +"<-bookDetail");
		%>
		<div align="center">
			<form>
				<table>
					<thead>
						<tr>
							<th><%=bookDetail.getBookName()%></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>작가 :<%=bookDetail.getBookAuthor()%></td>
							<td>출판사 :<%=bookDetail.getPublisherName()%></td>
							<td>장르 :<%=bookDetail.getBookcodeName()%></td>
							<td>가격 :<%=bookDetail.getBookPrice()%></td>
							<td>마일리지 :<%=bookDetail.getBookPrice()%></td>
							<td>수량 :<%=bookDetail.getBookAmount()%></td>
							<td>절판 상황 :<%=bookDetail.getBookOut()%></td>
						</tr>
					</tbody>
				</table>
				<table>
					<thead>
						<tr>
							<th></th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</form>
		</div>
	</body>
</html>