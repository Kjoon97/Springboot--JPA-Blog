package com.joon.blog;

import org.junit.jupiter.api.Test;

import com.joon.blog.model.Reply;

public class ReplyObjectTest {    //오브젝트를 출력하면 toString()으로 출력된다. 
	
	@Test
	public void 투스트링테스트() {
		Reply reply = Reply.builder()
				.id(1)
				.user(null)
				.board(null)
				.content("안녕")
				.build();
		
		System.out.println(reply);
	}
}
