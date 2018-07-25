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
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		
		ShoppingPurchaseList purchaseList = new ShoppingPurchaseList();
		ArrayList<ShoppingPurchaseList> userPurchaseList = new ArrayList<ShoppingPurchaseList>();
		ShoppingcartService shoppingcartService = new ShoppingcartService();
		
		userPurchaseList = shoppingcartService.selectShoppingcart(sessionMemberNo);
	%>
	<div align="center">
		<h3>장바구니</h3>
		<form action="./../orders/insertOrdersForm.jsp" method="post">
			<table border="1">
				<thead>
					<tr>
						<th>도서명</th>
						<th>판매가</th>
						<th>수량</th>
						<th>합계</th>
						<th>적립 마일리지</th>
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
								<td><input type="hidden" name="bookName" value="<%=purchaseList.getBookName()%>"><a href="./../book/listBookDetail.jsp?bookNo=<%=purchaseList.getMemberNo()%>"><%=purchaseList.getBookName()%></a></td>
								<td><input type="hidden" name="bookNo" value="<%=purchaseList.getBookNo()%>"><%=purchaseList.getShoppingcartPrice()%>원</td>
								<td><input type="number" name="totalAmount" value="<%=purchaseList.getShoppingcartAmount()%>"></td>
								<td><input type="hidden" name="totalPrice" value="<%=purchaseList.getShoppingcartPrice() * purchaseList.getShoppingcartAmount()%>"><%=purchaseList.getShoppingcartPrice()*purchaseList.getShoppingcartAmount()%>원</td>
								<td><input type="hidden" name="totalPoint" value="<%=purchaseList.getBookPoint() * purchaseList.getShoppingcartAmount()%>"><%=purchaseList.getBookPoint() * purchaseList.getShoppingcartAmount()%>원</td>
								<td><%=purchaseList.getShoppingcartDate()%></td>
								<td><input type="hidden" name="shoppingNo" value="<%=purchaseList.getShoppingcartNo()%>"><input type="submit" value="주문하기"></td>
								<td><a href="./deleteShoppingcartAction.jsp?shoppingcartNo=<%=purchaseList.getShoppingcartNo()%>">삭제하기</a></td>
							</tr>
				<%
					}
				%>
				</tbody>
			</table><br>
		</form>
		<a href="../book/bookList.jsp">쇼핑 계속하기</a>
	</div>
	</body>
</html>