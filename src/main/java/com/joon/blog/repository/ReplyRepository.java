package com.joon.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.joon.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply,Integer>{
	
	
	//인터페이스에서는 public생략가능. 
	@Modifying
	@Query(value = "INSERT INTO reply(userId,boardId,content,createDate) VALUES(?1,?2,?3,now())", nativeQuery = true) //nativeQuery = true하면 내가 작성한 쿼리 작동함.
	int mSave(int userId, int boardId, String content); //ReplySaveRequestDto가 들고있는 userId, boardId, content가 ?1,?2,?3,now()에 순서대로 들어감
	     //업데이트된 행의 개수를 리턴해준다.       /                                        //-> 덕분에 ReplySaveRequestDtod에서 영속화 할 필요 없음 
}
