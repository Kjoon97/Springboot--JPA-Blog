package com.joon.blog.model;

//enum을 만들어서 내가 넣는 값을 강제할 수 있음
//USER 또는 ADMIN만 넣게 강제하는 것 
//일반적으로 데이터의 도메인(범위)을 만들때 씀 - 남,여 지정했는데 -> 남자, 여자로 실수로 지정 하는것 방지하기 위함
public enum RoleType {
	USER,ADMIN
}
