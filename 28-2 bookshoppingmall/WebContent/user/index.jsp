<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Index</title>
	</head>
	<body>
		<%
			// 세션 정보 받아오기
			int sessionMemberNo = (int)session.getAttribute("sessionMemberNo");
			String sessionMemberName = (String)session.getAttribute("sessionMemberName");
		%>
		
		<h1>28번가</h1>
		<br><br>
		<%
			if(sessionMemberName == null){
		%>
				<div> 로그인 | 회원가입 | 장바구니 | 마이페이지 </div>
		<%
			} else {
		%>
				<div> <%= sessionMemberName %> 님| 회원가입 | 장바구니 | 마이페이지 </div>
		<%
			}
		%>
	</body>
</html>