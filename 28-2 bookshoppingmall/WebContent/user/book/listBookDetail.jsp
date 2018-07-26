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
		
			//리스트에서 보내온 값을 받는 코드
			int bookNo = Integer.parseInt(request.getParameter("bookNo"));
			
			//화면에서 보여줄 값을 가져오기
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
					<!-- 책의 상세정보 테이블 -->
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
							<td>구매 수량 :<input type="number" name="BookAmount" max="<%=bookDetail.getBookAmount()%>"><input type="hidden" name="maxAmount" value="<%=bookDetail.getBookAmount()%>"></td>
					
						</tr>
						<tr>
							<td>절판 상황 :<%=bookDetail.getBookOut()%></td>
						</tr>
					</tbody>
				</table><br>
				<!-- 책소개 테이블 -->
				<table>
					<thead>
						<tr>
							<th>책 소개</th>
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
			
			<jsp:include page="../../bookreview/insertBookreviewForm.jsp">
				<jsp:param value="<%=bookDetail.getBookNo()%>" name="bookNo"/>
			</jsp:include>
		</div>
	</body>
</html>