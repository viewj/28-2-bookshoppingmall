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
			System.out.println("location : user/AdminQnaUpdateForm.jsp");
			
			request.setCharacterEncoding("UTF-8");
			int qnaNo = Integer.parseInt(request.getParameter("qna_no"));
			QnaService qnaService = new QnaService();
			Qna qna = qnaService.selectForUpdateQna(qnaNo);
			
			MemberService memberService = new MemberService();
			String memberName = memberService.selectMemberNameForQnaList(qna.getMember_no());
		%>
		<form action="./adminQnaUpdateAction.jsp" method="post">
			<div>번호 : <input type="text" id="qnaNo" value="<%=qna.getQna_no()%>" readonly></div>
			<div>아이디 : <input type="text" id="memberName" value="<%=memberName%>" readonly></div>
			<div>제목 : <input type="text" id="qnaTitle" value="<%=qna.getQna_title()%>"></div>
			<div>내용 : <input type="text" id="qnaContent" value="<%=qna.getQna_content()%>"></div>
			<div>작성 날짜 : <%=qna.getQna_date()%></div>
		</form>
		<input type="submit" value="수정하기">
	</body>
</html>