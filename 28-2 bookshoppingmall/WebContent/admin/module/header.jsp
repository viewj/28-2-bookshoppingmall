<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="quickNav" align="right">
	<%
		// 세션 정보 받아오기
		Integer sessionAdminNo = (Integer)session.getAttribute("sessionAdminNo");
		String sessionAdminId = (String)session.getAttribute("sessionAdminId");
		if(sessionAdminNo == null){
	%>
	
			<a href="<%= request.getContextPath() %>/admin/login/adminLoginForm.jsp">로그인</a> |
			<a href="<%= request.getContextPath() %>/admin/adminSignUpForm.jsp">회원 가입</a> | 
			<a href="#로그인?장바구니">장바구니</a> | 
			<a href="#로그인?내정보">내 정보</a>
	<%
		} else {
	%>
			안녕! <%= sessionAdminId %>님 |
			<a href="<%= request.getContextPath() %>/admin/login/adminLogout.jsp">로그아웃</a> | 
			<a href="#장바구니">장바구니</a> | 
			<a href="#내정보">내 정보</a>
	<%
		}
	%>
</div>
<br><br>
<div id="mainTitle" align="center">
	<h1><a href="<%=request.getContextPath() %>/admin/index.jsp">The Lives of Others</a></h1>
</div>
<div id="mainNav">
	<ul>
		<li><a href="<%= request.getContextPath() %>/admin/manager/bookcodeManager.jsp">도서코드 관리</a></li>
		<li><a href="<%= request.getContextPath() %>/admin/adminLoginForm.jsp">출판사 관리</a></li>
		<li><a href="<%= request.getContextPath() %>/admin/adminLoginForm.jsp">판매도서 관리</a></li>
		<li><a href="<%= request.getContextPath() %>/admin/adminLoginForm.jsp">주문내역 관리</a></li>
		<li><a href="<%= request.getContextPath() %>/admin/adminLoginForm.jsp">Q &amp; A</a></li>
		<li><a href="<%= request.getContextPath() %>/admin/bookIntro/bookIntroInsert.jsp?">도서 소개글 쓰기</a></li>
	</ul>
</div>
<br><br>