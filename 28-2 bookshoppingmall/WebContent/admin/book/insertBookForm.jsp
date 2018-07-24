<!-- 2018.07.23 28기 전재현 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("UTF-8");
		
			Bookcode bookCode = new Bookcode();
			
			BookcodeService bookCodeService = new BookcodeService();
			ArrayList<Bookcode> totalList = bookCodeService.getAllBookcodes();
			
		%>
		<h2 align="center">책 등록 하기</h2>
		<br><br>
		<div>
			<form action="../book/insertBookAction.jsp" method="post">
				<div>
					장르 :&nbsp;&nbsp;
					<select name="bookCodeNo">
						<%
							for(int i=0; i<totalList.size(); i++) {
								bookCode = totalList.get(i);
						%>
							
								<option value="<%= bookCode.getBookcodeNo()%>"><%=bookCode.getBookcodeName()%></option>
						<%
							}
						%>
					</select>	
				</div>
				<div>
					책 이름 :&nbsp;&nbsp;<input type="text" name="bookName" placeholder="Name...">
				</div>
				<div>
					저자 :&nbsp;&nbsp;<input type="text" name="bookAuthor" placeholder="auther...">
				</div>
				<div>
					출판사 :&nbsp;&nbsp;
					<select name="publisherNo">
						<option value="1">1</option>
					</select>
				</div>
				<div>
					판매 금액 :&nbsp;&nbsp;<input type="text" name="bookPrice" placeholder="price...">
				</div>
				<div>
					적립 마일리지 :&nbsp;&nbsp;<input type="text" name="bookPoint" placeholder="point...">
				</div>
				<div>
					재고 수량 :&nbsp;&nbsp;<input type="text" name="bookAmount" placeholder="amount...">
				</div>
				<div>
					절판 :&nbsp;&nbsp;
					<select name="bookOut">
						<option>절판</option>
						<option>절판아님</option>
					</select>
				</div>
				<div>
					<input type="submit" value="등록">
				</div>
			</form>
		</div>
	</body>
</html>