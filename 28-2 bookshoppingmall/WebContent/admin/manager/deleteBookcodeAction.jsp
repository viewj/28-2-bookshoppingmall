<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.rain.bookshop.utils.*" %>
<%@ page import="org.rain.bookshop.service.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Delete Bookcode Action</title>
	</head>
	<body>
		<%
			LocationUtils.printStartLoaction("/admin/manager/deleteBookcodeAction.jsp");
			
			String[] arrayBookcodeNo = request.getParameterValues("bookcodeNo");
			
			for(int i = 0; i < arrayBookcodeNo.length; i++){
				// 현재 arrayBookcodeNo 배열의 각각 요소에 담긴 값은 문자열이다.
				// 따라서 parseInt 메서드를 통해 int형으로 형변환 하였다.
				BookcodeService.deleteBookcode(Integer.parseInt(arrayBookcodeNo[i]));
			}
			
			response.sendRedirect(request.getContextPath() + "/admin/manager/bookcodeManager.jsp");
			
			LocationUtils.printEndLoaction("/admin/manager/deleteBookcodeAction.jsp");
		%>
	</body>
</html>