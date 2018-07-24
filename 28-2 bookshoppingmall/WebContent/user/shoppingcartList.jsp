<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import=java.sql.* %>
<%@ page import="java.util.*" %>
<%@ page import="service.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>shoppingcart</title>
	</head>
	<body>
		<table>
			<tr>
				<td>번호</td>
				<td>도서 명</td>
				<td>갯수</td>
				<td>단가</td>
				<td>장바구니에 담은 날짜</td>
			</tr>
			<%
			Integer sessionMemberNo = (Integer)session.getAttribute("sessionMemberNo");
			
			ShoppingcartService shoppingcartService = new ShoppingcartService();
			
			ArrayList<Shoppingcart> list = shoppingcartService.selectShoppingcart(sessionMemberNo);
			Shoppingcart shoppingcart = null;
			int bookNo = 0;
			String bookName = null;
			for(int i=0; i<list.size(); i++){
				shoppingcart = list.get(i);
				bookNo = shoppingcart.getBookNo();
			%>
				<tr>
					<td><%=shoppingcart.getShoppingcartAmount() %></td>
				</tr>
				<tr>
					<td>
						<% 
							BookService bookService = new BookService();
							bookName = bookService.selectBookName(bookNo);
							
						%>
					</td>
				</tr>
				<tr>
					<td><form action="<%=request.getContextPath() %>/user/shoppingcartAmountAction.jsp"><input type="number" name="cartAmount" value="<%=shoppingcart.getShoppingcartAmount() %>"></form> </td>
				</tr>
				<tr>
					<td><%=shoppingcart.getShoppingcartPrice() %></td>
				</tr>
				<tr>
					<td><%=shoppingcart.getShoppingcartDate() %></td>
				</tr>
			<%
			}
			%>
		</table>
	</body>
</html>