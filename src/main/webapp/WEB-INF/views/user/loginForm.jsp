<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "../layout/header.jsp" %>

<div class="container">
	<form action="/auth/loginProc" method="post">   <%--submit버튼 누르면 form이 action을 탄다. --%>
	 <%--아래 username, password 값을 가지고 /auth/loginProc으로 이동함. --%>
		<div class="form-group">
			<label for="username">Username</label> 
			<input type="text" name ="username" class="form-control" placeholder="Enter Username" id="username">
		</div>	
		<div class="form-group">
			<label for="password">Password</label> 
			<input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<button id="btn-login" class="btn btn-primary">로그인</button>
	</form>

</div>
<%-- <script src="/js/user.js"></script> --%>
<%@ include file = "../layout/footer.jsp" %>
