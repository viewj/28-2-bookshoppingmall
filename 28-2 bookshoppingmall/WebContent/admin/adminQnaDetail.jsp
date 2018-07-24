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
			
			QnaService qnaService = new QnaService();
			ArrayList<QnaForAdmin> arrayListQna = qnaService.selectAllQnasForAdmin();
		%>
		<div></div>
	</body>
</html>