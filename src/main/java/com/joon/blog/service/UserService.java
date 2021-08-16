package com.joon.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joon.blog.model.User;
import com.joon.blog.repository.UserRepository;

//Service 어노테이션으로 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌-> IOC를 해준다는 뜻(메모리에 대신 띄워준다).
@Service
public class UserService {

	@Autowired   //DI하기
	private UserRepository userRepository;  //여기 객체에 들어온다. 
	
	//서비스함수 만들기  -> 여러 트랜잭션들의 집합체인 하나의 서비스(트랜잭션) 
	@Transactional
	public int 회원가입(User user) {
		try {
			userRepository.save(user);   //하나의 트랜잭션
//			userRepository.save(user);   //하나의 트랜잭션
//			userRepository.save(user);   //하나의 트랜잭션
//			userRepository.save(user);   //하나의 트랜잭션
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("UserService:회원가입()"+e.getMessage());
		}
		return -1;
	}
}
 