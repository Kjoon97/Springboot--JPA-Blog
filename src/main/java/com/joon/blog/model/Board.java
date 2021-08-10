package com.joon.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private int id;
	
	@Column(nullable = false, length=100)
	private String title;
	
	@Lob //대용량 데이터 쓸때 사용하는 어노테이션
	private String content; // 글내용 (섬머노트 라이브러리 사용할건데 글이<html>태그가 섞여서 디자인 됨-> 용량이 커짐)
	
	@ColumnDefault("0") // 0 숫자이기 때문에 "" 안에 '' 안 써도됨
	private int count; //조회수 
	
     //Many=Board, User=One -> 한명의 유저는 여러 개의 게시글을 쓸 수 있다. (OneToOne이라면 한 유저 당 한 게시글 쓸 수 있음) 
	@ManyToOne(fetch = FetchType.EAGER) // ManyToOne 기본패치 타입 = EAGER로 디폴트다. 생략 가능 
	@JoinColumn(name="userId") // @Entity로 각각의 변수이름이 그대로 테이블에 반영되지만 @JoinColumn(name="userId")으로 user오브젝트 이름은 userId로 반영됨
	private User user; // DB는 오브젝트를 저장할 수 없다->외래키 이용하거나 조인해야,, 자바는 JPA(ORM)를 사용하면 오브젝트를 저장할 수 있다
	                   // 이 점에서 DB와 자바는 충돌이 일어나는데 그래서 자바 프로그램에서 DB의 자료형에 맞춰서 테이블을 생성한다. 
	                   // ->(필드 값은 userId로 되고, 연관관계는 ManyToOne으로 만들어짐)
	                  // -> User 클래스를 참조하면서 자동으로 Board에서 외래키가 만들어짐
	@OneToMany(mappedBy ="board", fetch = FetchType.EAGER) //원래는 OneToMany가 기본 패치 타입이 lazy이나 펼치기가 아닌 개방형 댓글이기 때문에eager로 함.
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
