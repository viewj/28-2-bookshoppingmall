<!-- 2018.07.25 28기 전재현 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>장바구니 등록실행</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("UTF-8");
			
			Integer sessionMemberNo = (Integer)session.getAttribute("sessionMemberNo");
			String sessionMemberId = (String)session.getAttribute("sessionMemberId");
			String sessionMemberName = (String)session.getAttribute("sessionMemberName");
			System.out.println(sessionMemberNo +"<- sessionMemberNo");
			
			//장바구니에 담을 상세정보값
			int bookNo = Integer.parseInt(request.getParameter("bookNo"));
			int shoppingAmount = Integer.parseInt(request.getParameter("BookAmount"));
			int shoppingPrice = Integer.parseInt(request.getParameter("bookPrice"));
			int maxAmount = Integer.parseInt(request.getParameter("maxAmount"));
			//shoppingcart변수에 화면에서 가져온 값 대입
			Shoppingcart shoppingcart = new Shoppingcart();
			
			shoppingcart.setBookNo(bookNo);
			shoppingcart.setMemberNo(sessionMemberNo);
			shoppingcart.setShoppingcartAmount(shoppingAmount);
			shoppingcart.setShoppingcartPrice(shoppingPrice);
			
			//shoppingcartService게층을 통하여 DB입력 실행
			ShoppingcartService shoppingcartService = new ShoppingcartService();
			
			shoppingcartService.addShoppingcart(shoppingcart);
			
			response.sendRedirect("./shoppingcartList.jsp");
		%>
	</body>
</html>