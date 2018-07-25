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
			Integer sessionAdminNo = (Integer)session.getAttribute("sessionAdminNo");
			String sessionAdminId = (String)session.getAttribute("sessionAdminId");
			String sessionAdminName = (String)session.getAttribute("sessionAdminName");
			
			int shoppingcartNo = Integer.parseInt(request.getParameter("shoppingNo"));
			int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
			int ordersAmount = Integer.parseInt(request.getParameter("totalAmount"));
			String bookName = request.getParameter("bookName");
			
		%>
		<div>
			<h2 align="center">배송지 작성</h2>
			<br><br>
			<form action="*" method="post">
				<h3>구하시는 책정보</h3>
				<div>구매 하시는 책 : <%=bookName%></div><br>
				
				<div>구매 하시는 수량 : <input type="hidden" name="ordersAmount"><%=ordersAmount%></div>
				
				<h3>주문자 정보</h3>
				<div><input type="hidden" name="memberName" value="<%=sessionAdminName%>">주문자 명 : <%=sessionAdminName%></div>
				
				<div>주문자 핸드폰 번호 :<input type="text" name="phoneNumber1" size="5" maxlength="3">&nbsp;-&nbsp;<input type="text" name="phoneNumber2" size="5" maxlength="4">&nbsp;-&nbsp;<input type="text" name="phoneNumber3" size="5" maxlength="4"></div>
				
				<div>
					주문자 주소 :
					<input type="text" name="ordersAddr1" placeholder="구,읍,리">
					<input type="text" name="orderAddr2" placeholder="상세주소...">
				</div>
				
				<div>
					적립 마일리지 :&nbsp;&nbsp;<input type="text" name="bookPoint" placeholder="point...">
				</div>
				
				<div>
					재고 수량 :&nbsp;&nbsp;<input type="text" name="bookAmount" placeholder="amount...">
				</div>
				
				<div>
					절판 :&nbsp;&nbsp;
					<select name="bookOut">
						<option>절판</option>
						<option>절판아님</option>
					</select>
				</div>
				
				<div>
					<input type="submit" value="등록">
					<a href="./bookManager.jsp" >취소</a>
				</div>
			</form>
		</div>
	</body>
</html>