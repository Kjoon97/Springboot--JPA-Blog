package com.joon.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joon.blog.model.RoleType;
import com.joon.blog.model.User;
import com.joon.blog.repository.UserRepository;

//Service 어노테이션으로 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌-> IOC를 해준다는 뜻(메모리에 대신 띄워준다).
@Service
public class UserService {

	@Autowired   //DI하기
	private UserRepository userRepository;  //여기 객체에 들어온다. 
	
	@Autowired
	private BCryptPasswordEncoder encoder;  //패스워드를 암호화해서 넣기위해 필요
	
	//서비스함수 만들기  -> 여러 트랜잭션들의 집합체인 하나의 서비스(트랜잭션) 
	@Transactional
	public void 회원가입(User user) {
		
	        String rawPassword = user.getPassword();  //1234 원문
	        String encPassword = encoder.encode(rawPassword); // 해쉬화
	        user.setPassword(encPassword);   // 해쉬화 된 것을 유저의 패스워드로 설정
	        user.setRole(RoleType.USER);
			userRepository.save(user);   //하나의 트랜잭션
//			userRepository.save(user);   //하나의 트랜잭션

	}
	@Transactional
	public void 회원수정(User user) {
		//수정시에는 영속성 컨텍스트에 User 오브젝트를 영속화시키고, 영속화된 User오브젝트를 수정하는 것이 좋다. 
		// select를 할건데 select를 해서 User오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서이다. 
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려주기 때문이다. 
		User persistance= userRepository.findById(user.getId()).orElseThrow(()->{  //영속화시키기 
			return new IllegalArgumentException("회원찾기 실패");
		});
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);                         //영속화된 것을 변경. 
		persistance.setEmail(user.getEmail());
		//회원 수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit이 자동으로 된다. 
		//-> 영속화된 persistance객체의 변화가 감지되면 더티 채킹이 되어 변화된 것들을 DB에 자동으로 update문 날림. 
		
	}
	
//	전통적인 로그인방법
//	@Transactional(readOnly = true)  //Select할 때 트랜잭션이 시작하고, 서비스 종료시에 트랜잭션을 종료해서 (정합성 유지)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
 