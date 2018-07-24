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
		<form action="<%=request.getContextPath()%>/user/userQnaUpdateAction.jsp" method="post">
			<table>
			
			<%
				int qnaNo = Integer.parseInt(request.getParameter("qna_no"));
				
				QnaService qnaService = new QnaService();
				
				Qna qna = qnaService.selectForUpdateQna(qnaNo);
			%>
				<tr>
					<td>번호 : <input type="text" name="qnaNo" value="<%=qnaNo %>" readonly></td>
				</tr>
				<tr>
					<td>이름 : <% 
								int memberNo = qna.getMember_no();
								MemberService memberService = new MemberService();
								String memberName = memberService.selectMemberNameForQnaList(memberNo);
							%> 
							<input tyep="text" value="<%=memberName %>" readonly>
							<input type="hidden" name="memberNo" value="<%=qna.getMember_no() %>">
					</td>
				</tr>
				<tr>
					<td>제목 : <input type="text" name="reTitle" value="<%=qna.getQna_title()%>"></td>
				</tr>
				<tr>
					<td>내용 : <textarea name="reContent" value="<%=qna.getQna_content()%>"></textarea></td>
				</tr>
				
			</table>
			<input type="submit" value="수정">
		</form>
	</body>
</html>