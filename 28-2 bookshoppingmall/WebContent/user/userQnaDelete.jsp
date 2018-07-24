<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="service.*" %>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	<%
		Qna qna = new Qna();
	 	qna.setQna_no(Integer.parseInt(request.getParameter("qna_no")));
		
	 	QnaService qnaService = new QnaService();
	 	qnaService.deleteQna(qna);
	 	
	 	response.sendRedirect(request.getContextPath()+"/user/userQnaList.jsp");
	%>
	</body>
</html>