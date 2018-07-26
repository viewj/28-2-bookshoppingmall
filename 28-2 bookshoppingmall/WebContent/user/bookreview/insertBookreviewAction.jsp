<!-- 2018.07.26 28기 전재현 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>리뷰 글 입력</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("UTF-8");
		
			Integer sessionMemberNo = (Integer)session.getAttribute("sessionMemberNo");
			String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		
			//화면에서 입력값 받아오는 코드입니다.
			String bookreviewContent = request.getParameter("bookreviewContent");
			int bookNo = Integer.parseInt(request.getParameter("bookNo"));
			int memberNo = Integer.parseInt(request.getParameter("memberNo"));
			
			System.out.println(bookreviewContent +"bookreviewContent insertBookreviewAction.jsp");
			System.out.println(bookNo +"bookNo insertBookreviewAction.jsp");
			System.out.println(memberNo +"memberNo insertBookreviewAction.jsp");
			
			//DB에 등록하기 위해 받아온 값들을 VO에 담는 코드입니다.
			Bookreview bookreview = new Bookreview();
			
			bookreview.setBookNo(bookNo);
			bookreview.setMemberNo(memberNo);
			bookreview.setBookreviewContent(bookreviewContent);
			
			//Service를 실행입니다
			BookreviewService bookreviewService = new BookreviewService();
			bookreviewService.insertBookreview(bookreview);
			
			response.sendRedirect("./../book/listBookDetail.jsp?bookNo="+bookNo);
		%>
	</body>
</html>