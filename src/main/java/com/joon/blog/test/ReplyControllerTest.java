package com.joon.blog.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.joon.blog.model.Board;
import com.joon.blog.model.Reply;
import com.joon.blog.repository.BoardRepository;
import com.joon.blog.repository.ReplyRepository;

@RestController
public class ReplyControllerTest {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	
	@GetMapping("/test/board/{id}")
	public Board getBoard(@PathVariable int id) {
		return boardRepository.findById(id).get(); 
	}//Board 오브젝트를 리턴하는데, Jackson라이브러리 발동(->오브젝트를 json으로 리턴해줌) 이때 모델이 들고있는 getter 호출해서 뽑아서 json으로 바꿔줌
	// board->reply->board->reply 무한반복...
	
	@GetMapping("/test/reply")
	public List<Reply> getReply() {
		return replyRepository.findAll();
	}
	
}  
