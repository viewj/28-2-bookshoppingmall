<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	<%
		Integer sessionMemberNo = (Integer)session.getAttribute("sessionMemberNo");
	%>
		<form action="<%=request.getContextPath() %>/user/userQnaInsertAction.jsp" method="post">
			<table>
				<tr>
					<td>제목 : <input type="text" name="qnaTitle"></td>
				</tr>
				<tr>
					<td>내용 : <textarea name="qnaContent"></textarea><input type="hidden" name="memberNo"></td>
				</tr>
				<tr><td><input type="submit" value="완료"></td></tr>
			</table>
		</form>
	</body>
</html>