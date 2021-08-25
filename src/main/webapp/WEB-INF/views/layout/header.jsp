<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>  <%--jstl 토튜리얼 사이트에서 코드 복붙 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>      <%--시큐리티 taglib 사이트에서 복붙--%>

<sec:authorize access="isAuthenticated">
   <sec:authentication property="principal" var="principal"/>  <%-- principal은 현재 유저 오브젝트에 다이렉트 접근가능하게해줌, 객체가 var값("principal")에 저장됨.--%>
</sec:authorize>


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

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">      <%--summer note--%>
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>              <%--summer note--%>
</head>
<body>
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/">KangJoonHyuk</a>      <%--href ="/blog" 으로 홈 화면을 메인 화면으로 설정함--%>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">


			<c:choose>
				<c:when test="${empty principal}"> <%--조건 문: principal이 비어있으면 로그인이 안된 것.--%>
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="/auth/loginForm">로그인</a></li> <%-- ->인증이 필요없는 것에는 다 auth를 붙임--%>
						<li class="nav-item"><a class="nav-link" href="/auth/joinForm">회원가입</a></li>
					</ul>
				</c:when>
				<c:otherwise>  <%--조건 문: 안 비어있으면 로그인이 성공한 것(아래 메뉴 등장)--%>
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="/board/saveForm">글쓰기</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/updateForm">회원정보</a></li>
						<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
					</ul>
				</c:otherwise>
			</c:choose>
			
		</div>
	</nav>
	<br/>
	
	
	
	
	