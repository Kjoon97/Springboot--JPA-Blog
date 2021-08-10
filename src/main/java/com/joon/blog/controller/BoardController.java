package com.joon.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  //파일 리턴할 때 사용하는 어노테이션
public class BoardController {
	
	@GetMapping({"","/"})  //아무것도 안 붙였을 때랑 /를 붙였을 때 이동
	public String index() {
		//    application.yml의 설정 덕분에 /WEB-INF/views + /index(리턴문)  + .jsp로 됨
		///WEB-INF/views/index.jsp로 입력 된다.
		// 결국 index.jsp를 출력함.
		// http://localhost:8000/blog/ 웹브라우저 주소에 입력 후 확인해보기. 
		return "index";
	}
}
