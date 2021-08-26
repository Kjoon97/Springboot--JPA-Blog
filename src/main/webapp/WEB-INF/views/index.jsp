<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "layout/header.jsp" %>

<div class="container">
     <%-- BoardController에서 가져온 boads 컬렉션을 items가 받을 수 있음--%><%-- 리퀘스트 정보가 넘어올 때 JSTL에서 boards를 받아 올 수 있음 --%>
	<c:forEach var="board" items="${boards.content}">       <%-- boards컬렉션에서 한건 씩 board 변수에 집어넣음 --%>
		<div class="card m-2">     <%-- m-2로하면 마진이 2만큼 생겨서 화면 간의 조금 여유를 줄 수 있음--%>
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4>   <%-- Board클래스의 객체인 board의 title이 출력됨.Board클래스 위에 @Data해줌--%>
				<a href="/board/${board.id}" class="btn btn-primary">상세 보기</a>
			</div>
		</div>
	</c:forEach>

<ul class="pagination justify-content-center">    <%--부트스트랩 페이지 넘기는 버튼 복붙 ,justify-content-center는 가운데로 오게하는 것--%>
 <c:choose>
   <c:when test ="${boards.first}">
     <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>  <%--boards의 number는 해당 페이지를 뜻함--%>
   </c:when>
   <c:otherwise>
     <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
    </c:otherwise>
 </c:choose>
 <c:choose>
   <c:when test ="${boards.last}">
     <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
   </c:when>
   <c:otherwise>
      <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
   </c:otherwise>
 </c:choose>
</ul>


</div>
<%@ include file = "layout/footer.jsp" %>
