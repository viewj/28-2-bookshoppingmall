<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>AdminQnaDetail</title>
	</head>
	<body>
		<%
			System.out.println("");
			System.out.println("location : user/AdminQnaDetail.jsp");
			
			request.setCharacterEncoding("UTF-8");
			
			int qnaNo = Integer.parseInt(request.getParameter("qna_no"));
			QnaService qnaService = new QnaService();
			Qna qna = qnaService.selectForUpdateQna(qnaNo);
			
			MemberService memberService = new MemberService();
			String memberName = memberService.selectMemberNameForQnaList(qna.getMember_no());
		%>
		<div>번호 : <%=qna.getQna_no()%></div>
		<div>이름 : <%=memberName%></div>
		<div>제목 : <%=qna.getQna_title()%></div>
		<div>내용 : <%=qna.getQna_content()%></div>
		<div>작성 날짜 : <%=qna.getQna_date()%></div>
		<span><a href="<%=request.getContextPath()%>/admin/adminQnaUpdateForm.jsp?qna_no=<%=qnaNo%>">수정</a></span>
		<span><a href="<%=request.getContextPath()%>/admin/adminQnaDelete.jsp?qna_no=<%=qnaNo%>">삭제</a></span>
		<span><a href="<%=request.getContextPath()%>/admin/adminQnaList.jsp">목록</a></span>
	</body>
</html>