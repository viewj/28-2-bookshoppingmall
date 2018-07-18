<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>User Login Action</title>
	</head>
	<body>
		<%	
			// 파라미터 인코딩 정보 
			request.setCharacterEncoding("UTF-8");
			
			// MemberService 객체 생성
			MemberService memberService = new MemberService();
			
			// member 객체 생성
			Member member = new Member();
		
			// userLogin으로 부터 넘겨받은 파라미터를 각각의 변수에 대입
			String memberId = request.getParameter("memberId");
			String memberPw = request.getParameter("memberPw");
			
			// 값 테스트 
			System.out.println("userLoginAction, memberId & memberPw : " + memberId + " & " + memberPw);
			
			// member 객체 내부 데이터 영역에 setter를 이용하여 값 대입
			member.setMemberId(memberId);
			member.setMemberPw(memberPw);
			
			// memberLoginCheck 메서드를 호출하여 사용자가 입력한 정보와 DB의 정보를 비교하고 그 결과를 변수에 대입
			String resultOfLogin = memberService.memberLoginCheck(member);
			
			// 아직 세션을 자바에서 설정하는 법을 알지 못하기 때문에 jsp에서 작성
			if(resultOfLogin.equals("로그인성공")){
				// memberGetVO 메서드를 통해 세션에 필요한 정보를 VO에 대입
				memberService.memberGetVO(member);
				
				// 세션에 필요한 정보를 VO로부터 받아 세션영역에 대입.
				session.setAttribute("sessionMemberNo", member.getMemberNo());
				session.setAttribute("sessionMemberName", member.getMemberName());
				
				response.sendRedirect("/user/index.jsp");
			} else if(resultOfLogin.equals("비밀번호불일치")) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				response.sendRedirect("/user/userLogin.jsp");
			} else{
				System.out.println("아이디가 일치하지 않습니다.");
				response.sendRedirect("/user/userLogin.jsp");
			}
		%>
	</body>
</html>