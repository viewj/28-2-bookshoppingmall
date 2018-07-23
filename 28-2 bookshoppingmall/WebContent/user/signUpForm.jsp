<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Sign Up</title>
	</head>
	<body>
		<h1>Create New Account</h1>
		<br><br>
		<form action="<%= request.getContextPath() %>/user/signUpAction.jsp" method="post">
			<label>
				ID<br><br>
				<input type="text" name="memberId">
			</label>
			<br><br>
			<label>
				Password<br><br>
				<input type="password" name="memberPw">
			</label>
			<br><br>
			<label>
				Your Name<br><br>
				<input type="text" name="memberName">
			</label>
			<br><br>
			<label>
				Your Address<br><br>
				<input tpye="text" name="memberAddr">
			</label>
			<br><br><br>
			<button>Create</button>
		</form>
	</body>
</html>