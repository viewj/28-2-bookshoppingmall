<!-- 2018.07.25 28�� ������ -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>�ֹ� �Է� â</title>
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
			<h2 align="center">����� �ۼ�</h2>
			<br><br>
			<form action="*" method="post">
				<h3>���Ͻô� å����</h3>
				<div>���� �Ͻô� å : <%=bookName%></div><br>
				
				<div>���� �Ͻô� ���� : <input type="hidden" name="ordersAmount"><%=ordersAmount%></div>
				
				<h3>�ֹ��� ����</h3>
				<div><input type="hidden" name="memberName" value="<%=sessionAdminName%>">�ֹ��� �� : <%=sessionAdminName%></div>
				
				<div>�ֹ��� �ڵ��� ��ȣ :<input type="text" name="phoneNumber1" size="5" maxlength="3">&nbsp;-&nbsp;<input type="text" name="phoneNumber2" size="5" maxlength="4">&nbsp;-&nbsp;<input type="text" name="phoneNumber3" size="5" maxlength="4"></div>
				
				<div>
					�ֹ��� �ּ� :
					<input type="text" name="ordersAddr1" placeholder="��,��,��">
					<input type="text" name="orderAddr2" placeholder="���ּ�...">
				</div>
				
				<div>
					���� ���ϸ��� :&nbsp;&nbsp;<input type="text" name="bookPoint" placeholder="point...">
				</div>
				
				<div>
					��� ���� :&nbsp;&nbsp;<input type="text" name="bookAmount" placeholder="amount...">
				</div>
				
				<div>
					���� :&nbsp;&nbsp;
					<select name="bookOut">
						<option>����</option>
						<option>���Ǿƴ�</option>
					</select>
				</div>
				
				<div>
					<input type="submit" value="���">
					<a href="./bookManager.jsp" >���</a>
				</div>
			</form>
		</div>
	</body>
</html>