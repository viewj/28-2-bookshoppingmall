<!-- 2018.07.24 28기 전재현 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>관리자용 책 리스트</title>
	</head>
	<body>
		<%
			BookService bookService = new BookService();
			BookInformation bookInformation = new BookInformation();
			ArrayList<BookInformation> bookTotalList = new ArrayList<BookInformation>();
			
			bookTotalList = bookService.selectBook();
		%>
		<h2>책 리스트</h2>
		<br><br>
		<div>
			<table border="1">
				<thead>
					<tr>
						<th>번호</th>
						<th>장르</th>
						<th>출판사</th>
						<th><a href="">책 이름</a></th>
						<th>작가</th>
						<th>금액</th>
						<th>마일리지</th>
						<th>재고 수량</th>
						<th>절판 여부</th>
						<th>등록일</th>
						<th>수정</th>
						<th>삭제</th>
					</tr>
				</thead>
				<tbody>
					<%
						for(int i=0; i<bookTotalList.size(); i++) {
							bookInformation = bookTotalList.get(i);
					%>
						<tr>
							<td><%=bookInformation.getBookNo()%></td>
							<td><%=bookInformation.getBookCodeName()%></td>
							<td><%=bookInformation.getPublisherName()%></td>
							<td><%=bookInformation.getBookName()%></td>
							<td><%=bookInformation.getBookAuthor()%></td>
							<td><%=bookInformation.getBookPrice()%></td>
							<td><%=bookInformation.getBookPoint()%></td>
							<td><%=bookInformation.getBookAmount()%></td>
							<td><%=bookInformation.getBookOut()%></td>
							<td><%=bookInformation.getBookDate()%></td>
							<td><a href="./updateBookForm.jsp?bookNo=<%=bookInformation.getBookNo()%>">수정</a></td>
							<td><a href="./deleteBookAction.jsp?bookNo=<%=bookInformation.getBookNo()%>">삭제</a></td>
						</tr>
					<%
						}
					%>
				</tbody>
			</table>
			
			<div align="center">
				<a href="./insertBookForm.jsp">등록</a>
				<a href="./../index.jsp">메인으로</a>
			</div>
		</div>
	</body>
</html>