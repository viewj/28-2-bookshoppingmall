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
		<h1>일반 회원 가입</h1>
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
				이름<br><br>
				<input type="text" name="memberName">
			</label>
			<br><br>
			<label>
				주소<br><br>
				<input type="text" name="memberAddr">
			</label>
			<br><br>
			<label>
				관심 장르<br><br>
				<%
					// getAllBookcodes 메서드를 호출하고 리턴받은 리스트를 객체참조변수에 대입
					BookcodeService bookcodeService = new BookcodeService();
					ArrayList<Bookcode> arrayListOfAllBookcodes = bookcodeService.getAllBookcodes();
					for(int i = 0; i < arrayListOfAllBookcodes.size(); i++){
				%>
						<input type="checkbox" name="memberinter" value="<%= arrayListOfAllBookcodes.get(i).getBookcodeNo()%>"><%= arrayListOfAllBookcodes.get(i).getBookcodeName() %>
				<%
					}
				%>
			</label>
			<br><br><br>
			<button>가입</button>
		</form>
	</body>
</html>