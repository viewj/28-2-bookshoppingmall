<!-- 2018.07.25 28기 전재현 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>장바구니 목록 삭제</title>
	</head>
	<body>
		<%
			int shoppingcartNo = Integer.parseInt(request.getParameter("shoppingcartNo"));
		
			ShoppingcartService shoppingcartService = new ShoppingcartService();
			
			shoppingcartService.deleteShoppingcart(shoppingcartNo);
			
			response.sendRedirect("./shoppingcartList.jsp");
		%>
	</body>
</html>