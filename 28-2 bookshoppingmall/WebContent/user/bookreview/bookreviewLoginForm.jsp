<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>책 상세페이지에서 등록버튼 클리시 이동되는 로그인 화면</title>
	</head>
	<body>
		<%
			int bookNo = Integer.parseInt(request.getParameter("bookNo"));
		%>
		<h1>일반회원 로그인</h1>
		<br><br>
		<form action="<%= request.getContextPath() %>/user/bookreview/bookreviewLoginAction.jsp?bookNo=<%=bookNo%>" method="post">
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
			<button>로그인</button>
		</form>
	</body>
</html>