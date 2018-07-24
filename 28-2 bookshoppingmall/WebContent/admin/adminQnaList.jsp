<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>AdminQnaList</title>
	</head>
	<body>
		<%
			System.out.println("");
			System.out.println("location : user/AdminQnaList.jsp");
			
			QnaService qnaService = new QnaService();
			ArrayList<QnaForAdmin> arrayListQna = qnaService.selectAllQnasForAdmin();
		%>
		<table>
			<thead>
				<tr>
					<th>번호</th>	<th>제목</th>	<th>아이디</th>	<th>작성날짜</th>	<th>삭제하기</th>	<th>답변 달기</th>
				</tr>
			</thead>
			<tbody>
				<%
					for(int i=0; i<arrayListQna.size();i++) {
						QnaForAdmin qnaForAdmin = arrayListQna.get(i);
				%>
					<tr>
						<td><%=qnaForAdmin.getQna_no()%></td>
						<td><a href="<%=request.getContextPath()%>/admin/adminQnaDetail.jsp?qna_no=<%=qnaForAdmin.getQna_no()%>"><%=qnaForAdmin.getQna_title()%></a></td>
						<td><%=qnaForAdmin.getMember_id()%></td>
						<td><%=qnaForAdmin.getQna_date()%></td>
						<td><a href="<%=request.getContextPath()%>/admin/adminQnaDelete.jsp?qna_no=<%=qnaForAdmin.getQna_no()%>">삭제</a>
						<td><a href="<%=request.getContextPath()%>/admin/adminQnaInsert.jsp">답변</a>
					</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</body>
</html>