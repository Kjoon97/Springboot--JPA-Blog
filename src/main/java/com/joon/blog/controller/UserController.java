package com.joon.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


// 인증이 안된 사용자들이 출입하 수 있는 경로를 /auth/..이하경로로 허용.
// 그냥 주소가 /이면 index.jsp 허용
// static 이하에 있는 /js/**, /css/**, /image/** 허용

@Controller
public class UserController {
	
	@GetMapping("/auth/joinForm")  //회원가입하러 들어가는데 인증이 있을 필요는 없음. ->인증이 필요없는 것에는 다 auth를 붙임
	public String joinForm() {
		
		
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")// ->인증이 필요없는 것에는 다 auth를 붙임
	public String loginForm() {
		
		
		return "user/loginForm";
	}
	
	@GetMapping("/user/updateForm")// ->인증이 필요없는 것에는 다 auth를 붙임
	public String updateForm() {
		
		
		return "user/updateForm";
	}
}





