//제이쿼리
let index ={
	init:function(){
		$("#btn-save").on("click",()=>{  // function(){}를 쓰지 않고  ()=>{}쓰는 이유는 this를 바인딩하기 위함이다. 
			this.save();                  //fuction(){}를 쓰면 this는 해당 this를 가르키지 않고 window 객체를 가리킴. 
		}); //리스너 생성, 누군가가 btn-save를 찾아서 클릭이 되면 save()함수 호출됨
	},
	
	save:function(){
		//alert("user의 save함수 호출됨");
		let Data ={                    //data오브젝트에 넣는다. 아이디 값으로 각각의 값을 찾고 변수에 넣는다. 
			username:$("#username").val(),    //joinForm에 정의된 아이디들.
			password:$("#password").val(),
			email:$("#email").val()
		};
		
	//	console.log(data);  //data값 잘 들어가는지 콘솔로 확인
	// ajax 호출 시 default가 비동기 호출 - 회원가입 요청이 100초가 걸리면 그동안에 다른 걸 cpu가 하고, 
	// 100초가 끝나서 요청이 끝나면 cpu가 하던 걸 멈추고 돌아와 done()을 실행
	//ajax가 통신을 성고하고 서버가 json을 리턴해주면 자동으로 자바스크립트 오브젝트로 변환해준다. 
	$.ajax({	//회원 가입 수행 요청
	   type: "POST",   //insert할 것이기 때문에 POST타입.
	   url:"/blog/api/user",    //어느 주소로 호출할지 \
	   data:JSON.stringify(Data),   //data:Data라고 하면 자바는 js 객체를 이해 못하므로 JSON으로 바꿔줘야한다. 
	   contentType:"application/json; charset=utf-8", //data는 json 데이터를 보내는 것이고, 문자는 utf-8이다. 알려주기위함
	   dataType:"json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열인데(생긴게 json이라면) javascript객체로 변경( 이 코드는 없어도 괜찮)
	}).done(function(resp){ // 응답의 결과가 성공했을 때 그 결과 오브젝트(자바스크립트)가 resp에 전달됨. UserApiController의 리턴값 1이 전달 됨. 
		alert("회원 가입이 완료되었습니다.");
		console.log(resp);
		//alert(resp);
		location.href = "/blog"
	}).fail(function(error){// 실패하면 
		alert(JSON.stringify(error));
	}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청한다.  
	
	}
}

index.init();   //index함수 호출