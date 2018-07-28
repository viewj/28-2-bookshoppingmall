<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>AdminQnaDelete</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("UTF-8");
			
			int qnaNo = Integer.parseInt(request.getParameter("qna_no"));
			QnaService qnaService = new QnaService();
			qnaService.deleteQna(qnaNo);
			
			response.sendRedirect(request.getContextPath() + "/admin/adminQnaList.jsp");
		%>
	</body>
</html>