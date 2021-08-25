package com.joon.blog.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joon.blog.model.Board;
import com.joon.blog.model.User;
import com.joon.blog.repository.BoardRepository;
import com.joon.blog.repository.UserRepository;

//Service 어노테이션으로 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌-> IOC를 해준다는 뜻(메모리에 대신 띄워준다).
@Service
public class BoardService {

	@Autowired   //BoardRepository DI하기
	private BoardRepository boardRepository;  //여기 객체에 들어온다. 
	
	//서비스함수 만들기  -> 여러 트랜잭션들의 집합체인 하나의 서비스(트랜잭션) 
	@Transactional
	public void 글쓰기(Board board,User user) {  //title,content  
		board.setCount(0); //조회수는 0으로 강제로 넣음
		board.setUser(user);
		boardRepository.save(board);
	} // ( model에 Board에 무슨 값을 가져와야하는지 살펴봐야함)- id는 자동으로 생성되고, title, content는 받을거고, 
      	// 조회수는 강제로 값 넣어줄거고, user정보 가져와야함. reply는 mappedby로 되어있어 데이터베이스에 들어있는 값이 아니므로 신경x
	
	
	public List<Board> 글목록(){
		return boardRepository.findAll();
	}
}
 