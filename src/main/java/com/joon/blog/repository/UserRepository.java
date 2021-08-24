package com.joon.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joon.blog.model.User;

//JpaRepository<User,Integer>-> 해당 JpaRepository는 User테이블을 관리하고, User테이블 주요 키는 integer(숫자)이다. 
public interface UserRepository extends JpaRepository<User,Integer>{
	//JPA 네이밍 쿼리 전략
//	User findByUsernameAndPassword(String username, String password);  // 첫번째 방법(전통적인 로그인방법)
	// 이 함수는 JPA내장 함수가 아니지만 내가 이런 형식으로 함수이름을 쓰면 쿼리 역할을 함수가 한다.
	// 이 함수는 SELECT *FROM user WHERE = '첫번째 파라미터' AND WHERE ='두번째 파라미터' 역할을한다.  
	
	Optional<User>findByUsername(String username);  // == SELECT *FROM user WHERE username = ?; 네이밍 쿼리 전략
	
	
//	@Query(value = "SELECT *FROM user WHERE = '첫번째 파라미터' AND WHERE ='두번째 파라미터'", nativeQuery = true) //두번째 방법
//    User login(String username, String password);   //위의 쿼리문이 호출되고 User 객체를 리턴
 
} //-> 일반적인 crud를 할 경우 JpaRepository에 기능이 다 있기 때문에 extends했으면 함수 안에 더 무언가를 추가로 정의할 필요없음
//자동으로 bean등록이된다. ->@Repository어노테이션 생략 가능 
//여기서 bean으로 등록된다는 것은 스프링 ioc에서 객체를 가지고있는 것 그래서 필요한 곳에서 injection으로 DI를 할 수 있는것 
// 스프링이 자동으로 UserRepository를 메모리에 띄워준다

//JpaRepository는 많은 기능을 가지고있음 
//예를 들어 findAll()->해당 테이블이 가지고 있는 행들을 모두 리턴, 정렬해서 리턴, 
//save()->데이터를 insert,업데이트 가능, findbyId()->id로 행을 찾기, deletebyId()-> id로 행삭제.. 등 많음