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
		<table>
		<%
			int qnaNo = Integer.parseInt(request.getParameter("qna_no"));
			QnaService qnaService = new QnaService();
			
			Qna qna = qnaService.userQnaListDetails(qnaNo);
		%>
			<tr>
				<td>번호 : <%=qna.getQna_no() %></td>
			</tr>
			<tr>
				<td>이름 : <% 
							int memberNo = qna.getMember_no();
							MemberService memberService = new MemberService();
							String memberName = memberService.selectMemberNameForQnaList(memberNo);
						%> 
						<%=memberName %>
				</td>
			</tr>
			<tr>
				<td>제목 : <%=qna.getQna_title() %></td>
			</tr>
			<tr>
				<td>내용 : <%=qna.getQna_content() %></td>
			</tr>
			<tr>
				<td>작성날짜 : <%=qna.getQna_date() %></td>
			</tr>
		</table>
		<a href="<%=request.getContextPath() %>/user/userQnaUpdateForm.jsp?qna_no=<%=qna.getQna_no()%>">수정</a>
		<a href="<%=request.getContextPath() %>/user/userQnaDelete.jsp?qna_no=<%=qna.getQna_no()%>">삭제</a>
		<a href="<%=request.getContextPath() %>/user/userQnaList.jsp">목록</a>
	</body>
</html>