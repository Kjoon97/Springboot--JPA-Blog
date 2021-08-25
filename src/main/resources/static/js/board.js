//제이쿼리
let index ={
	init:function(){
		$("#btn-save").on("click",()=>{  // function(){}를 쓰지 않고  ()=>{}쓰는 이유는 this를 바인딩하기 위함이다. 
			this.save();                  //fuction(){}를 쓰면 this는 해당 this를 가르키지 않고 window 객체를 가리킴. 
		}); //리스너 생성, 누군가가 btn-save를 찾아서 클릭이 되면 save()함수 호출됨
		//$("#btn-login").on("click",()=>{  // 전통적인 로그인 방식
		//	this.login();                  
		//});
		
	},
	
	save:function(){
		
    	let Data ={                    
			title:$("#title").val(),    
			content:$("#content").val()
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
	
}
index.init();   //index함수 호출