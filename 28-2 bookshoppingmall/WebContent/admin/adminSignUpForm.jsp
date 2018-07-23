<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Sign Up</title>
	</head>
	<body>
		<h1>Create New Account</h1>
		<br><br>
		<form action="<%= request.getContextPath() %>/admin/adminSignUpAction.jsp" method="post">
			<label>
				ID<br><br>
				<input type="text" name="adminId">
			</label>
			<br><br>
			<label>
				Password<br><br>
				<input type="password" name="adminPw">
			</label>
			<br><br>
			<label>
				Your Name<br><br>
				<input type="text" name="adminName">
			</label>
			<br><br>
			<button>Create</button>
		</form>
	</body>
</html>