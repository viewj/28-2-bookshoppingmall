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
			int totalPoint = Integer.parseInt(request.getParameter("totalPoint"));
			int bookNo = Integer.parseInt(request.getParameter("bookNo"));
			int ordersAmount = Integer.parseInt(request.getParameter("totalAmount"));
			String bookName = request.getParameter("bookName");
			
		%>
		<div align="center">
			<h2 align="center">����� �ۼ�</h2>
			<br><br>
			<form action="*" method="post">
			
				<h4>���Ͻô� å����</h4>
			
				<div>���� �Ͻô� å : <%=bookName%></div><br>
				
				<div>���� �Ͻô� ���� : <input type="hidden" name="ordersAmount"><%=ordersAmount%></div><br>
				
				<div>���� �ݾ� : <%=totalPrice%>��</div><br>
				
				<div>���� ���ϸ��� �ݾ� : <%=totalPoint%>��</div><br>
				
				<h4>�ֹ��� ����</h4>
				
				<div><input type="hidden" name="memberNo" value="<%=sessionAdminNo%>">�ֹ��� �� : <%=sessionAdminName%></div><br>
				
				<div>�ֹ��� �ڵ��� ��ȣ :<input type="text" name="phoneNumber1" size="5" maxlength="3">&nbsp;-&nbsp;<input type="text" name="phoneNumber2" size="5" maxlength="4">&nbsp;-&nbsp;<input type="text" name="phoneNumber3" size="5" maxlength="4"></div><br>
				
				<div>
					�ֹ��� �ּ� :
					<input type="text" name="ordersAddr1" placeholder="��,��,��">&nbsp;
					<input type="text" name="orderAddr2" placeholder="���ּ�...">
				</div><br>
				
				<div>
				</div>
				
				<div>
				</div>
				
				<div>
					<input type="submit" value="���">
					<a href="./bookManager.jsp" >���</a>
				</div>
			</form>
		</div>
	</body>
</html>