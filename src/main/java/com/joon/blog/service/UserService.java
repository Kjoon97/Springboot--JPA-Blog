package com.joon.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joon.blog.model.User;
import com.joon.blog.repository.UserRepository;

//Service 어노테이션으로 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌-> IOC를 해준다는 뜻(메모리에 대신 띄워준다).
@Service
public class UserService {

	@Autowired   //DI하기
	private UserRepository userRepository;  //여기 객체에 들어온다. 
	
	//서비스함수 만들기  -> 여러 트랜잭션들의 집합체인 하나의 서비스(트랜잭션) 
	@Transactional
	public void 회원가입(User user) {
			userRepository.save(user);   //하나의 트랜잭션
//			userRepository.save(user);   //하나의 트랜잭션
//			userRepository.save(user);   //하나의 트랜잭션
//			userRepository.save(user);   //하나의 트랜잭션
	}
	
	@Transactional(readOnly = true)  //Select할 때 트랜잭션이 시작하고, 서비스 종료시에 트랜잭션을 종료해서 (정합성 유지)
	public User 로그인(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
}
 