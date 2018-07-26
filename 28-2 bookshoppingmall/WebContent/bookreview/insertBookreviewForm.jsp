<!-- 2018.07.26 28기 전재현 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>리뷰 쓰기 화면</title>
	</head>
	<body>
		<% 
			request.setCharacterEncoding("UTF-8");
		
			Integer sessionMemberNo = (Integer)session.getAttribute("sessionMemberNo");
			String sessionMemberId = (String)session.getAttribute("sessionMemberId");
			
			//include에 있는 값 받아오는 코드입니다
			int bookNo = (int) Integer.parseInt(request.getParameter("bookNo"));
			
		%>
		<div align="center">
			<h3 align="left">리뷰</h3>
			<br><br>
			<form action="./insertBookreviewAction.jsp?memberNo=<%=sessionMemberNo%>" method="post">
				<textarea rows="5" cols="100" name="bookreviewContent"></textarea>
				<input type="hidden" name="bookNo" value="bookNo">
				<input type="submit" value="등록">
			</form>
		</div>
	</body>
</html>
