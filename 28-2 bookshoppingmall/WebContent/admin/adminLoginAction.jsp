<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<%@page import="org.apache.catalina.filters.SetCharacterEncodingFilter"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>AdminLoginAction</title>
	</head>
	<body>
		<%
			System.out.println("");
			System.out.println("location : user/userLoginAction.jsp");
			
			request.setCharacterEncoding("UTF-8");
			
			String adminId = request.getParameter("adminId");
			String adminPw = request.getParameter("adminPw");
			
			Admin admin = new Admin();
			admin.setAdminId(adminId);
			admin.setAdminPw(adminPw);
			
			AdminService adminService = new AdminService();
			String resultOfLogin = adminService.adminLoginCheck(admin);
			
			System.out.println("로그인 결과 : " + resultOfLogin);
			
			if(resultOfLogin.equals("로그인성공")) {
				adminService.adminGetVO(admin);
				
				session.setAttribute("sessionAdminId", admin.getAdminId());
				session.setAttribute("sessionAdminNo", admin.getAdminNo());
				
				Integer sessionAdminNo = (Integer)session.getAttribute("sessionAdminNo");
				System.out.println(sessionAdminNo);
				
				response.sendRedirect(request.getContextPath() + "/admin/index.jsp");
			} else if (resultOfLogin.equals("비밀번호불일치")) {
				System.out.println("비밀번호 불일치");
				response.sendRedirect(request.getContextPath() + "/admin/adminLoginForm.jsp");
			} else {
				System.out.println("아이디 불일치");
				response.sendRedirect(request.getContextPath() + "/admin/adminLoginForm.jsp");
			}
			
			System.out.println("");
			System.out.println("End of user/userLoginAction.jsp");
		%>
	</body>
</html>