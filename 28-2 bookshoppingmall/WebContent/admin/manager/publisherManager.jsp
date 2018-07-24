<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="service.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Publisher Manager</title>
	</head>
	<body>
		<h1>출판사 관리</h1>
		<br>
		<form action="<%= request.getContextPath() %>/admin/manager/addPublisherAction.jsp" method="post">
			<div>
				출판사 이름 :
				<input type="text" name="publisherName" placeholder="Name...">
			</div><br>
			
			<div>
				출판사 주소 :
				<input type="text" name="publisherWebsite" placeholder="URL...">
			</div><br><br>
			<input type="submit" value="+ 추가">
			<br><br>
		</form>
		
		<form action="<%= request.getContextPath() %>/admin/deletePublisherAction.jsp" method="post">
			<table border="1">
				<thead>
					<tr>
						<th></th>
						<th>출판사 코드</th>
						<th>출판사 명</th>
						<th>출판사 URL</th>
					</tr>
				</thead>
				<tbody>
					<%
						PublisherService publisherService = new PublisherService();
						ArrayList<Publisher> arrayListOfPublishers = publisherService.getAllPublishers();
						for(int i = 0; i < arrayListOfPublishers.size(); i++){
					%>
							<tr>
								<td><input type="checkbox" name="bookcodeNo" value="<%= arrayListOfPublishers.get(i).getPublisherNo() %>"></td>
								<td><%= arrayListOfPublishers.get(i).getPublisherNo() %></td>
								<td><%= arrayListOfPublishers.get(i).getPublisherName() %></td>
								<td><%= arrayListOfPublishers.get(i).getPublisherWebsite() %></td>
							</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<br>
			<input type="submit" value="- 삭제">
			<div><a href="./../index.jsp">메인으로</a></div>
		</form>
	</body>
</html>