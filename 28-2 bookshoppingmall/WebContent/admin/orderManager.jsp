<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="service.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset=UTF-8">
		<title>Order Manager</title>
	</head>
	<body>
		<%
			System.out.println("");
			System.out.println("location : user/index.jsp");
			
			// 세션 정보 받아오기
			Integer sessionAdminNo = (Integer)session.getAttribute("sessionAdminNo");
			String sessionAdminId = (String)session.getAttribute("sessionAdminId");
			
			QnaService qnaService = new QnaService();
			ArrayList<Qna> arrayListQna = qnaService.selectAllQnas();
		%>
		<h1>주문 관리</h1>
		<br>
		<form action="<%=request.getContextPath() %>/admin/deleteOrderAction.jsp" method="post">
			<table border="1">
				<thead>
					<tr>
						<th></th>
						
					</tr>
				</thead>
			</table>
		</form>
	</body>
</html>