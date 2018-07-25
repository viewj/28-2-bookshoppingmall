<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>AdminQnaUpdateAction</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("UTF-8");
			
			System.out.println("");
			System.out.println("location : admin/AdminQnaUpdateAction.jsp");
			
			int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
			String qnaTitle = request.getParameter("qnaTitle");
			String qnaContent = request.getParameter("qnaContent");
			
			Qna qna = new Qna();
			qna.setQna_no(qnaNo);
			qna.setQna_title(qnaTitle);
			qna.setQna_content(qnaContent);
			
			System.out.println(qna.getQna_no());
			System.out.println(qna.getQna_title());
			System.out.println(qna.getQna_content());
			
			QnaService qnaService = new QnaService();
			qnaService.updateQna(qna);
			
			response.sendRedirect(request.getContextPath()+"/admin/adminQnaList.jsp");
		%>
	</body>
</html>