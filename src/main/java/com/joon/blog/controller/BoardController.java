package com.joon.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.joon.blog.config.auth.PrincipalDetail;
import com.joon.blog.service.BoardService;

@Controller  //파일 리턴할 때 사용하는 어노테이션
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	@GetMapping({"","/"})  //아무것도 안 붙였을 때랑 /를 붙였을 때 이동
	public String index(Model model) {  // 데이터를 가져 올 때는 Model이 필요
		
		model.addAttribute("boards",boardService.글목록());   //model에 글목록을 다 가져옴. boardRepository의 findAll()함수로 가능. 
		
		
		
		//컨트롤러에서 세션을 접근하기위해서 파라미터에 @AuthenticationPrincipal PrincipalDetail principal추가
		//System.out.println("로그인 사용자 아이디:"+principal.getUsername());   // 테스트 로그
		
		//    application.yml의 설정 덕분에 /WEB-INF/views + /index(리턴문)  + .jsp로 됨
		///WEB-INF/views/index.jsp로 입력 된다.
		// 결국 index.jsp를 출력함.
		// http://localhost:8000/blog/ 웹브라우저 주소에 입력 후 확인해보기. 
		return "index";   // index라는 페이지에 boards가 넘어간다. 
	}      //@Controller는 리턴할 때 viewResolver가 작동하는데 작동하면 해당 index페이지로 model의 정보를 들고 이동한다.("board"라는 컬렉션을 들고 이동)
	       // viewResolver가 리턴할 때, applicaion.yml에서 설정한 prefix, suffix가 붙어서 리턴된다. return index라고 되어있지만
	       // 사실상 /WEB-INF/views/index.jsp
	
	//User권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {    //이 주소를 호출 하면 글쓰기 창으로 이동. 
		return "board/saveForm";
	}
}
