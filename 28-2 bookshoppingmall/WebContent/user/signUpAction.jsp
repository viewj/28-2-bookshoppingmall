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
			System.out.println("location : user/singUpAction.jsp");
			
			// 요청정보 인코딩 방식
			request.setCharacterEncoding("UTF-8");
			
			// signUpForm으로 부터 받은 정보를 담기 위한 VO
			String[] bookcodeForInsertMemberinter = request.getParameterValues("memberinter");
			ArrayList<Memberinter> arrayListMemberinter = new ArrayList<Memberinter>();
			Member member = new Member();

			// VO에 각각의 정보를 담음
			member.setMemberId(request.getParameter("memberId"));
			member.setMemberPw(request.getParameter("memberPw"));
			member.setMemberName(request.getParameter("memberName"));
			member.setMemberAddr(request.getParameter("memberAddr"));
			
			// 관심장르를 선택하지 않았을 경우를 대비함.
			int countForMemberinter = 0; 
			if(bookcodeForInsertMemberinter != null){
				countForMemberinter = bookcodeForInsertMemberinter.length;
			}
			// signUpForm에서 관심장르로 선택한 bookcode들(다중선택으로 인한 배열)을 Memberinter 객체에 대입.
			for(int i = 0; i < countForMemberinter; i++){
				Memberinter memberinter = new Memberinter();
				
				// 폼에서 입력받은 배열(관심장르)을 int 데이터 타입으로 바꾸고 memberinter 객체 내부에 대입.
				memberinter.setBookcodeNo(Integer.parseInt(bookcodeForInsertMemberinter[i]));
				
				// 값이 대입된 memberinter 객체를 리스트에 추가
				arrayListMemberinter.add(memberinter);
			}
			
			// 값 테스트
			System.out.println("member VO unit test");
			System.out.println("memberId : " + member.getMemberId());
			System.out.println("memberPw : " + member.getMemberPw());
			System.out.println("memberName : " + member.getMemberName());
			System.out.println("memberAddr : " + member.getMemberAddr());
			
			// 회원가입 메서드를 호출하고 그 리턴 값(true = 회원가입 성공, false = 실패) 에 따라 세션정보 대입 여부를 정함
			// 회원가입이 성공하면 회원가입한 아이디로 바로 로그인되게 하기위함.
			MemberService memberService = new MemberService();
			
			if(memberService.addMember(member, arrayListMemberinter)){
				session.setAttribute("sessionMemberNo", member.getMemberNo());
				session.setAttribute("sessionMemberId", member.getMemberId());
				
				// 페이지 이동
				response.sendRedirect(request.getContextPath() + "/user/index.jsp");
			} else{
				// 회원가입 실패시 다시 회원가입 폼으로
				System.out.println("회원가입에 실패하였습니다.");
				response.sendRedirect(request.getContextPath() + "/user/signUpForm.jsp");
			}
		%>
	</body>
</html>