<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>User Login</title>
	</head>
	<body>
		<%
		%>
		<h1>�Ϲ�ȸ�� �α���</h1>
		<br><br>
		<form action="/user/userLoginAction.jsp" method="post">
			<label>
				���̵�<br><br>
				<input type="text" id="memberId" name="memberId">
			</label>
			<br><br>
			<label>
				��й�ȣ<br><br>
				<input type="password" id="memberPw" name='memberPw'>
			</label>
			<br><br>
			<button>�α���</button>
		</form>
	</body>
</html>