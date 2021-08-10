package com.joon.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//어떤 클래스를 만들 때는 항상 변수는 private으로 만들기 , 변수를 수정하기 위한 getter,setter함수 생성
// 함수를 통해서 변수에 접근하는 방식으로  해야 함  

//@Getter // Getter 생성
//@Setter // Setter 생성
//@Data // Getter 와 Setter 모두 생성하고자 할 때
//@AllArgsConstructor  // 생성자 만들고자 할 때 
//@RequiredArgsConstructor  // final 붙은 애들에게만 생성자  만들어 줌 
//@NoArgsConstructor // 빈생성자 생성

@Data
@NoArgsConstructor
public class Member {      
	private  int id;                        // 데이터베이스에서 들고온 값을 변경하지 못 하도록 하기 위해 final 사용
	private String username;
	private String password;
	private String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
}
