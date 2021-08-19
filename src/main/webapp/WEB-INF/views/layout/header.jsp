<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>  <%--jstl 토튜리얼 사이트에서 코드 복붙 --%>
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


			<c:choose>
				<c:when test="${empty sessionScope.principal}"> <%--조건 문: sessionScope.principal이 null이거나 비어있으면--%>
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="/blog/user/loginForm">로그인</a></li>
						<li class="nav-item"><a class="nav-link" href="/blog/user/joinForm">회원가입</a></li>
					</ul>
				</c:when>
				<c:otherwise>  <%--조건 문: 안 비어있으면 이 메뉴 등장--%>
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="/blog/board/writeForm">글쓰기</a></li>
						<li class="nav-item"><a class="nav-link" href="/blog/user/userForm">회원정보</a></li>
						<li class="nav-item"><a class="nav-link" href="/blog/user/logout">로그아웃</a></li>
					</ul>
				</c:otherwise>
			</c:choose>
			
		</div>
	</nav>
	<br/>
	
	
	
	
	