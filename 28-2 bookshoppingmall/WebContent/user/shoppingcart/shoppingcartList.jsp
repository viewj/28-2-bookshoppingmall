<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="service.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>장바구니 리스트</title>
	</head>
	<body>
	<%
		Integer sessionMemberNo = (Integer)session.getAttribute("sessionMemberNo");
		
		ShoppingPurchaseList purchaseList = new ShoppingPurchaseList();
		ArrayList<ShoppingPurchaseList> userPurchaseList = new ArrayList<ShoppingPurchaseList>();
		ShoppingcartService shoppingcartService = new ShoppingcartService();
		
		userPurchaseList = shoppingcartService.selectShoppingcart(sessionMemberNo);
	%>
	<div align="center">
		<h3>장바구니</h3>
		<table border="1">
			<thead>
				<tr>
					<th>도서명</th>
					<th>판매가</th>
					<th>수량</th>
					<th>합계</th>
					<th>장바구니에 담은 날짜</th>
					<th>바로 주문</th>
					<th>삭제</th>
				</tr>
			</thead>
			<%
				for(int i=0; i<userPurchaseList.size(); i++) {
					purchaseList = userPurchaseList.get(i);
			%>
					<tbody>
						<tr>
							<td><%=purchaseList.getBookName()%></td>
							<td><%=purchaseList.getShoppingcartPrice()%></td>
							<td><%=purchaseList.getShoppingcartAmount()%></td>
							<td><%=purchaseList.getShoppingcartPrice()*purchaseList.getShoppingcartAmount()%></td>
							<td><%=purchaseList.getShoppingcartDate()%></td>
							<td><a href="">주문하기</a></td>
							<td><a href="./deleteShoppingcartAction.jsp?shoppingcartNo=<%=purchaseList.getShoppingcartNo()%>">삭제하기</a></td>
						</tr>
			<%
				}
			%>
			</tbody>
		</table><br>
		
		<a href="../book/bookList.jsp">쇼핑 계속하기</a>
		<a >주문하기</a>
	</div>
	</body>
</html>