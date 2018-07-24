<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="service.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>Publisher Manager</title>
	</head>
	<body>
		<h1>���ǻ� ����</h1>
		<br>
		<form action="<%= request.getContextPath() %>/admin/addPublisherAction.jsp" method="post">
			<input type="text" name="bookcodeName">
			<br><br>
			<input type="submit" value="+ �߰�">
			<br><br>
		</form>
		
		<form action="<%= request.getContextPath() %>/admin/deletePublisherAction.jsp" method="post">
			<table border="1">
				<thead>
					<tr>
						<th></th>
						<th>���ǻ� �ڵ�</th>
						<th>���ǻ� ��</th>
						<th>���ǻ� URL</th>
					</tr>
				</thead>
				<tbody>
					<%
						ArrayList<Publisher> arrayListOfPublisher = new ArrayList<Publisher>();
						BookcodeService bookcodeService = new BookcodeService();
						
						for(int i = 0; i < arrayListOfPublisher.size(); i++){
					%>
							<tr>
								<td><input type="checkbox" name="bookcodeNo" value="<%= arrayListOfPublisher.get(i).getPublisherNo() %>"></td>
								<td><%= arrayListOfPublisher.get(i).getPublisherNo() %></td>
								<td><%= arrayListOfPublisher.get(i).getPublisherName() %></td>
								<td><%= arrayListOfPublisher.get(i).getPublisherWebsite() %></td>
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