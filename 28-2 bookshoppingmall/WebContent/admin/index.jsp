<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.rain.bookshop.utils.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Index</title>
	</head>
	<body>
		<%
			LocationUtils.printStartLoaction("/admin/index.jsp");
		
			// 세션 정보 받아오기
			Integer sessionAdminNo = (Integer)session.getAttribute("sessionAdminNo");
			String sessionAdminId = (String)session.getAttribute("sessionAdminId");
			
			// 값 테스트
			System.out.println("sessionAdminNo & sessionAdminId : " + sessionAdminNo + " & " + sessionAdminId);
			
			String contentPage = request.getContextPath() + "/admin/module/header.jsp";
		%>
		<jsp:include page="./module/header.jsp" flush="false"/>
		<%
			LocationUtils.printEndLoaction("/admin/index.jsp");
		%>
	</body>
</html>