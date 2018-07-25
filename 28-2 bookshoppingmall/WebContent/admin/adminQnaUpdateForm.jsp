<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>AdminQnaUpdateForm</title>
	</head>
	<body>
		<%
			System.out.println("");
			System.out.println("location : admin/AdminQnaUpdateForm.jsp");
			
			request.setCharacterEncoding("UTF-8");
			int qnaNo = Integer.parseInt(request.getParameter("qna_no"));
			QnaService qnaService = new QnaService();
			Qna qna = qnaService.selectForUpdateQna(qnaNo);
			
			Member member = new Member();
			MemberService memberService = new MemberService();
			member = memberService.memberSelect(qna.getMember_no());
		%>
		<form action="<%=request.getContextPath()%>/admin/adminQnaUpdateAction.jsp" method="post">
			<div>번호 : <input type="text" name="qnaNo" value="<%=qnaNo%>" readonly></div>
			<div>아이디 : <input type="text" name="memberId" value="<%=member.getMemberId()%>" readonly></div>
			<div>제목 : <input type="text" name="qnaTitle" value="<%=qna.getQna_title()%>"></div>
			<div>내용 : <input type="text" name="qnaContent" value="<%=qna.getQna_content()%>"></div>
			<div>작성 날짜 : <%=qna.getQna_date()%></div>
		<input type="submit" value="수정하기">
		</form>
		
	</body>
</html>