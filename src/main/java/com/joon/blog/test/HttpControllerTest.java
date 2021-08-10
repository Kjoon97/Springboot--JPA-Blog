package com.joon.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// @Controller : 사용자가 요청하면 -> 응답(HTML 파일)


@RestController    // @RestController : 사용자가 요청하면-> 응답(데이터) 
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest:";
	//builder 장점 :  객체에 값을 넣을때 순서를 지키지 않아도 된다.객체 값 순서를 헷갈려서 객체의 값을 잘못 넣는 실수하는 것을 방지한다.
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = new Member.MemberBuilder().username("joon").password("1234").email("jh83370@naver.com").build();
		System.out.println(TAG+"getter:"+ m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG+"setter:"+ m.getUsername());
		return "lombok test 완료"; 
		}
	
	// 인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다. (put,post, delete요청은 할 수 없음)
	// http://localhost:8000/blog/http/get (select 해달라)
	@GetMapping("/http/get")
	public String getTest(Member m) { // 쿼리 스트링방법( id=1&username=joon&password=1234&email=ssar@nate.com)->스프링이 자동으로 변수 값 넣어줌
		                                                        // ->MessageConverter(스프링부트)가 자동으로 파싱해서 오브젝트에 넣어줌 
		return "get 요청: " + m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	// http://localhost:8000/blog/http/post (insert 해달라)
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { //MessageConverter(스프링부트)가 자동으로 파싱해서 오브젝트에 넣어줌 
		                                                                                     // @RequestBody를 이용해야.
		return "post 요청"+ m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	// http://localhost:8000/blog/http/put (update해달라)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청"+ m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
		}
	
	// http://localhost:8000/blog/http/delete (delete해달라)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
