<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="service.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>장바구니 리스트</title>
		
	</head>
	<body onload="init();">
	<script language="JavaScript">
			var sell_price;
			var totalAmount;
			
			function init () {
				sell_price = document.form.sell_price.value;
				totalAmount = document.form.totalAmount.value;
				document.form.totalPrice.value = sell_price;
				change();
			}
			
			function add () {
				hm = document.form.totalAmount;
				totalPrice = document.form.totalPrice;
				hm.value ++ ;
			
				totalPrice.value = parseInt(hm.value) * sell_price;
			}
			
			function del () {
				hm = document.form.totalAmount;
				totalPrice = document.form.totalPrice;
					if (hm.value > 1) {
						hm.value -- ;
						totalPrice.value = parseInt(hm.value) * sell_price;
					}
			}
			
			function change () {
				hm = document.form.totalAmount;
				totalPrice = document.form.totalPrice;
			
					if (hm.value < 0) {
						hm.value = 0;
					}
					totalPrice.value = parseInt(hm.value) * sell_price;
			}  
	</script>
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
		<form action="./../orders/insertOrdersForm.jsp" method="post" name="form">
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
				int maxAmount = 0;
				for(int i=0; i<userPurchaseList.size(); i++) {
					purchaseList = userPurchaseList.get(i);
					maxAmount = purchaseList.getShoppingcartMaxAmount();
			%>
					<tbody>
						<tr>
							<td><input type="hidden" name="bookName" value="<%=purchaseList.getBookName()%>"><a href="./../book/listBookDetail.jsp?bookNo=<%=purchaseList.getMemberNo()%>"><%=purchaseList.getBookName()%></a></td>
							<td><input type="hidden" name="bookNo" value="<%=purchaseList.getBookNo()%>"><%=purchaseList.getShoppingcartPrice()%>원</td>
							<td>수량 :<input type=hidden name="sell_price" value="<%=purchaseList.getShoppingcartPrice()%>">
									<input type="text" name="totalAmount" value="<%=purchaseList.getShoppingcartAmount() %>" size="3" onchange="change();">
									<input type="button" value=" + " onclick="add();"><input type="button" value=" - " onclick="del();"><br>
							</td>
							<td><input type="text" name="totalPrice" size="11" readonly>원</td>
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