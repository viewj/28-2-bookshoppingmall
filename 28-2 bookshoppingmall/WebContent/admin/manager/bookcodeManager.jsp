<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="service.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Bookcode Manager</title>
	</head>
	<body>
		<h1>도서코드 관리</h1>
		<br>
		<form action="<%= request.getContextPath() %>/admin/manager/addBookcodeAction.jsp" method="post">
			<input type="text" name="bookcodeName">
			<br><br>
			<input type="submit" value="+ 추가">
			<br><br>
		</form>
		
		<form action="<%= request.getContextPath() %>/admin/deleteBookcodeAction.jsp" method="post">
			<table border="1">
				<thead>
					<tr>
						<th></th>
						<th>도서 코드</th>
						<th>도서 장르 명</th>
					</tr>
				</thead>
				<tbody>
					<%
						
						BookcodeService bookcodeService = new BookcodeService();
						ArrayList<Bookcode> arrayListOfBookcodes = bookcodeService.getAllBookcodes();
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
			<input type="submit" value="- 삭제">
			<div><a href="./../index.jsp">메인으로</a></div>
		</form>
	</body>
</html>