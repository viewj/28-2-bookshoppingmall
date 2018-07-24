<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="service.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	
		<table border="1">
			<tr>
				<td>번호</td> 
				<td>제목</td> 
				<td>이름</td> 
				<td>날짜</td>
			</tr>
			<%
				QnaService qnaService = new QnaService();
				ArrayList<Qna> arrayListselectAllQnas = qnaService.selectAllQnas();
				
				for(int i=0; i<arrayListselectAllQnas.size(); i++){
					Qna qna = arrayListselectAllQnas.get(i);
			%>
					<tr>
						<td><%=qna.getQna_no()%> </td>
						<td><a href="<%=request.getContextPath()%>/user/userQnaDetail.jsp?qna_no=<%=qna.getQna_no()%>"><%=qna.getQna_title()%></a></td>
						<% 
							int memberNo = qna.getMember_no();
							MemberService memberService = new MemberService();
							String memberName = memberService.selectMemberNameForQnaList(memberNo);
						%> 
						<td><%=memberName %></td>
						<td><%=qna.getQna_date()%> </td>
					</tr>
			<%
					
				}
			%>
		</table>
		<a href="<%=request.getContextPath() %>/user/userQnaInsertForm.jsp">질문하기</a>

	</body>
</html>


