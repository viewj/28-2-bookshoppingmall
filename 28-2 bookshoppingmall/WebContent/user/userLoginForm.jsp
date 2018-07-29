<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Sign In</title>
	</head>
	<body>
		<%
			int bookNo = 0;
			
			bookNo = Integer.parseInt(request.getParameter("bookNo"));
			
		%>
		<h1>일반회원 로그인</h1>
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
				<input type="hidden" name="bookNo" value="<%= bookNo %>">
			</label>
			<br><br><br>
			<button>로그인</button>
		</form>
	</body>
</html>