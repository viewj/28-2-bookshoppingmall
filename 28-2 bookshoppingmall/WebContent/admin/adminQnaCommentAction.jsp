<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>adminQnaInsert</title>
	</head>
	<body>
		<%
			// 세션 정보 받아오기
			Integer sessionAdminNo = (Integer)session.getAttribute("sessionAdminNo");
			String sessionAdminId = (String)session.getAttribute("sessionAdminId");
			
			// 세션 정보 보내기
			session.setAttribute("sessionAdminId", sessionAdminId);
			session.setAttribute("sessionAdminNo", sessionAdminNo);
			
			AdminService adminService = new AdminService();
			int adminNo = adminService.selectAdminNo(sessionAdminId);
			
			int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
			String qnaComentContent = request.getParameter("qnaCommentContent");
			
			QnaComment qnaComment = new QnaComment();
			qnaComment.setAdminNo(adminNo);
			qnaComment.setQnaNo(qnaNo);
			qnaComment.setQnaCommentContent(qnaComentContent);
			
			QnaCommentService qnaCommentService = new QnaCommentService();
			qnaCommentService.addQnaComment(qnaComment);
			
			response.sendRedirect(request.getContextPath()+"/admin/adminQnaDetail.jsp?qna_no="+qnaNo);
		%>
	</body>
</html>