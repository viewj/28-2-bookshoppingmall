<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Admin Login</title>
	</head>
	<body>
		<%
		%>
		<h1>������ �α���</h1>
		<br><br>
		<form action="/admin/adminLoginAction.jsp" method="post">
			<label>
				���̵�<br><br>
				<input type="text" id="adminId" name="adminId">
			</label>
			<br><br>
			<label>
				��й�ȣ<br><br>
				<input type="password" id="adminPw" name='adminPw'>
			</label>
			<br><br>
			<button>�α���</button>
		</form>
	</body>
</html>