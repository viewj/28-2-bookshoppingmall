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
			
			int bookNo = Integer.parseInt(request.getParameter("bookNo"));
			
			Bookcode bookCode = new Bookcode();
			BookcodeService bookCodeService = new BookcodeService();
			BookService bookService = new BookService();
			BookInformation userInformation = new BookInformation();
			
			//장르 option정보 출력하기 위해 Bookcode타입의 ArrayList클래스를 사용했습니다.
			ArrayList<Bookcode> totalList = bookCodeService.getAllBookcodes();
			
			//수정할 데이터 정보를 가져오는 메서드입니다.
			userInformation = bookService.updateBookSelect(bookNo);
			
		%>
		<h2 align="center">책 등록 하기</h2>
		<br><br>
		<div>
			<form action="../book/updateBookAction.jsp" method="post">
				<div>
					장르 :&nbsp;&nbsp;
					<select name="bookCodeNo">
						<%
							for(int i=0; i<totalList.size(); i++) {
								bookCode = totalList.get(i);
								
								if(userInformation.getBookCodeName().equals(bookCode.getBookcodeName())) {
									%>
										<option value="<%= bookCode.getBookcodeNo()%>" selected><%=bookCode.getBookcodeName()%></option>
									<%
									
								}else {
									%>
										<option value="<%= bookCode.getBookcodeNo()%>"><%=bookCode.getBookcodeName()%></option>
									<%
								}
							}
						%>
					</select>	
				</div>
				<div>
					<input type="hidden" name="bookNo" value="<%=bookNo%>">
					책 이름 :&nbsp;&nbsp;<input type="text" name="bookName" placeholder="Name..." value="<%=userInformation.getBookName()%>">
				</div>
				
				<div>
					저자 :&nbsp;&nbsp;<input type="text" name="bookAuthor" placeholder="auther..." value="<%=userInformation.getBookAuthor()%>">
				</div>
				
				<div>
					출판사 :&nbsp;&nbsp;
					<select name="publisherNo">
						<option value="1">1</option>
					</select>
				</div>
				
				<div>
					판매 금액 :&nbsp;&nbsp;<input type="text" name="bookPrice" placeholder="price..." value="<%=userInformation.getBookPoint()%>">
				</div>
				
				<div>
					적립 마일리지 :&nbsp;&nbsp;<input type="text" name="bookPoint" placeholder="point..."value="<%=userInformation.getBookPrice()%>">
				</div>
				
				<div>
					재고 수량 :&nbsp;&nbsp;<input type="text" name="bookAmount" placeholder="amount..." value="<%=userInformation.getBookAmount()%>">
				</div>
				
				<div>
					절판 :&nbsp;&nbsp;
					<select name="bookOut">
					<%
						if(userInformation.getBookOut().equals("절판")) {
							%>
								<option value="<%=userInformation.getBookOut()%>" selected> <%=userInformation.getBookOut()%></option>
								<option>절판 아님</option>
							<%
						}else {
							%>
								<option value="<%=userInformation.getBookOut()%>" selected> <%=userInformation.getBookOut()%></option>
								<option>절판</option>
							<%
						}
					%>
					</select>
				</div>
				
				<div>
					<input type="submit" value="등록">
					<a href="./bookManager.jsp" >취소</a>
				</div>
			</form>
		</div>
	</body>
</html>