<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "../layout/header.jsp"%>

	<div class="container">
	   <form>   
	    <input type ="hidden" id ="id" value="${board.id}"/>
		<div class="form-group">
			<label for="title">Title</label> 
			<input value="${board.title}" type="text" class="form-control" placeholder="Enter title" id="title">
		</div>
		<div class="form-group">
			<label for="content">Content</label>
			<textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>   <%--클래스 이름 이와 같이 변경 --%>
		</div>
	</form>
	  <button id="btn-update" class="btn btn-primary">글 수정 완료</button>
	</div>
	
	<script>
      $('.summernote').summernote({      <%--.summernote를 이용해서 form-control summernote클래스 사용--%>
        tabsize: 2,
        height: 300    <%--글쓰기 칸 높이--%>
      });
    </script>

<script src="/js/board.js"></script>	
<%@ include file = "../layout/footer.jsp"%>
