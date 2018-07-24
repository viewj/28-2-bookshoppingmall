<!-- 2018.07.25 28기 전재현 -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>주문 입력 창</title>
	</head>
	<body>
		<%
			int shoppingcartNo = Integer.parseInt(request.getParameter("shoppingNo"));
			System.out.println(shoppingcartNo +"<-shoppingcartNo");
			
			
		%>
	</body>
</html>