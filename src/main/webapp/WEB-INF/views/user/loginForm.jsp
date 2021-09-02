<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "../layout/header.jsp" %>

<div class="container" style="height: 500px">
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
		<a href ="https://kauth.kakao.com/oauth/authorize?client_id=c0a13425dd6eb7604d1ab2f9f1754aa6&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img height="38px" src="/image/kakao_login_button.png"/></a>    <!-- 카카오 로그인 버튼 -->
	</form>

</div>
<%-- <script src="/js/user.js"></script> --%>
<%@ include file = "../layout/footer.jsp" %>
