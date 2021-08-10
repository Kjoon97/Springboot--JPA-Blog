
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/blog">KangJoonHyuk</a>      <%--href ="/blog" 으로 홈 화면을 메인 화면으로 설정함--%>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="/user/login">로그인</a></li>
				<li class="nav-item"><a class="nav-link" href="/user/join">회원가입</a></li>
			</ul>
		</div>
	</nav>
	<br>

	<div class="container">

		<div class="card m-2">        <%-- m-2로하면 마진이 2만큼 생겨서 화면 간의 조금 여유를 줄 수 있음--%>
			<div class="card-body">
				<h4 class="card-title">제목 적는 부분</h4>
				<p class="card-text">내용 적는 부분</p>
				<a href="#" class="btn btn-primary">상세 보기</a>
			</div>
		</div>
		<div class="card m-2">        <%-- m-2로하면 마진이 2만큼 생겨서 화면 간의 조금 여유를 줄 수 있음--%>
			<div class="card-body">
				<h4 class="card-title">제목 적는 부분</h4>
				<p class="card-text">내용 적는 부분</p>
				<a href="#" class="btn btn-primary">상세 보기</a>
			</div>
		</div>
		<div class="card m-2">        <%-- m-2로하면 마진이 2만큼 생겨서 화면 간의 조금 여유를 줄 수 있음--%>
			<div class="card-body">
				<h4 class="card-title">제목 적는 부분</h4>
				<p class="card-text">내용 적는 부분</p>
				<a href="#" class="btn btn-primary">상세 보기</a>
			</div>
		</div>

	</div>

	<div class="jumbotron text-center" style="margin-bottom: 0">
		<p>Created by joonhyuk kang</p>   <%--Footer--%>
		<p>📞 010-8888-6666</p>    <%--Footer--%>
		<p>🏁 서울시 강남구 역삼동</p>    <%--Footer--%>
	</div>
</body>
</html>