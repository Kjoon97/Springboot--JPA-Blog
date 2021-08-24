package com.joon.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.joon.blog.model.User;


// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를 스프링 시큐리티 고유한 세션 저장소에 저장한다. 
// UserDetails 타입의 PrincipalDetail이 저장된다. 
public class PrincipalDetail implements UserDetails{  // UserDetails타입의 user오브젝트를 만들어야한다. - 시큐리티가 로그인 요청하고, 
	                                                   // 세션을 등록하는데 그때 타입이 UserDetails타입이어서 User오브젝트를 등록할 수는 없기때문. 
	private User user; // 콤포지션(객체를 품고있다는 뜻)

	public PrincipalDetail(User user) {  // PrincipalDetailService클래스에서 PrincipalDetail()를 리턴하는데 초기화안하면 null값이므로 생성자 만듦.
		this.user = user;
	}
	//UserDetails가 들고 있는 추상 메서드를 전부 오바라이딩해야.

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	//계정이 만료되지 않았는지 리턴한다. (리턴 값 - true: 만료 안됨, false: 해당 유저는 만료)
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정이 잠겨있는지 유무 (리턴 값 - true: 안 잠긴 상태)
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	//비밀번호 만료되지 않았는지 리턴한다. (리턴 값 - true: 만료 안됨, false: 해당 유저는 만료)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
    //계정이 활성화(사용가능) 유무 (리턴값 - true : 활성화)
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	//계정이 갖고있는 권한을 리턴. 러턴 타입이 GrantedAuthority를 상속한 collection타입이어야함. 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();  //애초에 ArrayList는 Collection 타입이다.
		collectors.add(()->{return "ROLE_"+user.getRole();}); //스프링에서 Role를 받을 때 ROLE_를 붙이는게 규칙임. 꼭 넣어야함.
		                                                       // 안붙이면 인식 못 함. ex) ROLE_USER
		return collectors;
	}
}
