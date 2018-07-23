<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Sign In</title>
	</head>
	<body>
		<h1>The Lives of Others</h1>
		<br><br>
		<form action="<%= request.getContextPath() %>/user/userLoginAction.jsp" method="post">
			<label>
				ID<br><br>
				<input type="text" id="memberId" name="memberId">
			</label>
			<br><br>
			<label>
				Password<br><br>
				<input type="password" id="memberPw" name='memberPw'>
			</label>
			<label>
				
			</label>
			<br><br><br>
			<button>Sign in</button>
		</form>
	</body>
</html>