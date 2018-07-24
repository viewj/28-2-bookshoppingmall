<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="service.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sign Up Action</title>
	</head>
	<body>
		<%
			System.out.println("");
			System.out.println("location : admin/adminsingUpAction.jsp");
			
			// 요청정보 인코딩 방식
			request.setCharacterEncoding("UTF-8");
			
			// signUpForm으로 부터 받은 정보를 담기 위한 VO
			Admin admin = new Admin();
			AdminService adminService = new AdminService();
			
			// VO에 각각의 정보를 담음
			admin.setAdminId(request.getParameter("adminId"));
			admin.setAdminPw(request.getParameter("adminPw"));
			admin.setAdminName(request.getParameter("adminName"));
					
			// 값 테스트
			System.out.println("admin VO unit test");
			System.out.println("adminId : " + admin.getAdminId());
			System.out.println("adminPw : " + admin.getAdminPw());
			System.out.println("adminName : " + admin.getAdminName());
			
			// 회원가입 메서드를 호출하고 그 리턴 값(true = 회원가입 성공, false = 실패) 에 따라 세션정보 대입 여부를 정함
			// 회원가입이 성공하면 회원가입한 아이디로 바로 로그인되게 하기위함.
			MemberService memberService = new MemberService();
			
			if(adminService.addAdmin(admin)){
				session.setAttribute("sessionAdminNo", admin.getAdminNo());
				session.setAttribute("sessionAdminId", admin.getAdminId());
				
				// 페이지 이동
				response.sendRedirect(request.getContextPath() + "/admin/index.jsp");
			} else{
				// 회원가입 실패시 다시 회원가입 폼으로
				System.out.println("회원가입에 실패하였습니다.");
				response.sendRedirect(request.getContextPath() + "/admin/adminsignUpForm.jsp");
			}
		%>
	</body>
</html>