<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.rain.bookshop.dto.*" %>
<%@ page import="org.rain.bookshop.utils.*" %>
<%@ page import="org.rain.bookshop.service.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>AdminLoginAction</title>
	</head>
	<body>
		<%
			LocationUtils.printStartLoaction("/admin/adminLoginAction.jsp");
		
			request.setCharacterEncoding("UTF-8");
			
			String adminId = request.getParameter("adminId");
			String adminPw = request.getParameter("adminPw");
			
			Admin admin = new Admin();
			admin.setAdminId(adminId);
			admin.setAdminPw(adminPw);
			
			String resultOfLogin = LoginService.adminLoginCheck(admin);
			
			System.out.println("로그인 결과 : " + resultOfLogin);
			
			if(resultOfLogin.equals("로그인성공")) {
				// 성공 했을 경우 세션영역에 저장할 데이터를 가져온다.
				LoginService.adminGetVO(admin);
				
				session.setAttribute("sessionAdminId", admin.getAdminId());
				session.setAttribute("sessionAdminNo", admin.getAdminNo());
				
				response.sendRedirect(request.getContextPath() + "/admin/index.jsp");
			} else {
				// 실패 하였을 경우 로그인폼으로 돌아간다.
				response.sendRedirect(request.getContextPath() + "/admin/login/adminLoginForm.jsp");
			}
			
			LocationUtils.printEndLoaction("/admin/adminLoginAction.jsp");
		%>
	</body>
</html>