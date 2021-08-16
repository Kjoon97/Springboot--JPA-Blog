package com.joon.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joon.blog.dto.ResponseDto;
import com.joon.blog.model.RoleType;
import com.joon.blog.model.User;
import com.joon.blog.service.UserService;

@RestController // 데이터만 리턴해줄 것이기 때문에 사용
public class UserApiController {
	
	@Autowired  //의존성 주입 가능함, 왜냐면 스프링이 컴포넌트스캔을 할때 UserService의 service어노테이션을 보는 순간 스프링 Bean에 등록해서 메모리에 띄워준다.(IOC) 
	private UserService userService; 
	
	@PostMapping("/api/user")
	public ResponseDto<Integer>save(@RequestBody User user) {
		System.out.println("UserApiController: save 호출됨"); 
		
		user.setRole(RoleType.USER);      //회원가입에서 User디비에 입력으로 username,비밀번호,이메일은 직접입력하고
		                                  //id(기본 키),timestamp는 자동입력되고 남은 role만 강제로 입력해준다. 
	
		int result = userService.회원가입(user);  //result가 1이면 성공, -1이면 실패 
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);  // 리턴값이 user.js의 ajax의 done()에 들어감 , 
		                                                     //status200은 http에서 통신이 정상적으로 성공했다는 뜻
	}// 자바오브젝트가 리턴되면를 JSON으로 변환해서 리턴한다.(Jackson발동)

}//실제로 디비에 insert하고 아래에서 return하면된다. 
