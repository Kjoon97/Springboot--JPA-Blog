package com.joon.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.joon.blog.config.auth.PrincipalDetail;

@Controller  //파일 리턴할 때 사용하는 어노테이션
public class BoardController {

	
	@GetMapping({"","/"})  //아무것도 안 붙였을 때랑 /를 붙였을 때 이동
	public String index(@AuthenticationPrincipal PrincipalDetail principal) {  //컨트롤러에서 세션을 접근하기위해서
		                                               //파라미터에 @AuthenticationPrincipal PrincipalDetail principal추가
		        
		
		System.out.println("로그인 사용자 아이디:"+principal.getUsername());   // 테스트 로그
		//    application.yml의 설정 덕분에 /WEB-INF/views + /index(리턴문)  + .jsp로 됨
		///WEB-INF/views/index.jsp로 입력 된다.
		// 결국 index.jsp를 출력함.
		// http://localhost:8000/blog/ 웹브라우저 주소에 입력 후 확인해보기. 
		return "index";
	}
}
