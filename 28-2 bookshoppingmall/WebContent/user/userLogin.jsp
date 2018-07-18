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
		<h1>일반회원 로그인</h1>
		<br><br>
		<form action="/user/userLoginAction.jsp" method="post">
			<label>
				아이디<br><br>
				<input type="text" id="memberId" name="memberId">
			</label>
			<br><br>
			<label>
				비밀번호<br><br>
				<input type="password" id="memberPw" name='memberPw'>
			</label>
			<br><br>
			<button>로그인</button>
		</form>
	</body>
</html>