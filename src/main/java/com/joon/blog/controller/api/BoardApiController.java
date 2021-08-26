package com.joon.blog.controller.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joon.blog.config.auth.PrincipalDetail;
import com.joon.blog.dto.ResponseDto;
import com.joon.blog.model.Board;
import com.joon.blog.service.BoardService;

@RestController // 데이터만 리턴해줄 것이기 때문에 사용
public class BoardApiController {

	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer>save(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principal) {
		
		boardService.글쓰기(board,principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);  // 리턴값이 user.js의 ajax의 done()에 들어감 ,  //정상적으로 잘 됐다고 응답
		                                                     //status200은 http에서 통신이 정상적으로 성공했다는 뜻, 1도 정상이라는 뜻
	}// 자바오브젝트가 리턴되면를 JSON으로 변환해서 리턴한다.(Jackson발동)
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.글삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/api/board/{id}")   // @DeleteMapping이랑 주소가 같은데,@PutMapping으로 매서드가 다르기 때문에 괜찮음 
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
		boardService.글수정하기(id,board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}