<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.rain.bookshop.dto.*" %>
<%@ page import="org.rain.bookshop.service.*" %>
<%@ page import="org.rain.bookshop.utils.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Bookcode Manager</title>
		
	</head>
	<body>
		<%
			LocationUtils.printStartLoaction("/admin/manager/bookcodeManager.jsp");
			
			// 세션 정보 받아오기
			Integer sessionAdminNo = (Integer)session.getAttribute("sessionAdminNo");
			String sessionAdminId = (String)session.getAttribute("sessionAdminId");
		%>
		<jsp:include page="/admin/module/header.jsp" flush="false"/>
		<h1>도서코드 관리</h1>
		<br>
		<form id="addBookcode" action="<%= request.getContextPath() %>/admin/manager/addBookcodeAction.jsp" method="post">
			<input id="bookcodeName" type="text" name="bookcodeName">
			<br>
			<span id="addBookcodeHelper" style="color:red"></span>
			<br><br>
			<input type="button" id="addBookcodeBtn" value="+ 추가">
			<br><br>
		</form>
		
		<form id="deleteBookcode" action="<%= request.getContextPath() %>/admin/manager/deleteBookcodeAction.jsp" method="post">
			<table border="1">
				<thead>
					<tr>
						<th></th>
						<th>도서 코드</th>
						<th>도서 장르 명</th>
					</tr>
				</thead>
				<tbody>
					<%
						ArrayList<Bookcode> arrayListOfBookcodes = BookcodeService.getAllBookcodes();
						for(int i = 0; i < arrayListOfBookcodes.size(); i++){
					%>
							<tr>
								<td><input class="bookcodeCheckBox" type="checkbox" name="bookcodeNo" value="<%= arrayListOfBookcodes.get(i).getBookcodeNo() %>"></td>
								<td><%= arrayListOfBookcodes.get(i).getBookcodeNo() %></td>
								<td><%= arrayListOfBookcodes.get(i).getBookcodeName() %></td>
							</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<br>
			<span id="deleteBookcodeHelper" style="color:red"></span>
			<br><br>
			<input id="deleteBookcodeBtn" type="button" value="- 삭제">
			<script>
				// 폼
				let	addBookcodeForm = document.getElementById('addBookcode');
				let deleteBookcodeForm = document.getElementById('deleteBookcode');
				
				// input type
				let bookcodeName = document.getElementById('bookcodeName');
				let addBookcodeHelper = document.getElementById('addBookcodeHelper');
				let bookcodeCheckBox = document.getElementsByClassName('bookcodeCheckBox');
				let deleteBookcodeHelper = document.getElementById('deleteBookcodeHelper');
				
				// 버튼
				let addBookcodeBtn = document.getElementById('addBookcodeBtn');
				let deleteBookcodeBtn = document.getElementById('deleteBookcodeBtn');
				
				// 세션영역의 값 
				let sessionAdminNo = <%= sessionAdminNo %>;
				
				// 추가 버튼을 클릭했을 때
				addBookcodeBtn.addEventListener('click', ()=>{
					if(sessionAdminNo != null){
						if(bookcodeName.value == ''){
							deleteBookcodeHelper.innerHTML='';
							addBookcodeHelper.innerHTML='값을 입력해 주세요.';
						} else {
							addBookcodeForm.submit();
						}
					}else{
						location.href='<%= request.getContextPath() %>/admin/login/adminLoginForm.jsp';
					}
				});
				
				// 삭제 버튼을 클릭했을 때
				deleteBookcodeBtn.addEventListener('click', ()=>{
					if(sessionAdminNo != null){
						let flag = false;
						for(let i=0;i<bookcodeCheckBox.length;i++){
							if(bookcodeCheckBox[i].checked){
								flag = true;
								break;
							}
						}
						
						if(flag){
							deleteBookcodeForm.submit();
						} else{
							deleteBookcodeHelper.innerHTML='삭제할 bookcode를 선택해 주세요.';
							addBookcodeHelper.innerHTML='';
						}
					}
				});
			</script>
			<%
				LocationUtils.printEndLoaction("/admin/manager/bookcodeManager.jsp");
			%>
		</form>
	</body>
</html>