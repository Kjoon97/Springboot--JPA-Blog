package com.joon.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joon.blog.model.RoleType;
import com.joon.blog.model.User;
import com.joon.blog.repository.UserRepository;

@RestController   // 어떤 페이지로 이동이아니라, 데이터만 리턴해주는 (회원가입 잘됐다 안됐다 응답만)(html파일이 아니라 data를 리턴해주는 컨트롤러)
public class DummyControllerTest {
 // @Autowired를 안 붙이면 스프링이@RestController를 읽어서 DummyControllerTest를 메모리에 띄우고 userRepository는 걍 null이다. 
//@Autowired를 붙이면 DummyControllerTest가 메모리에 뜰 때 @Autowired도 같이 뜬다
//왜냐하면 @Autowired는 여기서 UserRepository타입으로 스프링이 관리하는 객체가 있다면 userRepository에 넣어주는 역할한다.
//다른 패키지에 UserRepository인터페이스가 있기때문에 메모리에 떠있고 그대로 @Autowired로 사용하면된다.->의존성 주입이다.(DI)
	
	@Autowired    //의존성 주입
	private UserRepository userRepository;
	
	//삭제하기 
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			return "삭제 실패하였습니다. 해당 id는 데이터베이스에 없습니다.";
		}
		return "삭제되었습니다. id"+id;
	}
	
	
	//email,password 수정(업데이트)
	@Transactional   //이 어노테이션을 쓰면 save함수 안써도 업데이트가 됨 -> 더티체킹
	@PutMapping("/dummy/user/{id}")  // 아래에 똑같은 주소로 매핑되어있는데 그건 getMapping이고 이건 putMapping이어서 구분이 된다.
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {  //json을 받아오려면 @RequestBody 필요함
		System.out.println("id:"+id);                                              //내가 보낸 json를 스프링이 자바오브젝트로 변환해서 받아줌
		System.out.println("password:"+requestUser.getPassword());               //->MessageConverter의 Jackson라이브러리가 변환해서 받아줌
		System.out.println("email:"+requestUser.getEmail());
		
	
		User user = userRepository.findById(id).orElseThrow(()->{  //영속화 과정 //자바는 함수를 파라미터로 못 쓰기 때문에 람다식 이용
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());   //변경
		user.setEmail(requestUser.getEmail());         //변경
		 
		//userRepository.save(user);       //save함수는 id를 전달해주지 않으면 insert를 해주고, id를 전달하고 해당 id에 대한 데이터가 있으면 업데이트,
		return user;                      //해당 id에대한 데이터가 없으면 insert를 해준다. 
	}
	
	
	
	
	
	
	//전체 데이터를 한꺼번에 리턴
	//http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();    //전체를 리턴 
	}
	
	//한 페이지 당 2건의 데이터를 리턴 받아 볼 예정 (id로 분류하고 그 후에 가입 순(최신 순)으로 분류)
	//첫 번째 페이지 http://localhost:8000/blog/dummy/user/page?page=0
	//두 번째 페이지 http://localhost:8000/blog/dummy/user/page?page=1
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2,sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	
	//User클래스의 변수들이 필요한데, id는 @GeneratedValue로 자동으로 들어가고, user인것도 ColumnDefault로 디폴트로 들어가고
	// CreationTimestamp도 자동으로 들어간다.
	//따라서 username,password,email 정보만 가져오면 된다. -> 함수 파라미터 이용
	//http://localhost:8000/blog/dummy/join으로 요청을 할건데 
	//http의 body에 username과 password, email 데이터를 가지고 요청하게 되면 
	//username, password, email값이 join파라미터에 각각 들어간다.
	
	//{id}주소로 파라미터를 전달 받을 수 있다.
	//http://localhost:8000/blog/dummy/user/3이라고 하면 3이 id에 쏙 들어간다. 
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) { // 상세보기/ 3이 함수에 들어가위한 파라미터 {id}이기 때문에 변수도 꼭 id라고 이름지어야한다. 그래야 매핑됨
		//user/4를 찾으면 내가 데이터베이스에서 못 찾으면 user가 null이 될것 아니냐, 그럼 리턴할 때 null이 린턴한ㄷ. 그럼 프로그램에 문제가 생기지 않겠니?
		//그래서 나는 Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해 
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() { //예외처리: 에러메세지 뜨게함
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id:"+id);
			}
		});
//				.orElseGet(new Supplier<User>() {    //아이디가 없는 아이디이면 각 컬럼 값을 null로 출력함
//					@Override
//					public User get() {
//						// TODO Auto-generated method stub
//						return new User();
//					}
//				});
//				.get(); //에러메세지 안 뜸 
		//웹브라우저에서 요청한것이기 때문에 -> return값을 웹브라우저가 받음, @RestController는 html파일이아니라 데이터를 린턴하기때문에 웹브라우저가 이해 못함
		return user;  //user객체는 자바 오브젝트 -> 웹브라우저는 이해 못함 -> 자바오브젝트를 웹브라우저가 이해할 수 있는 데이터(json)으로 변환해야한다. 
		// 스프링 부트 => MessageConverter라는 애가 응답시에 자동으로 작동한다. 
		// 자바 오브젝트를 리턴하게되면 MessageConverter가 Jackson 라이브러리를 호출해서 user오브젝트를 jason으로 변환해서 브라우저에게 던져준다. 
		//그래서 웹브라우저에서 뜨는 건 json형식이다.
	}
	
	
	@PostMapping("/dummy/join")//회원가입하기 위해 필요 (주소는 /dummy/join)
	public String join(User user) { 
		System.out.println("유저아이디:"+user.getUsername());       
		System.out.println("패스워드:"+user.getPassword());     
		System.out.println("이메일:"+user.getEmail());       
		
		user.setRole(RoleType.USER);
		userRepository.save(user); //->회원가입 가능하게됨
		return "회원 가입이 완료되었습니다.";
	}
}
 
//join이라는 함수에 파라미터 3개는 key=value형태를 받아줌(약속된 규칙)
//x-www-form-urlencoded로 전송되는 key=value형태의 데이터를 스프링이 함수 파라미터에 파싱해서 넣어줌. 
//더 강력한 것은 String username, String password, String email가 아닌 오브젝트를 파라미터에 써도됨->User user로 가능
//postman에서 만약 password를 password2로 잘 못 입력하면 일치하지 않아 파싱이 일어나지 않음 
//데이터베이스에 insert하기 위해서는 새로운 패키지 필요함 ->com.joon.blog.repository 생성