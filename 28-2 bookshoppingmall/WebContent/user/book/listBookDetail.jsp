<!-- 2018.07.24 28기 전재현 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>책 정보 상세 보기 </title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("UTF-8");
			
			Integer sessionMemberNo = (Integer)session.getAttribute("sessionMemberNo");
			String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		
			System.out.println(sessionMemberId + "<- sessionMemberId listBookDetail.jsp");
			
			//리스트에서 보내온 값을 받는 코드
			int bookNo = Integer.parseInt(request.getParameter("bookNo"));
			
			//화면에서 보여줄 값을 가져오기
			BookService bookService = new BookService();
			
			BookDetail bookDetail = bookService.selectBookDatail(bookNo);
			System.out.println(bookDetail +"<-bookDetail listBookDetail.jsp");
			
			//댓글 리스트목록 가져오기
			
			BookreviewService bookreviewService = new BookreviewService();
			
			ArrayList<BookreviewList> reviewList = bookreviewService.totalReview(bookNo);
			
			
		%>
		<div align="center">
			<form action="./../shoppingcart/insertShoppingcartAction.jsp" method="post">
				<table>
					<thead>
						<tr>
							<th><%=bookDetail.getBookName()%></th>
							<td><input type="hidden" name="bookName" value="<%=bookDetail.getBookName()%>"></td>
						</tr>
					</thead>
					<!-- 책의 상세정보 테이블 -->
					<tbody>
						<tr>
							<td><input type="hidden" name="bookNo" value="<%=bookDetail.getBookNo()%>">작가 :<%=bookDetail.getBookAuthor()%></td>
						</tr>
						<tr>
							<td>출판사 :<%=bookDetail.getPublisherName()%></td>
						</tr>
						<tr>
							<td>장르 :<%=bookDetail.getBookcodeName()%></td>
						</tr>
						<tr>
							<td><input type="hidden" name="bookPrice" value="<%=bookDetail.getBookPrice()%>">가격 :<%=bookDetail.getBookPrice()%></td>
						</tr>
						<tr>
							<td><input type="hidden" name="BookPoint" value="<%=bookDetail.getBookPoint()%>">마일리지 :<%=bookDetail.getBookPoint()%></td>
						</tr>
						<tr>
							<td>현재 수량 :<%=bookDetail.getBookAmount()%></td>
						</tr>
						<tr>
							<td>구매 수량 :<input type="number" name="BookAmount" max="<%=bookDetail.getBookAmount()%>" min="0" value="1"><input type="hidden" name="maxAmount" value="<%=bookDetail.getBookAmount()%>"></td>
					
						</tr>
						<tr>
							<td>절판 상황 :<%=bookDetail.getBookOut()%></td>
						</tr>
					</tbody>
				</table><br>
				<!-- 책소개 테이블 -->
				<table>
					<thead>
						<tr>
							<th>책 소개</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>작성자 :<%=bookDetail.getBookintroWriter()%></td>
							<td><input type="hidden" name="BookintroWriter" value="<%=bookDetail.getBookintroWriter()%>"></td>
						</tr>
						<tr>
							<td>내용 : <%=bookDetail.getBookintroContent()%></td>
							<td><input type="hidden" name="BookintroContent" value="<%=bookDetail.getBookintroContent()%>"></td>
						</tr>
					</tbody>
				</table><br>
				
				<a href="*">구매</a>
				<input type="submit" value="장바구니에 담기">
			</form>
		</div>
		
		<!-- 댓글 입력 창 & 댓글리스트  -->
		<div align="center">
			<h3>리뷰</h3>
			<form action="./../bookreview/insertBookreviewAction.jsp?memberNo=<%=sessionMemberNo%>" method="post">
				<textarea rows="2" cols="100" name="bookreviewContent"></textarea>
				<input type="hidden" name="bookNo" value="<%=bookNo%>">
				<%
					if(sessionMemberId == null) {
				%>
						<a href="./../bookreview/bookreviewLoginForm.jsp?bookNo=<%=bookNo%>">등록</a>
				<%		
					}else {
				%>
						<input type="submit" value="등록">
				<%
					}
				%>
			</form>
			<!-- 상세페이지 댓글 리스트 -->
			<div>
				<form>
					<%
						for(int i=0; i<reviewList.size(); i++) {
							BookreviewList bookreviewList = reviewList.get(i);
					%>
						<table>
							<thead>
								<tr>
									<th><input type="hidden" name="memberId" value="<%=bookreviewList.getMember().getMemberId()%>"><%=bookreviewList.getMember().getMemberId()%></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input type="hidden" name="bookreviewContent" value="<%=bookreviewList.getBookreview().getBookreviewContent()%>"><%=bookreviewList.getBookreview().getBookreviewContent()%></td>
									
									
									<%
										if(sessionMemberId == bookreviewList.getMember().getMemberId()) {
									%>
											<td><input type="submit" value="수정"></td>
											<td><a href="deleteBookreviewAction.jsp?bookreviewContent=<%=bookreviewList.getBookreview().getBookreviewContent()%>&bookNo=<%=bookNo%>">삭제</a></td>
									<%
										}
									%>
								</tr>
							</tbody>
						</table>
					<%
						}
					%>
				</form>
			</div>
		</div>
	</body>
</html>