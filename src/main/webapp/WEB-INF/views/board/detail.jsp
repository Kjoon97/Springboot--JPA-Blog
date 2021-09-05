<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<button class="btn btn-outline-secondary" onclick="history.back()">돌아가기</button>

	<c:if test="${board.user.id == principal.user.id}">     <%--사용자 본인의 게시판만 수정,삭제 가능--%>
		<a href="/board/${board.id}/updateForm" class="btn btn-outline-warning">수정</a>
		<button id="btn-delete" class="btn btn-outline-danger">삭제</button>
	</c:if>
	
	<br />
	<br />
	
	<div>
		글 번호: <span id="id"><i>${board.id} </i></span> 
		작성자: <span><i>${board.user.username} </i></span>
	</div>
	
	<br />
	
	<div>
		<h3>${board.title}</h3>
	</div> 
	
	<hr />
	<div>
		<div>${board.content}</div>
	</div>
	<hr />
	
	<div class="card" >
	     <form>
	       <input type="hidden" id="boardId" value="${board.id}"/>
	       <div class="card-body"><textarea id="reply-content" class="form-control" rows ="1"></textarea></div>
	     
	       <div class="card-footer"><button type="button" id="btn-reply-save" class="btn btn-primary">등록</button></div>
	       </form>
	</div>
	</br>
	<div class ="card">
	   <div class ="card-header">댓글 리스트</div>
	   <ul id="reply--box" class="list-group">
	   
	   <c:forEach var="reply" items="${board.replys}"> <%--BoarController에서 받은 board안에 replys가 있음. replys를 reply변수 에 한 개씩 차례로 넣는다.--%>
	       
	       <li id ="reply--1" class="list-group-item d-flex justify-content-between"> <%-- d-flex를 해주면 아래 2개의 div태그가 따로 구분되서 각자의 영역이된다->세로에서 가로축으로 나란히 됨.--%>
	          <div>${reply.content}</div>         <%--댓글 내용--%>       <%-- justify-content-between해주면 아래의 2개의 div가 서로떨어짐.--%>
	          <div class="d-flex">
	              <div class="font-italic">작성자: ${reply.user.username } &nbsp;</div>
	              <button class="badge">삭제</button>  
	          </div>
	        </li>
	   
	   </c:forEach>  
	  
	
	    </ul>
	</div>
</div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
