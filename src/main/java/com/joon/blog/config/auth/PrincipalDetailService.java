package com.joon.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.joon.blog.model.User;
import com.joon.blog.repository.UserRepository;

@Service  //Bean에 등록
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	//스프링이 로그인 요청을 가로챌 때 , username, password변수 2개를 가로채는데
	//password 부분처리는 알아서 함(패스워드 틀린것도 알아서 처리함)
	//username이 DB에 있는지만 확인해주면 됨 -> loadUserByUsername()함수에서 함. 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.:"+username);
				});
		return new PrincipalDetail(principal); //이 때 시큐리티의 세션에 유저 정보가 저장이 된다. 이때 타입이 UserDetails타입
	}  //이렇게 유저정보를 안넘기면 기본 아이디 user, 콘솔에서 부여받는 패스워드 키로만 세팅이 되지, 우리 유저값은 안들어감.
       //PrincipalDetailService클래스의 존재 이유다. - 유저 정보를 넘기기위한 것. 
}
