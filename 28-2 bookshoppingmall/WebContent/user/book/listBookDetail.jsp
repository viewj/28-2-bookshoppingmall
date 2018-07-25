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
			
			Integer sessionMemberNo = (Integer)session.getAttribute("sessionMemberNo");
			String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		
			int bookNo = Integer.parseInt(request.getParameter("bookNo"));
			
			BookService bookService = new BookService();
			
			BookDetail bookDetail = bookService.selectBookDatail(bookNo);
			
			System.out.println(bookDetail +"<-bookDetail");
		%>
		<div align="center">
			<form action="./../shoppingcart/insertShoppingcartAction.jsp" method="post">
				<table>
					<thead>
						<tr>
							<th><%=bookDetail.getBookName()%></th>
							<td><input type="hidden" name="bookName" value="<%=bookDetail.getBookName()%>"></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="hidden" name="bookNo" value="<%=bookDetail.getBookNo()%>">작가 :<%=bookDetail.getBookAuthor()%></td>
						</tr>
						<tr>
							<td>출판사 :<%=bookDetail.getPublisherName()%></td>
						</tr>
						<tr>
							<td>장르 :<%=bookDetail.getBookcodeName()%></td>
						</tr>
						<tr>
							<td><input type="hidden" name="bookPrice" value="<%=bookDetail.getBookPrice()%>">가격 :<%=bookDetail.getBookPrice()%></td>
						</tr>
						<tr>
							<td><input type="hidden" name="BookPoint" value="<%=bookDetail.getBookPoint()%>">마일리지 :<%=bookDetail.getBookPoint()%></td>
						</tr>
						<tr>
							<td>현재 수량 :<%=bookDetail.getBookAmount()%></td>
						</tr>
						<tr>
							<td>구매 수량 :<input type="number" name="BookAmount" max="<%=bookDetail.getBookAmount()%>"></td>
						</tr>
						<tr>
							<td>절판 상황 :<%=bookDetail.getBookOut()%></td>
						</tr>
					</tbody>
				</table><br>
				
				<table>
					<thead>
						<tr>
							<th>책 소계</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>작성자 :<%=bookDetail.getBookintroWriter()%></td>
							<td><input type="hidden" name="BookintroWriter" value="<%=bookDetail.getBookintroWriter()%>"></td>
						</tr>
						<tr>
							<td>내용 : <%=bookDetail.getBookintroContent()%></td>
							<td><input type="hidden" name="BookintroContent" value="<%=bookDetail.getBookintroContent()%>"></td>
						</tr>
					</tbody>
				</table><br>
				
				<a href="*">구매</a>
				<input type="submit" value="장바구니에 담기">
			</form>
		</div>
	</body>
</html>