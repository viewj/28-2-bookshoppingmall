<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="service.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	<%
		request.setCharacterEncoding("utf-8");
	
		Qna qna = new Qna();
		qna.setQna_no(Integer.parseInt(request.getParameter("qnaNo")));
		qna.setQna_title(request.getParameter("reTitle"));
		qna.setMember_no(Integer.parseInt(request.getParameter("memberNo")));
		qna.setQna_content(request.getParameter("reContent"));
		
		QnaService qnaService = new QnaService();
		qnaService.updateQna(qna);
		response.sendRedirect(request.getContextPath()+"/user/userQnaList.jsp");
	%>
	</body>
</html>