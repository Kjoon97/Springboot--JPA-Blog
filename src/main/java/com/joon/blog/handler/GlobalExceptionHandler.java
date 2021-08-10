package com.joon.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice  //어디서든 예외가 발생할 때 이 함수를 실행 시키려면, @ControllerAdvice를 쓰면 됨
@RestController   // @RestController : 사용자가 요청하면-> (데이터) 응답 
public class GlobalExceptionHandler {

	//IllegalArgumentException용의 예외처리 
	@ExceptionHandler(value =IllegalArgumentException.class) // IllegalArgumentException이 발생하면 Exception에대한 에러를 스프링이 
	public String handleArgumentException(IllegalArgumentException e) {	   //이 함수에 전달한다. 
		return "<h1>"+e.getMessage()+"</h1>";
	}
	//모든 예외들의 처리 
	@ExceptionHandler(value =Exception.class) // IllegalArgumentException이 발생하면 Exception에대한 에러를 스프링이 
	public String handleArgumentException(Exception e) {	   //이 함수에 전달한다. 
		return "<h1>"+e.getMessage()+"</h1>";
	}
	
}
  