<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>AdminQnaList</title>
	</head>
	<body>
		<%
			System.out.println("");
			System.out.println("location : user/index.jsp");
			
			// 세션 정보 받아오기
			Integer sessionAdminNo = (Integer)session.getAttribute("sessionAdminNo");
			String sessionAdminId = (String)session.getAttribute("sessionAdminId");
			
			QnaService qnaService = new QnaService();
			ArrayList<Qna> arrayListQna = qnaService.selectAllQnas();
		%>
		<table>
			<thead>
				<tr>
					<th>번호</th>	<th>제목</th>	<th>아이디</th>	<th>날짜</th>	<th>수정</th>	<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<%
					for(int i=0; i<arrayListQna.size();i++) {
						Qna qna = arrayListQna.get(i);
				%>
					<tr>
						<td></td>
					</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<a href="<%=request.getContextPath()%>/admin/adminQnaInsert.jsp">글쓰기</a>
	</body>
</html>