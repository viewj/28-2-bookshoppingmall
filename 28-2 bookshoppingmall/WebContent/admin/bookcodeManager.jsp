<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="service.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>Bookcode Manager</title>
	</head>
	<body>
		<h1>�����ڵ� ����</h1>
		<br>
		<form action="<%= request.getContextPath() %>/admin/addBookcodeAction.jsp" method="post">
			<input type="text" name="bookcodeName">
			<br><br>
			<input type="submit" value="+ �߰�">
			<br><br>
		</form>
		
		<form action="<%= request.getContextPath() %>/admin/deleteBookcodeAction.jsp" method="post">
			<table border="1">
				<thead>
					<tr>
						<th></th>
						<th>���� �ڵ�</th>
						<th>���� �帣 ��</th>
					</tr>
				</thead>
				<tbody>
					<%
						ArrayList<Bookcode> arrayListOfBookcodes = new ArrayList<Bookcode>();
						BookcodeService bookcodeService = new BookcodeService();
						
						for(int i = 0; i < arrayListOfBookcodes.size(); i++){
					%>
							<tr>
								<td><input type="checkbox" name="bookcodeNo" value="<%= arrayListOfBookcodes.get(i).getBookcodeNo() %>"></td>
								<td><%= arrayListOfBookcodes.get(i).getBookcodeNo() %></td>
								<td><%= arrayListOfBookcodes.get(i).getBookcodeName() %></td>
							</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<br>
			<input type="submit" value="- ����">
		</form>
	</body>
</html>