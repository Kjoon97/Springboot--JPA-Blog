package com.joon.blog.controller.api;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joon.blog.config.auth.PrincipalDetail;
import com.joon.blog.dto.ResponseDto;
import com.joon.blog.model.RoleType;
import com.joon.blog.model.User;
import com.joon.blog.service.UserService;

@RestController // 데이터만 리턴해줄 것이기 때문에 사용
public class UserApiController {
	
	@Autowired  //의존성 주입 가능함, 왜냐면 스프링이 컴포넌트스캔을 할때 UserService의 service어노테이션을 보는 순간 스프링 Bean에 등록해서 메모리에 띄워준다.(IOC) 
	private UserService userService; 
	
	@Autowired
	private AuthenticationManager authenticationManager;
	

	@PostMapping("/auth/joinProc") // 회원가입 로직이 실행되는 부분으로->인증이 필요없는 것에는 다 auth를 붙임
	public ResponseDto<Integer>save(@RequestBody User user) {  //json데이터를 받으려면 꼭 @RequestBody를 써줘야한다. 안써주면 json데이터 못 받음
		                          // json데이터가 아니라 key=value, x-www-form-urlencoded형태 데이터 받고 싶으면 @RequestBody 안쓰면 된다.
		System.out.println("UserApiController: save 호출됨"); 
		
		
		//user.setRole(RoleType.USER);      //회원가입에서 User디비에 입력으로 username,비밀번호,이메일은 직접입력하고
		                                  //id(기본 키),timestamp는 자동입력되고 남은 role만 강제로 입력해준다. 
	
	    userService.회원가입(user);  //result가 1이면 성공, -1이면 실패 
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);  // 리턴값이 user.js의 ajax의 done()에 들어감 , 
		                                                     //status200은 http에서 통신이 정상적으로 성공했다는 뜻
	}// 자바오브젝트가 리턴되면를 JSON으로 변환해서 리턴한다.(Jackson발동)
	
	@PutMapping("/user")    //json데이터를 받으려면 꼭 @RequestBody를 써줘야한다.
	public ResponseDto<Integer> update(@RequestBody User user){  
		 
		userService.회원수정(user);
		//여기서는 트랜잭션이 종료되기 때문에 DB값은 변경이 됐음
		//하지만 세션 값은 변경되지않은 상태이기 때문에 우리가 직접 세션 값을 변경해 줄 것이다. 
		
		//세션 등록하기
		//1. Authentication객체 생성    //user.getUsername(),user.getPassword()는 user.js파일에서 받아와야
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		//2. Authentication객체 세션에 등록.
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); 
	}
	
	
	
	
	
	
//	@PostMapping("/api/user/login")     // 전통적인 로그인 하기
//	public ResponseDto<Integer>login(@RequestBody User user, HttpSession session){
//		System.out.println("UserApiController: login 호출됨");
//		User principal = userService.로그인(user);   //principal 은 접근주체라는 용어임
//		
//		if(principal!=null) {
//			session.setAttribute("principal", principal); //세션이 생성됨.
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);  //1은 로그인 성공을 의미  , 세션이 만들어지고 1을 응답하게되면
//	}  //loginForm.jsp에서 User.js가 실행될 때->User.js에서 로그인이 완료되면 -> /blog로 이동할거고 ->레이아웃인 header.jsp로 이동

}//실제로 디비에 insert하고 아래에서 return하면된다. 
