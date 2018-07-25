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
			
			// 세션 정보 받아오기
			Integer sessionAdminNo = (Integer)session.getAttribute("sessionAdminNo");
			String sessionAdminId = (String)session.getAttribute("sessionAdminId");
			
			// 세션 정보 보내기
			session.setAttribute("sessionAdminId", sessionAdminId);
			session.setAttribute("sessionAdminNo", sessionAdminNo);
			
			int qnaNo = Integer.parseInt(request.getParameter("qna_no"));
						
			QnaService qnaService = new QnaService();
			Qna qna = qnaService.selectForUpdateQna(qnaNo);
			
			Member member = new Member();
			MemberService memberService = new MemberService();
			member = memberService.memberSelect(qna.getMember_no());
		%>
		<div>번호 : <%=qna.getQna_no()%></div>
		<div>아이디 : <%=member.getMemberId()%></div>
		<div>제목 : <%=qna.getQna_title()%></div>
		<div>내용 : <%=qna.getQna_content()%></div>
		<div>작성 날짜 : <%=qna.getQna_date()%></div>
		<span><a href="<%=request.getContextPath()%>/admin/adminQnaUpdateForm.jsp?qna_no=<%=qnaNo%>">수정</a></span>
		<span><a href="<%=request.getContextPath()%>/admin/adminQnaDelete.jsp?qna_no=<%=qnaNo%>">삭제</a></span>
		<span><a href="<%=request.getContextPath()%>/admin/adminQnaList.jsp">목록</a></span>	<br><br>
		<form action="<%=request.getContextPath()%>/admin/adminQnaCommentAction.jsp">
			<span><input type="text" style="width:200px; height:50px;" name="qnaCommentContent"></span>
			<input type="hidden" name="qnaNo" value="<%=qna.getQna_no()%>">
			<span><input type="submit" value="답변하기"></span> <br><br>
		</form>
		
		<%
			QnaCommentService qnaCommentService = new QnaCommentService();
			qnaCommentService.selectAllQnaComments(qnaNo);
		%>
	</body>
</html>