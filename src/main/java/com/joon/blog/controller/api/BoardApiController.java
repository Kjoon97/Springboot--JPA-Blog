package com.joon.blog.controller.api;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joon.blog.config.auth.PrincipalDetail;
import com.joon.blog.dto.ResponseDto;
import com.joon.blog.model.Board;
import com.joon.blog.model.RoleType;
import com.joon.blog.model.User;
import com.joon.blog.service.BoardService;
import com.joon.blog.service.UserService;

@RestController // 데이터만 리턴해줄 것이기 때문에 사용
public class BoardApiController {

	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer>save(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principal) {
		
		boardService.글쓰기(board,principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);  // 리턴값이 user.js의 ajax의 done()에 들어감 ,  //정상적으로 잘 됐다고 응답
		                                                     //status200은 http에서 통신이 정상적으로 성공했다는 뜻
	}// 자바오브젝트가 리턴되면를 JSON으로 변환해서 리턴한다.(Jackson발동)
	

}