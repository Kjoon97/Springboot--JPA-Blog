package com.joon.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//JPA는 ORM인데 ORM은 자바뿐만아니라 모든 언어들의 object를 테이블로 매핑해주는 기술이다. 
// 나는 그냥 object를 만들기만 하면 JPA는 그걸 테이블로 생성해준다. 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // user 클래스가 mySQL에 테이블이 생성된다. 각각의 변수 이름이 그대로 저장됨
//@DynamicInsert // insert할 때 JPA에서 null인 필드 제외시켜줌 -> role에 null값이 아닌 user가 넣어짐
public class User {

	@Id// primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 db의 넘버링 전략을 따름(oracle이면 시퀀스, mysql이면 auto_increment)
	private int id;  // oracle(시퀀스), mysql(auto_increment로 넘버링하는 전략)- id부분을 비워놔도 자동으로 값이 들어감(auto_increment)
	
	@Column(nullable=false,length=20,unique= true) //아이디가 null값이 되지않고, 최대한 20자가 넘지않게 설정
	private String username; //아이디
	  
	@Column(nullable=false,length=100) //  비밀번호가 null값이 되지않고, 최대한 100자가 넘지않게 설정
	private String password;             // 비밀번호를 해쉬형태(비번 암호화)로 변환해서 db에 저장할 것이기에 넉넉히 100자로 제한
	
	@Column(nullable=false,length=50)  //  이메일이 null값이 되지않고, 최대한 50자가 넘지않게 설정
	private String email;
	
	//@ColumnDefault("'user'") // 회원가입시 디폴트 값을 'user'로 설정, @ColumnDefault는 따옴표안에 ''도 써야함
	@Enumerated(EnumType.STRING) //DB는 RoleType이라는게 없기 때문에 해당 Enum이 STRING이라고 알려줘야함. 
	private RoleType role; //admin, user, manager - 각 로그인되는 역할에 따라 웹페이지에대한 권한이 달라짐
	                     //ex) admin -> 전체 글 삭제 가능,,
	                     // string이 아닌 RoleType이라고하면 ADMIN, USER으로 타입이 강제됨 USERS라고 실수로 적는거 방지
	//String으로 설정하면 managerrrr같은 오타를 입력해도 입력되는데, Enum을 사용하면 admin, user, manager 딱 3개 중 하나만
	// 들어갈 수 있도록 도메인(범위)을 설정할 수 있다.ex) 성별 도메인 - 남,녀 / 학년 도메인 -1학년~3학년..등
	
	@CreationTimestamp // 현재 시간이 자동으로 입력 됨 , 현재시간을 createDate에 넣어주고 insert함 , save()함수 작동 시 작동 함
	private Timestamp createDate; //자바 sql의 Timestamp(회원정보 수정은 updateDate)
}
