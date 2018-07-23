<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			System.out.println("location : user/singUpAction.jsp");
			
			// 요청정보 인코딩 방식
			request.setCharacterEncoding("UTF-8");
			
			MemberService memberService = new MemberService();
			
			// signUpForm으로 부터 받은 정보를 담기 위한 VO
			Memberinter memberinter = new Memberinter();
			Member member = new Member();
			
			// VO에 각각의 정보를 담음
			member.setMemberId(request.getParameter("memberId"));
			member.setMemberPw(request.getParameter("memberPw"));
			member.setMemberName(request.getParameter("memberName"));
			member.setMemberAddr(request.getParameter("memberAddr"));
			
			// 값 테스트
			System.out.println("member VO unit test");
			System.out.println("memberId : " + member.getMemberId());
			System.out.println("memberPw : " + member.getMemberPw());
			System.out.println("memberName : " + member.getMemberName());
			System.out.println("memberAddr : " + member.getMemberAddr());
			
			// 회원가입 메서드를 호출하고 그 리턴 값(true = 회원가입 성공, false = 실패) 에 따라 세션정보 대입 여부를 정함
			// 회원가입이 성공하면 회원가입한 아이디로 바로 로그인되게 하기위함.
			if(memberService.addMember(member, memberinter)){
				session.setAttribute("sessionMemberNo", member.getMemberNo());
				session.setAttribute("sessionMemberId", member.getMemberId());
			}
			
			// 페이지 이동
			response.sendRedirect(request.getContextPath() + "/user/index.jsp");
		%>
	</body>
</html>