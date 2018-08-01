<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Admin Login</title>
	</head>
	<body>
		<h1>관리자 로그인</h1>
		<br><br>
		<form action="<%= request.getContextPath() %>/admin/login/adminLoginAction.jsp" method="post">
			<label>
				아이디<br><br>
				<input type="text" id="adminId" name="adminId">
			</label>
			<br><br>
			<label>
				비밀번호<br><br>
				<input type="password" id="adminPw" name='adminPw'>
			</label>
			<br><br>
			<button>로그인</button>
		</form>
	</body>
</html>