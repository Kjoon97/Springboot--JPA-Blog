//제이쿼리
let index ={
	init:function(){
		$("#btn-save").on("click",()=>{  // function(){}를 쓰지 않고  ()=>{}쓰는 이유는 this를 바인딩하기 위함이다. 
			this.save();                  //fuction(){}를 쓰면 this는 해당 this를 가르키지 않고 window 객체를 가리킴. 
		}); //리스너 생성, 누군가가 btn-save를 찾아서 클릭이 되면 save()함수 호출됨
		//$("#btn-login").on("click",()=>{  // 전통적인 로그인 방식
		//	this.login();                  
		//});
		$("#btn-delete").on("click",()=>{    //삭제 버튼
			this.deleteById();                  
		});
		
		$("#btn-update").on("click",()=>{    //수정 버튼
			this.update();                  
		});
		$("#btn-reply-save").on("click",()=>{    //댓글 등록
			this.replySave();                  
		});
		
	},
	
	save:function(){
		
    	let Data ={                    
			title:$("#title").val(),       //saveForm.jsp의 title값 가져오기
			content:$("#content").val()    //saveForm.jsp의 content값 가져오기
		};
		
	$.ajax({	
	   type: "POST",   //글쓰기(insert)할 것이기 때문에 POST타입.
	   url:"/api/board",    //어느 주소로 호출할지 
	   data:JSON.stringify(Data),   
	   contentType:"application/json; charset=utf-8", 
	   dataType:"json"
	}).done(function(resp){ 
		alert("글쓰기가 완료되었습니다.");
		console.log(resp);
		//alert(resp);
		location.href = "/";
	}).fail(function(error){// 실패하면 
		alert(JSON.stringify(error));
	}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청한다.  
	
	},
	
	deleteById: function() {
		let id = $("#id").text();   //detail.jsp의 id값 가져오기   (detail.jsp- <span id ="id"><i>${board.id} </i></span>)
		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,    //어느 주소로 호출할지 
			dataType: "json"
		}).done(function(resp) {
			alert("삭제가 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {// 실패하면 
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청한다.  

	},
	
	update:function(){
		
		let id = $("#id").val();    //updateForm.jsp의 id값 가져오기
		
    	let Data ={                      
			title:$("#title").val(),      //updateForm.jsp의 title값 가져오기
			content:$("#content").val()   //updateForm.jsp의 content값 가져오기
		};
		
	$.ajax({	
	   type: "PUT",   //수정 - PUT타입
	   url:"/api/board/"+id,    //어느 주소로 호출할지 
	   data:JSON.stringify(Data),   
	   contentType:"application/json; charset=utf-8", 
	   dataType:"json"
	}).done(function(resp){ 
		alert("글 수정이 완료되었습니다.");
		console.log(resp);
		//alert(resp);
		location.href = "/";
	}).fail(function(error){// 실패하면 
		alert(JSON.stringify(error));
	}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청한다.  
	
	},
	
	
	replySave:function(){
    	let Data ={    
	        userId: $("#userId").val(),   
	        boardId: $("#boardId").val(),
			content:$("#reply-content").val()    //saveForm.jsp의 content값 가져오기
		};
		console.log(Data);
		
	$.ajax({	
	   type: "POST",   //글쓰기(insert)할 것이기 때문에 POST타입.
	   url:`/api/board/${Data.boardId}/reply`,    //어느 주소로 호출할지 -> BoardApiController에 주소. 
	   data:JSON.stringify(Data),   
	   contentType:"application/json; charset=utf-8", 
	   dataType:"json"
	}).done(function(resp){ 
		alert("댓글 작성이 완료되었습니다.");
		console.log(resp);
		//alert(resp);
		location.href =`/board/${Data.boardId}`;
	}).fail(function(error){// 실패하면 
		alert(JSON.stringify(error));
	}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청한다.  
	
	},
	
	replyDelete:function(boardId,replyId){    // detail.jsp에서 리스너가 아니라 onClick함수이기때문에 위에 init function에 안 넣어도 된다. 
	$.ajax({	
	   type: "DELETE",  
	   url:`/api/board/${boardId}/reply/${replyId}`,    //어느 주소로 호출할지 ,,어떤 데이터를 들고갈게 아니기 때문에 Data.boarId라고 안함. 
	/*  data:JSON.stringify(Data),                      
	   contentType:"application/json; charset=utf-8", */   //마찬가지로 데이터를 들고갈게 아니기때문에 필요 없음 -> 주석처리
	   dataType:"json"
	}).done(function(resp){ 
		alert("댓글 삭제 성공");
		location.href =`/board/${boardId}`;   //삭제되면 해당 로직으로 다시 돌아오기.
	}).fail(function(error){// 실패하면 
		alert(JSON.stringify(error));
	}); 
	
	},

}
index.init();   //index함수 호출